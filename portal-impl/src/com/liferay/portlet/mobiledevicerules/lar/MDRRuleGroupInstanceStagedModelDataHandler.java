/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.mobiledevicerules.lar;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupInstanceLocalServiceUtil;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupLocalServiceUtil;

import java.util.Map;

/**
 * @author Mate Thurzo
 */
public class MDRRuleGroupInstanceStagedModelDataHandler
	extends BaseStagedModelDataHandler<MDRRuleGroupInstance> {

	public static final String[] CLASS_NAMES =
		{MDRRuleGroupInstance.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws SystemException {

		MDRRuleGroupInstance ruleGroupInstance =
			MDRRuleGroupInstanceLocalServiceUtil.
				fetchMDRRuleGroupInstanceByUuidAndGroupId(uuid, groupId);

		if (ruleGroupInstance != null) {
			MDRRuleGroupInstanceLocalServiceUtil.deleteRuleGroupInstance(
				ruleGroupInstance);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			MDRRuleGroupInstance ruleGroupInstance)
		throws Exception {

		MDRRuleGroup ruleGroup = MDRRuleGroupLocalServiceUtil.getRuleGroup(
			ruleGroupInstance.getRuleGroupId());

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, ruleGroupInstance, ruleGroup,
			PortletDataContext.REFERENCE_TYPE_PARENT);

		Element ruleGroupInstanceElement =
			portletDataContext.getExportDataElement(ruleGroupInstance);

		String className = ruleGroupInstance.getClassName();

		if (className.equals(Layout.class.getName())) {
			Layout layout = LayoutLocalServiceUtil.getLayout(
				ruleGroupInstance.getClassPK());

			ruleGroupInstanceElement.addAttribute(
				"layout-uuid", layout.getUuid());
		}

		portletDataContext.addClassedModel(
			ruleGroupInstanceElement,
			ExportImportPathUtil.getModelPath(ruleGroupInstance),
			ruleGroupInstance);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			MDRRuleGroupInstance ruleGroupInstance)
		throws Exception {

		long userId = portletDataContext.getUserId(
			ruleGroupInstance.getUserUuid());

		StagedModelDataHandlerUtil.importReferenceStagedModel(
			portletDataContext, ruleGroupInstance, MDRRuleGroup.class,
			ruleGroupInstance.getRuleGroupId());

		Map<Long, Long> ruleGroupIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				MDRRuleGroup.class);

		Long ruleGroupId = MapUtil.getLong(
			ruleGroupIds, ruleGroupInstance.getRuleGroupId(),
			ruleGroupInstance.getRuleGroupId());

		long classPK = 0;

		Element ruleGroupInstanceElement =
			portletDataContext.getImportDataStagedModelElement(
				ruleGroupInstance);

		String layoutUuid = ruleGroupInstanceElement.attributeValue(
			"layout-uuid");

		try {
			if (Validator.isNotNull(layoutUuid)) {
				Layout layout =
					LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
						layoutUuid, portletDataContext.getScopeGroupId(),
						portletDataContext.isPrivateLayout());

				classPK = layout.getPrimaryKey();
			}
			else {
				LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
					portletDataContext.getScopeGroupId(),
					portletDataContext.isPrivateLayout());

				classPK = layoutSet.getLayoutSetId();
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				StringBundler sb = new StringBundler(5);

				sb.append("Layout ");
				sb.append(layoutUuid);
				sb.append(" is missing for rule group instance ");
				sb.append(ruleGroupInstance.getRuleGroupInstanceId());
				sb.append(", skipping this rule group instance.");

				_log.warn(sb.toString());
			}

			return;
		}

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			ruleGroupInstance);

		serviceContext.setUserId(userId);

		MDRRuleGroupInstance importedRuleGroupInstance = null;

		if (portletDataContext.isDataStrategyMirror()) {
			MDRRuleGroupInstance existingMDRRuleGroupInstance =
				MDRRuleGroupInstanceLocalServiceUtil.
					fetchMDRRuleGroupInstanceByUuidAndGroupId(
						ruleGroupInstance.getUuid(),
						portletDataContext.getScopeGroupId());

			if (existingMDRRuleGroupInstance == null) {
				serviceContext.setUuid(ruleGroupInstance.getUuid());

				importedRuleGroupInstance =
					MDRRuleGroupInstanceLocalServiceUtil.addRuleGroupInstance(
						portletDataContext.getScopeGroupId(),
						ruleGroupInstance.getClassName(), classPK, ruleGroupId,
						ruleGroupInstance.getPriority(), serviceContext);
			}
			else {
				importedRuleGroupInstance =
					MDRRuleGroupInstanceLocalServiceUtil.
						updateRuleGroupInstance(
							existingMDRRuleGroupInstance.
								getRuleGroupInstanceId(),
							ruleGroupInstance.getPriority());
			}
		}
		else {
			importedRuleGroupInstance =
				MDRRuleGroupInstanceLocalServiceUtil.addRuleGroupInstance(
					portletDataContext.getScopeGroupId(),
					ruleGroupInstance.getClassName(), classPK, ruleGroupId,
					ruleGroupInstance.getPriority(), serviceContext);
		}

		portletDataContext.importClassedModel(
			ruleGroupInstance, importedRuleGroupInstance);
	}

	private static Log _log = LogFactoryUtil.getLog(
		MDRRuleGroupInstanceStagedModelDataHandler.class);

}
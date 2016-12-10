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

package com.liferay.portlet.layoutprototypes.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutPrototypeLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * @author Daniela Zapata Riesco
 */
public class LayoutPrototypeStagedModelDataHandler
	extends BaseStagedModelDataHandler <LayoutPrototype> {

	public static final String[] CLASS_NAMES =
		{LayoutPrototype.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		LayoutPrototype layoutPrototype =
			LayoutPrototypeLocalServiceUtil.
				fetchLayoutPrototypeByUuidAndCompanyId(
					uuid, group.getCompanyId());

		if (layoutPrototype != null) {
			LayoutPrototypeLocalServiceUtil.deleteLayoutPrototype(
				layoutPrototype);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(LayoutPrototype layoutPrototype) {
		return layoutPrototype.getNameCurrentValue();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			LayoutPrototype layoutPrototype)
		throws Exception {

		Element layoutPrototypeElement =
			portletDataContext.getExportDataElement(layoutPrototype);

		exportLayouts(
			portletDataContext, layoutPrototype, layoutPrototypeElement);

		portletDataContext.addClassedModel(
			layoutPrototypeElement,
			ExportImportPathUtil.getModelPath(layoutPrototype),
			layoutPrototype);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			LayoutPrototype layoutPrototype)
		throws Exception {

		long userId = portletDataContext.getUserId(
			layoutPrototype.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			layoutPrototype);

		serviceContext.setAttribute("addDefaultLayout", false);

		LayoutPrototype importedLayoutPrototype = null;

		if (portletDataContext.isDataStrategyMirror()) {
			LayoutPrototype existingLayoutPrototype =
				LayoutPrototypeLocalServiceUtil.
					fetchLayoutPrototypeByUuidAndCompanyId(
						layoutPrototype.getUuid(),
						portletDataContext.getCompanyId());

			if (existingLayoutPrototype == null) {
				serviceContext.setUuid(layoutPrototype.getUuid());

				importedLayoutPrototype =
					LayoutPrototypeLocalServiceUtil.addLayoutPrototype(
						userId, portletDataContext.getCompanyId(),
						layoutPrototype.getNameMap(),
						layoutPrototype.getDescription(),
						layoutPrototype.isActive(), serviceContext);
			}
			else {
				importedLayoutPrototype =
					LayoutPrototypeLocalServiceUtil.updateLayoutPrototype(
						existingLayoutPrototype.getLayoutPrototypeId(),
						layoutPrototype.getNameMap(),
						layoutPrototype.getDescription(),
						layoutPrototype.isActive(), serviceContext);
			}
		}
		else {
			importedLayoutPrototype =
				LayoutPrototypeLocalServiceUtil.addLayoutPrototype(
					userId, portletDataContext.getCompanyId(),
					layoutPrototype.getNameMap(),
					layoutPrototype.getDescription(),
					layoutPrototype.isActive(), serviceContext);
		}

		importLayouts(
			portletDataContext, layoutPrototype,
			importedLayoutPrototype.getGroupId());

		portletDataContext.importClassedModel(
			layoutPrototype, importedLayoutPrototype);
	}

	protected void exportLayouts(
			PortletDataContext portletDataContext,
			LayoutPrototype layoutPrototype, Element layoutPrototypeElement)
		throws Exception {

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			layoutPrototype.getGroupId(), true,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		for (Layout layout : layouts) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, layoutPrototype, layout,
				PortletDataContext.REFERENCE_TYPE_EMBEDDED);
		}
	}

	protected void importLayouts(
			PortletDataContext portletDataContext,
			LayoutPrototype layoutPrototype, long importedGroupId)
		throws PortalException {

		long groupId = portletDataContext.getGroupId();
		boolean privateLayout = portletDataContext.isPrivateLayout();
		long scopeGroupId = portletDataContext.getScopeGroupId();

		try {
			portletDataContext.setGroupId(importedGroupId);
			portletDataContext.setPrivateLayout(true);
			portletDataContext.setScopeGroupId(importedGroupId);

			StagedModelDataHandlerUtil.importReferenceStagedModels(
				portletDataContext, layoutPrototype, Layout.class);
		}
		finally {
			portletDataContext.setGroupId(groupId);
			portletDataContext.setPrivateLayout(privateLayout);
			portletDataContext.setScopeGroupId(scopeGroupId);
		}
	}

	@Override
	protected boolean validateMissingReference(
			String uuid, long companyId, long groupId)
		throws Exception {

		LayoutPrototype layoutPrototype =
			LayoutPrototypeLocalServiceUtil.
				fetchLayoutPrototypeByUuidAndCompanyId(uuid, companyId);

		if (layoutPrototype == null) {
			return false;
		}

		return true;
	}

}
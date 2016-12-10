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

package com.liferay.portlet.polls.lar;

import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.polls.model.PollsChoice;
import com.liferay.portlet.polls.model.PollsQuestion;
import com.liferay.portlet.polls.service.PollsChoiceLocalServiceUtil;
import com.liferay.portlet.polls.service.PollsQuestionLocalServiceUtil;

import java.util.Map;

/**
 * @author Shinn Lok
 * @author Mate Thurzo
 */
public class PollsChoiceStagedModelDataHandler
	extends BaseStagedModelDataHandler<PollsChoice> {

	public static final String[] CLASS_NAMES = {PollsChoice.class.getName()};

	@Override
	public void deleteStagedModel(
		String uuid, long groupId, String className, String extraData) {

		throw new UnsupportedOperationException();
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(PollsChoice choice) {
		return choice.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, PollsChoice choice)
		throws Exception {

		PollsQuestion question = PollsQuestionLocalServiceUtil.getQuestion(
			choice.getQuestionId());

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, choice, question,
			PortletDataContext.REFERENCE_TYPE_STRONG);

		Element choiceElement = portletDataContext.getExportDataElement(choice);

		portletDataContext.addClassedModel(
			choiceElement, ExportImportPathUtil.getModelPath(choice), choice);
	}

	@Override
	protected void doImportCompanyStagedModel(
			PortletDataContext portletDataContext, String uuid, long choiceId)
		throws Exception {

		PollsChoice existingChoice =
			PollsChoiceLocalServiceUtil.fetchPollsChoiceByUuidAndGroupId(
				uuid, portletDataContext.getCompanyGroupId());

		Map<Long, Long> choiceIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				PollsChoice.class);

		choiceIds.put(choiceId, existingChoice.getChoiceId());
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, PollsChoice choice)
		throws Exception {

		long userId = portletDataContext.getUserId(choice.getUserUuid());

		StagedModelDataHandlerUtil.importReferenceStagedModel(
			portletDataContext, choice, PollsQuestion.class,
			choice.getQuestionId());

		Map<Long, Long> questionIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				PollsQuestion.class);

		long questionId = MapUtil.getLong(
			questionIds, choice.getQuestionId(), choice.getQuestionId());

		PollsChoice importedChoice = null;

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			choice);

		if (portletDataContext.isDataStrategyMirror()) {
			PollsChoice existingChoice =
				PollsChoiceLocalServiceUtil.fetchPollsChoiceByUuidAndGroupId(
					choice.getUuid(), portletDataContext.getScopeGroupId());

			if (existingChoice == null) {
				serviceContext.setUuid(choice.getUuid());

				importedChoice = PollsChoiceLocalServiceUtil.addChoice(
					userId, questionId, choice.getName(),
					choice.getDescription(), serviceContext);
			}
			else {
				importedChoice = PollsChoiceLocalServiceUtil.updateChoice(
					existingChoice.getChoiceId(), questionId, choice.getName(),
					choice.getDescription(), serviceContext);
			}
		}
		else {
			importedChoice = PollsChoiceLocalServiceUtil.addChoice(
				userId, questionId, choice.getName(), choice.getDescription(),
				serviceContext);
		}

		portletDataContext.importClassedModel(choice, importedChoice);
	}

	@Override
	protected boolean validateMissingReference(
			String uuid, long companyId, long groupId)
		throws Exception {

		PollsChoice choice =
			PollsChoiceLocalServiceUtil.fetchPollsChoiceByUuidAndGroupId(
				uuid, groupId);

		if (choice == null) {
			return false;
		}

		return true;
	}

}
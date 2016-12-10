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

package com.liferay.portlet.usersadmin.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.OrgLabor;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.model.PasswordPolicyRel;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.Website;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrgLaborLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.PasswordPolicyLocalServiceUtil;
import com.liferay.portal.service.PasswordPolicyRelLocalServiceUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.WebsiteLocalServiceUtil;
import com.liferay.portlet.usersadmin.util.UsersAdminUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author David Mendez Gonzalez
 */
public class OrganizationStagedModelDataHandler
	extends BaseStagedModelDataHandler<Organization> {

	public static final String[] CLASS_NAMES = {Organization.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		Organization organization =
			OrganizationLocalServiceUtil.fetchOrganizationByUuidAndCompanyId(
				uuid, group.getCompanyId());

		if (organization != null) {
			OrganizationLocalServiceUtil.deleteOrganization(organization);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(Organization organization) {
		return organization.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Organization organization)
		throws Exception {

		Queue<Organization> organizations = new LinkedList<Organization>();

		organizations.add(organization);

		while (!organizations.isEmpty()) {
			Organization exportedOrganization = organizations.remove();

			if (organization.getParentOrganizationId() !=
					OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) {

				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, organization,
					organization.getParentOrganization(),
					PortletDataContext.REFERENCE_TYPE_PARENT);
			}

			exportAddresses(portletDataContext, exportedOrganization);
			exportEmailAddresses(portletDataContext, exportedOrganization);
			exportOrgLabors(portletDataContext, exportedOrganization);
			exportPasswordPolicyRel(portletDataContext, exportedOrganization);
			exportPhones(portletDataContext, exportedOrganization);
			exportWebsites(portletDataContext, exportedOrganization);

			Element organizationElement =
				portletDataContext.getExportDataElement(exportedOrganization);

			portletDataContext.addClassedModel(
				organizationElement,
				ExportImportPathUtil.getModelPath(exportedOrganization),
				exportedOrganization);

			organizations.addAll(exportedOrganization.getSuborganizations());
		}
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Organization organization)
		throws Exception {

		long userId = portletDataContext.getUserId(organization.getUserUuid());

		if (organization.getParentOrganizationId() !=
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) {

			StagedModelDataHandlerUtil.importReferenceStagedModel(
				portletDataContext, organization, Organization.class,
				organization.getParentOrganizationId());
		}

		Map<Long, Long> organizationIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Organization.class);

		long parentOrganizationId = MapUtil.getLong(
			organizationIds, organization.getParentOrganizationId(),
			organization.getParentOrganizationId());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			organization);

		serviceContext.setUserId(userId);

		Organization existingOrganization =
			OrganizationLocalServiceUtil.fetchOrganizationByUuidAndCompanyId(
				organization.getUuid(), portletDataContext.getCompanyId());

		if (existingOrganization == null) {
			existingOrganization =
				OrganizationLocalServiceUtil.fetchOrganization(
					portletDataContext.getCompanyId(), organization.getName());
		}

		Organization importedOrganization = null;

		if (existingOrganization == null) {
			serviceContext.setUuid(organization.getUuid());

			importedOrganization =
				OrganizationLocalServiceUtil.addOrganization(
					userId, parentOrganizationId, organization.getName(),
					organization.getType(), organization.getRegionId(),
					organization.getCountryId(), organization.getStatusId(),
					organization.getComments(), false, serviceContext);
		}
		else {
			importedOrganization =
				OrganizationLocalServiceUtil.updateOrganization(
					portletDataContext.getCompanyId(),
					existingOrganization.getOrganizationId(),
					parentOrganizationId, organization.getName(),
					organization.getType(), organization.getRegionId(),
					organization.getCountryId(), organization.getStatusId(),
					organization.getComments(), false, serviceContext);
		}

		importAddresses(portletDataContext, organization, importedOrganization);
		importEmailAddresses(
			portletDataContext, organization, importedOrganization);
		importOrgLabors(portletDataContext, organization, importedOrganization);
		importPasswordPolicyRel(
			portletDataContext, organization, importedOrganization);
		importPhones(portletDataContext, organization, importedOrganization);
		importWebsites(portletDataContext, organization, importedOrganization);

		portletDataContext.importClassedModel(
			organization, importedOrganization);
	}

	protected void exportAddresses(
			PortletDataContext portletDataContext, Organization organization)
		throws PortalException, SystemException {

		List<Address> addresses = AddressLocalServiceUtil.getAddresses(
			organization.getCompanyId(), organization.getModelClassName(),
			organization.getOrganizationId());

		for (Address address : addresses) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, organization, address,
				PortletDataContext.REFERENCE_TYPE_EMBEDDED);
		}
	}

	protected void exportEmailAddresses(
			PortletDataContext portletDataContext, Organization organization)
		throws PortalException, SystemException {

		List<EmailAddress> emailAddresses =
			EmailAddressLocalServiceUtil.getEmailAddresses(
				organization.getCompanyId(), organization.getModelClassName(),
				organization.getOrganizationId());

		for (EmailAddress emailAddress : emailAddresses) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, organization, emailAddress,
				PortletDataContext.REFERENCE_TYPE_EMBEDDED);
		}
	}

	protected void exportOrgLabors(
			PortletDataContext portletDataContext, Organization organization)
		throws SystemException {

		List<OrgLabor> orgLabors = OrgLaborLocalServiceUtil.getOrgLabors(
			organization.getOrganizationId());

		String path = ExportImportPathUtil.getModelPath(
			organization, OrgLabor.class.getSimpleName());

		portletDataContext.addZipEntry(path, orgLabors);
	}

	protected void exportPasswordPolicyRel(
			PortletDataContext portletDataContext, Organization organization)
		throws PortalException, SystemException {

		PasswordPolicyRel passwordPolicyRel =
			PasswordPolicyRelLocalServiceUtil.fetchPasswordPolicyRel(
				Organization.class.getName(), organization.getOrganizationId());

		if (passwordPolicyRel == null) {
			return;
		}

		PasswordPolicy passwordPolicy =
			PasswordPolicyLocalServiceUtil.getPasswordPolicy(
				passwordPolicyRel.getPasswordPolicyId());

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, organization, passwordPolicy,
			PortletDataContext.REFERENCE_TYPE_STRONG);
	}

	protected void exportPhones(
			PortletDataContext portletDataContext, Organization organization)
		throws PortalException, SystemException {

		List<Phone> phones = PhoneLocalServiceUtil.getPhones(
			organization.getCompanyId(), organization.getModelClassName(),
			organization.getOrganizationId());

		for (Phone phone : phones) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, organization, phone,
				PortletDataContext.REFERENCE_TYPE_EMBEDDED);
		}
	}

	protected void exportWebsites(
			PortletDataContext portletDataContext, Organization organization)
		throws PortalException, SystemException {

		List<Website> websites = WebsiteLocalServiceUtil.getWebsites(
			organization.getCompanyId(), organization.getModelClassName(),
			organization.getOrganizationId());

		for (Website website : websites) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, organization, website,
				PortletDataContext.REFERENCE_TYPE_EMBEDDED);
		}
	}

	protected void importAddresses(
			PortletDataContext portletDataContext, Organization organization,
			Organization importedOrganization)
		throws PortalException, SystemException {

		List<Element> addressElements =
			portletDataContext.getReferenceDataElements(
				organization, Address.class);

		List<Address> addresses = new ArrayList<Address>(
			addressElements.size());

		for (Element addressElement : addressElements) {
			String addressPath = addressElement.attributeValue("path");

			Address address = (Address)portletDataContext.getZipEntryAsObject(
				addressPath);

			address.setClassPK(importedOrganization.getOrganizationId());

			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, address);

			Map<Long, Long> addressIds =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					Address.class);

			long addressId = addressIds.get(address.getPrimaryKey());

			address.setAddressId(addressId);

			addresses.add(address);
		}

		UsersAdminUtil.updateAddresses(
			Organization.class.getName(),
			importedOrganization.getOrganizationId(), addresses);
	}

	protected void importEmailAddresses(
			PortletDataContext portletDataContext, Organization organization,
			Organization importedOrganization)
		throws PortalException, SystemException {

		List<Element> emailAddressElements =
			portletDataContext.getReferenceDataElements(
				organization, EmailAddress.class);

		List<EmailAddress> emailAddresses = new ArrayList<EmailAddress>(
			emailAddressElements.size());

		for (Element emailAddressElement : emailAddressElements) {
			String emailAddressPath = emailAddressElement.attributeValue(
				"path");

			EmailAddress emailAddress =
				(EmailAddress)portletDataContext.getZipEntryAsObject(
					emailAddressPath);

			emailAddress.setClassPK(importedOrganization.getOrganizationId());

			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, emailAddress);

			Map<Long, Long> emailAddressIds =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					EmailAddress.class);

			long emailAddressId = emailAddressIds.get(
				emailAddress.getPrimaryKey());

			emailAddress.setEmailAddressId(emailAddressId);

			emailAddresses.add(emailAddress);
		}

		UsersAdminUtil.updateEmailAddresses(
			Organization.class.getName(),
			importedOrganization.getOrganizationId(), emailAddresses);
	}

	protected void importOrgLabors(
			PortletDataContext portletDataContext, Organization organization,
			Organization importedOrganization)
		throws PortalException, SystemException {

		String path = ExportImportPathUtil.getModelPath(
			organization, OrgLabor.class.getSimpleName());

		List<OrgLabor> orgLabors =
			(List<OrgLabor>)portletDataContext.getZipEntryAsObject(path);

		for (OrgLabor orgLabor : orgLabors) {
			orgLabor.setOrgLaborId(0);
		}

		UsersAdminUtil.updateOrgLabors(
			importedOrganization.getOrganizationId(), orgLabors);
	}

	protected void importPasswordPolicyRel(
			PortletDataContext portletDataContext, Organization organization,
			Organization importedOrganization)
		throws PortalException, SystemException {

		List<Element> passwordPolicyElements =
			portletDataContext.getReferenceDataElements(
				organization, PasswordPolicy.class);

		if (passwordPolicyElements.isEmpty()) {
			return;
		}

		Element passwordPolicyElement = passwordPolicyElements.get(0);

		String passwordPolicyPath = passwordPolicyElement.attributeValue(
			"path");

		PasswordPolicy passwordPolicy =
			(PasswordPolicy)portletDataContext.getZipEntryAsObject(
				passwordPolicyPath);

		StagedModelDataHandlerUtil.importStagedModel(
			portletDataContext, passwordPolicy);

		Map<Long, Long> passwordPolicyIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				PasswordPolicy.class);

		long passwordPolicyId = passwordPolicyIds.get(
			passwordPolicy.getPrimaryKey());

		OrganizationLocalServiceUtil.addPasswordPolicyOrganizations(
			passwordPolicyId,
			new long[] {importedOrganization.getOrganizationId()});
	}

	protected void importPhones(
			PortletDataContext portletDataContext, Organization organization,
			Organization importedOrganization)
		throws PortalException, SystemException {

		List<Element> phoneElements =
			portletDataContext.getReferenceDataElements(
				organization, Phone.class);

		List<Phone> phones = new ArrayList<Phone>(phoneElements.size());

		for (Element phoneElement : phoneElements) {
			String phonePath = phoneElement.attributeValue("path");

			Phone phone = (Phone)portletDataContext.getZipEntryAsObject
				(phonePath);

			phone.setClassPK(importedOrganization.getOrganizationId());

			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, phone);

			Map<Long, Long> phoneIds =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					Phone.class);

			long phoneId = phoneIds.get(phone.getPrimaryKey());

			phone.setPhoneId(phoneId);

			phones.add(phone);
		}

		UsersAdminUtil.updatePhones(
			Organization.class.getName(),
			importedOrganization.getOrganizationId(), phones);
	}

	protected void importWebsites(
			PortletDataContext portletDataContext, Organization organization,
			Organization importedOrganization)
		throws PortalException, SystemException {

		List<Element> websiteElements =
			portletDataContext.getReferenceDataElements(
				organization, Website.class);

		List<Website> websites = new ArrayList<Website>(websiteElements.size());

		for (Element websiteElement : websiteElements) {
			String websitePath = websiteElement.attributeValue("path");

			Website website = (Website)portletDataContext.getZipEntryAsObject(
				websitePath);

			website.setClassPK(importedOrganization.getOrganizationId());

			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, website);

			Map<Long, Long> websiteIds =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					Website.class);

			long websiteId = websiteIds.get(website.getPrimaryKey());

			website.setWebsiteId(websiteId);

			websites.add(website);
		}

		UsersAdminUtil.updateWebsites(
			Organization.class.getName(),
			importedOrganization.getOrganizationId(), websites);
	}

	@Override
	protected boolean validateMissingReference(
			String uuid, long companyId, long groupId)
		throws Exception {

		Organization organization =
			OrganizationLocalServiceUtil.fetchOrganizationByUuidAndCompanyId(
				uuid, companyId);

		if (organization == null) {
			return false;
		}

		return true;
	}

}
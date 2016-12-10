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

package com.liferay.portal.service.impl;

import com.liferay.portal.AddressCityException;
import com.liferay.portal.AddressStreetException;
import com.liferay.portal.AddressZipException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Account;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.AddressLocalServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class AddressLocalServiceImpl extends AddressLocalServiceBaseImpl {

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #addAddress(long, String,
	 *             long, String, String, String, String, String, long, long,
	 *             int, boolean, boolean, ServiceContext)}
	 */
	@Override
	public Address addAddress(
			long userId, String className, long classPK, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId, int typeId, boolean mailing,
			boolean primary)
		throws PortalException, SystemException {

		return addAddress(
			userId, className, classPK, street1, street2, street3, city, zip,
			regionId, countryId, typeId, mailing, primary,
			new ServiceContext());
	}

	@Override
	public Address addAddress(
			long userId, String className, long classPK, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId, int typeId, boolean mailing,
			boolean primary, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		long classNameId = PortalUtil.getClassNameId(className);
		Date now = new Date();

		validate(
			0, user.getCompanyId(), classNameId, classPK, street1, city, zip,
			regionId, countryId, typeId, mailing, primary);

		long addressId = counterLocalService.increment();

		Address address = addressPersistence.create(addressId);

		address.setUuid(serviceContext.getUuid());
		address.setCompanyId(user.getCompanyId());
		address.setUserId(user.getUserId());
		address.setUserName(user.getFullName());
		address.setCreateDate(serviceContext.getCreateDate(now));
		address.setModifiedDate(serviceContext.getModifiedDate(now));
		address.setClassNameId(classNameId);
		address.setClassPK(classPK);
		address.setStreet1(street1);
		address.setStreet2(street2);
		address.setStreet3(street3);
		address.setCity(city);
		address.setZip(zip);
		address.setRegionId(regionId);
		address.setCountryId(countryId);
		address.setTypeId(typeId);
		address.setMailing(mailing);
		address.setPrimary(primary);

		addressPersistence.update(address);

		return address;
	}

	@Override
	@SystemEvent(
		action = SystemEventConstants.ACTION_SKIP,
		type = SystemEventConstants.TYPE_DELETE)
	public Address deleteAddress(Address address) throws SystemException {
		addressPersistence.remove(address);

		return address;
	}

	@Override
	public Address deleteAddress(long addressId)
		throws PortalException, SystemException {

		Address address = addressPersistence.findByPrimaryKey(addressId);

		return addressLocalService.deleteAddress(address);
	}

	@Override
	public void deleteAddresses(long companyId, String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		List<Address> addresses = addressPersistence.findByC_C_C(
			companyId, classNameId, classPK);

		for (Address address : addresses) {
			addressLocalService.deleteAddress(address);
		}
	}

	@Override
	public List<Address> getAddresses() throws SystemException {
		return addressPersistence.findAll();
	}

	@Override
	public List<Address> getAddresses(
			long companyId, String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return addressPersistence.findByC_C_C(companyId, classNameId, classPK);
	}

	@Override
	public Address updateAddress(
			long addressId, String street1, String street2, String street3,
			String city, String zip, long regionId, long countryId, int typeId,
			boolean mailing, boolean primary)
		throws PortalException, SystemException {

		validate(
			addressId, 0, 0, 0, street1, city, zip, regionId, countryId, typeId,
			mailing, primary);

		Address address = addressPersistence.findByPrimaryKey(addressId);

		address.setModifiedDate(new Date());
		address.setStreet1(street1);
		address.setStreet2(street2);
		address.setStreet3(street3);
		address.setCity(city);
		address.setZip(zip);
		address.setRegionId(regionId);
		address.setCountryId(countryId);
		address.setTypeId(typeId);
		address.setMailing(mailing);
		address.setPrimary(primary);

		addressPersistence.update(address);

		return address;
	}

	protected void validate(
			long addressId, long companyId, long classNameId, long classPK,
			boolean mailing, boolean primary)
		throws SystemException {

		// Check to make sure there isn't another address with the same company
		// id, class name, and class pk that also has mailing set to true

		if (mailing) {
			List<Address> addresses = addressPersistence.findByC_C_C_M(
				companyId, classNameId, classPK, mailing);

			for (Address address : addresses) {
				if ((addressId <= 0) || (address.getAddressId() != addressId)) {
					address.setMailing(false);

					addressPersistence.update(address);
				}
			}
		}

		// Check to make sure there isn't another address with the same company
		// id, class name, and class pk that also has primary set to true

		if (primary) {
			List<Address> addresses = addressPersistence.findByC_C_C_P(
				companyId, classNameId, classPK, primary);

			for (Address address : addresses) {
				if ((addressId <= 0) || (address.getAddressId() != addressId)) {
					address.setPrimary(false);

					addressPersistence.update(address);
				}
			}
		}
	}

	protected void validate(
			long addressId, long companyId, long classNameId, long classPK,
			String street1, String city, String zip, long regionId,
			long countryId, int typeId, boolean mailing, boolean primary)
		throws PortalException, SystemException {

		if (Validator.isNull(street1)) {
			throw new AddressStreetException();
		}
		else if (Validator.isNull(city)) {
			throw new AddressCityException();
		}
		else if (Validator.isNull(zip)) {
			Country country = countryService.fetchCountry(countryId);

			if ((country != null) && country.isZipRequired()) {
				throw new AddressZipException();
			}
		}

		if (addressId > 0) {
			Address address = addressPersistence.findByPrimaryKey(addressId);

			companyId = address.getCompanyId();
			classNameId = address.getClassNameId();
			classPK = address.getClassPK();
		}

		if ((classNameId == PortalUtil.getClassNameId(Account.class)) ||
			(classNameId == PortalUtil.getClassNameId(Contact.class)) ||
			(classNameId == PortalUtil.getClassNameId(Organization.class))) {

			listTypeService.validate(
				typeId, classNameId, ListTypeConstants.ADDRESS);
		}

		validate(addressId, companyId, classNameId, classPK, mailing, primary);
	}

}
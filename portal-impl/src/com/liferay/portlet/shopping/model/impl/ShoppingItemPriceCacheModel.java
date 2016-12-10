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

package com.liferay.portlet.shopping.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.shopping.model.ShoppingItemPrice;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ShoppingItemPrice in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingItemPrice
 * @generated
 */
public class ShoppingItemPriceCacheModel implements CacheModel<ShoppingItemPrice>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{itemPriceId=");
		sb.append(itemPriceId);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", minQuantity=");
		sb.append(minQuantity);
		sb.append(", maxQuantity=");
		sb.append(maxQuantity);
		sb.append(", price=");
		sb.append(price);
		sb.append(", discount=");
		sb.append(discount);
		sb.append(", taxable=");
		sb.append(taxable);
		sb.append(", shipping=");
		sb.append(shipping);
		sb.append(", useShippingFormula=");
		sb.append(useShippingFormula);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ShoppingItemPrice toEntityModel() {
		ShoppingItemPriceImpl shoppingItemPriceImpl = new ShoppingItemPriceImpl();

		shoppingItemPriceImpl.setItemPriceId(itemPriceId);
		shoppingItemPriceImpl.setItemId(itemId);
		shoppingItemPriceImpl.setMinQuantity(minQuantity);
		shoppingItemPriceImpl.setMaxQuantity(maxQuantity);
		shoppingItemPriceImpl.setPrice(price);
		shoppingItemPriceImpl.setDiscount(discount);
		shoppingItemPriceImpl.setTaxable(taxable);
		shoppingItemPriceImpl.setShipping(shipping);
		shoppingItemPriceImpl.setUseShippingFormula(useShippingFormula);
		shoppingItemPriceImpl.setStatus(status);

		shoppingItemPriceImpl.resetOriginalValues();

		return shoppingItemPriceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemPriceId = objectInput.readLong();
		itemId = objectInput.readLong();
		minQuantity = objectInput.readInt();
		maxQuantity = objectInput.readInt();
		price = objectInput.readDouble();
		discount = objectInput.readDouble();
		taxable = objectInput.readBoolean();
		shipping = objectInput.readDouble();
		useShippingFormula = objectInput.readBoolean();
		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(itemPriceId);
		objectOutput.writeLong(itemId);
		objectOutput.writeInt(minQuantity);
		objectOutput.writeInt(maxQuantity);
		objectOutput.writeDouble(price);
		objectOutput.writeDouble(discount);
		objectOutput.writeBoolean(taxable);
		objectOutput.writeDouble(shipping);
		objectOutput.writeBoolean(useShippingFormula);
		objectOutput.writeInt(status);
	}

	public long itemPriceId;
	public long itemId;
	public int minQuantity;
	public int maxQuantity;
	public double price;
	public double discount;
	public boolean taxable;
	public double shipping;
	public boolean useShippingFormula;
	public int status;
}
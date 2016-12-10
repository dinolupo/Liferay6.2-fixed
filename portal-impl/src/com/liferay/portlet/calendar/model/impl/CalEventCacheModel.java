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

package com.liferay.portlet.calendar.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.calendar.model.CalEvent;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CalEvent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CalEvent
 * @generated
 */
public class CalEventCacheModel implements CacheModel<CalEvent>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(47);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", eventId=");
		sb.append(eventId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", location=");
		sb.append(location);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", durationHour=");
		sb.append(durationHour);
		sb.append(", durationMinute=");
		sb.append(durationMinute);
		sb.append(", allDay=");
		sb.append(allDay);
		sb.append(", timeZoneSensitive=");
		sb.append(timeZoneSensitive);
		sb.append(", type=");
		sb.append(type);
		sb.append(", repeating=");
		sb.append(repeating);
		sb.append(", recurrence=");
		sb.append(recurrence);
		sb.append(", remindBy=");
		sb.append(remindBy);
		sb.append(", firstReminder=");
		sb.append(firstReminder);
		sb.append(", secondReminder=");
		sb.append(secondReminder);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CalEvent toEntityModel() {
		CalEventImpl calEventImpl = new CalEventImpl();

		if (uuid == null) {
			calEventImpl.setUuid(StringPool.BLANK);
		}
		else {
			calEventImpl.setUuid(uuid);
		}

		calEventImpl.setEventId(eventId);
		calEventImpl.setGroupId(groupId);
		calEventImpl.setCompanyId(companyId);
		calEventImpl.setUserId(userId);

		if (userName == null) {
			calEventImpl.setUserName(StringPool.BLANK);
		}
		else {
			calEventImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			calEventImpl.setCreateDate(null);
		}
		else {
			calEventImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			calEventImpl.setModifiedDate(null);
		}
		else {
			calEventImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			calEventImpl.setTitle(StringPool.BLANK);
		}
		else {
			calEventImpl.setTitle(title);
		}

		if (description == null) {
			calEventImpl.setDescription(StringPool.BLANK);
		}
		else {
			calEventImpl.setDescription(description);
		}

		if (location == null) {
			calEventImpl.setLocation(StringPool.BLANK);
		}
		else {
			calEventImpl.setLocation(location);
		}

		if (startDate == Long.MIN_VALUE) {
			calEventImpl.setStartDate(null);
		}
		else {
			calEventImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			calEventImpl.setEndDate(null);
		}
		else {
			calEventImpl.setEndDate(new Date(endDate));
		}

		calEventImpl.setDurationHour(durationHour);
		calEventImpl.setDurationMinute(durationMinute);
		calEventImpl.setAllDay(allDay);
		calEventImpl.setTimeZoneSensitive(timeZoneSensitive);

		if (type == null) {
			calEventImpl.setType(StringPool.BLANK);
		}
		else {
			calEventImpl.setType(type);
		}

		calEventImpl.setRepeating(repeating);

		if (recurrence == null) {
			calEventImpl.setRecurrence(StringPool.BLANK);
		}
		else {
			calEventImpl.setRecurrence(recurrence);
		}

		calEventImpl.setRemindBy(remindBy);
		calEventImpl.setFirstReminder(firstReminder);
		calEventImpl.setSecondReminder(secondReminder);

		calEventImpl.resetOriginalValues();

		return calEventImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		eventId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		location = objectInput.readUTF();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		durationHour = objectInput.readInt();
		durationMinute = objectInput.readInt();
		allDay = objectInput.readBoolean();
		timeZoneSensitive = objectInput.readBoolean();
		type = objectInput.readUTF();
		repeating = objectInput.readBoolean();
		recurrence = objectInput.readUTF();
		remindBy = objectInput.readInt();
		firstReminder = objectInput.readInt();
		secondReminder = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(eventId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (location == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(location);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);
		objectOutput.writeInt(durationHour);
		objectOutput.writeInt(durationMinute);
		objectOutput.writeBoolean(allDay);
		objectOutput.writeBoolean(timeZoneSensitive);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeBoolean(repeating);

		if (recurrence == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(recurrence);
		}

		objectOutput.writeInt(remindBy);
		objectOutput.writeInt(firstReminder);
		objectOutput.writeInt(secondReminder);
	}

	public String uuid;
	public long eventId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String description;
	public String location;
	public long startDate;
	public long endDate;
	public int durationHour;
	public int durationMinute;
	public boolean allDay;
	public boolean timeZoneSensitive;
	public String type;
	public boolean repeating;
	public String recurrence;
	public int remindBy;
	public int firstReminder;
	public int secondReminder;
}
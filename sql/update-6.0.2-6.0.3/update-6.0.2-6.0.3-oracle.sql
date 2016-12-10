alter table AssetEntry add classUuid VARCHAR2(75 CHAR) null;

alter table DLFileEntry add extension VARCHAR2(75 CHAR) null;

alter table DLFileVersion add extension VARCHAR2(75 CHAR) null;
alter table DLFileVersion add title VARCHAR2(255 CHAR) null;
alter table DLFileVersion add changeLog varchar2(4000) null;
alter table DLFileVersion add extraSettings clob null;

commit;

update DLFileVersion set changeLog = description;

alter table Layout add uuid_ VARCHAR2(75 CHAR) null;

alter table MBMessage add rootMessageId number(30,0);

commit;

update MBMessage set rootMessageId = (select rootMessageId from MBThread where MBThread.threadId = MBMessage.threadId);

drop table SocialEquityAssetEntry;

create table SocialEquityAssetEntry (
	equityAssetEntryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	assetEntryId number(30,0),
	informationK number(30,20),
	informationB number(30,20)
);

drop table SocialEquityHistory;

create table SocialEquityHistory (
	equityHistoryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	createDate timestamp null,
	personalEquity number(30,0)
);

drop table SocialEquityLog;

create table SocialEquityLog (
	equityLogId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	assetEntryId number(30,0),
	actionId VARCHAR2(75 CHAR) null,
	actionDate number(30,0),
	active_ number(1, 0),
	expiration number(30,0),
	type_ number(30,0),
	value number(30,0)
);

drop table SocialEquitySetting;

create table SocialEquitySetting (
	equitySettingId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	classNameId number(30,0),
	actionId VARCHAR2(75 CHAR) null,
	dailyLimit number(30,0),
	lifespan number(30,0),
	type_ number(30,0),
	uniqueEntry number(1, 0),
	value number(30,0)
);

drop table SocialEquityUser;

create table SocialEquityUser (
	equityUserId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	contributionK number(30,20),
	contributionB number(30,20),
	participationK number(30,20),
	participationB number(30,20),
	rank number(30,0)
);

alter table User_ add facebookId number(30,0);

alter table WikiPageResource add uuid_ VARCHAR2(75 CHAR) null;

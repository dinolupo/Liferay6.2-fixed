alter table AssetEntry add classUuid varchar(75);

alter table DLFileEntry add extension varchar(75);

alter table DLFileVersion add extension varchar(75);
alter table DLFileVersion add title varchar(255);
alter table DLFileVersion add changeLog lvarchar;
alter table DLFileVersion add extraSettings text;



update DLFileVersion set changeLog = description;

alter table Layout add uuid_ varchar(75);

alter table MBMessage add rootMessageId int8;



update MBMessage set rootMessageId = (select rootMessageId from MBThread where MBThread.threadId = MBMessage.threadId);

drop table SocialEquityAssetEntry;

create table SocialEquityAssetEntry (
	equityAssetEntryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	assetEntryId int8,
	informationK float,
	informationB float
)
extent size 16 next size 16
lock mode row;

drop table SocialEquityHistory;

create table SocialEquityHistory (
	equityHistoryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	createDate datetime YEAR TO FRACTION,
	personalEquity int
)
extent size 16 next size 16
lock mode row;

drop table SocialEquityLog;

create table SocialEquityLog (
	equityLogId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	assetEntryId int8,
	actionId varchar(75),
	actionDate int,
	active_ boolean,
	expiration int,
	type_ int,
	value int
)
extent size 16 next size 16
lock mode row;

drop table SocialEquitySetting;

create table SocialEquitySetting (
	equitySettingId int8 not null primary key,
	groupId int8,
	companyId int8,
	classNameId int8,
	actionId varchar(75),
	dailyLimit int,
	lifespan int,
	type_ int,
	uniqueEntry boolean,
	value int
)
extent size 16 next size 16
lock mode row;

drop table SocialEquityUser;

create table SocialEquityUser (
	equityUserId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	contributionK float,
	contributionB float,
	participationK float,
	participationB float,
	rank int
)
extent size 16 next size 16
lock mode row;

alter table User_ add facebookId int8;

alter table WikiPageResource add uuid_ varchar(75);

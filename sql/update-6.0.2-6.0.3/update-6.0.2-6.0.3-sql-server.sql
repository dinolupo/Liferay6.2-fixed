alter table AssetEntry add classUuid nvarchar(75) null;

alter table DLFileEntry add extension nvarchar(75) null;

alter table DLFileVersion add extension nvarchar(75) null;
alter table DLFileVersion add title nvarchar(255) null;
alter table DLFileVersion add changeLog nvarchar(2000) null;
alter table DLFileVersion add extraSettings nvarchar(max) null;

go

update DLFileVersion set changeLog = description;

alter table Layout add uuid_ nvarchar(75) null;

alter table MBMessage add rootMessageId bigint;

go

update MBMessage set rootMessageId = (select rootMessageId from MBThread where MBThread.threadId = MBMessage.threadId);

drop table SocialEquityAssetEntry;

create table SocialEquityAssetEntry (
	equityAssetEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	assetEntryId bigint,
	informationK float,
	informationB float
);

drop table SocialEquityHistory;

create table SocialEquityHistory (
	equityHistoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate datetime null,
	personalEquity int
);

drop table SocialEquityLog;

create table SocialEquityLog (
	equityLogId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	assetEntryId bigint,
	actionId nvarchar(75) null,
	actionDate int,
	active_ bit,
	expiration int,
	type_ int,
	value int
);

drop table SocialEquitySetting;

create table SocialEquitySetting (
	equitySettingId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	actionId nvarchar(75) null,
	dailyLimit int,
	lifespan int,
	type_ int,
	uniqueEntry bit,
	value int
);

drop table SocialEquityUser;

create table SocialEquityUser (
	equityUserId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	contributionK float,
	contributionB float,
	participationK float,
	participationB float,
	rank int
);

alter table User_ add facebookId bigint;

alter table WikiPageResource add uuid_ nvarchar(75) null;

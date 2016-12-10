alter table AssetEntry add classUuid varchar(75);

alter table DLFileEntry add extension varchar(75);

alter table DLFileVersion add extension varchar(75);
alter table DLFileVersion add title varchar(255);
alter table DLFileVersion add changeLog varchar(750);
alter table DLFileVersion add extraSettings clob;

commit;

update DLFileVersion set changeLog = description;

alter table Layout add uuid_ varchar(75);

alter table MBMessage add rootMessageId bigint;

commit;

update MBMessage set rootMessageId = (select rootMessageId from MBThread where MBThread.threadId = MBMessage.threadId);

drop table SocialEquityAssetEntry;

create table SocialEquityAssetEntry (
	equityAssetEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	assetEntryId bigint,
	informationK double,
	informationB double
);

drop table SocialEquityHistory;

create table SocialEquityHistory (
	equityHistoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate timestamp,
	personalEquity integer
);

drop table SocialEquityLog;

create table SocialEquityLog (
	equityLogId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	assetEntryId bigint,
	actionId varchar(75),
	actionDate integer,
	active_ smallint,
	expiration integer,
	type_ integer,
	value integer
);

drop table SocialEquitySetting;

create table SocialEquitySetting (
	equitySettingId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	actionId varchar(75),
	dailyLimit integer,
	lifespan integer,
	type_ integer,
	uniqueEntry smallint,
	value integer
);

drop table SocialEquityUser;

create table SocialEquityUser (
	equityUserId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	contributionK double,
	contributionB double,
	participationK double,
	participationB double,
	rank integer
);

alter table User_ add facebookId bigint;

alter table WikiPageResource add uuid_ varchar(75);

alter table AssetEntry add classUuid varchar(75) null;

alter table DLFileEntry add extension varchar(75) null;

alter table DLFileVersion add extension varchar(75) null;
alter table DLFileVersion add title varchar(255) null;
alter table DLFileVersion add changeLog text null;
alter table DLFileVersion add extraSettings text null;

commit;

update DLFileVersion set changeLog = description;

alter table Layout add uuid_ varchar(75) null;

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
	informationK double precision,
	informationB double precision
);

drop table SocialEquityHistory;

create table SocialEquityHistory (
	equityHistoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate timestamp null,
	personalEquity integer
);

drop table SocialEquityLog;

create table SocialEquityLog (
	equityLogId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	assetEntryId bigint,
	actionId varchar(75) null,
	actionDate integer,
	active_ bool,
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
	actionId varchar(75) null,
	dailyLimit integer,
	lifespan integer,
	type_ integer,
	uniqueEntry bool,
	value integer
);

drop table SocialEquityUser;

create table SocialEquityUser (
	equityUserId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	contributionK double precision,
	contributionB double precision,
	participationK double precision,
	participationB double precision,
	rank integer
);

alter table User_ add facebookId bigint;

alter table WikiPageResource add uuid_ varchar(75) null;

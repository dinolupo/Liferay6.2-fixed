alter table AssetEntry add classUuid varchar(75);

alter table DLFileEntry add extension varchar(75);

alter table DLFileVersion add extension varchar(75);
alter table DLFileVersion add title varchar(255);
alter table DLFileVersion add changeLog varchar(4000);
alter table DLFileVersion add extraSettings blob;

commit;


alter table Layout add uuid_ varchar(75);

alter table MBMessage add rootMessageId int64;

commit;


drop table SocialEquityAssetEntry;

create table SocialEquityAssetEntry (
	equityAssetEntryId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	assetEntryId int64,
	informationK double precision,
	informationB double precision
);

drop table SocialEquityHistory;

create table SocialEquityHistory (
	equityHistoryId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	createDate timestamp,
	personalEquity integer
);

drop table SocialEquityLog;

create table SocialEquityLog (
	equityLogId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	assetEntryId int64,
	actionId varchar(75),
	actionDate integer,
	active_ smallint,
	expiration integer,
	type_ integer,
	value integer
);

drop table SocialEquitySetting;

create table SocialEquitySetting (
	equitySettingId int64 not null primary key,
	groupId int64,
	companyId int64,
	classNameId int64,
	actionId varchar(75),
	dailyLimit integer,
	lifespan integer,
	type_ integer,
	uniqueEntry smallint,
	value integer
);

drop table SocialEquityUser;

create table SocialEquityUser (
	equityUserId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	contributionK double precision,
	contributionB double precision,
	participationK double precision,
	participationB double precision,
	rank integer
);

alter table User_ add facebookId int64;

alter table WikiPageResource add uuid_ varchar(75);

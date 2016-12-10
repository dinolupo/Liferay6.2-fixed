alter table AssetEntry add classUuid varchar(75) null;

alter table DLFileEntry add extension varchar(75) null;

alter table DLFileVersion add extension varchar(75) null;
alter table DLFileVersion add title varchar(255) null;
alter table DLFileVersion add changeLog varchar(1000) null;
alter table DLFileVersion add extraSettings text null;

go

update DLFileVersion set changeLog = description;

alter table Layout add uuid_ varchar(75) null;

alter table MBMessage add rootMessageId decimal(20,0)
go

go

update MBMessage set rootMessageId = (select rootMessageId from MBThread where MBThread.threadId = MBMessage.threadId)
go

drop table SocialEquityAssetEntry;

create table SocialEquityAssetEntry (
	equityAssetEntryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	assetEntryId decimal(20,0),
	informationK float,
	informationB float
)
go

drop table SocialEquityHistory;

create table SocialEquityHistory (
	equityHistoryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	createDate datetime null,
	personalEquity int
)
go

drop table SocialEquityLog;

create table SocialEquityLog (
	equityLogId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	assetEntryId decimal(20,0),
	actionId varchar(75) null,
	actionDate int,
	active_ int,
	expiration int,
	type_ int,
	value int
)
go

drop table SocialEquitySetting;

create table SocialEquitySetting (
	equitySettingId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	classNameId decimal(20,0),
	actionId varchar(75) null,
	dailyLimit int,
	lifespan int,
	type_ int,
	uniqueEntry int,
	value int
)
go

drop table SocialEquityUser;

create table SocialEquityUser (
	equityUserId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	contributionK float,
	contributionB float,
	participationK float,
	participationB float,
	rank int
)
go

alter table User_ add facebookId decimal(20,0)
go

alter table WikiPageResource add uuid_ varchar(75) null;

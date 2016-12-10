create table AssetCategory (
	uuid_ varchar(75),
	categoryId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	parentCategoryId int64,
	leftCategoryId int64,
	rightCategoryId int64,
	name varchar(75),
	title varchar(4000),
	vocabularyId int64
);

create table AssetCategoryProperty (
	categoryPropertyId int64 not null primary key,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	categoryId int64,
	key_ varchar(75),
	value varchar(75)
);

create table AssetEntries_AssetCategories (
	entryId int64 not null,
	categoryId int64 not null,
	primary key (entryId, categoryId)
);

create table AssetEntries_AssetTags (
	entryId int64 not null,
	tagId int64 not null,
	primary key (entryId, tagId)
);

create table AssetEntry (
	entryId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId int64,
	classPK int64,
	visible smallint,
	startDate timestamp,
	endDate timestamp,
	publishDate timestamp,
	expirationDate timestamp,
	mimeType varchar(75),
	title varchar(255),
	description varchar(4000),
	summary varchar(4000),
	url varchar(4000),
	height integer,
	width integer,
	priority double precision,
	viewCount integer
);

create table AssetTag (
	tagId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	assetCount integer
);

create table AssetTagProperty (
	tagPropertyId int64 not null primary key,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	tagId int64,
	key_ varchar(75),
	value varchar(255)
);

create table AssetTagStats (
	tagStatsId int64 not null primary key,
	tagId int64,
	classNameId int64,
	assetCount integer
);

create table AssetVocabulary (
	uuid_ varchar(75),
	vocabularyId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	title varchar(4000),
	description varchar(4000),
	settings_ varchar(4000)
);

alter table BlogsEntry add allowPingbacks smallint;
alter table BlogsEntry add status integer;
alter table BlogsEntry add statusByUserId int64;
alter table BlogsEntry add statusByUserName varchar(75);
alter table BlogsEntry add statusDate timestamp;

commit;


alter table DLFileEntry add pendingVersion varchar(75);

alter table DLFileShortcut add status integer;
alter table DLFileShortcut add statusByUserId int64;
alter table DLFileShortcut add statusByUserName varchar(75);
alter table DLFileShortcut add statusDate timestamp;

commit;


drop index IX_6C5E6512;

alter table DLFileVersion add status integer;
alter table DLFileVersion add statusByUserId int64;
alter table DLFileVersion add statusByUserName varchar(75);
alter table DLFileVersion add statusDate timestamp;

commit;


alter table JournalArticle add status integer;
alter table JournalArticle add statusByUserId int64;
alter table JournalArticle add statusByUserName varchar(75);
alter table JournalArticle add statusDate timestamp;

commit;


alter table Layout add layoutPrototypeId int64;

create table LayoutPrototype (
	layoutPrototypeId int64 not null primary key,
	companyId int64,
	name varchar(4000),
	description varchar(4000),
	settings_ varchar(4000),
	active_ smallint
);

alter table LayoutSet add layoutSetPrototypeId int64;

create table LayoutSetPrototype (
	layoutSetPrototypeId int64 not null primary key,
	companyId int64,
	name varchar(4000),
	description varchar(4000),
	settings_ varchar(4000),
	active_ smallint
);

alter table MBMessage add allowPingbacks smallint;
alter table MBMessage add status integer;
alter table MBMessage add statusByUserId int64;
alter table MBMessage add statusByUserName varchar(75);
alter table MBMessage add statusDate timestamp;

commit;


alter table MBThread add status integer;
alter table MBThread add statusByUserId int64;
alter table MBThread add statusByUserName varchar(75);
alter table MBThread add statusDate timestamp;

commit;


alter table ShoppingItem add groupId int64;

alter table WikiPage add status integer;
alter table WikiPage add statusByUserId int64;
alter table WikiPage add statusByUserName varchar(75);
alter table WikiPage add statusDate timestamp;

commit;


create table WorkflowDefinitionLink (
	workflowDefinitionLinkId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId int64,
	workflowDefinitionName varchar(75),
	workflowDefinitionVersion integer
);

create table WorkflowInstanceLink (
	workflowInstanceLinkId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId int64,
	classPK int64,
	workflowInstanceId int64
);

commit;

create table AssetLink (
	linkId int64 not null primary key,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	entryId1 int64,
	entryId2 int64,
	type_ integer,
	weight integer
);

create table Team (
	teamId int64 not null primary key,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	groupId int64,
	name varchar(75),
	description varchar(4000)
);

create table Users_Teams (
	userId int64 not null,
	teamId int64 not null,
	primary key (userId, teamId)
);

commit;

alter table AssetEntry add socialInformationEquity double precision;

alter table Company add maxUsers integer;

commit;



alter table LayoutSet add settings_ blob;



alter table PasswordPolicy add minAlphanumeric integer;
alter table PasswordPolicy add minLowerCase integer;
alter table PasswordPolicy add minNumbers integer;
alter table PasswordPolicy add minSymbols integer;
alter table PasswordPolicy add minUpperCase integer;
alter table PasswordPolicy add resetTicketMaxAge int64;

commit;


create table SocialEquityAssetEntry (
	equityAssetEntryId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	assetEntryId int64,
	informationK double precision,
	informationB double precision
);

create table SocialEquityHistory (
	equityHistoryId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	createDate timestamp,
	personalEquity integer
);

create table SocialEquityLog (
	equityLogId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	assetEntryId int64,
	actionId varchar(75),
	actionDate integer,
	type_ integer,
	value integer,
	validity integer,
	active_ smallint
);

create table SocialEquitySetting (
	equitySettingId int64 not null primary key,
	groupId int64,
	companyId int64,
	classNameId int64,
	actionId varchar(75),
	type_ integer,
	value integer,
	validity integer
);

create table SocialEquityUser (
	equityUserId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	contributionK double precision,
	contributionB double precision,
	participationK double precision,
	participationB double precision
);

create table Ticket (
	ticketId int64 not null primary key,
	companyId int64,
	createDate timestamp,
	classNameId int64,
	classPK int64,
	key_ varchar(75),
	expirationDate timestamp
);

alter table User_ add socialContributionEquity double precision;
alter table User_ add socialParticipationEquity double precision;
alter table User_ add socialPersonalEquity double precision;

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

create table ClusterGroup (
	clusterGroupId int64 not null primary key,
	clusterNodeIds varchar(75),
	wholeCluster smallint
);

alter table User_ add digest varchar(256);

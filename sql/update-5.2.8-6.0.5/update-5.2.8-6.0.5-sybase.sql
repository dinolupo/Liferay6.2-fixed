create table AssetCategory (
	uuid_ varchar(75) null,
	categoryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentCategoryId decimal(20,0),
	leftCategoryId decimal(20,0),
	rightCategoryId decimal(20,0),
	name varchar(75) null,
	title varchar(1000) null,
	vocabularyId decimal(20,0)
)
go

create table AssetCategoryProperty (
	categoryPropertyId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId decimal(20,0),
	key_ varchar(75) null,
	value varchar(75) null
)
go

create table AssetEntries_AssetCategories (
	entryId decimal(20,0) not null,
	categoryId decimal(20,0) not null,
	primary key (entryId, categoryId)
)
go

create table AssetEntries_AssetTags (
	entryId decimal(20,0) not null,
	tagId decimal(20,0) not null,
	primary key (entryId, tagId)
)
go

create table AssetEntry (
	entryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	visible int,
	startDate datetime null,
	endDate datetime null,
	publishDate datetime null,
	expirationDate datetime null,
	mimeType varchar(75) null,
	title varchar(255) null,
	description varchar(1000) null,
	summary varchar(1000) null,
	url varchar(1000) null,
	height int,
	width int,
	priority float,
	viewCount int
)
go

create table AssetTag (
	tagId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	assetCount int
)
go

create table AssetTagProperty (
	tagPropertyId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	tagId decimal(20,0),
	key_ varchar(75) null,
	value varchar(255) null
)
go

create table AssetTagStats (
	tagStatsId decimal(20,0) not null primary key,
	tagId decimal(20,0),
	classNameId decimal(20,0),
	assetCount int
)
go

create table AssetVocabulary (
	uuid_ varchar(75) null,
	vocabularyId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	title varchar(1000) null,
	description varchar(1000) null,
	settings_ varchar(1000) null
)
go

alter table BlogsEntry add allowPingbacks int;
alter table BlogsEntry add status int;
alter table BlogsEntry add statusByUserId decimal(20,0)
go
alter table BlogsEntry add statusByUserName varchar(75)
go
alter table BlogsEntry add statusDate datetime;

go

update BlogsEntry set allowPingbacks = 1;
update BlogsEntry set status = 0 where draft = 0;
update BlogsEntry set status = 2 where draft = 1;
update BlogsEntry set statusByUserId = userId where draft = 0;
update BlogsEntry set statusByUserName = userName where draft = 0;
update BlogsEntry set statusDate = createDate where draft = 0;

alter table DLFileEntry add pendingVersion varchar(75) null;

alter table DLFileShortcut add status int;
alter table DLFileShortcut add statusByUserId decimal(20,0)
go
alter table DLFileShortcut add statusByUserName varchar(75)
go
alter table DLFileShortcut add statusDate datetime;

go

update DLFileShortcut set status = 0;
update DLFileShortcut set statusByUserId = userId;
update DLFileShortcut set statusByUserName = userId;
update DLFileShortcut set statusDate = createDate;

drop index DLFileVersion.IX_6C5E6512;

alter table DLFileVersion add status int;
alter table DLFileVersion add statusByUserId decimal(20,0)
go
alter table DLFileVersion add statusByUserName varchar(75)
go
alter table DLFileVersion add statusDate datetime;

go

update DLFileVersion set status = 0;
update DLFileVersion set statusByUserId = userId;
update DLFileVersion set statusByUserName = userId;
update DLFileVersion set statusDate = createDate;

alter table JournalArticle add status int;
alter table JournalArticle add statusByUserId decimal(20,0)
go
alter table JournalArticle add statusByUserName varchar(75)
go
alter table JournalArticle add statusDate datetime;

go

update JournalArticle set status = 0 where approved = 1;
update JournalArticle set status = 2 where approved = 0;
update JournalArticle set statusByUserId = approvedByUserId;
update JournalArticle set statusByUserName = approvedByUserName;
update JournalArticle set statusDate = approvedDate where expired = 0;
update JournalArticle set statusDate = expirationDate where expired = 1;

alter table Layout add layoutPrototypeId decimal(20,0)
go

create table LayoutPrototype (
	layoutPrototypeId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	name varchar(1000) null,
	description varchar(1000) null,
	settings_ varchar(1000) null,
	active_ int
)
go

alter table LayoutSet add layoutSetPrototypeId decimal(20,0)
go

create table LayoutSetPrototype (
	layoutSetPrototypeId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	name varchar(1000) null,
	description varchar(1000) null,
	settings_ varchar(1000) null,
	active_ int
)
go

alter table MBMessage add allowPingbacks int;
alter table MBMessage add status int;
alter table MBMessage add statusByUserId decimal(20,0)
go
alter table MBMessage add statusByUserName varchar(75)
go
alter table MBMessage add statusDate datetime;

go

update MBMessage set allowPingbacks = 1;
update MBMessage set status = 0;
update MBMessage set statusByUserId = userId;
update MBMessage set statusByUserName = userId;
update MBMessage set statusDate = createDate;

alter table MBThread add status int;
alter table MBThread add statusByUserId decimal(20,0)
go
alter table MBThread add statusByUserName varchar(75)
go
alter table MBThread add statusDate datetime;

go

update MBThread set status = 0;

alter table ShoppingItem add groupId decimal(20,0)
go

alter table WikiPage add status int;
alter table WikiPage add statusByUserId decimal(20,0)
go
alter table WikiPage add statusByUserName varchar(75)
go
alter table WikiPage add statusDate datetime;

go

update WikiPage set status = 0;
update WikiPage set statusByUserId = userId;
update WikiPage set statusByUserName = userId;
update WikiPage set statusDate = createDate;

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	workflowDefinitionName varchar(75) null,
	workflowDefinitionVersion int
)
go

create table WorkflowInstanceLink (
	workflowInstanceLinkId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	workflowInstanceId decimal(20,0)
)
go

go

create table AssetLink (
	linkId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	entryId1 decimal(20,0),
	entryId2 decimal(20,0),
	type_ int,
	weight int
)
go

create table Team (
	teamId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	groupId decimal(20,0),
	name varchar(75) null,
	description varchar(1000) null
)
go

create table Users_Teams (
	userId decimal(20,0) not null,
	teamId decimal(20,0) not null,
	primary key (userId, teamId)
)
go

go

alter table AssetEntry add socialInformationEquity float;

alter table Company add maxUsers int;

go

update Company set maxUsers = 0;

update ExpandoTable set name = 'CUSTOM_FIELDS' where name = 'DEFAULT_TABLE';

alter table LayoutSet add settings_ text null;

update MBMessage set categoryId = -1 where groupId = 0;

update MBThread set categoryId = -1 where groupId = 0;

alter table PasswordPolicy add minAlphanumeric int;
alter table PasswordPolicy add minLowerCase int;
alter table PasswordPolicy add minNumbers int;
alter table PasswordPolicy add minSymbols int;
alter table PasswordPolicy add minUpperCase int;
alter table PasswordPolicy add resetTicketMaxAge decimal(20,0)
go

go

update PasswordPolicy set minAlphanumeric = 0;
update PasswordPolicy set minLowerCase = 0;
update PasswordPolicy set minNumbers = 0;
update PasswordPolicy set minSymbols = 0;
update PasswordPolicy set minUpperCase = 0;
update PasswordPolicy set resetTicketMaxAge = 86400;

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

create table SocialEquityHistory (
	equityHistoryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	createDate datetime null,
	personalEquity int
)
go

create table SocialEquityLog (
	equityLogId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	assetEntryId decimal(20,0),
	actionId varchar(75) null,
	actionDate int,
	type_ int,
	value int,
	validity int,
	active_ int
)
go

create table SocialEquitySetting (
	equitySettingId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	classNameId decimal(20,0),
	actionId varchar(75) null,
	type_ int,
	value int,
	validity int
)
go

create table SocialEquityUser (
	equityUserId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	contributionK float,
	contributionB float,
	participationK float,
	participationB float
)
go

create table Ticket (
	ticketId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	createDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	key_ varchar(75) null,
	expirationDate datetime null
)
go

alter table User_ add socialContributionEquity float;
alter table User_ add socialParticipationEquity float;
alter table User_ add socialPersonalEquity float;

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

create table ClusterGroup (
	clusterGroupId decimal(20,0) not null primary key,
	clusterNodeIds varchar(75) null,
	wholeCluster int
)
go

alter table User_ add digest varchar(256) null;

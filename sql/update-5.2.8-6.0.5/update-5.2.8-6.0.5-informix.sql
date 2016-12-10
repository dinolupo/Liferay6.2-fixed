create table AssetCategory (
	uuid_ varchar(75),
	categoryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	parentCategoryId int8,
	leftCategoryId int8,
	rightCategoryId int8,
	name varchar(75),
	title lvarchar,
	vocabularyId int8
)
extent size 16 next size 16
lock mode row;

create table AssetCategoryProperty (
	categoryPropertyId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	categoryId int8,
	key_ varchar(75),
	value varchar(75)
)
extent size 16 next size 16
lock mode row;

create table AssetEntries_AssetCategories (
	entryId int8 not null,
	categoryId int8 not null,
	primary key (entryId, categoryId)
)
extent size 16 next size 16
lock mode row;

create table AssetEntries_AssetTags (
	entryId int8 not null,
	tagId int8 not null,
	primary key (entryId, tagId)
)
extent size 16 next size 16
lock mode row;

create table AssetEntry (
	entryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	visible boolean,
	startDate datetime YEAR TO FRACTION,
	endDate datetime YEAR TO FRACTION,
	publishDate datetime YEAR TO FRACTION,
	expirationDate datetime YEAR TO FRACTION,
	mimeType varchar(75),
	title varchar(255),
	description lvarchar,
	summary lvarchar,
	url lvarchar,
	height int,
	width int,
	priority float,
	viewCount int
)
extent size 16 next size 16
lock mode row;

create table AssetTag (
	tagId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name varchar(75),
	assetCount int
)
extent size 16 next size 16
lock mode row;

create table AssetTagProperty (
	tagPropertyId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	tagId int8,
	key_ varchar(75),
	value varchar(255)
)
extent size 16 next size 16
lock mode row;

create table AssetTagStats (
	tagStatsId int8 not null primary key,
	tagId int8,
	classNameId int8,
	assetCount int
)
extent size 16 next size 16
lock mode row;

create table AssetVocabulary (
	uuid_ varchar(75),
	vocabularyId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name varchar(75),
	title lvarchar,
	description lvarchar,
	settings_ lvarchar
)
extent size 16 next size 16
lock mode row;

alter table BlogsEntry add allowPingbacks boolean;
alter table BlogsEntry add status int;
alter table BlogsEntry add statusByUserId int8;
alter table BlogsEntry add statusByUserName varchar(75);
alter table BlogsEntry add statusDate datetime YEAR TO FRACTION;



update BlogsEntry set allowPingbacks = 'T';
update BlogsEntry set status = 0 where draft = 'F';
update BlogsEntry set status = 2 where draft = 'T';
update BlogsEntry set statusByUserId = userId where draft = 'F';
update BlogsEntry set statusByUserName = userName where draft = 'F';
update BlogsEntry set statusDate = createDate where draft = 'F';

alter table DLFileEntry add pendingVersion varchar(75);

alter table DLFileShortcut add status int;
alter table DLFileShortcut add statusByUserId int8;
alter table DLFileShortcut add statusByUserName varchar(75);
alter table DLFileShortcut add statusDate datetime YEAR TO FRACTION;



update DLFileShortcut set status = 0;
update DLFileShortcut set statusByUserId = userId;
update DLFileShortcut set statusByUserName = userId;
update DLFileShortcut set statusDate = createDate;

drop index IX_6C5E6512;

alter table DLFileVersion add status int;
alter table DLFileVersion add statusByUserId int8;
alter table DLFileVersion add statusByUserName varchar(75);
alter table DLFileVersion add statusDate datetime YEAR TO FRACTION;



update DLFileVersion set status = 0;
update DLFileVersion set statusByUserId = userId;
update DLFileVersion set statusByUserName = userId;
update DLFileVersion set statusDate = createDate;

alter table JournalArticle add status int;
alter table JournalArticle add statusByUserId int8;
alter table JournalArticle add statusByUserName varchar(75);
alter table JournalArticle add statusDate datetime YEAR TO FRACTION;



update JournalArticle set status = 0 where approved = 'T';
update JournalArticle set status = 2 where approved = 'F';
update JournalArticle set statusByUserId = approvedByUserId;
update JournalArticle set statusByUserName = approvedByUserName;
update JournalArticle set statusDate = approvedDate where expired = 'F';
update JournalArticle set statusDate = expirationDate where expired = 'T';

alter table Layout add layoutPrototypeId int8;

create table LayoutPrototype (
	layoutPrototypeId int8 not null primary key,
	companyId int8,
	name lvarchar,
	description lvarchar,
	settings_ lvarchar,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

alter table LayoutSet add layoutSetPrototypeId int8;

create table LayoutSetPrototype (
	layoutSetPrototypeId int8 not null primary key,
	companyId int8,
	name lvarchar,
	description lvarchar,
	settings_ lvarchar,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

alter table MBMessage add allowPingbacks boolean;
alter table MBMessage add status int;
alter table MBMessage add statusByUserId int8;
alter table MBMessage add statusByUserName varchar(75);
alter table MBMessage add statusDate datetime YEAR TO FRACTION;



update MBMessage set allowPingbacks = 'T';
update MBMessage set status = 0;
update MBMessage set statusByUserId = userId;
update MBMessage set statusByUserName = userId;
update MBMessage set statusDate = createDate;

alter table MBThread add status int;
alter table MBThread add statusByUserId int8;
alter table MBThread add statusByUserName varchar(75);
alter table MBThread add statusDate datetime YEAR TO FRACTION;



update MBThread set status = 0;

alter table ShoppingItem add groupId int8;

alter table WikiPage add status int;
alter table WikiPage add statusByUserId int8;
alter table WikiPage add statusByUserName varchar(75);
alter table WikiPage add statusDate datetime YEAR TO FRACTION;



update WikiPage set status = 0;
update WikiPage set statusByUserId = userId;
update WikiPage set statusByUserName = userId;
update WikiPage set statusDate = createDate;

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	workflowDefinitionName varchar(75),
	workflowDefinitionVersion int
)
extent size 16 next size 16
lock mode row;

create table WorkflowInstanceLink (
	workflowInstanceLinkId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	workflowInstanceId int8
)
extent size 16 next size 16
lock mode row;



create table AssetLink (
	linkId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	entryId1 int8,
	entryId2 int8,
	type_ int,
	weight int
)
extent size 16 next size 16
lock mode row;

create table Team (
	teamId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	groupId int8,
	name varchar(75),
	description lvarchar
)
extent size 16 next size 16
lock mode row;

create table Users_Teams (
	userId int8 not null,
	teamId int8 not null,
	primary key (userId, teamId)
)
extent size 16 next size 16
lock mode row;



alter table AssetEntry add socialInformationEquity float;

alter table Company add maxUsers int;



update Company set maxUsers = 0;

update ExpandoTable set name = 'CUSTOM_FIELDS' where name = 'DEFAULT_TABLE';

alter table LayoutSet add settings_ text;

update MBMessage set categoryId = -1 where groupId = 0;

update MBThread set categoryId = -1 where groupId = 0;

alter table PasswordPolicy add minAlphanumeric int;
alter table PasswordPolicy add minLowerCase int;
alter table PasswordPolicy add minNumbers int;
alter table PasswordPolicy add minSymbols int;
alter table PasswordPolicy add minUpperCase int;
alter table PasswordPolicy add resetTicketMaxAge int8;



update PasswordPolicy set minAlphanumeric = 0;
update PasswordPolicy set minLowerCase = 0;
update PasswordPolicy set minNumbers = 0;
update PasswordPolicy set minSymbols = 0;
update PasswordPolicy set minUpperCase = 0;
update PasswordPolicy set resetTicketMaxAge = 86400;

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

create table SocialEquityLog (
	equityLogId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	assetEntryId int8,
	actionId varchar(75),
	actionDate int,
	type_ int,
	value int,
	validity int,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

create table SocialEquitySetting (
	equitySettingId int8 not null primary key,
	groupId int8,
	companyId int8,
	classNameId int8,
	actionId varchar(75),
	type_ int,
	value int,
	validity int
)
extent size 16 next size 16
lock mode row;

create table SocialEquityUser (
	equityUserId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	contributionK float,
	contributionB float,
	participationK float,
	participationB float
)
extent size 16 next size 16
lock mode row;

create table Ticket (
	ticketId int8 not null primary key,
	companyId int8,
	createDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	key_ varchar(75),
	expirationDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

alter table User_ add socialContributionEquity float;
alter table User_ add socialParticipationEquity float;
alter table User_ add socialPersonalEquity float;

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

create table ClusterGroup (
	clusterGroupId int8 not null primary key,
	clusterNodeIds varchar(75),
	wholeCluster boolean
)
extent size 16 next size 16
lock mode row;

alter table User_ add digest varchar(256);

create table AssetCategory (
	uuid_ varchar(75) null,
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	parentCategoryId bigint,
	leftCategoryId bigint,
	rightCategoryId bigint,
	name varchar(75) null,
	title longvarchar null,
	vocabularyId bigint
);

create table AssetCategoryProperty (
	categoryPropertyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	categoryId bigint,
	key_ varchar(75) null,
	value varchar(75) null
);

create table AssetEntries_AssetCategories (
	entryId bigint not null,
	categoryId bigint not null,
	primary key (entryId, categoryId)
);

create table AssetEntries_AssetTags (
	entryId bigint not null,
	tagId bigint not null,
	primary key (entryId, tagId)
);

create table AssetEntry (
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId bigint,
	classPK bigint,
	visible bit,
	startDate timestamp null,
	endDate timestamp null,
	publishDate timestamp null,
	expirationDate timestamp null,
	mimeType varchar(75) null,
	title varchar(255) null,
	description longvarchar null,
	summary longvarchar null,
	url longvarchar null,
	height int,
	width int,
	priority double,
	viewCount int
);

create table AssetTag (
	tagId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name varchar(75) null,
	assetCount int
);

create table AssetTagProperty (
	tagPropertyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	tagId bigint,
	key_ varchar(75) null,
	value varchar(255) null
);

create table AssetTagStats (
	tagStatsId bigint not null primary key,
	tagId bigint,
	classNameId bigint,
	assetCount int
);

create table AssetVocabulary (
	uuid_ varchar(75) null,
	vocabularyId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name varchar(75) null,
	title longvarchar null,
	description longvarchar null,
	settings_ longvarchar null
);

alter table BlogsEntry add allowPingbacks bit;
alter table BlogsEntry add status int;
alter table BlogsEntry add statusByUserId bigint;
alter table BlogsEntry add statusByUserName varchar(75);
alter table BlogsEntry add statusDate timestamp;

commit;

update BlogsEntry set allowPingbacks = true;
update BlogsEntry set status = 0 where draft = false;
update BlogsEntry set status = 2 where draft = true;
update BlogsEntry set statusByUserId = userId where draft = false;
update BlogsEntry set statusByUserName = userName where draft = false;
update BlogsEntry set statusDate = createDate where draft = false;

alter table DLFileEntry add pendingVersion varchar(75) null;

alter table DLFileShortcut add status int;
alter table DLFileShortcut add statusByUserId bigint;
alter table DLFileShortcut add statusByUserName varchar(75);
alter table DLFileShortcut add statusDate timestamp;

commit;

update DLFileShortcut set status = 0;
update DLFileShortcut set statusByUserId = userId;
update DLFileShortcut set statusByUserName = userId;
update DLFileShortcut set statusDate = createDate;

drop index IX_6C5E6512;

alter table DLFileVersion add status int;
alter table DLFileVersion add statusByUserId bigint;
alter table DLFileVersion add statusByUserName varchar(75);
alter table DLFileVersion add statusDate timestamp;

commit;

update DLFileVersion set status = 0;
update DLFileVersion set statusByUserId = userId;
update DLFileVersion set statusByUserName = userId;
update DLFileVersion set statusDate = createDate;

alter table JournalArticle add status int;
alter table JournalArticle add statusByUserId bigint;
alter table JournalArticle add statusByUserName varchar(75);
alter table JournalArticle add statusDate timestamp;

commit;

update JournalArticle set status = 0 where approved = true;
update JournalArticle set status = 2 where approved = false;
update JournalArticle set statusByUserId = approvedByUserId;
update JournalArticle set statusByUserName = approvedByUserName;
update JournalArticle set statusDate = approvedDate where expired = false;
update JournalArticle set statusDate = expirationDate where expired = true;

alter table Layout add layoutPrototypeId bigint;

create table LayoutPrototype (
	layoutPrototypeId bigint not null primary key,
	companyId bigint,
	name longvarchar null,
	description longvarchar null,
	settings_ longvarchar null,
	active_ bit
);

alter table LayoutSet add layoutSetPrototypeId bigint;

create table LayoutSetPrototype (
	layoutSetPrototypeId bigint not null primary key,
	companyId bigint,
	name longvarchar null,
	description longvarchar null,
	settings_ longvarchar null,
	active_ bit
);

alter table MBMessage add allowPingbacks bit;
alter table MBMessage add status int;
alter table MBMessage add statusByUserId bigint;
alter table MBMessage add statusByUserName varchar(75);
alter table MBMessage add statusDate timestamp;

commit;

update MBMessage set allowPingbacks = true;
update MBMessage set status = 0;
update MBMessage set statusByUserId = userId;
update MBMessage set statusByUserName = userId;
update MBMessage set statusDate = createDate;

alter table MBThread add status int;
alter table MBThread add statusByUserId bigint;
alter table MBThread add statusByUserName varchar(75);
alter table MBThread add statusDate timestamp;

commit;

update MBThread set status = 0;

alter table ShoppingItem add groupId bigint;

alter table WikiPage add status int;
alter table WikiPage add statusByUserId bigint;
alter table WikiPage add statusByUserName varchar(75);
alter table WikiPage add statusDate timestamp;

commit;

update WikiPage set status = 0;
update WikiPage set statusByUserId = userId;
update WikiPage set statusByUserName = userId;
update WikiPage set statusDate = createDate;

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId bigint,
	workflowDefinitionName varchar(75) null,
	workflowDefinitionVersion int
);

create table WorkflowInstanceLink (
	workflowInstanceLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId bigint,
	classPK bigint,
	workflowInstanceId bigint
);

commit;

create table AssetLink (
	linkId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	entryId1 bigint,
	entryId2 bigint,
	type_ int,
	weight int
);

create table Team (
	teamId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	groupId bigint,
	name varchar(75) null,
	description longvarchar null
);

create table Users_Teams (
	userId bigint not null,
	teamId bigint not null,
	primary key (userId, teamId)
);

commit;

alter table AssetEntry add socialInformationEquity double;

alter table Company add maxUsers int;

commit;

update Company set maxUsers = 0;

update ExpandoTable set name = 'CUSTOM_FIELDS' where name = 'DEFAULT_TABLE';

alter table LayoutSet add settings_ longvarchar null;

update MBMessage set categoryId = -1 where groupId = 0;

update MBThread set categoryId = -1 where groupId = 0;

alter table PasswordPolicy add minAlphanumeric int;
alter table PasswordPolicy add minLowerCase int;
alter table PasswordPolicy add minNumbers int;
alter table PasswordPolicy add minSymbols int;
alter table PasswordPolicy add minUpperCase int;
alter table PasswordPolicy add resetTicketMaxAge bigint;

commit;

update PasswordPolicy set minAlphanumeric = 0;
update PasswordPolicy set minLowerCase = 0;
update PasswordPolicy set minNumbers = 0;
update PasswordPolicy set minSymbols = 0;
update PasswordPolicy set minUpperCase = 0;
update PasswordPolicy set resetTicketMaxAge = 86400;

create table SocialEquityAssetEntry (
	equityAssetEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	assetEntryId bigint,
	informationK double,
	informationB double
);

create table SocialEquityHistory (
	equityHistoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate timestamp null,
	personalEquity int
);

create table SocialEquityLog (
	equityLogId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	assetEntryId bigint,
	actionId varchar(75) null,
	actionDate int,
	type_ int,
	value int,
	validity int,
	active_ bit
);

create table SocialEquitySetting (
	equitySettingId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	actionId varchar(75) null,
	type_ int,
	value int,
	validity int
);

create table SocialEquityUser (
	equityUserId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	contributionK double,
	contributionB double,
	participationK double,
	participationB double
);

create table Ticket (
	ticketId bigint not null primary key,
	companyId bigint,
	createDate timestamp null,
	classNameId bigint,
	classPK bigint,
	key_ varchar(75) null,
	expirationDate timestamp null
);

alter table User_ add socialContributionEquity double;
alter table User_ add socialParticipationEquity double;
alter table User_ add socialPersonalEquity double;

alter table AssetEntry add classUuid varchar(75) null;

alter table DLFileEntry add extension varchar(75) null;

alter table DLFileVersion add extension varchar(75) null;
alter table DLFileVersion add title varchar(255) null;
alter table DLFileVersion add changeLog longvarchar null;
alter table DLFileVersion add extraSettings longvarchar null;

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
	informationK double,
	informationB double
);

drop table SocialEquityHistory;

create table SocialEquityHistory (
	equityHistoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate timestamp null,
	personalEquity int
);

drop table SocialEquityLog;

create table SocialEquityLog (
	equityLogId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	assetEntryId bigint,
	actionId varchar(75) null,
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
	actionId varchar(75) null,
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
	contributionK double,
	contributionB double,
	participationK double,
	participationB double,
	rank int
);

alter table User_ add facebookId bigint;

alter table WikiPageResource add uuid_ varchar(75) null;

create table ClusterGroup (
	clusterGroupId bigint not null primary key,
	clusterNodeIds varchar(75) null,
	wholeCluster bit
);

alter table User_ add digest varchar(256) null;

create table AssetCategory (
	uuid_ nvarchar(75) null,
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentCategoryId bigint,
	leftCategoryId bigint,
	rightCategoryId bigint,
	name nvarchar(75) null,
	title nvarchar(2000) null,
	vocabularyId bigint
);

create table AssetCategoryProperty (
	categoryPropertyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId bigint,
	key_ nvarchar(75) null,
	value nvarchar(75) null
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
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	visible bit,
	startDate datetime null,
	endDate datetime null,
	publishDate datetime null,
	expirationDate datetime null,
	mimeType nvarchar(75) null,
	title nvarchar(255) null,
	description nvarchar(2000) null,
	summary nvarchar(2000) null,
	url nvarchar(2000) null,
	height int,
	width int,
	priority float,
	viewCount int
);

create table AssetTag (
	tagId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(75) null,
	assetCount int
);

create table AssetTagProperty (
	tagPropertyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	tagId bigint,
	key_ nvarchar(75) null,
	value nvarchar(255) null
);

create table AssetTagStats (
	tagStatsId bigint not null primary key,
	tagId bigint,
	classNameId bigint,
	assetCount int
);

create table AssetVocabulary (
	uuid_ nvarchar(75) null,
	vocabularyId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(75) null,
	title nvarchar(2000) null,
	description nvarchar(2000) null,
	settings_ nvarchar(2000) null
);

alter table BlogsEntry add allowPingbacks bit;
alter table BlogsEntry add status int;
alter table BlogsEntry add statusByUserId bigint;
alter table BlogsEntry add statusByUserName nvarchar(75);
alter table BlogsEntry add statusDate datetime;

go

update BlogsEntry set allowPingbacks = 1;
update BlogsEntry set status = 0 where draft = 0;
update BlogsEntry set status = 2 where draft = 1;
update BlogsEntry set statusByUserId = userId where draft = 0;
update BlogsEntry set statusByUserName = userName where draft = 0;
update BlogsEntry set statusDate = createDate where draft = 0;

alter table DLFileEntry add pendingVersion nvarchar(75) null;

alter table DLFileShortcut add status int;
alter table DLFileShortcut add statusByUserId bigint;
alter table DLFileShortcut add statusByUserName nvarchar(75);
alter table DLFileShortcut add statusDate datetime;

go

update DLFileShortcut set status = 0;
update DLFileShortcut set statusByUserId = userId;
update DLFileShortcut set statusByUserName = userId;
update DLFileShortcut set statusDate = createDate;

drop index DLFileVersion.IX_6C5E6512;

alter table DLFileVersion add status int;
alter table DLFileVersion add statusByUserId bigint;
alter table DLFileVersion add statusByUserName nvarchar(75);
alter table DLFileVersion add statusDate datetime;

go

update DLFileVersion set status = 0;
update DLFileVersion set statusByUserId = userId;
update DLFileVersion set statusByUserName = userId;
update DLFileVersion set statusDate = createDate;

alter table JournalArticle add status int;
alter table JournalArticle add statusByUserId bigint;
alter table JournalArticle add statusByUserName nvarchar(75);
alter table JournalArticle add statusDate datetime;

go

update JournalArticle set status = 0 where approved = 1;
update JournalArticle set status = 2 where approved = 0;
update JournalArticle set statusByUserId = approvedByUserId;
update JournalArticle set statusByUserName = approvedByUserName;
update JournalArticle set statusDate = approvedDate where expired = 0;
update JournalArticle set statusDate = expirationDate where expired = 1;

alter table Layout add layoutPrototypeId bigint;

create table LayoutPrototype (
	layoutPrototypeId bigint not null primary key,
	companyId bigint,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	settings_ nvarchar(2000) null,
	active_ bit
);

alter table LayoutSet add layoutSetPrototypeId bigint;

create table LayoutSetPrototype (
	layoutSetPrototypeId bigint not null primary key,
	companyId bigint,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	settings_ nvarchar(2000) null,
	active_ bit
);

alter table MBMessage add allowPingbacks bit;
alter table MBMessage add status int;
alter table MBMessage add statusByUserId bigint;
alter table MBMessage add statusByUserName nvarchar(75);
alter table MBMessage add statusDate datetime;

go

update MBMessage set allowPingbacks = 1;
update MBMessage set status = 0;
update MBMessage set statusByUserId = userId;
update MBMessage set statusByUserName = userId;
update MBMessage set statusDate = createDate;

alter table MBThread add status int;
alter table MBThread add statusByUserId bigint;
alter table MBThread add statusByUserName nvarchar(75);
alter table MBThread add statusDate datetime;

go

update MBThread set status = 0;

alter table ShoppingItem add groupId bigint;

alter table WikiPage add status int;
alter table WikiPage add statusByUserId bigint;
alter table WikiPage add statusByUserName nvarchar(75);
alter table WikiPage add statusDate datetime;

go

update WikiPage set status = 0;
update WikiPage set statusByUserId = userId;
update WikiPage set statusByUserName = userId;
update WikiPage set statusDate = createDate;

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	workflowDefinitionName nvarchar(75) null,
	workflowDefinitionVersion int
);

create table WorkflowInstanceLink (
	workflowInstanceLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	workflowInstanceId bigint
);

go

create table AssetLink (
	linkId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	entryId1 bigint,
	entryId2 bigint,
	type_ int,
	weight int
);

create table Team (
	teamId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	groupId bigint,
	name nvarchar(75) null,
	description nvarchar(2000) null
);

create table Users_Teams (
	userId bigint not null,
	teamId bigint not null,
	primary key (userId, teamId)
);

go

alter table AssetEntry add socialInformationEquity float;

alter table Company add maxUsers int;

go

update Company set maxUsers = 0;

update ExpandoTable set name = 'CUSTOM_FIELDS' where name = 'DEFAULT_TABLE';

alter table LayoutSet add settings_ nvarchar(max) null;

update MBMessage set categoryId = -1 where groupId = 0;

update MBThread set categoryId = -1 where groupId = 0;

alter table PasswordPolicy add minAlphanumeric int;
alter table PasswordPolicy add minLowerCase int;
alter table PasswordPolicy add minNumbers int;
alter table PasswordPolicy add minSymbols int;
alter table PasswordPolicy add minUpperCase int;
alter table PasswordPolicy add resetTicketMaxAge bigint;

go

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
	informationK float,
	informationB float
);

create table SocialEquityHistory (
	equityHistoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate datetime null,
	personalEquity int
);

create table SocialEquityLog (
	equityLogId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	assetEntryId bigint,
	actionId nvarchar(75) null,
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
	actionId nvarchar(75) null,
	type_ int,
	value int,
	validity int
);

create table SocialEquityUser (
	equityUserId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	contributionK float,
	contributionB float,
	participationK float,
	participationB float
);

create table Ticket (
	ticketId bigint not null primary key,
	companyId bigint,
	createDate datetime null,
	classNameId bigint,
	classPK bigint,
	key_ nvarchar(75) null,
	expirationDate datetime null
);

alter table User_ add socialContributionEquity float;
alter table User_ add socialParticipationEquity float;
alter table User_ add socialPersonalEquity float;

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

create table ClusterGroup (
	clusterGroupId bigint not null primary key,
	clusterNodeIds nvarchar(75) null,
	wholeCluster bit
);

alter table User_ add digest nvarchar(256) null;

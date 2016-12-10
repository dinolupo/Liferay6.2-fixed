create table AssetCategory (
	uuid_ VARCHAR2(75 CHAR) null,
	categoryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	parentCategoryId number(30,0),
	leftCategoryId number(30,0),
	rightCategoryId number(30,0),
	name VARCHAR2(75 CHAR) null,
	title varchar2(4000) null,
	vocabularyId number(30,0)
);

create table AssetCategoryProperty (
	categoryPropertyId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	categoryId number(30,0),
	key_ VARCHAR2(75 CHAR) null,
	value VARCHAR2(75 CHAR) null
);

create table AssetEntries_AssetCategories (
	entryId number(30,0) not null,
	categoryId number(30,0) not null,
	primary key (entryId, categoryId)
);

create table AssetEntries_AssetTags (
	entryId number(30,0) not null,
	tagId number(30,0) not null,
	primary key (entryId, tagId)
);

create table AssetEntry (
	entryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	visible number(1, 0),
	startDate timestamp null,
	endDate timestamp null,
	publishDate timestamp null,
	expirationDate timestamp null,
	mimeType VARCHAR2(75 CHAR) null,
	title VARCHAR2(255 CHAR) null,
	description varchar2(4000) null,
	summary varchar2(4000) null,
	url varchar2(4000) null,
	height number(30,0),
	width number(30,0),
	priority number(30,20),
	viewCount number(30,0)
);

create table AssetTag (
	tagId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name VARCHAR2(75 CHAR) null,
	assetCount number(30,0)
);

create table AssetTagProperty (
	tagPropertyId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	tagId number(30,0),
	key_ VARCHAR2(75 CHAR) null,
	value VARCHAR2(255 CHAR) null
);

create table AssetTagStats (
	tagStatsId number(30,0) not null primary key,
	tagId number(30,0),
	classNameId number(30,0),
	assetCount number(30,0)
);

create table AssetVocabulary (
	uuid_ VARCHAR2(75 CHAR) null,
	vocabularyId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name VARCHAR2(75 CHAR) null,
	title varchar2(4000) null,
	description varchar2(4000) null,
	settings_ varchar2(4000) null
);

alter table BlogsEntry add allowPingbacks number(1, 0);
alter table BlogsEntry add status number(30,0);
alter table BlogsEntry add statusByUserId number(30,0);
alter table BlogsEntry add statusByUserName VARCHAR2(75 CHAR);
alter table BlogsEntry add statusDate timestamp;

commit;

update BlogsEntry set allowPingbacks = 1;
update BlogsEntry set status = 0 where draft = 0;
update BlogsEntry set status = 2 where draft = 1;
update BlogsEntry set statusByUserId = userId where draft = 0;
update BlogsEntry set statusByUserName = userName where draft = 0;
update BlogsEntry set statusDate = createDate where draft = 0;

alter table DLFileEntry add pendingVersion VARCHAR2(75 CHAR) null;

alter table DLFileShortcut add status number(30,0);
alter table DLFileShortcut add statusByUserId number(30,0);
alter table DLFileShortcut add statusByUserName VARCHAR2(75 CHAR);
alter table DLFileShortcut add statusDate timestamp;

commit;

update DLFileShortcut set status = 0;
update DLFileShortcut set statusByUserId = userId;
update DLFileShortcut set statusByUserName = userId;
update DLFileShortcut set statusDate = createDate;

drop index IX_6C5E6512;

alter table DLFileVersion add status number(30,0);
alter table DLFileVersion add statusByUserId number(30,0);
alter table DLFileVersion add statusByUserName VARCHAR2(75 CHAR);
alter table DLFileVersion add statusDate timestamp;

commit;

update DLFileVersion set status = 0;
update DLFileVersion set statusByUserId = userId;
update DLFileVersion set statusByUserName = userId;
update DLFileVersion set statusDate = createDate;

alter table JournalArticle add status number(30,0);
alter table JournalArticle add statusByUserId number(30,0);
alter table JournalArticle add statusByUserName VARCHAR2(75 CHAR);
alter table JournalArticle add statusDate timestamp;

commit;

update JournalArticle set status = 0 where approved = 1;
update JournalArticle set status = 2 where approved = 0;
update JournalArticle set statusByUserId = approvedByUserId;
update JournalArticle set statusByUserName = approvedByUserName;
update JournalArticle set statusDate = approvedDate where expired = 0;
update JournalArticle set statusDate = expirationDate where expired = 1;

alter table Layout add layoutPrototypeId number(30,0);

create table LayoutPrototype (
	layoutPrototypeId number(30,0) not null primary key,
	companyId number(30,0),
	name varchar2(4000) null,
	description varchar2(4000) null,
	settings_ varchar2(4000) null,
	active_ number(1, 0)
);

alter table LayoutSet add layoutSetPrototypeId number(30,0);

create table LayoutSetPrototype (
	layoutSetPrototypeId number(30,0) not null primary key,
	companyId number(30,0),
	name varchar2(4000) null,
	description varchar2(4000) null,
	settings_ varchar2(4000) null,
	active_ number(1, 0)
);

alter table MBMessage add allowPingbacks number(1, 0);
alter table MBMessage add status number(30,0);
alter table MBMessage add statusByUserId number(30,0);
alter table MBMessage add statusByUserName VARCHAR2(75 CHAR);
alter table MBMessage add statusDate timestamp;

commit;

update MBMessage set allowPingbacks = 1;
update MBMessage set status = 0;
update MBMessage set statusByUserId = userId;
update MBMessage set statusByUserName = userId;
update MBMessage set statusDate = createDate;

alter table MBThread add status number(30,0);
alter table MBThread add statusByUserId number(30,0);
alter table MBThread add statusByUserName VARCHAR2(75 CHAR);
alter table MBThread add statusDate timestamp;

commit;

update MBThread set status = 0;

alter table ShoppingItem add groupId number(30,0);

alter table WikiPage add status number(30,0);
alter table WikiPage add statusByUserId number(30,0);
alter table WikiPage add statusByUserName VARCHAR2(75 CHAR);
alter table WikiPage add statusDate timestamp;

commit;

update WikiPage set status = 0;
update WikiPage set statusByUserId = userId;
update WikiPage set statusByUserName = userId;
update WikiPage set statusDate = createDate;

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	workflowDefinitionName VARCHAR2(75 CHAR) null,
	workflowDefinitionVersion number(30,0)
);

create table WorkflowInstanceLink (
	workflowInstanceLinkId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	workflowInstanceId number(30,0)
);

commit;

create table AssetLink (
	linkId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	entryId1 number(30,0),
	entryId2 number(30,0),
	type_ number(30,0),
	weight number(30,0)
);

create table Team (
	teamId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	groupId number(30,0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null
);

create table Users_Teams (
	userId number(30,0) not null,
	teamId number(30,0) not null,
	primary key (userId, teamId)
);

commit;

alter table AssetEntry add socialInformationEquity number(30,20);

alter table Company add maxUsers number(30,0);

commit;

update Company set maxUsers = 0;

update ExpandoTable set name = 'CUSTOM_FIELDS' where name = 'DEFAULT_TABLE';

alter table LayoutSet add settings_ clob null;

update MBMessage set categoryId = -1 where groupId = 0;

update MBThread set categoryId = -1 where groupId = 0;

alter table PasswordPolicy add minAlphanumeric number(30,0);
alter table PasswordPolicy add minLowerCase number(30,0);
alter table PasswordPolicy add minNumbers number(30,0);
alter table PasswordPolicy add minSymbols number(30,0);
alter table PasswordPolicy add minUpperCase number(30,0);
alter table PasswordPolicy add resetTicketMaxAge number(30,0);

commit;

update PasswordPolicy set minAlphanumeric = 0;
update PasswordPolicy set minLowerCase = 0;
update PasswordPolicy set minNumbers = 0;
update PasswordPolicy set minSymbols = 0;
update PasswordPolicy set minUpperCase = 0;
update PasswordPolicy set resetTicketMaxAge = 86400;

create table SocialEquityAssetEntry (
	equityAssetEntryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	assetEntryId number(30,0),
	informationK number(30,20),
	informationB number(30,20)
);

create table SocialEquityHistory (
	equityHistoryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	createDate timestamp null,
	personalEquity number(30,0)
);

create table SocialEquityLog (
	equityLogId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	assetEntryId number(30,0),
	actionId VARCHAR2(75 CHAR) null,
	actionDate number(30,0),
	type_ number(30,0),
	value number(30,0),
	validity number(30,0),
	active_ number(1, 0)
);

create table SocialEquitySetting (
	equitySettingId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	classNameId number(30,0),
	actionId VARCHAR2(75 CHAR) null,
	type_ number(30,0),
	value number(30,0),
	validity number(30,0)
);

create table SocialEquityUser (
	equityUserId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	contributionK number(30,20),
	contributionB number(30,20),
	participationK number(30,20),
	participationB number(30,20)
);

create table Ticket (
	ticketId number(30,0) not null primary key,
	companyId number(30,0),
	createDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	key_ VARCHAR2(75 CHAR) null,
	expirationDate timestamp null
);

alter table User_ add socialContributionEquity number(30,20);
alter table User_ add socialParticipationEquity number(30,20);
alter table User_ add socialPersonalEquity number(30,20);

alter table AssetEntry add classUuid VARCHAR2(75 CHAR) null;

alter table DLFileEntry add extension VARCHAR2(75 CHAR) null;

alter table DLFileVersion add extension VARCHAR2(75 CHAR) null;
alter table DLFileVersion add title VARCHAR2(255 CHAR) null;
alter table DLFileVersion add changeLog varchar2(4000) null;
alter table DLFileVersion add extraSettings clob null;

commit;

update DLFileVersion set changeLog = description;

alter table Layout add uuid_ VARCHAR2(75 CHAR) null;

alter table MBMessage add rootMessageId number(30,0);

commit;

update MBMessage set rootMessageId = (select rootMessageId from MBThread where MBThread.threadId = MBMessage.threadId);

drop table SocialEquityAssetEntry;

create table SocialEquityAssetEntry (
	equityAssetEntryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	assetEntryId number(30,0),
	informationK number(30,20),
	informationB number(30,20)
);

drop table SocialEquityHistory;

create table SocialEquityHistory (
	equityHistoryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	createDate timestamp null,
	personalEquity number(30,0)
);

drop table SocialEquityLog;

create table SocialEquityLog (
	equityLogId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	assetEntryId number(30,0),
	actionId VARCHAR2(75 CHAR) null,
	actionDate number(30,0),
	active_ number(1, 0),
	expiration number(30,0),
	type_ number(30,0),
	value number(30,0)
);

drop table SocialEquitySetting;

create table SocialEquitySetting (
	equitySettingId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	classNameId number(30,0),
	actionId VARCHAR2(75 CHAR) null,
	dailyLimit number(30,0),
	lifespan number(30,0),
	type_ number(30,0),
	uniqueEntry number(1, 0),
	value number(30,0)
);

drop table SocialEquityUser;

create table SocialEquityUser (
	equityUserId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	contributionK number(30,20),
	contributionB number(30,20),
	participationK number(30,20),
	participationB number(30,20),
	rank number(30,0)
);

alter table User_ add facebookId number(30,0);

alter table WikiPageResource add uuid_ VARCHAR2(75 CHAR) null;

create table ClusterGroup (
	clusterGroupId number(30,0) not null primary key,
	clusterNodeIds VARCHAR2(75 CHAR) null,
	wholeCluster number(1, 0)
);

alter table User_ add digest VARCHAR2(256 CHAR) null;

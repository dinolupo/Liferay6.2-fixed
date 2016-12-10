alter table AssetCategory add description varchar(4000);

alter table AssetEntry add classTypeId bigint;
alter table AssetEntry add layoutUuid varchar(75);

update AssetEntry set classUuid = (select uuid_ from JournalArticleResource where AssetEntry.classPK = JournalArticleResource.resourcePrimKey) where visible = 1 and classNameId = (select classNameId from ClassName_ where value = 'com.liferay.portlet.journal.model.JournalArticle');

alter table BlogsEntry add description varchar(4000);
alter table BlogsEntry add smallImage smallint;
alter table BlogsEntry add smallImageId bigint;
alter table BlogsEntry add smallImageURL varchar(4000);

alter table BookmarksEntry add userName varchar(75);
alter table BookmarksEntry add resourceBlockId bigint;
alter table BookmarksEntry add description varchar(4000);

commit;

update BookmarksEntry set description = comments;

alter table BookmarksEntry drop column comments;

alter table BookmarksFolder add userName varchar(75);
alter table BookmarksFolder add resourceBlockId bigint;

alter table CalEvent add location varchar(4000);

update ClassName_ set value = 'com.liferay.portal.model.UserPersonalSite' where value = 'com.liferay.portal.model.UserPersonalCommunity';

drop index IX_975996C0;

alter table Company add active_ smallint;

commit;

update Company set active_ = 1;

alter table Country add zipRequired smallint;

commit;

update Country set zipRequired = 1;

create table DDLRecord (
	uuid_ varchar(75),
	recordId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	versionUserId bigint,
	versionUserName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	DDMStorageId bigint,
	recordSetId bigint,
	version varchar(75),
	displayIndex integer
);

create table DDLRecordSet (
	uuid_ varchar(75),
	recordSetId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	DDMStructureId bigint,
	recordSetKey varchar(75),
	name varchar(4000),
	description varchar(4000),
	minDisplayRows integer,
	scope integer
);

create table DDLRecordVersion (
	recordVersionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	DDMStorageId bigint,
	recordSetId bigint,
	recordId bigint,
	version varchar(75),
	displayIndex integer,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table DDMContent (
	uuid_ varchar(75),
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(4000),
	description varchar(4000),
	xml clob
);

create table DDMStorageLink (
	uuid_ varchar(75),
	storageLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
);

create table DDMStructure (
	uuid_ varchar(75),
	structureId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	structureKey varchar(75),
	name varchar(4000),
	description varchar(4000),
	xsd clob,
	storageType varchar(75),
	type_ integer
);

create table DDMStructureLink (
	structureLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
);

create table DDMTemplate (
	uuid_ varchar(75),
	templateId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	structureId bigint,
	name varchar(4000),
	description varchar(4000),
	type_ varchar(75),
	mode_ varchar(75),
	language varchar(75),
	script clob
);

create table DLContent (
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	repositoryId bigint,
	path_ varchar(255),
	version varchar(75),
	data_ blob,
	size_ bigint
);

create table DLFileEntryMetadata (
	uuid_ varchar(75),
	fileEntryMetadataId bigint not null primary key,
	DDMStorageId bigint,
	DDMStructureId bigint,
	fileEntryTypeId bigint,
	fileEntryId bigint,
	fileVersionId bigint
);

create table DLFileEntryType (
	uuid_ varchar(75),
	fileEntryTypeId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	description varchar(4000)
);

create table DLFileEntryTypes_DDMStructures (
	fileEntryTypeId bigint not null,
	structureId bigint not null,
	primary key (fileEntryTypeId, structureId)
);

create table DLFileEntryTypes_DLFolders (
	fileEntryTypeId bigint not null,
	folderId bigint not null,
	primary key (fileEntryTypeId, folderId)
);

alter table DLFileEntry add repositoryId bigint;
alter table DLFileEntry add mimeType varchar(75);
alter table DLFileEntry add fileEntryTypeId bigint;
alter table DLFileEntry add smallImageId bigint;
alter table DLFileEntry add largeImageId bigint;
alter table DLFileEntry add custom1ImageId bigint;
alter table DLFileEntry add custom2ImageId bigint;

commit;

update DLFileEntry set repositoryId = groupId;

drop index IX_CE705D48;
drop index IX_40B56512;

alter table DLFileRank add fileEntryId bigint;

drop index IX_55C736AC;
drop index IX_346A0992;

alter table DLFileShortcut add repositoryId bigint;
alter table DLFileShortcut add toFileEntryId bigint;

commit;

update DLFileShortcut set repositoryId = groupId;

drop index IX_B413F1EC;
drop index IX_94E784D2;
drop index IX_2F8FED9C;

alter table DLFileVersion add modifiedDate timestamp;
alter table DLFileVersion add repositoryId bigint;
alter table DLFileVersion add fileEntryId bigint;
alter table DLFileVersion add mimeType varchar(75);
alter table DLFileVersion add fileEntryTypeId bigint;

commit;

update DLFileVersion set modifiedDate = statusDate;
update DLFileVersion set repositoryId = groupId;

alter table DLFolder add repositoryId bigint;
alter table DLFolder add mountPoint smallint;
alter table DLFolder add defaultFileEntryTypeId bigint;
alter table DLFolder add overrideFileEntryTypes smallint;

commit;

update DLFolder set repositoryId = groupId;
update DLFolder set mountPoint = 0;

create table DLSync (
	syncId bigint not null primary key,
	companyId bigint,
	createDate timestamp,
	modifiedDate timestamp,
	fileId bigint,
	fileUuid varchar(75),
	repositoryId bigint,
	parentFolderId bigint,
	name varchar(255),
	event varchar(75),
	type_ varchar(75),
	version varchar(75)
);

alter table Group_ add site smallint;

update Group_ set name = 'User Personal Site' where name = 'User Personal Community';
update Group_ set type_ = 3 where classNameId = (select classNameId from ClassName_ where value = 'com.liferay.portal.model.Organization');

alter table IGFolder add userName varchar(75);

alter table IGImage add userName varchar(75);

alter table JournalArticle add classNameId bigint;
alter table JournalArticle add classPK bigint;
alter table JournalArticle add layoutUuid varchar(75);

commit;

update JournalArticle set classNameId = 0;
update JournalArticle set classPK = 0;

drop index IX_FAD05595;

alter table Layout drop column layoutPrototypeId;
alter table Layout drop column dlFolderId;

alter table Layout add createDate timestamp;
alter table Layout add modifiedDate timestamp;
alter table Layout add keywords varchar(4000);
alter table Layout add robots varchar(4000);
alter table Layout add layoutPrototypeUuid varchar(75);
alter table Layout add layoutPrototypeLinkEnabled smallint;
alter table Layout add sourcePrototypeLayoutUuid varchar(75);

commit;

update Layout set createDate = current timestamp;
update Layout set modifiedDate = current timestamp;

create table LayoutBranch (
	LayoutBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	layoutSetBranchId bigint,
	plid bigint,
	name varchar(75),
	description varchar(4000),
	master smallint
);

alter table LayoutPrototype add uuid_ varchar(75);

create table LayoutRevision (
	layoutRevisionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	layoutSetBranchId bigint,
	layoutBranchId bigint,
	parentLayoutRevisionId bigint,
	head smallint,
	major smallint,
	plid bigint,
	privateLayout smallint,
	name varchar(4000),
	title varchar(4000),
	description varchar(4000),
	keywords varchar(4000),
	robots varchar(4000),
	typeSettings clob,
	iconImage smallint,
	iconImageId bigint,
	themeId varchar(75),
	colorSchemeId varchar(75),
	wapThemeId varchar(75),
	wapColorSchemeId varchar(75),
	css varchar(4000),
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

alter table LayoutSet drop column layoutSetPrototypeId;

alter table LayoutSet add createDate timestamp;
alter table LayoutSet add modifiedDate timestamp;
alter table LayoutSet add layoutSetPrototypeUuid varchar(75);
alter table LayoutSet add layoutSetPrototypeLinkEnabled smallint;

drop index IX_5ABC2905;

commit;

update LayoutSet set createDate = current timestamp;
update LayoutSet set modifiedDate = current timestamp;

create table LayoutSetBranch (
	layoutSetBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	privateLayout smallint,
	name varchar(75),
	description varchar(4000),
	master smallint
);

alter table LayoutSetPrototype add createDate timestamp;
alter table LayoutSetPrototype add modifiedDate timestamp;
alter table LayoutSetPrototype add uuid_ varchar(75);

commit;

update LayoutSetPrototype set createDate = current timestamp;
update LayoutSetPrototype set modifiedDate = current timestamp;

alter table MBCategory add displayStyle varchar(75);

commit;

update MBCategory set displayStyle = 'default';

alter table MBMailingList add allowAnonymous smallint;

alter table MBMessage add format varchar(75);
alter table MBMessage add answer smallint;

commit;

update MBMessage set format = 'bbcode';

alter table MBThread add companyId bigint;
alter table MBThread add rootMessageUserId bigint;
alter table MBThread add question smallint;

create table MBThreadFlag (
	threadFlagId bigint not null primary key,
	userId bigint,
	modifiedDate timestamp,
	threadId bigint
);

create table MDRAction (
	uuid_ varchar(75),
	actionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	ruleGroupInstanceId bigint,
	name varchar(4000),
	description varchar(4000),
	type_ varchar(255),
	typeSettings clob
);

create table MDRRule (
	uuid_ varchar(75),
	ruleId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	ruleGroupId bigint,
	name varchar(4000),
	description varchar(4000),
	type_ varchar(255),
	typeSettings clob
);

create table MDRRuleGroup (
	uuid_ varchar(75),
	ruleGroupId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(4000),
	description varchar(4000)
);

create table MDRRuleGroupInstance (
	uuid_ varchar(75),
	ruleGroupInstanceId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	ruleGroupId bigint,
	priority integer
);

alter table Organization_ drop column leftOrganizationId;
alter table Organization_ drop column rightOrganizationId;

alter table Organization_ add treePath varchar(4000);

alter table PollsVote add companyId bigint;
alter table PollsVote add userName varchar(75);
alter table PollsVote add createDate timestamp;
alter table PollsVote add modifiedDate timestamp;

create table PortalPreferences (
	portalPreferencesId bigint not null primary key,
	ownerId bigint,
	ownerType integer,
	preferences clob
);

create table Repository (
	uuid_ varchar(75),
	repositoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	name varchar(75),
	description varchar(4000),
	portletId varchar(75),
	typeSettings clob,
	dlFolderId bigint
);

create table RepositoryEntry (
	uuid_ varchar(75),
	repositoryEntryId bigint not null primary key,
	groupId bigint,
	repositoryId bigint,
	mappedId varchar(75)
);

create table ResourceBlock (
	resourceBlockId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	name varchar(75),
	permissionsHash varchar(75),
	referenceCount bigint
);

create table ResourceBlockPermission (
	resourceBlockPermissionId bigint not null primary key,
	resourceBlockId bigint,
	roleId bigint,
	actionIds bigint
);

drop index IX_8D83D0CE;

alter table ResourcePermission add ownerId bigint;

create table ResourceTypePermission (
	resourceTypePermissionId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	name varchar(75),
	roleId bigint,
	actionIds bigint
);

create table SocialActivityAchievement (
	activityAchievementId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate bigint,
	name varchar(75),
	firstInGroup smallint
);

create table SocialActivityCounter (
	activityCounterId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	classPK bigint,
	name varchar(75),
	ownerType integer,
	currentValue integer,
	totalValue integer,
	graceValue integer,
	startPeriod integer,
	endPeriod integer
);

create table SocialActivityLimit (
	activityLimitId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	classNameId bigint,
	classPK bigint,
	activityType integer,
	activityCounterName varchar(75),
	value varchar(75)
);

create table SocialActivitySetting (
	activitySettingId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	activityType integer,
	name varchar(75),
	value varchar(1024)
);

update Role_ set name = 'Site Administrator' where name = 'Community Administrator';
update Role_ set name = 'Site Content Reviewer' where name = 'Community Content Reviewer';
update Role_ set name = 'Site Member' where name = 'Community Member';
update Role_ set name = 'Site Owner' where name = 'Community Owner';
update Role_ set name = 'Organization User' where name = 'Organization Member';

alter table Ticket add type_ integer;
alter table Ticket add extraInfo clob;

commit;

update Ticket set type_ = 3;

alter table User_ add emailAddressVerified smallint;
alter table User_ add status int;

commit;

update User_ set emailAddressVerified = 1;
update User_ set status = 0;
update User_ set status = 5 where active_ = 0;

alter table User_ drop column active_;

alter table UserGroup add addedByLDAPImport smallint;

create table UserGroups_Teams (
	userGroupId bigint not null,
	teamId bigint not null,
	primary key (userGroupId, teamId)
);

create table UserNotificationEvent (
	uuid_ varchar(75),
	userNotificationEventId bigint not null primary key,
	companyId bigint,
	userId bigint,
	type_ varchar(75),
	timestamp bigint,
	deliverBy bigint,
	payload clob,
	archived smallint
);

create table VirtualHost (
	virtualHostId bigint not null primary key,
	companyId bigint,
	layoutSetId bigint,
	hostname varchar(75)
);

alter table WorkflowDefinitionLink add classPK bigint;
alter table WorkflowDefinitionLink add typePK bigint;

commit;

update WorkflowDefinitionLink set classPK = 0;
update WorkflowDefinitionLink set typePK = 0;

drop table QUARTZ_BLOB_TRIGGERS;
drop table QUARTZ_CALENDARS;
drop table QUARTZ_CRON_TRIGGERS;
drop table QUARTZ_FIRED_TRIGGERS;
drop table QUARTZ_JOB_DETAILS;
drop table QUARTZ_JOB_LISTENERS;
drop table QUARTZ_LOCKS;
drop table QUARTZ_PAUSED_TRIGGER_GRPS;
drop table QUARTZ_SCHEDULER_STATE;
drop table QUARTZ_SIMPLE_TRIGGERS;
drop table QUARTZ_TRIGGERS;
drop table QUARTZ_TRIGGER_LISTENERS;

create table QUARTZ_BLOB_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	BLOB_DATA blob,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table QUARTZ_CALENDARS (
	SCHED_NAME varchar(120) not null,
	CALENDAR_NAME varchar(200) not null,
	CALENDAR blob not null,
	primary key (SCHED_NAME,CALENDAR_NAME)
);

create table QUARTZ_CRON_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	CRON_EXPRESSION varchar(200) not null,
	TIME_ZONE_ID varchar(80),
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table QUARTZ_FIRED_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	ENTRY_ID varchar(95) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	INSTANCE_NAME varchar(200) not null,
	FIRED_TIME bigint not null,
	PRIORITY integer not null,
	STATE varchar(16) not null,
	JOB_NAME varchar(200),
	JOB_GROUP varchar(200),
	IS_NONCONCURRENT smallint NULL,
	REQUESTS_RECOVERY smallint NULL,
	primary key (SCHED_NAME, ENTRY_ID)
);

create table QUARTZ_JOB_DETAILS (
	SCHED_NAME varchar(120) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250),
	JOB_CLASS_NAME varchar(250) not null,
	IS_DURABLE smallint not null,
	IS_NONCONCURRENT smallint not null,
	IS_UPDATE_DATA smallint not null,
	REQUESTS_RECOVERY smallint not null,
	JOB_DATA blob,
	primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
);

create table QUARTZ_LOCKS (
	SCHED_NAME varchar(120) not null,
	LOCK_NAME varchar(40) not null ,
	primary key (SCHED_NAME, LOCK_NAME)
);

create table QUARTZ_PAUSED_TRIGGER_GRPS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_GROUP varchar(200) not null,
	primary key (SCHED_NAME, TRIGGER_GROUP)
);

create table QUARTZ_SCHEDULER_STATE (
	SCHED_NAME varchar(120) not null,
	INSTANCE_NAME varchar(200) not null,
	LAST_CHECKIN_TIME bigint not null,
	CHECKIN_INTERVAL bigint not null,
	primary key (SCHED_NAME, INSTANCE_NAME)
);

create table QUARTZ_SIMPLE_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	REPEAT_COUNT bigint not null,
	REPEAT_INTERVAL bigint not null,
	TIMES_TRIGGERED bigint not null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table QUARTZ_SIMPROP_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	STR_PROP_1 varchar(512),
	STR_PROP_2 varchar(512),
	STR_PROP_3 varchar(512),
	INT_PROP_1 integer,
	INT_PROP_2 integer,
	LONG_PROP_1 bigint,
	LONG_PROP_2 bigint,
	DEC_PROP_1 NUMERIC(13,4),
	DEC_PROP_2 NUMERIC(13,4),
	BOOL_PROP_1 smallint,
	BOOL_PROP_2 smallint,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table QUARTZ_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250),
	NEXT_FIRE_TIME bigint,
	PREV_FIRE_TIME bigint,
	PRIORITY integer,
	TRIGGER_STATE varchar(16) not null,
	TRIGGER_TYPE varchar(8) not null,
	START_TIME bigint not null,
	END_TIME bigint,
	CALENDAR_NAME varchar(200),
	MISFIRE_INSTR integer,
	JOB_DATA blob,
	primary key  (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

commit;

create index IX_88328984 on QUARTZ_JOB_DETAILS (SCHED_NAME, JOB_GROUP);
create index IX_779BCA37 on QUARTZ_JOB_DETAILS (SCHED_NAME, REQUESTS_RECOVERY);

create index IX_BE3835E5 on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
create index IX_4BD722BM on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_GROUP);
create index IX_204D31E8 on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME);
create index IX_339E078M on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME, REQUESTS_RECOVERY);
create index IX_5005E3AF on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);
create index IX_BC2F03B0 on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_GROUP);

create index IX_186442A4 on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_STATE);
create index IX_1BA1F9DC on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP);
create index IX_91CA7CCE on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP, NEXT_FIRE_TIME, TRIGGER_STATE, MISFIRE_INSTR);
create index IX_D219AFDE on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP, TRIGGER_STATE);
create index IX_A85822A0 on QUARTZ_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);
create index IX_8AA50BE1 on QUARTZ_TRIGGERS (SCHED_NAME, JOB_GROUP);
create index IX_EEFE382A on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME);
create index IX_F026CF4C on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME, TRIGGER_STATE);
create index IX_F2DD7C7E on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME, TRIGGER_STATE, MISFIRE_INSTR);
create index IX_1F92813C on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME, MISFIRE_INSTR);
create index IX_99108B6E on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_STATE);
create index IX_CD7132D0 on QUARTZ_TRIGGERS (SCHED_NAME, CALENDAR_NAME);

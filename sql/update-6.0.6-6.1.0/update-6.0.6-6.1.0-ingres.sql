alter table AssetCategory add description varchar(1000) null;

alter table AssetEntry add classTypeId bigint;
alter table AssetEntry add layoutUuid varchar(75) null;

update AssetEntry set classUuid = (select uuid_ from JournalArticleResource where AssetEntry.classPK = JournalArticleResource.resourcePrimKey) where visible = 1 and classNameId = (select classNameId from ClassName_ where value = 'com.liferay.portlet.journal.model.JournalArticle');

alter table BlogsEntry add description varchar(1000) null;
alter table BlogsEntry add smallImage tinyint;
alter table BlogsEntry add smallImageId bigint;
alter table BlogsEntry add smallImageURL varchar(1000) null;

alter table BookmarksEntry add userName varchar(75) null;
alter table BookmarksEntry add resourceBlockId bigint;
alter table BookmarksEntry add description varchar(1000) null;

commit;\g

update BookmarksEntry set description = comments;

alter table BookmarksEntry drop column comments;

alter table BookmarksFolder add userName varchar(75) null;
alter table BookmarksFolder add resourceBlockId bigint;

alter table CalEvent add location varchar(1000) null;

update ClassName_ set value = 'com.liferay.portal.model.UserPersonalSite' where value = 'com.liferay.portal.model.UserPersonalCommunity';

drop index IX_975996C0;

alter table Company add active_ tinyint;

commit;\g

update Company set active_ = 1;

alter table Country add zipRequired tinyint;

commit;\g

update Country set zipRequired = 1;

create table DDLRecord (
	uuid_ varchar(75) null,
	recordId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	versionUserId bigint,
	versionUserName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	DDMStorageId bigint,
	recordSetId bigint,
	version varchar(75) null,
	displayIndex integer
);

create table DDLRecordSet (
	uuid_ varchar(75) null,
	recordSetId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	DDMStructureId bigint,
	recordSetKey varchar(75) null,
	name varchar(1000) null,
	description varchar(1000) null,
	minDisplayRows integer,
	scope integer
);

create table DDLRecordVersion (
	recordVersionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	DDMStorageId bigint,
	recordSetId bigint,
	recordId bigint,
	version varchar(75) null,
	displayIndex integer,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate timestamp null
);

create table DDMContent (
	uuid_ varchar(75) null,
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name varchar(1000) null,
	description varchar(1000) null,
	xml long varchar null
);

create table DDMStorageLink (
	uuid_ varchar(75) null,
	storageLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
);

create table DDMStructure (
	uuid_ varchar(75) null,
	structureId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId bigint,
	structureKey varchar(75) null,
	name varchar(1000) null,
	description varchar(1000) null,
	xsd long varchar null,
	storageType varchar(75) null,
	type_ integer
);

create table DDMStructureLink (
	structureLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
);

create table DDMTemplate (
	uuid_ varchar(75) null,
	templateId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	structureId bigint,
	name varchar(1000) null,
	description varchar(1000) null,
	type_ varchar(75) null,
	mode_ varchar(75) null,
	language varchar(75) null,
	script long varchar null
);

create table DLContent (
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	repositoryId bigint,
	path_ varchar(255) null,
	version varchar(75) null,
	data_ blob,
	size_ bigint
);

create table DLFileEntryMetadata (
	uuid_ varchar(75) null,
	fileEntryMetadataId bigint not null primary key,
	DDMStorageId bigint,
	DDMStructureId bigint,
	fileEntryTypeId bigint,
	fileEntryId bigint,
	fileVersionId bigint
);

create table DLFileEntryType (
	uuid_ varchar(75) null,
	fileEntryTypeId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name varchar(75) null,
	description varchar(1000) null
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
alter table DLFileEntry add mimeType varchar(75) null;
alter table DLFileEntry add fileEntryTypeId bigint;
alter table DLFileEntry add smallImageId bigint;
alter table DLFileEntry add largeImageId bigint;
alter table DLFileEntry add custom1ImageId bigint;
alter table DLFileEntry add custom2ImageId bigint;

commit;\g

update DLFileEntry set repositoryId = groupId;

drop index IX_CE705D48;
drop index IX_40B56512;

alter table DLFileRank add fileEntryId bigint;

drop index IX_55C736AC;
drop index IX_346A0992;

alter table DLFileShortcut add repositoryId bigint;
alter table DLFileShortcut add toFileEntryId bigint;

commit;\g

update DLFileShortcut set repositoryId = groupId;

drop index IX_B413F1EC;
drop index IX_94E784D2;
drop index IX_2F8FED9C;

alter table DLFileVersion add modifiedDate timestamp null;
alter table DLFileVersion add repositoryId bigint;
alter table DLFileVersion add fileEntryId bigint;
alter table DLFileVersion add mimeType varchar(75) null;
alter table DLFileVersion add fileEntryTypeId bigint;

commit;\g

update DLFileVersion set modifiedDate = statusDate;
update DLFileVersion set repositoryId = groupId;

alter table DLFolder add repositoryId bigint;
alter table DLFolder add mountPoint tinyint;
alter table DLFolder add defaultFileEntryTypeId bigint;
alter table DLFolder add overrideFileEntryTypes tinyint;

commit;\g

update DLFolder set repositoryId = groupId;
update DLFolder set mountPoint = 0;

create table DLSync (
	syncId bigint not null primary key,
	companyId bigint,
	createDate timestamp null,
	modifiedDate timestamp null,
	fileId bigint,
	fileUuid varchar(75) null,
	repositoryId bigint,
	parentFolderId bigint,
	name varchar(255) null,
	event varchar(75) null,
	type_ varchar(75) null,
	version varchar(75) null
);

alter table Group_ add site tinyint;

update Group_ set name = 'User Personal Site' where name = 'User Personal Community';
update Group_ set type_ = 3 where classNameId = (select classNameId from ClassName_ where value = 'com.liferay.portal.model.Organization');

alter table IGFolder add userName varchar(75) null;

alter table IGImage add userName varchar(75) null;

alter table JournalArticle add classNameId bigint null;
alter table JournalArticle add classPK bigint null;
alter table JournalArticle add layoutUuid varchar(75) null;

commit;\g

update JournalArticle set classNameId = 0;
update JournalArticle set classPK = 0;

drop index IX_FAD05595;

alter table Layout drop column layoutPrototypeId;
alter table Layout drop column dlFolderId;

alter table Layout add createDate timestamp null;
alter table Layout add modifiedDate timestamp null;
alter table Layout add keywords varchar(1000) null;
alter table Layout add robots varchar(1000) null;
alter table Layout add layoutPrototypeUuid varchar(75) null;
alter table Layout add layoutPrototypeLinkEnabled tinyint null;
alter table Layout add sourcePrototypeLayoutUuid varchar(75) null;

commit;\g

update Layout set createDate = date('now');
update Layout set modifiedDate = date('now');

create table LayoutBranch (
	LayoutBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	layoutSetBranchId bigint,
	plid bigint,
	name varchar(75) null,
	description varchar(1000) null,
	master tinyint
);

alter table LayoutPrototype add uuid_ varchar(75) null;

create table LayoutRevision (
	layoutRevisionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	layoutSetBranchId bigint,
	layoutBranchId bigint,
	parentLayoutRevisionId bigint,
	head tinyint,
	major tinyint,
	plid bigint,
	privateLayout tinyint,
	name varchar(1000) null,
	title varchar(1000) null,
	description varchar(1000) null,
	keywords varchar(1000) null,
	robots varchar(1000) null,
	typeSettings long varchar null,
	iconImage tinyint,
	iconImageId bigint,
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css varchar(1000) null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate timestamp null
);

alter table LayoutSet drop column layoutSetPrototypeId;

alter table LayoutSet add createDate timestamp null;
alter table LayoutSet add modifiedDate timestamp null;
alter table LayoutSet add layoutSetPrototypeUuid varchar(75) null;
alter table LayoutSet add layoutSetPrototypeLinkEnabled tinyint null;

drop index IX_5ABC2905;

commit;\g

update LayoutSet set createDate = date('now');
update LayoutSet set modifiedDate = date('now');

create table LayoutSetBranch (
	layoutSetBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	privateLayout tinyint,
	name varchar(75) null,
	description varchar(1000) null,
	master tinyint
);

alter table LayoutSetPrototype add createDate timestamp null;
alter table LayoutSetPrototype add modifiedDate timestamp null;
alter table LayoutSetPrototype add uuid_ varchar(75) null;

commit;\g

update LayoutSetPrototype set createDate = date('now');
update LayoutSetPrototype set modifiedDate = date('now');

alter table MBCategory add displayStyle varchar(75) null;

commit;\g

update MBCategory set displayStyle = 'default';

alter table MBMailingList add allowAnonymous tinyint;

alter table MBMessage add format varchar(75) null;
alter table MBMessage add answer tinyint;

commit;\g

update MBMessage set format = 'bbcode';

alter table MBThread add companyId bigint;
alter table MBThread add rootMessageUserId bigint;
alter table MBThread add question tinyint;

create table MBThreadFlag (
	threadFlagId bigint not null primary key,
	userId bigint,
	modifiedDate timestamp null,
	threadId bigint
);

create table MDRAction (
	uuid_ varchar(75) null,
	actionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId bigint,
	classPK bigint,
	ruleGroupInstanceId bigint,
	name varchar(1000) null,
	description varchar(1000) null,
	type_ varchar(255) null,
	typeSettings long varchar null
);

create table MDRRule (
	uuid_ varchar(75) null,
	ruleId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	ruleGroupId bigint,
	name varchar(1000) null,
	description varchar(1000) null,
	type_ varchar(255) null,
	typeSettings long varchar null
);

create table MDRRuleGroup (
	uuid_ varchar(75) null,
	ruleGroupId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name varchar(1000) null,
	description varchar(1000) null
);

create table MDRRuleGroupInstance (
	uuid_ varchar(75) null,
	ruleGroupInstanceId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId bigint,
	classPK bigint,
	ruleGroupId bigint,
	priority integer
);

alter table Organization_ drop column leftOrganizationId;
alter table Organization_ drop column rightOrganizationId;

alter table Organization_ add treePath varchar(1000) null;

alter table PollsVote add companyId bigint;
alter table PollsVote add userName varchar(75) null;
alter table PollsVote add createDate timestamp null;
alter table PollsVote add modifiedDate timestamp null;

create table PortalPreferences (
	portalPreferencesId bigint not null primary key,
	ownerId bigint,
	ownerType integer,
	preferences long varchar null
);

create table Repository (
	uuid_ varchar(75) null,
	repositoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId bigint,
	name varchar(75) null,
	description varchar(1000) null,
	portletId varchar(75) null,
	typeSettings long varchar null,
	dlFolderId bigint
);

create table RepositoryEntry (
	uuid_ varchar(75) null,
	repositoryEntryId bigint not null primary key,
	groupId bigint,
	repositoryId bigint,
	mappedId varchar(75) null
);

create table ResourceBlock (
	resourceBlockId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	name varchar(75) null,
	permissionsHash varchar(75) null,
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
	name varchar(75) null,
	roleId bigint,
	actionIds bigint
);

create table SocialActivityAchievement (
	activityAchievementId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate bigint,
	name varchar(75) null,
	firstInGroup tinyint
);

create table SocialActivityCounter (
	activityCounterId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	classPK bigint,
	name varchar(75) null,
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
	activityCounterName varchar(75) null,
	value varchar(75) null
);

create table SocialActivitySetting (
	activitySettingId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	activityType integer,
	name varchar(75) null,
	value varchar(1024) null
);

update Role_ set name = 'Site Administrator' where name = 'Community Administrator';
update Role_ set name = 'Site Content Reviewer' where name = 'Community Content Reviewer';
update Role_ set name = 'Site Member' where name = 'Community Member';
update Role_ set name = 'Site Owner' where name = 'Community Owner';
update Role_ set name = 'Organization User' where name = 'Organization Member';

alter table Ticket add type_ integer;
alter table Ticket add extraInfo long varchar null;

commit;\g

update Ticket set type_ = 3;

alter table User_ add emailAddressVerified tinyint;
alter table User_ add status int;

commit;\g

update User_ set emailAddressVerified = 1;
update User_ set status = 0;
update User_ set status = 5 where active_ = 0;

alter table User_ drop column active_;

alter table UserGroup add addedByLDAPImport tinyint;

create table UserGroups_Teams (
	userGroupId bigint not null,
	teamId bigint not null,
	primary key (userGroupId, teamId)
);

create table UserNotificationEvent (
	uuid_ varchar(75) null,
	userNotificationEventId bigint not null primary key,
	companyId bigint,
	userId bigint,
	type_ varchar(75) null,
	timestamp bigint,
	deliverBy bigint,
	payload long varchar null,
	archived tinyint
);

create table VirtualHost (
	virtualHostId bigint not null primary key,
	companyId bigint,
	layoutSetId bigint,
	hostname varchar(75) null
);

alter table WorkflowDefinitionLink add classPK bigint;
alter table WorkflowDefinitionLink add typePK bigint;

commit;\g

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
	BLOB_DATA blob null,
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
	JOB_NAME varchar(200) null,
	JOB_GROUP varchar(200) null,
	IS_NONCONCURRENT tinyint NULL,
	REQUESTS_RECOVERY tinyint NULL,
	primary key (SCHED_NAME, ENTRY_ID)
);

create table QUARTZ_JOB_DETAILS (
	SCHED_NAME varchar(120) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250) null,
	JOB_CLASS_NAME varchar(250) not null,
	IS_DURABLE tinyint not null,
	IS_NONCONCURRENT tinyint not null,
	IS_UPDATE_DATA tinyint not null,
	REQUESTS_RECOVERY tinyint not null,
	JOB_DATA blob null,
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
	STR_PROP_1 varchar(512) null,
	STR_PROP_2 varchar(512) null,
	STR_PROP_3 varchar(512) null,
	INT_PROP_1 integer null,
	INT_PROP_2 integer null,
	LONG_PROP_1 bigint null,
	LONG_PROP_2 bigint null,
	DEC_PROP_1 NUMERIC(13,4) null,
	DEC_PROP_2 NUMERIC(13,4) null,
	BOOL_PROP_1 tinyint null,
	BOOL_PROP_2 tinyint null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table QUARTZ_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250) null,
	NEXT_FIRE_TIME bigint null,
	PREV_FIRE_TIME bigint null,
	PRIORITY integer null,
	TRIGGER_STATE varchar(16) not null,
	TRIGGER_TYPE varchar(8) not null,
	START_TIME bigint not null,
	END_TIME bigint null,
	CALENDAR_NAME varchar(200) null,
	MISFIRE_INSTR integer null,
	JOB_DATA blob null,
	primary key  (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

commit;\g

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

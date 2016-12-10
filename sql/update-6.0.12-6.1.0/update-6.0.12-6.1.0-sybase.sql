alter table AssetCategory add description varchar(1000) null;

alter table AssetEntry add classTypeId decimal(20,0)
go
alter table AssetEntry add layoutUuid varchar(75) null;

update AssetEntry set classUuid = (select uuid_ from JournalArticleResource where AssetEntry.classPK = JournalArticleResource.resourcePrimKey) where visible = 1 and classNameId = (select classNameId from ClassName_ where value = 'com.liferay.portlet.journal.model.JournalArticle')
go

alter table BlogsEntry add description varchar(1000) null;
alter table BlogsEntry add smallImage int;
alter table BlogsEntry add smallImageId decimal(20,0)
go
alter table BlogsEntry add smallImageURL varchar(1000) null;

alter table BookmarksEntry add resourceBlockId decimal(20,0)
go
alter table BookmarksEntry add description varchar(1000) null;

go

update BookmarksEntry set description = comments;

alter table BookmarksEntry drop comments;

alter table BookmarksFolder add resourceBlockId decimal(20,0)
go

alter table CalEvent add location varchar(1000) null;

update ClassName_ set value = 'com.liferay.portal.model.UserPersonalSite' where value = 'com.liferay.portal.model.UserPersonalCommunity';

alter table Company add active_ int;

go

update Company set active_ = 1;

alter table Country add zipRequired int;

go

update Country set zipRequired = 1;

create table DDLRecord (
	uuid_ varchar(75) null,
	recordId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	versionUserId decimal(20,0),
	versionUserName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	DDMStorageId decimal(20,0),
	recordSetId decimal(20,0),
	version varchar(75) null,
	displayIndex int
)
go

create table DDLRecordSet (
	uuid_ varchar(75) null,
	recordSetId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	DDMStructureId decimal(20,0),
	recordSetKey varchar(75) null,
	name varchar(1000) null,
	description varchar(1000) null,
	minDisplayRows int,
	scope int
)
go

create table DDLRecordVersion (
	recordVersionId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	DDMStorageId decimal(20,0),
	recordSetId decimal(20,0),
	recordId decimal(20,0),
	version varchar(75) null,
	displayIndex int,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table DDMContent (
	uuid_ varchar(75) null,
	contentId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(1000) null,
	description varchar(1000) null,
	xml text null
)
go

create table DDMStorageLink (
	uuid_ varchar(75) null,
	storageLinkId decimal(20,0) not null primary key,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	structureId decimal(20,0)
)
go

create table DDMStructure (
	uuid_ varchar(75) null,
	structureId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	structureKey varchar(75) null,
	name varchar(1000) null,
	description varchar(1000) null,
	xsd text null,
	storageType varchar(75) null,
	type_ int
)
go

create table DDMStructureLink (
	structureLinkId decimal(20,0) not null primary key,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	structureId decimal(20,0)
)
go

create table DDMTemplate (
	uuid_ varchar(75) null,
	templateId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	structureId decimal(20,0),
	name varchar(1000) null,
	description varchar(1000) null,
	type_ varchar(75) null,
	mode_ varchar(75) null,
	language varchar(75) null,
	script text null
)
go

create table DLContent (
	contentId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	repositoryId decimal(20,0),
	path_ varchar(255) null,
	version varchar(75) null,
	data_ image,
	size_ decimal(20,0)
)
go

create table DLFileEntryMetadata (
	uuid_ varchar(75) null,
	fileEntryMetadataId decimal(20,0) not null primary key,
	DDMStorageId decimal(20,0),
	DDMStructureId decimal(20,0),
	fileEntryTypeId decimal(20,0),
	fileEntryId decimal(20,0),
	fileVersionId decimal(20,0)
)
go

create table DLFileEntryType (
	uuid_ varchar(75) null,
	fileEntryTypeId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	description varchar(1000) null
)
go

create table DLFileEntryTypes_DDMStructures (
	fileEntryTypeId decimal(20,0) not null,
	structureId decimal(20,0) not null,
	primary key (fileEntryTypeId, structureId)
)
go

create table DLFileEntryTypes_DLFolders (
	fileEntryTypeId decimal(20,0) not null,
	folderId decimal(20,0) not null,
	primary key (fileEntryTypeId, folderId)
)
go

alter table DLFileEntry add repositoryId decimal(20,0)
go
alter table DLFileEntry add fileEntryTypeId decimal(20,0)
go
alter table DLFileEntry add smallImageId decimal(20,0)
go
alter table DLFileEntry add largeImageId decimal(20,0)
go
alter table DLFileEntry add custom1ImageId decimal(20,0)
go
alter table DLFileEntry add custom2ImageId decimal(20,0)
go

go

update DLFileEntry set repositoryId = groupId;

alter table DLFileShortcut add repositoryId decimal(20,0)
go

go

update DLFileShortcut set repositoryId = groupId;

alter table DLFileVersion add modifiedDate datetime null;
alter table DLFileVersion add repositoryId decimal(20,0)
go
alter table DLFileVersion add folderId decimal(20,0)
go
alter table DLFileVersion add fileEntryTypeId decimal(20,0)
go

go

update DLFileVersion set modifiedDate = statusDate;
update DLFileVersion set repositoryId = groupId;

alter table DLFolder add repositoryId decimal(20,0)
go
alter table DLFolder add mountPoint int;
alter table DLFolder add defaultFileEntryTypeId decimal(20,0)
go
alter table DLFolder add overrideFileEntryTypes int;

go

update DLFolder set repositoryId = groupId;
update DLFolder set mountPoint = 0;

create table DLSync (
	syncId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	createDate datetime null,
	modifiedDate datetime null,
	fileId decimal(20,0),
	fileUuid varchar(75) null,
	repositoryId decimal(20,0),
	parentFolderId decimal(20,0),
	name varchar(255) null,
	event varchar(75) null,
	type_ varchar(75) null,
	version varchar(75) null
)
go

alter table Group_ add site int;

update Group_ set name = 'User Personal Site' where name = 'User Personal Community';
update Group_ set type_ = 3 where classNameId = (select classNameId from ClassName_ where value = 'com.liferay.portal.model.Organization')
go

alter table JournalArticle add classNameId decimal(20,0) null;
alter table JournalArticle add classPK decimal(20,0) null;
alter table JournalArticle add layoutUuid varchar(75) null;

go

update JournalArticle set classNameId = 0;
update JournalArticle set classPK = 0;

drop index Layout.IX_FAD05595;

alter table Layout drop layoutPrototypeId;
alter table Layout drop dlFolderId;

alter table Layout add createDate datetime null;
alter table Layout add modifiedDate datetime null;
alter table Layout add keywords varchar(1000) null;
alter table Layout add robots varchar(1000) null;
alter table Layout add layoutPrototypeUuid varchar(75) null;
alter table Layout add layoutPrototypeLinkEnabled int null;
alter table Layout add sourcePrototypeLayoutUuid varchar(75) null;

go

update Layout set createDate = getdate()
go
update Layout set modifiedDate = getdate()
go

create table LayoutBranch (
	LayoutBranchId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	layoutSetBranchId decimal(20,0),
	plid decimal(20,0),
	name varchar(75) null,
	description varchar(1000) null,
	master int
)
go

alter table LayoutPrototype add uuid_ varchar(75) null;

create table LayoutRevision (
	layoutRevisionId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	layoutSetBranchId decimal(20,0),
	layoutBranchId decimal(20,0),
	parentLayoutRevisionId decimal(20,0),
	head int,
	major int,
	plid decimal(20,0),
	privateLayout int,
	name varchar(1000) null,
	title varchar(1000) null,
	description varchar(1000) null,
	keywords varchar(1000) null,
	robots varchar(1000) null,
	typeSettings text null,
	iconImage int,
	iconImageId decimal(20,0),
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css varchar(1000) null,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

alter table LayoutSet drop layoutSetPrototypeId;

alter table LayoutSet add createDate datetime null;
alter table LayoutSet add modifiedDate datetime null;
alter table LayoutSet add layoutSetPrototypeUuid varchar(75) null;
alter table LayoutSet add layoutSetPrototypeLinkEnabled int null;

go

update LayoutSet set createDate = getdate()
go
update LayoutSet set modifiedDate = getdate()
go

create table LayoutSetBranch (
	layoutSetBranchId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	privateLayout int,
	name varchar(75) null,
	description varchar(1000) null,
	master int
)
go

alter table LayoutSetPrototype add uuid_ varchar(75) null;
alter table LayoutSetPrototype add createDate datetime null;
alter table LayoutSetPrototype add modifiedDate datetime null;

go

update LayoutSetPrototype set createDate = getdate()
go
update LayoutSetPrototype set modifiedDate = getdate()
go

alter table MBCategory add displayStyle varchar(75) null;

go

update MBCategory set displayStyle = 'default';

alter table MBMessage add format varchar(75) null;
alter table MBMessage add answer int;

go

update MBMessage set format = 'bbcode';

alter table MBThread add question int;

create table MBThreadFlag (
	threadFlagId decimal(20,0) not null primary key,
	userId decimal(20,0),
	modifiedDate datetime null,
	threadId decimal(20,0)
)
go

create table MDRAction (
	uuid_ varchar(75) null,
	actionId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	ruleGroupInstanceId decimal(20,0),
	name varchar(1000) null,
	description varchar(1000) null,
	type_ varchar(255) null,
	typeSettings text null
)
go

create table MDRRule (
	uuid_ varchar(75) null,
	ruleId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	ruleGroupId decimal(20,0),
	name varchar(1000) null,
	description varchar(1000) null,
	type_ varchar(255) null,
	typeSettings text null
)
go

create table MDRRuleGroup (
	uuid_ varchar(75) null,
	ruleGroupId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(1000) null,
	description varchar(1000) null
)
go

create table MDRRuleGroupInstance (
	uuid_ varchar(75) null,
	ruleGroupInstanceId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	ruleGroupId decimal(20,0),
	priority int
)
go

alter table Organization_ drop leftOrganizationId;
alter table Organization_ drop rightOrganizationId;

alter table Organization_ add treePath varchar(1000) null;

alter table PollsVote add companyId decimal(20,0)
go
alter table PollsVote add userName varchar(75) null;
alter table PollsVote add createDate datetime null;
alter table PollsVote add modifiedDate datetime null;

create table Repository (
	uuid_ varchar(75) null,
	repositoryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	name varchar(75) null,
	description varchar(1000) null,
	portletId varchar(75) null,
	typeSettings text null,
	dlFolderId decimal(20,0)
)
go

create table RepositoryEntry (
	uuid_ varchar(75) null,
	repositoryEntryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	repositoryId decimal(20,0),
	mappedId varchar(75) null
)
go

create table ResourceBlock (
	resourceBlockId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	groupId decimal(20,0),
	name varchar(75) null,
	permissionsHash varchar(75) null,
	referenceCount decimal(20,0)
)
go

create table ResourceBlockPermission (
	resourceBlockPermissionId decimal(20,0) not null primary key,
	resourceBlockId decimal(20,0),
	roleId decimal(20,0),
	actionIds decimal(20,0)
)
go

drop index ResourcePermission.IX_8D83D0CE;
drop index ResourcePermission.IX_4A1F4402;

create table ResourceTypePermission (
	resourceTypePermissionId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	groupId decimal(20,0),
	name varchar(75) null,
	roleId decimal(20,0),
	actionIds decimal(20,0)
)
go

create table SocialActivityAchievement (
	activityAchievementId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	createDate decimal(20,0),
	name varchar(75) null,
	firstInGroup int
)
go

create table SocialActivityCounter (
	activityCounterId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0),
	name varchar(75) null,
	ownerType int,
	currentValue int,
	totalValue int,
	graceValue int,
	startPeriod int,
	endPeriod int
)
go

create table SocialActivityLimit (
	activityLimitId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0),
	activityType int,
	activityCounterName varchar(75) null,
	value varchar(75) null
)
go

create table SocialActivitySetting (
	activitySettingId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	classNameId decimal(20,0),
	activityType int,
	name varchar(75) null,
	value varchar(1024) null
)
go

update Role_ set name = 'Site Administrator' where name = 'Community Administrator';
update Role_ set name = 'Site Content Reviewer' where name = 'Community Content Reviewer';
update Role_ set name = 'Site Member' where name = 'Community Member';
update Role_ set name = 'Site Owner' where name = 'Community Owner';
update Role_ set name = 'Organization User' where name = 'Organization Member';

alter table User_ add emailAddressVerified int;
alter table User_ add status int;

go

update User_ set emailAddressVerified = 1;
update User_ set status = 0;
update User_ set status = 5 where active_ = 0;

alter table User_ drop active_;

alter table UserGroup add addedByLDAPImport int;

alter table UserNotificationEvent add archived int;

alter table WorkflowDefinitionLink add classPK decimal(20,0)
go
alter table WorkflowDefinitionLink add typePK decimal(20,0)
go

go

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
	BLOB_DATA image null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
go

create table QUARTZ_CALENDARS (
	SCHED_NAME varchar(120) not null,
	CALENDAR_NAME varchar(200) not null,
	CALENDAR image not null,
	primary key (SCHED_NAME,CALENDAR_NAME)
)
go

create table QUARTZ_CRON_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	CRON_EXPRESSION varchar(200) not null,
	TIME_ZONE_ID varchar(80),
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
go

create table QUARTZ_FIRED_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	ENTRY_ID varchar(95) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	INSTANCE_NAME varchar(200) not null,
	FIRED_TIME decimal(20,0) not null,
	PRIORITY int not null,
	STATE varchar(16) not null,
	JOB_NAME varchar(200) null,
	JOB_GROUP varchar(200) null,
	IS_NONCONCURRENT int NULL,
	REQUESTS_RECOVERY int NULL,
	primary key (SCHED_NAME, ENTRY_ID)
)
go

create table QUARTZ_JOB_DETAILS (
	SCHED_NAME varchar(120) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250) null,
	JOB_CLASS_NAME varchar(250) not null,
	IS_DURABLE int not null,
	IS_NONCONCURRENT int not null,
	IS_UPDATE_DATA int not null,
	REQUESTS_RECOVERY int not null,
	JOB_DATA image null,
	primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
)
go

create table QUARTZ_LOCKS (
	SCHED_NAME varchar(120) not null,
	LOCK_NAME varchar(40) not null ,
	primary key (SCHED_NAME, LOCK_NAME)
)
go

create table QUARTZ_PAUSED_TRIGGER_GRPS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_GROUP varchar(200) not null,
	primary key (SCHED_NAME, TRIGGER_GROUP)
)
go

create table QUARTZ_SCHEDULER_STATE (
	SCHED_NAME varchar(120) not null,
	INSTANCE_NAME varchar(200) not null,
	LAST_CHECKIN_TIME decimal(20,0) not null,
	CHECKIN_INTERVAL decimal(20,0) not null,
	primary key (SCHED_NAME, INSTANCE_NAME)
)
go

create table QUARTZ_SIMPLE_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	REPEAT_COUNT decimal(20,0) not null,
	REPEAT_INTERVAL decimal(20,0) not null,
	TIMES_TRIGGERED decimal(20,0) not null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
go

create table QUARTZ_SIMPROP_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	STR_PROP_1 varchar(512) null,
	STR_PROP_2 varchar(512) null,
	STR_PROP_3 varchar(512) null,
	INT_PROP_1 int null,
	INT_PROP_2 int null,
	LONG_PROP_1 decimal(20,0) null,
	LONG_PROP_2 decimal(20,0) null,
	DEC_PROP_1 NUMERIC(13,4) null,
	DEC_PROP_2 NUMERIC(13,4) null,
	BOOL_PROP_1 int null,
	BOOL_PROP_2 int null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
go

create table QUARTZ_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250) null,
	NEXT_FIRE_TIME decimal(20,0) null,
	PREV_FIRE_TIME decimal(20,0) null,
	PRIORITY int null,
	TRIGGER_STATE varchar(16) not null,
	TRIGGER_TYPE varchar(8) not null,
	START_TIME decimal(20,0) not null,
	END_TIME decimal(20,0) null,
	CALENDAR_NAME varchar(200) null,
	MISFIRE_INSTR int null,
	JOB_DATA image null,
	primary key  (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
go

go

create index IX_88328984 on QUARTZ_JOB_DETAILS (SCHED_NAME, JOB_GROUP)
go
create index IX_779BCA37 on QUARTZ_JOB_DETAILS (SCHED_NAME, REQUESTS_RECOVERY)
go

create index IX_BE3835E5 on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
go
create index IX_4BD722BM on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_GROUP)
go
create index IX_204D31E8 on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME)
go
create index IX_339E078M on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME, REQUESTS_RECOVERY)
go
create index IX_5005E3AF on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP)
go
create index IX_BC2F03B0 on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_GROUP)
go

create index IX_186442A4 on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_STATE)
go
create index IX_1BA1F9DC on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP)
go
create index IX_91CA7CCE on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP, NEXT_FIRE_TIME, TRIGGER_STATE, MISFIRE_INSTR)
go
create index IX_D219AFDE on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP, TRIGGER_STATE)
go
create index IX_A85822A0 on QUARTZ_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP)
go
create index IX_8AA50BE1 on QUARTZ_TRIGGERS (SCHED_NAME, JOB_GROUP)
go
create index IX_EEFE382A on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME)
go
create index IX_F026CF4C on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME, TRIGGER_STATE)
go
create index IX_F2DD7C7E on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME, TRIGGER_STATE, MISFIRE_INSTR)
go
create index IX_1F92813C on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME, MISFIRE_INSTR)
go
create index IX_99108B6E on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_STATE)
go
create index IX_CD7132D0 on QUARTZ_TRIGGERS (SCHED_NAME, CALENDAR_NAME)
go

alter table AssetCategory add description nvarchar(2000) null;

alter table AssetEntry add classTypeId bigint;
alter table AssetEntry add layoutUuid nvarchar(75) null;

update AssetEntry set classUuid = (select uuid_ from JournalArticleResource where AssetEntry.classPK = JournalArticleResource.resourcePrimKey) where visible = 1 and classNameId = (select classNameId from ClassName_ where value = 'com.liferay.portlet.journal.model.JournalArticle');

alter table BlogsEntry add description nvarchar(2000) null;
alter table BlogsEntry add smallImage bit;
alter table BlogsEntry add smallImageId bigint;
alter table BlogsEntry add smallImageURL nvarchar(2000) null;

alter table BookmarksEntry add resourceBlockId bigint;
alter table BookmarksEntry add description nvarchar(2000) null;

go

update BookmarksEntry set description = comments;

alter table BookmarksEntry drop column comments;

alter table BookmarksFolder add resourceBlockId bigint;

alter table CalEvent add location nvarchar(2000) null;

update ClassName_ set value = 'com.liferay.portal.model.UserPersonalSite' where value = 'com.liferay.portal.model.UserPersonalCommunity';

alter table Company add active_ bit;

go

update Company set active_ = 1;

alter table Country add zipRequired bit;

go

update Country set zipRequired = 1;

create table DDLRecord (
	uuid_ nvarchar(75) null,
	recordId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	versionUserId bigint,
	versionUserName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	DDMStorageId bigint,
	recordSetId bigint,
	version nvarchar(75) null,
	displayIndex int
);

create table DDLRecordSet (
	uuid_ nvarchar(75) null,
	recordSetId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	DDMStructureId bigint,
	recordSetKey nvarchar(75) null,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	minDisplayRows int,
	scope int
);

create table DDLRecordVersion (
	recordVersionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	DDMStorageId bigint,
	recordSetId bigint,
	recordId bigint,
	version nvarchar(75) null,
	displayIndex int,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table DDMContent (
	uuid_ nvarchar(75) null,
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	xml nvarchar(max) null
);

create table DDMStorageLink (
	uuid_ nvarchar(75) null,
	storageLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
);

create table DDMStructure (
	uuid_ nvarchar(75) null,
	structureId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	structureKey nvarchar(75) null,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	xsd nvarchar(max) null,
	storageType nvarchar(75) null,
	type_ int
);

create table DDMStructureLink (
	structureLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
);

create table DDMTemplate (
	uuid_ nvarchar(75) null,
	templateId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	structureId bigint,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	type_ nvarchar(75) null,
	mode_ nvarchar(75) null,
	language nvarchar(75) null,
	script nvarchar(max) null
);

create table DLContent (
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	repositoryId bigint,
	path_ nvarchar(255) null,
	version nvarchar(75) null,
	data_ image,
	size_ bigint
);

create table DLFileEntryMetadata (
	uuid_ nvarchar(75) null,
	fileEntryMetadataId bigint not null primary key,
	DDMStorageId bigint,
	DDMStructureId bigint,
	fileEntryTypeId bigint,
	fileEntryId bigint,
	fileVersionId bigint
);

create table DLFileEntryType (
	uuid_ nvarchar(75) null,
	fileEntryTypeId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(75) null,
	description nvarchar(2000) null
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
alter table DLFileEntry add fileEntryTypeId bigint;
alter table DLFileEntry add smallImageId bigint;
alter table DLFileEntry add largeImageId bigint;
alter table DLFileEntry add custom1ImageId bigint;
alter table DLFileEntry add custom2ImageId bigint;

go

update DLFileEntry set repositoryId = groupId;

alter table DLFileShortcut add repositoryId bigint;

go

update DLFileShortcut set repositoryId = groupId;

alter table DLFileVersion add modifiedDate datetime null;
alter table DLFileVersion add repositoryId bigint;
alter table DLFileVersion add folderId bigint;
alter table DLFileVersion add fileEntryTypeId bigint;

go

update DLFileVersion set modifiedDate = statusDate;
update DLFileVersion set repositoryId = groupId;

alter table DLFolder add repositoryId bigint;
alter table DLFolder add mountPoint bit;
alter table DLFolder add defaultFileEntryTypeId bigint;
alter table DLFolder add overrideFileEntryTypes bit;

go

update DLFolder set repositoryId = groupId;
update DLFolder set mountPoint = 0;

create table DLSync (
	syncId bigint not null primary key,
	companyId bigint,
	createDate datetime null,
	modifiedDate datetime null,
	fileId bigint,
	fileUuid nvarchar(75) null,
	repositoryId bigint,
	parentFolderId bigint,
	name nvarchar(255) null,
	event nvarchar(75) null,
	type_ nvarchar(75) null,
	version nvarchar(75) null
);

alter table Group_ add site bit;

update Group_ set name = 'User Personal Site' where name = 'User Personal Community';
update Group_ set type_ = 3 where classNameId = (select classNameId from ClassName_ where value = 'com.liferay.portal.model.Organization');

alter table JournalArticle add classNameId bigint null;
alter table JournalArticle add classPK bigint null;
alter table JournalArticle add layoutUuid nvarchar(75) null;

go

update JournalArticle set classNameId = 0;
update JournalArticle set classPK = 0;

drop index Layout.IX_FAD05595;

alter table Layout drop column layoutPrototypeId;
alter table Layout drop column dlFolderId;

alter table Layout add createDate datetime null;
alter table Layout add modifiedDate datetime null;
alter table Layout add keywords nvarchar(2000) null;
alter table Layout add robots nvarchar(2000) null;
alter table Layout add layoutPrototypeUuid nvarchar(75) null;
alter table Layout add layoutPrototypeLinkEnabled bit null;
alter table Layout add sourcePrototypeLayoutUuid nvarchar(75) null;

go

update Layout set createDate = GetDate();
update Layout set modifiedDate = GetDate();

create table LayoutBranch (
	LayoutBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	layoutSetBranchId bigint,
	plid bigint,
	name nvarchar(75) null,
	description nvarchar(2000) null,
	master bit
);

alter table LayoutPrototype add uuid_ nvarchar(75) null;

create table LayoutRevision (
	layoutRevisionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	layoutSetBranchId bigint,
	layoutBranchId bigint,
	parentLayoutRevisionId bigint,
	head bit,
	major bit,
	plid bigint,
	privateLayout bit,
	name nvarchar(2000) null,
	title nvarchar(2000) null,
	description nvarchar(2000) null,
	keywords nvarchar(2000) null,
	robots nvarchar(2000) null,
	typeSettings nvarchar(max) null,
	iconImage bit,
	iconImageId bigint,
	themeId nvarchar(75) null,
	colorSchemeId nvarchar(75) null,
	wapThemeId nvarchar(75) null,
	wapColorSchemeId nvarchar(75) null,
	css nvarchar(2000) null,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

alter table LayoutSet drop column layoutSetPrototypeId;

alter table LayoutSet add createDate datetime null;
alter table LayoutSet add modifiedDate datetime null;
alter table LayoutSet add layoutSetPrototypeUuid nvarchar(75) null;
alter table LayoutSet add layoutSetPrototypeLinkEnabled bit null;

go

update LayoutSet set createDate = GetDate();
update LayoutSet set modifiedDate = GetDate();

create table LayoutSetBranch (
	layoutSetBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	privateLayout bit,
	name nvarchar(75) null,
	description nvarchar(2000) null,
	master bit
);

alter table LayoutSetPrototype add uuid_ nvarchar(75) null;
alter table LayoutSetPrototype add createDate datetime null;
alter table LayoutSetPrototype add modifiedDate datetime null;

go

update LayoutSetPrototype set createDate = GetDate();
update LayoutSetPrototype set modifiedDate = GetDate();

alter table MBCategory add displayStyle nvarchar(75) null;

go

update MBCategory set displayStyle = 'default';

alter table MBMessage add format nvarchar(75) null;
alter table MBMessage add answer bit;

go

update MBMessage set format = 'bbcode';

alter table MBThread add question bit;

create table MBThreadFlag (
	threadFlagId bigint not null primary key,
	userId bigint,
	modifiedDate datetime null,
	threadId bigint
);

create table MDRAction (
	uuid_ nvarchar(75) null,
	actionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	ruleGroupInstanceId bigint,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	type_ nvarchar(255) null,
	typeSettings nvarchar(max) null
);

create table MDRRule (
	uuid_ nvarchar(75) null,
	ruleId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	ruleGroupId bigint,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	type_ nvarchar(255) null,
	typeSettings nvarchar(max) null
);

create table MDRRuleGroup (
	uuid_ nvarchar(75) null,
	ruleGroupId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(2000) null,
	description nvarchar(2000) null
);

create table MDRRuleGroupInstance (
	uuid_ nvarchar(75) null,
	ruleGroupInstanceId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	ruleGroupId bigint,
	priority int
);

alter table Organization_ drop column leftOrganizationId;
alter table Organization_ drop column rightOrganizationId;

alter table Organization_ add treePath nvarchar(2000) null;

alter table PollsVote add companyId bigint;
alter table PollsVote add userName nvarchar(75) null;
alter table PollsVote add createDate datetime null;
alter table PollsVote add modifiedDate datetime null;

create table Repository (
	uuid_ nvarchar(75) null,
	repositoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	name nvarchar(75) null,
	description nvarchar(2000) null,
	portletId nvarchar(75) null,
	typeSettings nvarchar(max) null,
	dlFolderId bigint
);

create table RepositoryEntry (
	uuid_ nvarchar(75) null,
	repositoryEntryId bigint not null primary key,
	groupId bigint,
	repositoryId bigint,
	mappedId nvarchar(75) null
);

create table ResourceBlock (
	resourceBlockId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	name nvarchar(75) null,
	permissionsHash nvarchar(75) null,
	referenceCount bigint
);

create table ResourceBlockPermission (
	resourceBlockPermissionId bigint not null primary key,
	resourceBlockId bigint,
	roleId bigint,
	actionIds bigint
);

drop index ResourcePermission.IX_8D83D0CE;
drop index ResourcePermission.IX_4A1F4402;

create table ResourceTypePermission (
	resourceTypePermissionId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	name nvarchar(75) null,
	roleId bigint,
	actionIds bigint
);

create table SocialActivityAchievement (
	activityAchievementId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate bigint,
	name nvarchar(75) null,
	firstInGroup bit
);

create table SocialActivityCounter (
	activityCounterId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	classPK bigint,
	name nvarchar(75) null,
	ownerType int,
	currentValue int,
	totalValue int,
	graceValue int,
	startPeriod int,
	endPeriod int
);

create table SocialActivityLimit (
	activityLimitId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	classNameId bigint,
	classPK bigint,
	activityType int,
	activityCounterName nvarchar(75) null,
	value nvarchar(75) null
);

create table SocialActivitySetting (
	activitySettingId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	activityType int,
	name nvarchar(75) null,
	value nvarchar(1024) null
);

update Role_ set name = 'Site Administrator' where name = 'Community Administrator';
update Role_ set name = 'Site Content Reviewer' where name = 'Community Content Reviewer';
update Role_ set name = 'Site Member' where name = 'Community Member';
update Role_ set name = 'Site Owner' where name = 'Community Owner';
update Role_ set name = 'Organization User' where name = 'Organization Member';

alter table User_ add emailAddressVerified bit;
alter table User_ add status int;

go

update User_ set emailAddressVerified = 1;
update User_ set status = 0;
update User_ set status = 5 where active_ = 0;

alter table User_ drop column active_;

alter table UserGroup add addedByLDAPImport bit;

alter table UserNotificationEvent add archived bit;

alter table WorkflowDefinitionLink add classPK bigint;
alter table WorkflowDefinitionLink add typePK bigint;

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
	SCHED_NAME nvarchar(120) not null,
	TRIGGER_NAME nvarchar(200) not null,
	TRIGGER_GROUP nvarchar(200) not null,
	BLOB_DATA image null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table QUARTZ_CALENDARS (
	SCHED_NAME nvarchar(120) not null,
	CALENDAR_NAME nvarchar(200) not null,
	CALENDAR image not null,
	primary key (SCHED_NAME,CALENDAR_NAME)
);

create table QUARTZ_CRON_TRIGGERS (
	SCHED_NAME nvarchar(120) not null,
	TRIGGER_NAME nvarchar(200) not null,
	TRIGGER_GROUP nvarchar(200) not null,
	CRON_EXPRESSION nvarchar(200) not null,
	TIME_ZONE_ID nvarchar(80),
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table QUARTZ_FIRED_TRIGGERS (
	SCHED_NAME nvarchar(120) not null,
	ENTRY_ID nvarchar(95) not null,
	TRIGGER_NAME nvarchar(200) not null,
	TRIGGER_GROUP nvarchar(200) not null,
	INSTANCE_NAME nvarchar(200) not null,
	FIRED_TIME bigint not null,
	PRIORITY int not null,
	STATE nvarchar(16) not null,
	JOB_NAME nvarchar(200) null,
	JOB_GROUP nvarchar(200) null,
	IS_NONCONCURRENT bit NULL,
	REQUESTS_RECOVERY bit NULL,
	primary key (SCHED_NAME, ENTRY_ID)
);

create table QUARTZ_JOB_DETAILS (
	SCHED_NAME nvarchar(120) not null,
	JOB_NAME nvarchar(200) not null,
	JOB_GROUP nvarchar(200) not null,
	DESCRIPTION nvarchar(250) null,
	JOB_CLASS_NAME nvarchar(250) not null,
	IS_DURABLE bit not null,
	IS_NONCONCURRENT bit not null,
	IS_UPDATE_DATA bit not null,
	REQUESTS_RECOVERY bit not null,
	JOB_DATA image null,
	primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
);

create table QUARTZ_LOCKS (
	SCHED_NAME nvarchar(120) not null,
	LOCK_NAME nvarchar(40) not null ,
	primary key (SCHED_NAME, LOCK_NAME)
);

create table QUARTZ_PAUSED_TRIGGER_GRPS (
	SCHED_NAME nvarchar(120) not null,
	TRIGGER_GROUP nvarchar(200) not null,
	primary key (SCHED_NAME, TRIGGER_GROUP)
);

create table QUARTZ_SCHEDULER_STATE (
	SCHED_NAME nvarchar(120) not null,
	INSTANCE_NAME nvarchar(200) not null,
	LAST_CHECKIN_TIME bigint not null,
	CHECKIN_INTERVAL bigint not null,
	primary key (SCHED_NAME, INSTANCE_NAME)
);

create table QUARTZ_SIMPLE_TRIGGERS (
	SCHED_NAME nvarchar(120) not null,
	TRIGGER_NAME nvarchar(200) not null,
	TRIGGER_GROUP nvarchar(200) not null,
	REPEAT_COUNT bigint not null,
	REPEAT_INTERVAL bigint not null,
	TIMES_TRIGGERED bigint not null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table QUARTZ_SIMPROP_TRIGGERS (
	SCHED_NAME nvarchar(120) not null,
	TRIGGER_NAME nvarchar(200) not null,
	TRIGGER_GROUP nvarchar(200) not null,
	STR_PROP_1 nvarchar(512) null,
	STR_PROP_2 nvarchar(512) null,
	STR_PROP_3 nvarchar(512) null,
	INT_PROP_1 int null,
	INT_PROP_2 int null,
	LONG_PROP_1 bigint null,
	LONG_PROP_2 bigint null,
	DEC_PROP_1 NUMERIC(13,4) null,
	DEC_PROP_2 NUMERIC(13,4) null,
	BOOL_PROP_1 bit null,
	BOOL_PROP_2 bit null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table QUARTZ_TRIGGERS (
	SCHED_NAME nvarchar(120) not null,
	TRIGGER_NAME nvarchar(200) not null,
	TRIGGER_GROUP nvarchar(200) not null,
	JOB_NAME nvarchar(200) not null,
	JOB_GROUP nvarchar(200) not null,
	DESCRIPTION nvarchar(250) null,
	NEXT_FIRE_TIME bigint null,
	PREV_FIRE_TIME bigint null,
	PRIORITY int null,
	TRIGGER_STATE nvarchar(16) not null,
	TRIGGER_TYPE nvarchar(8) not null,
	START_TIME bigint not null,
	END_TIME bigint null,
	CALENDAR_NAME nvarchar(200) null,
	MISFIRE_INSTR int null,
	JOB_DATA image null,
	primary key  (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

go

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

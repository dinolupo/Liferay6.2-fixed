alter table AssetCategory add description varchar2(4000) null;

alter table AssetEntry add classTypeId number(30,0);
alter table AssetEntry add layoutUuid VARCHAR2(75 CHAR) null;

update AssetEntry set classUuid = (select uuid_ from JournalArticleResource where AssetEntry.classPK = JournalArticleResource.resourcePrimKey) where visible = 1 and classNameId = (select classNameId from ClassName_ where value = 'com.liferay.portlet.journal.model.JournalArticle');

alter table BlogsEntry add description varchar2(4000) null;
alter table BlogsEntry add smallImage number(1, 0);
alter table BlogsEntry add smallImageId number(30,0);
alter table BlogsEntry add smallImageURL varchar2(4000) null;

alter table BookmarksEntry add resourceBlockId number(30,0);
alter table BookmarksEntry add description varchar2(4000) null;

commit;

update BookmarksEntry set description = comments;

alter table BookmarksEntry drop column comments;

alter table BookmarksFolder add resourceBlockId number(30,0);

alter table CalEvent add location varchar2(4000) null;

update ClassName_ set value = 'com.liferay.portal.model.UserPersonalSite' where value = 'com.liferay.portal.model.UserPersonalCommunity';

alter table Company add active_ number(1, 0);

commit;

update Company set active_ = 1;

alter table Country add zipRequired number(1, 0);

commit;

update Country set zipRequired = 1;

create table DDLRecord (
	uuid_ VARCHAR2(75 CHAR) null,
	recordId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	versionUserId number(30,0),
	versionUserName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	DDMStorageId number(30,0),
	recordSetId number(30,0),
	version VARCHAR2(75 CHAR) null,
	displayIndex number(30,0)
);

create table DDLRecordSet (
	uuid_ VARCHAR2(75 CHAR) null,
	recordSetId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	DDMStructureId number(30,0),
	recordSetKey VARCHAR2(75 CHAR) null,
	name varchar2(4000) null,
	description varchar2(4000) null,
	minDisplayRows number(30,0),
	scope number(30,0)
);

create table DDLRecordVersion (
	recordVersionId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	DDMStorageId number(30,0),
	recordSetId number(30,0),
	recordId number(30,0),
	version VARCHAR2(75 CHAR) null,
	displayIndex number(30,0),
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table DDMContent (
	uuid_ VARCHAR2(75 CHAR) null,
	contentId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name varchar2(4000) null,
	description varchar2(4000) null,
	xml clob null
);

create table DDMStorageLink (
	uuid_ VARCHAR2(75 CHAR) null,
	storageLinkId number(30,0) not null primary key,
	classNameId number(30,0),
	classPK number(30,0),
	structureId number(30,0)
);

create table DDMStructure (
	uuid_ VARCHAR2(75 CHAR) null,
	structureId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	structureKey VARCHAR2(75 CHAR) null,
	name varchar2(4000) null,
	description varchar2(4000) null,
	xsd clob null,
	storageType VARCHAR2(75 CHAR) null,
	type_ number(30,0)
);

create table DDMStructureLink (
	structureLinkId number(30,0) not null primary key,
	classNameId number(30,0),
	classPK number(30,0),
	structureId number(30,0)
);

create table DDMTemplate (
	uuid_ VARCHAR2(75 CHAR) null,
	templateId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	structureId number(30,0),
	name varchar2(4000) null,
	description varchar2(4000) null,
	type_ VARCHAR2(75 CHAR) null,
	mode_ VARCHAR2(75 CHAR) null,
	language VARCHAR2(75 CHAR) null,
	script clob null
);

create table DLContent (
	contentId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	repositoryId number(30,0),
	path_ VARCHAR2(255 CHAR) null,
	version VARCHAR2(75 CHAR) null,
	data_ blob,
	size_ number(30,0)
);

create table DLFileEntryMetadata (
	uuid_ VARCHAR2(75 CHAR) null,
	fileEntryMetadataId number(30,0) not null primary key,
	DDMStorageId number(30,0),
	DDMStructureId number(30,0),
	fileEntryTypeId number(30,0),
	fileEntryId number(30,0),
	fileVersionId number(30,0)
);

create table DLFileEntryType (
	uuid_ VARCHAR2(75 CHAR) null,
	fileEntryTypeId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null
);

create table DLFileEntryTypes_DDMStructures (
	fileEntryTypeId number(30,0) not null,
	structureId number(30,0) not null,
	primary key (fileEntryTypeId, structureId)
);

create table DLFileEntryTypes_DLFolders (
	fileEntryTypeId number(30,0) not null,
	folderId number(30,0) not null,
	primary key (fileEntryTypeId, folderId)
);

alter table DLFileEntry add repositoryId number(30,0);
alter table DLFileEntry add fileEntryTypeId number(30,0);
alter table DLFileEntry add smallImageId number(30,0);
alter table DLFileEntry add largeImageId number(30,0);
alter table DLFileEntry add custom1ImageId number(30,0);
alter table DLFileEntry add custom2ImageId number(30,0);

commit;

update DLFileEntry set repositoryId = groupId;

alter table DLFileShortcut add repositoryId number(30,0);

commit;

update DLFileShortcut set repositoryId = groupId;

alter table DLFileVersion add modifiedDate timestamp null;
alter table DLFileVersion add repositoryId number(30,0);
alter table DLFileVersion add folderId number(30,0);
alter table DLFileVersion add fileEntryTypeId number(30,0);

commit;

update DLFileVersion set modifiedDate = statusDate;
update DLFileVersion set repositoryId = groupId;

alter table DLFolder add repositoryId number(30,0);
alter table DLFolder add mountPoint number(1, 0);
alter table DLFolder add defaultFileEntryTypeId number(30,0);
alter table DLFolder add overrideFileEntryTypes number(1, 0);

commit;

update DLFolder set repositoryId = groupId;
update DLFolder set mountPoint = 0;

create table DLSync (
	syncId number(30,0) not null primary key,
	companyId number(30,0),
	createDate timestamp null,
	modifiedDate timestamp null,
	fileId number(30,0),
	fileUuid VARCHAR2(75 CHAR) null,
	repositoryId number(30,0),
	parentFolderId number(30,0),
	name VARCHAR2(255 CHAR) null,
	event VARCHAR2(75 CHAR) null,
	type_ VARCHAR2(75 CHAR) null,
	version VARCHAR2(75 CHAR) null
);

alter table Group_ add site number(1, 0);

update Group_ set name = 'User Personal Site' where name = 'User Personal Community';
update Group_ set type_ = 3 where classNameId = (select classNameId from ClassName_ where value = 'com.liferay.portal.model.Organization');

alter table JournalArticle add classNameId number(30,0) null;
alter table JournalArticle add classPK number(30,0) null;
alter table JournalArticle add layoutUuid VARCHAR2(75 CHAR) null;

commit;

update JournalArticle set classNameId = 0;
update JournalArticle set classPK = 0;

drop index IX_FAD05595;

alter table Layout drop column layoutPrototypeId;
alter table Layout drop column dlFolderId;

alter table Layout add createDate timestamp null;
alter table Layout add modifiedDate timestamp null;
alter table Layout add keywords varchar2(4000) null;
alter table Layout add robots varchar2(4000) null;
alter table Layout add layoutPrototypeUuid VARCHAR2(75 CHAR) null;
alter table Layout add layoutPrototypeLinkEnabled number(1, 0) null;
alter table Layout add sourcePrototypeLayoutUuid VARCHAR2(75 CHAR) null;

commit;

update Layout set createDate = sysdate;
update Layout set modifiedDate = sysdate;

create table LayoutBranch (
	LayoutBranchId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	layoutSetBranchId number(30,0),
	plid number(30,0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	master number(1, 0)
);

alter table LayoutPrototype add uuid_ VARCHAR2(75 CHAR) null;

create table LayoutRevision (
	layoutRevisionId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	layoutSetBranchId number(30,0),
	layoutBranchId number(30,0),
	parentLayoutRevisionId number(30,0),
	head number(1, 0),
	major number(1, 0),
	plid number(30,0),
	privateLayout number(1, 0),
	name varchar2(4000) null,
	title varchar2(4000) null,
	description varchar2(4000) null,
	keywords varchar2(4000) null,
	robots varchar2(4000) null,
	typeSettings clob null,
	iconImage number(1, 0),
	iconImageId number(30,0),
	themeId VARCHAR2(75 CHAR) null,
	colorSchemeId VARCHAR2(75 CHAR) null,
	wapThemeId VARCHAR2(75 CHAR) null,
	wapColorSchemeId VARCHAR2(75 CHAR) null,
	css varchar2(4000) null,
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

alter table LayoutSet drop column layoutSetPrototypeId;

alter table LayoutSet add createDate timestamp null;
alter table LayoutSet add modifiedDate timestamp null;
alter table LayoutSet add layoutSetPrototypeUuid VARCHAR2(75 CHAR) null;
alter table LayoutSet add layoutSetPrototypeLinkEnabled number(1, 0) null;

commit;

update LayoutSet set createDate = sysdate;
update LayoutSet set modifiedDate = sysdate;

create table LayoutSetBranch (
	layoutSetBranchId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	privateLayout number(1, 0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	master number(1, 0)
);

alter table LayoutSetPrototype add uuid_ VARCHAR2(75 CHAR) null;
alter table LayoutSetPrototype add createDate timestamp null;
alter table LayoutSetPrototype add modifiedDate timestamp null;

commit;

update LayoutSetPrototype set createDate = sysdate;
update LayoutSetPrototype set modifiedDate = sysdate;

alter table MBCategory add displayStyle VARCHAR2(75 CHAR) null;

commit;

update MBCategory set displayStyle = 'default';

alter table MBMessage add format VARCHAR2(75 CHAR) null;
alter table MBMessage add answer number(1, 0);

commit;

update MBMessage set format = 'bbcode';

alter table MBThread add question number(1, 0);

create table MBThreadFlag (
	threadFlagId number(30,0) not null primary key,
	userId number(30,0),
	modifiedDate timestamp null,
	threadId number(30,0)
);

create table MDRAction (
	uuid_ VARCHAR2(75 CHAR) null,
	actionId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	ruleGroupInstanceId number(30,0),
	name varchar2(4000) null,
	description varchar2(4000) null,
	type_ VARCHAR2(255 CHAR) null,
	typeSettings clob null
);

create table MDRRule (
	uuid_ VARCHAR2(75 CHAR) null,
	ruleId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	ruleGroupId number(30,0),
	name varchar2(4000) null,
	description varchar2(4000) null,
	type_ VARCHAR2(255 CHAR) null,
	typeSettings clob null
);

create table MDRRuleGroup (
	uuid_ VARCHAR2(75 CHAR) null,
	ruleGroupId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name varchar2(4000) null,
	description varchar2(4000) null
);

create table MDRRuleGroupInstance (
	uuid_ VARCHAR2(75 CHAR) null,
	ruleGroupInstanceId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	ruleGroupId number(30,0),
	priority number(30,0)
);

alter table Organization_ drop column leftOrganizationId;
alter table Organization_ drop column rightOrganizationId;

alter table Organization_ add treePath varchar2(4000) null;

alter table PollsVote add companyId number(30,0);
alter table PollsVote add userName VARCHAR2(75 CHAR) null;
alter table PollsVote add createDate timestamp null;
alter table PollsVote add modifiedDate timestamp null;

create table Repository (
	uuid_ VARCHAR2(75 CHAR) null,
	repositoryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	portletId VARCHAR2(75 CHAR) null,
	typeSettings clob null,
	dlFolderId number(30,0)
);

create table RepositoryEntry (
	uuid_ VARCHAR2(75 CHAR) null,
	repositoryEntryId number(30,0) not null primary key,
	groupId number(30,0),
	repositoryId number(30,0),
	mappedId VARCHAR2(75 CHAR) null
);

create table ResourceBlock (
	resourceBlockId number(30,0) not null primary key,
	companyId number(30,0),
	groupId number(30,0),
	name VARCHAR2(75 CHAR) null,
	permissionsHash VARCHAR2(75 CHAR) null,
	referenceCount number(30,0)
);

create table ResourceBlockPermission (
	resourceBlockPermissionId number(30,0) not null primary key,
	resourceBlockId number(30,0),
	roleId number(30,0),
	actionIds number(30,0)
);

drop index IX_8D83D0CE;
drop index IX_4A1F4402;

create table ResourceTypePermission (
	resourceTypePermissionId number(30,0) not null primary key,
	companyId number(30,0),
	groupId number(30,0),
	name VARCHAR2(75 CHAR) null,
	roleId number(30,0),
	actionIds number(30,0)
);

create table SocialActivityAchievement (
	activityAchievementId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	createDate number(30,0),
	name VARCHAR2(75 CHAR) null,
	firstInGroup number(1, 0)
);

create table SocialActivityCounter (
	activityCounterId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	classNameId number(30,0),
	classPK number(30,0),
	name VARCHAR2(75 CHAR) null,
	ownerType number(30,0),
	currentValue number(30,0),
	totalValue number(30,0),
	graceValue number(30,0),
	startPeriod number(30,0),
	endPeriod number(30,0)
);

create table SocialActivityLimit (
	activityLimitId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	classNameId number(30,0),
	classPK number(30,0),
	activityType number(30,0),
	activityCounterName VARCHAR2(75 CHAR) null,
	value VARCHAR2(75 CHAR) null
);

create table SocialActivitySetting (
	activitySettingId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	classNameId number(30,0),
	activityType number(30,0),
	name VARCHAR2(75 CHAR) null,
	value VARCHAR2(1024 CHAR) null
);

update Role_ set name = 'Site Administrator' where name = 'Community Administrator';
update Role_ set name = 'Site Content Reviewer' where name = 'Community Content Reviewer';
update Role_ set name = 'Site Member' where name = 'Community Member';
update Role_ set name = 'Site Owner' where name = 'Community Owner';
update Role_ set name = 'Organization User' where name = 'Organization Member';

alter table User_ add emailAddressVerified number(1, 0);
alter table User_ add status int;

commit;

update User_ set emailAddressVerified = 1;
update User_ set status = 0;
update User_ set status = 5 where active_ = 0;

alter table User_ drop column active_;

alter table UserGroup add addedByLDAPImport number(1, 0);

alter table UserNotificationEvent add archived number(1, 0);

alter table WorkflowDefinitionLink add classPK number(30,0);
alter table WorkflowDefinitionLink add typePK number(30,0);

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
	SCHED_NAME VARCHAR2(120 CHAR) not null,
	TRIGGER_NAME VARCHAR2(200 CHAR) not null,
	TRIGGER_GROUP VARCHAR2(200 CHAR) not null,
	BLOB_DATA blob null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table QUARTZ_CALENDARS (
	SCHED_NAME VARCHAR2(120 CHAR) not null,
	CALENDAR_NAME VARCHAR2(200 CHAR) not null,
	CALENDAR blob not null,
	primary key (SCHED_NAME,CALENDAR_NAME)
);

create table QUARTZ_CRON_TRIGGERS (
	SCHED_NAME VARCHAR2(120 CHAR) not null,
	TRIGGER_NAME VARCHAR2(200 CHAR) not null,
	TRIGGER_GROUP VARCHAR2(200 CHAR) not null,
	CRON_EXPRESSION VARCHAR2(200 CHAR) not null,
	TIME_ZONE_ID VARCHAR2(80 CHAR),
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table QUARTZ_FIRED_TRIGGERS (
	SCHED_NAME VARCHAR2(120 CHAR) not null,
	ENTRY_ID VARCHAR2(95 CHAR) not null,
	TRIGGER_NAME VARCHAR2(200 CHAR) not null,
	TRIGGER_GROUP VARCHAR2(200 CHAR) not null,
	INSTANCE_NAME VARCHAR2(200 CHAR) not null,
	FIRED_TIME number(30,0) not null,
	PRIORITY number(30,0) not null,
	STATE VARCHAR2(16 CHAR) not null,
	JOB_NAME VARCHAR2(200 CHAR) null,
	JOB_GROUP VARCHAR2(200 CHAR) null,
	IS_NONCONCURRENT number(1, 0) NULL,
	REQUESTS_RECOVERY number(1, 0) NULL,
	primary key (SCHED_NAME, ENTRY_ID)
);

create table QUARTZ_JOB_DETAILS (
	SCHED_NAME VARCHAR2(120 CHAR) not null,
	JOB_NAME VARCHAR2(200 CHAR) not null,
	JOB_GROUP VARCHAR2(200 CHAR) not null,
	DESCRIPTION VARCHAR2(250 CHAR) null,
	JOB_CLASS_NAME VARCHAR2(250 CHAR) not null,
	IS_DURABLE number(1, 0) not null,
	IS_NONCONCURRENT number(1, 0) not null,
	IS_UPDATE_DATA number(1, 0) not null,
	REQUESTS_RECOVERY number(1, 0) not null,
	JOB_DATA blob null,
	primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
);

create table QUARTZ_LOCKS (
	SCHED_NAME VARCHAR2(120 CHAR) not null,
	LOCK_NAME VARCHAR2(40 CHAR) not null ,
	primary key (SCHED_NAME, LOCK_NAME)
);

create table QUARTZ_PAUSED_TRIGGER_GRPS (
	SCHED_NAME VARCHAR2(120 CHAR) not null,
	TRIGGER_GROUP VARCHAR2(200 CHAR) not null,
	primary key (SCHED_NAME, TRIGGER_GROUP)
);

create table QUARTZ_SCHEDULER_STATE (
	SCHED_NAME VARCHAR2(120 CHAR) not null,
	INSTANCE_NAME VARCHAR2(200 CHAR) not null,
	LAST_CHECKIN_TIME number(30,0) not null,
	CHECKIN_INTERVAL number(30,0) not null,
	primary key (SCHED_NAME, INSTANCE_NAME)
);

create table QUARTZ_SIMPLE_TRIGGERS (
	SCHED_NAME VARCHAR2(120 CHAR) not null,
	TRIGGER_NAME VARCHAR2(200 CHAR) not null,
	TRIGGER_GROUP VARCHAR2(200 CHAR) not null,
	REPEAT_COUNT number(30,0) not null,
	REPEAT_INTERVAL number(30,0) not null,
	TIMES_TRIGGERED number(30,0) not null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table QUARTZ_SIMPROP_TRIGGERS (
	SCHED_NAME VARCHAR2(120 CHAR) not null,
	TRIGGER_NAME VARCHAR2(200 CHAR) not null,
	TRIGGER_GROUP VARCHAR2(200 CHAR) not null,
	STR_PROP_1 VARCHAR2(512 CHAR) null,
	STR_PROP_2 VARCHAR2(512 CHAR) null,
	STR_PROP_3 VARCHAR2(512 CHAR) null,
	INT_PROP_1 number(30,0) null,
	INT_PROP_2 number(30,0) null,
	LONG_PROP_1 number(30,0) null,
	LONG_PROP_2 number(30,0) null,
	DEC_PROP_1 NUMERIC(13,4) null,
	DEC_PROP_2 NUMERIC(13,4) null,
	BOOL_PROP_1 number(1, 0) null,
	BOOL_PROP_2 number(1, 0) null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table QUARTZ_TRIGGERS (
	SCHED_NAME VARCHAR2(120 CHAR) not null,
	TRIGGER_NAME VARCHAR2(200 CHAR) not null,
	TRIGGER_GROUP VARCHAR2(200 CHAR) not null,
	JOB_NAME VARCHAR2(200 CHAR) not null,
	JOB_GROUP VARCHAR2(200 CHAR) not null,
	DESCRIPTION VARCHAR2(250 CHAR) null,
	NEXT_FIRE_TIME number(30,0) null,
	PREV_FIRE_TIME number(30,0) null,
	PRIORITY number(30,0) null,
	TRIGGER_STATE VARCHAR2(16 CHAR) not null,
	TRIGGER_TYPE VARCHAR2(8 CHAR) not null,
	START_TIME number(30,0) not null,
	END_TIME number(30,0) null,
	CALENDAR_NAME VARCHAR2(200 CHAR) null,
	MISFIRE_INSTR number(30,0) null,
	JOB_DATA blob null,
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

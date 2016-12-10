alter table AssetCategory add description lvarchar;

alter table AssetEntry add classTypeId int8;
alter table AssetEntry add layoutUuid varchar(75);

update AssetEntry set classUuid = (select uuid_ from JournalArticleResource where AssetEntry.classPK = JournalArticleResource.resourcePrimKey) where visible = 'T' and classNameId = (select classNameId from ClassName_ where value = 'com.liferay.portlet.journal.model.JournalArticle');

alter table BlogsEntry add description lvarchar;
alter table BlogsEntry add smallImage boolean;
alter table BlogsEntry add smallImageId int8;
alter table BlogsEntry add smallImageURL lvarchar;

alter table BookmarksEntry add userName varchar(75);
alter table BookmarksEntry add resourceBlockId int8;
alter table BookmarksEntry add description lvarchar;



update BookmarksEntry set description = comments;

alter table BookmarksEntry drop column comments;

alter table BookmarksFolder add userName varchar(75);
alter table BookmarksFolder add resourceBlockId int8;

alter table CalEvent add location lvarchar;

update ClassName_ set value = 'com.liferay.portal.model.UserPersonalSite' where value = 'com.liferay.portal.model.UserPersonalCommunity';

drop index IX_975996C0;

alter table Company add active_ boolean;



update Company set active_ = 'T';

alter table Country add zipRequired boolean;



update Country set zipRequired = 'T';

create table DDLRecord (
	uuid_ varchar(75),
	recordId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	versionUserId int8,
	versionUserName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	DDMStorageId int8,
	recordSetId int8,
	version varchar(75),
	displayIndex int
)
extent size 16 next size 16
lock mode row;

create table DDLRecordSet (
	uuid_ varchar(75),
	recordSetId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	DDMStructureId int8,
	recordSetKey varchar(75),
	name lvarchar,
	description lvarchar,
	minDisplayRows int,
	scope int
)
extent size 16 next size 16
lock mode row;

create table DDLRecordVersion (
	recordVersionId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	DDMStorageId int8,
	recordSetId int8,
	recordId int8,
	version varchar(75),
	displayIndex int,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table DDMContent (
	uuid_ varchar(75),
	contentId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name lvarchar,
	description lvarchar,
	xml text
)
extent size 16 next size 16
lock mode row;

create table DDMStorageLink (
	uuid_ varchar(75),
	storageLinkId int8 not null primary key,
	classNameId int8,
	classPK int8,
	structureId int8
)
extent size 16 next size 16
lock mode row;

create table DDMStructure (
	uuid_ varchar(75),
	structureId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	structureKey varchar(75),
	name lvarchar,
	description lvarchar,
	xsd text,
	storageType varchar(75),
	type_ int
)
extent size 16 next size 16
lock mode row;

create table DDMStructureLink (
	structureLinkId int8 not null primary key,
	classNameId int8,
	classPK int8,
	structureId int8
)
extent size 16 next size 16
lock mode row;

create table DDMTemplate (
	uuid_ varchar(75),
	templateId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	structureId int8,
	name lvarchar,
	description lvarchar,
	type_ varchar(75),
	mode_ varchar(75),
	language varchar(75),
	script text
)
extent size 16 next size 16
lock mode row;

create table DLContent (
	contentId int8 not null primary key,
	groupId int8,
	companyId int8,
	repositoryId int8,
	path_ varchar(255),
	version varchar(75),
	data_ blob,
	size_ int8
)
extent size 16 next size 16
lock mode row;

create table DLFileEntryMetadata (
	uuid_ varchar(75),
	fileEntryMetadataId int8 not null primary key,
	DDMStorageId int8,
	DDMStructureId int8,
	fileEntryTypeId int8,
	fileEntryId int8,
	fileVersionId int8
)
extent size 16 next size 16
lock mode row;

create table DLFileEntryType (
	uuid_ varchar(75),
	fileEntryTypeId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name varchar(75),
	description lvarchar
)
extent size 16 next size 16
lock mode row;

create table DLFileEntryTypes_DDMStructures (
	fileEntryTypeId int8 not null,
	structureId int8 not null,
	primary key (fileEntryTypeId, structureId)
)
extent size 16 next size 16
lock mode row;

create table DLFileEntryTypes_DLFolders (
	fileEntryTypeId int8 not null,
	folderId int8 not null,
	primary key (fileEntryTypeId, folderId)
)
extent size 16 next size 16
lock mode row;

alter table DLFileEntry add repositoryId int8;
alter table DLFileEntry add mimeType varchar(75);
alter table DLFileEntry add fileEntryTypeId int8;
alter table DLFileEntry add smallImageId int8;
alter table DLFileEntry add largeImageId int8;
alter table DLFileEntry add custom1ImageId int8;
alter table DLFileEntry add custom2ImageId int8;



update DLFileEntry set repositoryId = groupId;

drop index IX_CE705D48;
drop index IX_40B56512;

alter table DLFileRank add fileEntryId int8;

drop index IX_55C736AC;
drop index IX_346A0992;

alter table DLFileShortcut add repositoryId int8;
alter table DLFileShortcut add toFileEntryId int8;



update DLFileShortcut set repositoryId = groupId;

drop index IX_B413F1EC;
drop index IX_94E784D2;
drop index IX_2F8FED9C;

alter table DLFileVersion add modifiedDate datetime YEAR TO FRACTION;
alter table DLFileVersion add repositoryId int8;
alter table DLFileVersion add fileEntryId int8;
alter table DLFileVersion add mimeType varchar(75);
alter table DLFileVersion add fileEntryTypeId int8;



update DLFileVersion set modifiedDate = statusDate;
update DLFileVersion set repositoryId = groupId;

alter table DLFolder add repositoryId int8;
alter table DLFolder add mountPoint boolean;
alter table DLFolder add defaultFileEntryTypeId int8;
alter table DLFolder add overrideFileEntryTypes boolean;



update DLFolder set repositoryId = groupId;
update DLFolder set mountPoint = 'F';

create table DLSync (
	syncId int8 not null primary key,
	companyId int8,
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	fileId int8,
	fileUuid varchar(75),
	repositoryId int8,
	parentFolderId int8,
	name varchar(255),
	event varchar(75),
	type_ varchar(75),
	version varchar(75)
)
extent size 16 next size 16
lock mode row;

alter table Group_ add site boolean;

update Group_ set name = 'User Personal Site' where name = 'User Personal Community';
update Group_ set type_ = 3 where classNameId = (select classNameId from ClassName_ where value = 'com.liferay.portal.model.Organization');

alter table IGFolder add userName varchar(75);

alter table IGImage add userName varchar(75);

alter table JournalArticle add classNameId int8;
alter table JournalArticle add classPK int8;
alter table JournalArticle add layoutUuid varchar(75);



update JournalArticle set classNameId = 0;
update JournalArticle set classPK = 0;

drop index IX_FAD05595;

alter table Layout drop column layoutPrototypeId;
alter table Layout drop column dlFolderId;

alter table Layout add createDate datetime YEAR TO FRACTION;
alter table Layout add modifiedDate datetime YEAR TO FRACTION;
alter table Layout add keywords lvarchar;
alter table Layout add robots lvarchar;
alter table Layout add layoutPrototypeUuid varchar(75);
alter table Layout add layoutPrototypeLinkEnabled boolean;
alter table Layout add sourcePrototypeLayoutUuid varchar(75);



update Layout set createDate = CURRENT YEAR TO FRACTION;
update Layout set modifiedDate = CURRENT YEAR TO FRACTION;

create table LayoutBranch (
	LayoutBranchId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	layoutSetBranchId int8,
	plid int8,
	name varchar(75),
	description lvarchar,
	master boolean
)
extent size 16 next size 16
lock mode row;

alter table LayoutPrototype add uuid_ varchar(75);

create table LayoutRevision (
	layoutRevisionId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	layoutSetBranchId int8,
	layoutBranchId int8,
	parentLayoutRevisionId int8,
	head boolean,
	major boolean,
	plid int8,
	privateLayout boolean,
	name lvarchar,
	title lvarchar,
	description lvarchar,
	keywords lvarchar,
	robots lvarchar,
	typeSettings lvarchar(4096),
	iconImage boolean,
	iconImageId int8,
	themeId varchar(75),
	colorSchemeId varchar(75),
	wapThemeId varchar(75),
	wapColorSchemeId varchar(75),
	css lvarchar,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

alter table LayoutSet drop column layoutSetPrototypeId;

alter table LayoutSet add createDate datetime YEAR TO FRACTION;
alter table LayoutSet add modifiedDate datetime YEAR TO FRACTION;
alter table LayoutSet add layoutSetPrototypeUuid varchar(75);
alter table LayoutSet add layoutSetPrototypeLinkEnabled boolean;

drop index IX_5ABC2905;



update LayoutSet set createDate = CURRENT YEAR TO FRACTION;
update LayoutSet set modifiedDate = CURRENT YEAR TO FRACTION;

create table LayoutSetBranch (
	layoutSetBranchId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	privateLayout boolean,
	name varchar(75),
	description lvarchar,
	master boolean
)
extent size 16 next size 16
lock mode row;

alter table LayoutSetPrototype add createDate datetime YEAR TO FRACTION;
alter table LayoutSetPrototype add modifiedDate datetime YEAR TO FRACTION;
alter table LayoutSetPrototype add uuid_ varchar(75);



update LayoutSetPrototype set createDate = CURRENT YEAR TO FRACTION;
update LayoutSetPrototype set modifiedDate = CURRENT YEAR TO FRACTION;

alter table MBCategory add displayStyle varchar(75);



update MBCategory set displayStyle = 'default';

alter table MBMailingList add allowAnonymous boolean;

alter table MBMessage add format varchar(75);
alter table MBMessage add answer boolean;



update MBMessage set format = 'bbcode';

alter table MBThread add companyId int8;
alter table MBThread add rootMessageUserId int8;
alter table MBThread add question boolean;

create table MBThreadFlag (
	threadFlagId int8 not null primary key,
	userId int8,
	modifiedDate datetime YEAR TO FRACTION,
	threadId int8
)
extent size 16 next size 16
lock mode row;

create table MDRAction (
	uuid_ varchar(75),
	actionId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	ruleGroupInstanceId int8,
	name lvarchar,
	description lvarchar,
	type_ varchar(255),
	typeSettings lvarchar(4096)
)
extent size 16 next size 16
lock mode row;

create table MDRRule (
	uuid_ varchar(75),
	ruleId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	ruleGroupId int8,
	name lvarchar,
	description lvarchar,
	type_ varchar(255),
	typeSettings lvarchar(4096)
)
extent size 16 next size 16
lock mode row;

create table MDRRuleGroup (
	uuid_ varchar(75),
	ruleGroupId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name lvarchar,
	description lvarchar
)
extent size 16 next size 16
lock mode row;

create table MDRRuleGroupInstance (
	uuid_ varchar(75),
	ruleGroupInstanceId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	ruleGroupId int8,
	priority int
)
extent size 16 next size 16
lock mode row;

alter table Organization_ drop column leftOrganizationId;
alter table Organization_ drop column rightOrganizationId;

alter table Organization_ add treePath lvarchar;

alter table PollsVote add companyId int8;
alter table PollsVote add userName varchar(75);
alter table PollsVote add createDate datetime YEAR TO FRACTION;
alter table PollsVote add modifiedDate datetime YEAR TO FRACTION;

create table PortalPreferences (
	portalPreferencesId int8 not null primary key,
	ownerId int8,
	ownerType int,
	preferences text
)
extent size 16 next size 16
lock mode row;

create table Repository (
	uuid_ varchar(75),
	repositoryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	name varchar(75),
	description lvarchar,
	portletId varchar(75),
	typeSettings lvarchar(4096),
	dlFolderId int8
)
extent size 16 next size 16
lock mode row;

create table RepositoryEntry (
	uuid_ varchar(75),
	repositoryEntryId int8 not null primary key,
	groupId int8,
	repositoryId int8,
	mappedId varchar(75)
)
extent size 16 next size 16
lock mode row;

create table ResourceBlock (
	resourceBlockId int8 not null primary key,
	companyId int8,
	groupId int8,
	name varchar(75),
	permissionsHash varchar(75),
	referenceCount int8
)
extent size 16 next size 16
lock mode row;

create table ResourceBlockPermission (
	resourceBlockPermissionId int8 not null primary key,
	resourceBlockId int8,
	roleId int8,
	actionIds int8
)
extent size 16 next size 16
lock mode row;

drop index IX_8D83D0CE;

alter table ResourcePermission add ownerId int8;

create table ResourceTypePermission (
	resourceTypePermissionId int8 not null primary key,
	companyId int8,
	groupId int8,
	name varchar(75),
	roleId int8,
	actionIds int8
)
extent size 16 next size 16
lock mode row;

create table SocialActivityAchievement (
	activityAchievementId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	createDate int8,
	name varchar(75),
	firstInGroup boolean
)
extent size 16 next size 16
lock mode row;

create table SocialActivityCounter (
	activityCounterId int8 not null primary key,
	groupId int8,
	companyId int8,
	classNameId int8,
	classPK int8,
	name varchar(75),
	ownerType int,
	currentValue int,
	totalValue int,
	graceValue int,
	startPeriod int,
	endPeriod int
)
extent size 16 next size 16
lock mode row;

create table SocialActivityLimit (
	activityLimitId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	classNameId int8,
	classPK int8,
	activityType int,
	activityCounterName varchar(75),
	value varchar(75)
)
extent size 16 next size 16
lock mode row;

create table SocialActivitySetting (
	activitySettingId int8 not null primary key,
	groupId int8,
	companyId int8,
	classNameId int8,
	activityType int,
	name varchar(75),
	value lvarchar(1024)
)
extent size 16 next size 16
lock mode row;

update Role_ set name = 'Site Administrator' where name = 'Community Administrator';
update Role_ set name = 'Site Content Reviewer' where name = 'Community Content Reviewer';
update Role_ set name = 'Site Member' where name = 'Community Member';
update Role_ set name = 'Site Owner' where name = 'Community Owner';
update Role_ set name = 'Organization User' where name = 'Organization Member';

alter table Ticket add type_ int;
alter table Ticket add extraInfo text;



update Ticket set type_ = 3;

alter table User_ add emailAddressVerified boolean;
alter table User_ add status int;



update User_ set emailAddressVerified = 'T';
update User_ set status = 0;
update User_ set status = 5 where active_ = 'F';

alter table User_ drop column active_;

alter table UserGroup add addedByLDAPImport boolean;

create table UserGroups_Teams (
	userGroupId int8 not null,
	teamId int8 not null,
	primary key (userGroupId, teamId)
)
extent size 16 next size 16
lock mode row;

create table UserNotificationEvent (
	uuid_ varchar(75),
	userNotificationEventId int8 not null primary key,
	companyId int8,
	userId int8,
	type_ varchar(75),
	timestamp int8,
	deliverBy int8,
	payload text,
	archived boolean
)
extent size 16 next size 16
lock mode row;

create table VirtualHost (
	virtualHostId int8 not null primary key,
	companyId int8,
	layoutSetId int8,
	hostname varchar(75)
)
extent size 16 next size 16
lock mode row;

alter table WorkflowDefinitionLink add classPK int8;
alter table WorkflowDefinitionLink add typePK int8;



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
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_CALENDARS (
	SCHED_NAME varchar(120) not null,
	CALENDAR_NAME varchar(200) not null,
	CALENDAR blob not null,
	primary key (SCHED_NAME,CALENDAR_NAME)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_CRON_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	CRON_EXPRESSION varchar(200) not null,
	TIME_ZONE_ID varchar(80),
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_FIRED_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	ENTRY_ID varchar(95) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	INSTANCE_NAME varchar(200) not null,
	FIRED_TIME int8 not null,
	PRIORITY int not null,
	STATE varchar(16) not null,
	JOB_NAME varchar(200),
	JOB_GROUP varchar(200),
	IS_NONCONCURRENT boolean NULL,
	REQUESTS_RECOVERY boolean NULL,
	primary key (SCHED_NAME, ENTRY_ID)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_JOB_DETAILS (
	SCHED_NAME varchar(120) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250),
	JOB_CLASS_NAME varchar(250) not null,
	IS_DURABLE boolean not null,
	IS_NONCONCURRENT boolean not null,
	IS_UPDATE_DATA boolean not null,
	REQUESTS_RECOVERY boolean not null,
	JOB_DATA blob,
	primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_LOCKS (
	SCHED_NAME varchar(120) not null,
	LOCK_NAME varchar(40) not null ,
	primary key (SCHED_NAME, LOCK_NAME)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_PAUSED_TRIGGER_GRPS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_GROUP varchar(200) not null,
	primary key (SCHED_NAME, TRIGGER_GROUP)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_SCHEDULER_STATE (
	SCHED_NAME varchar(120) not null,
	INSTANCE_NAME varchar(200) not null,
	LAST_CHECKIN_TIME int8 not null,
	CHECKIN_INTERVAL int8 not null,
	primary key (SCHED_NAME, INSTANCE_NAME)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_SIMPLE_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	REPEAT_COUNT int8 not null,
	REPEAT_INTERVAL int8 not null,
	TIMES_TRIGGERED int8 not null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_SIMPROP_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	STR_PROP_1 varchar(512),
	STR_PROP_2 varchar(512),
	STR_PROP_3 varchar(512),
	INT_PROP_1 int,
	INT_PROP_2 int,
	LONG_PROP_1 int8,
	LONG_PROP_2 int8,
	DEC_PROP_1 NUMERIC(13,4),
	DEC_PROP_2 NUMERIC(13,4),
	BOOL_PROP_1 boolean,
	BOOL_PROP_2 boolean,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250),
	NEXT_FIRE_TIME int8,
	PREV_FIRE_TIME int8,
	PRIORITY int,
	TRIGGER_STATE varchar(16) not null,
	TRIGGER_TYPE varchar(8) not null,
	START_TIME int8 not null,
	END_TIME int8,
	CALENDAR_NAME varchar(200),
	MISFIRE_INSTR int,
	JOB_DATA blob,
	primary key  (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
extent size 16 next size 16
lock mode row;



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

alter table Address add uuid_ varchar(75);


alter table BookmarksEntry add treePath varchar(4000);
alter table BookmarksEntry add status integer;
alter table BookmarksEntry add statusByUserId int64;
alter table BookmarksEntry add statusByUserName varchar(75);
alter table BookmarksEntry add statusDate timestamp;

commit;


alter table BookmarksFolder add treePath varchar(4000);
alter table BookmarksFolder add status integer;
alter table BookmarksFolder add statusByUserId int64;
alter table BookmarksFolder add statusByUserName varchar(75);
alter table BookmarksFolder add statusDate timestamp;

commit;


create table BackgroundTask (
	backgroundTaskId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	servletContextNames varchar(255),
	taskExecutorClassName varchar(200),
	taskContext blob,
	completed smallint,
	completionDate timestamp,
	status integer,
	statusMessage blob
);

alter table Contact_ add classNameId int64;
alter table Contact_ add classPK int64;
alter table Contact_ add emailAddress varchar(75);




alter table DDMStructure add parentStructureId int64;

drop index IX_490E7A1E;

alter table DDMTemplate add cacheable smallint;
alter table DDMTemplate add smallImage smallint;
alter table DDMTemplate add smallImageId int64;
alter table DDMTemplate add smallImageURL varchar(4000);


alter table DLFileEntry drop column versionUserId;
alter table DLFileEntry drop column versionUserName;

alter table DLFileEntry add classNameId int64;
alter table DLFileEntry add classPK int64;
alter table DLFileEntry add treePath varchar(4000);
alter table DLFileEntry add manualCheckInRequired smallint;

commit;


alter table DLFileRank add active_ smallint;

commit;


alter table DLFileShortcut add treePath varchar(4000);
alter table DLFileShortcut add active_ smallint;

commit;


alter table DLFileVersion add treePath varchar(4000);
alter table DLFileVersion add checksum varchar(75);

alter table DLFolder add treePath varchar(4000);
alter table DLFolder add hidden_ smallint;
alter table DLFolder add status integer;
alter table DLFolder add statusByUserId int64;
alter table DLFolder add statusByUserName varchar(75);
alter table DLFolder add statusDate timestamp;

commit;


drop table DLSync;

create table DLSyncEvent (
	syncEventId int64 not null primary key,
	modifiedTime int64,
	event varchar(75),
	type_ varchar(75),
	typePK int64
);

alter table EmailAddress add uuid_ varchar(75);

alter table ExpandoRow add modifiedDate timestamp;

commit;


alter table Group_ add uuid_ varchar(75);
alter table Group_ add treePath varchar(4000);
alter table Group_ add manualMembership smallint;
alter table Group_ add membershipRestriction integer;
alter table Group_ add remoteStagingGroupCount integer;

commit;


drop table Groups_Permissions;

alter table Image drop column text_;

alter table JournalArticle add folderId int64;
alter table JournalArticle add treePath varchar(4000);

commit;


create table JournalFolder (
	uuid_ varchar(75),
	folderId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	parentFolderId int64,
	treePath varchar(4000),
	name varchar(100),
	description varchar(4000),
	status integer,
	statusByUserId int64,
	statusByUserName varchar(75),
	statusDate timestamp
);

alter table Layout add userId int64;
alter table Layout add userName varchar(75);

drop index IX_CED31606;

create table LayoutFriendlyURL (
	uuid_ varchar(75),
	layoutFriendlyURLId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	plid int64,
	privateLayout smallint,
	friendlyURL varchar(255),
	languageId varchar(75)
);

alter table LayoutPrototype add userId int64;
alter table LayoutPrototype add userName varchar(75);
alter table LayoutPrototype add createDate timestamp;
alter table LayoutPrototype add modifiedDate timestamp;

alter table LayoutSetPrototype add userId int64;
alter table LayoutSetPrototype add userName varchar(75);

drop index IX_228562AD;
drop index IX_DD635956;

alter table MBBan add uuid_ varchar(75);

alter table MBCategory add status integer;
alter table MBCategory add statusByUserId int64;
alter table MBCategory add statusByUserName varchar(75);
alter table MBCategory add statusDate timestamp;

alter table MBDiscussion add uuid_ varchar(75);
alter table MBDiscussion add groupId int64;
alter table MBDiscussion add companyId int64;
alter table MBDiscussion add userId int64;
alter table MBDiscussion add userName varchar(75);
alter table MBDiscussion add createDate timestamp;
alter table MBDiscussion add modifiedDate timestamp;

commit;



alter table MBMessage drop column attachments;

alter table MBThread add uuid_ varchar(75);
alter table MBThread add userId int64;
alter table MBThread add userName varchar(75);
alter table MBThread add createDate timestamp;
alter table MBThread add modifiedDate timestamp;

alter table MBThreadFlag add uuid_ varchar(75);
alter table MBThreadFlag add groupId int64;
alter table MBThreadFlag add companyId int64;
alter table MBThreadFlag add userName varchar(75);
alter table MBThreadFlag add createDate timestamp;

alter table Organization_ add uuid_ varchar(75);
alter table Organization_ add userId int64;
alter table Organization_ add userName varchar(75);
alter table Organization_ add createDate timestamp;
alter table Organization_ add modifiedDate timestamp;

drop table OrgGroupPermission;

alter table PasswordPolicy add uuid_ varchar(75);
alter table PasswordPolicy add regex varchar(75);

drop index IX_C3A17327;
drop index IX_ED7CF243;

drop table Permission_;

alter table Phone add uuid_ varchar(75);

alter table PollsChoice add groupId int64;
alter table PollsChoice add companyId int64;
alter table PollsChoice add userId int64;
alter table PollsChoice add userName varchar(75);
alter table PollsChoice add createDate timestamp;
alter table PollsChoice add modifiedDate timestamp;

alter table PollsVote add uuid_ varchar(75);
alter table PollsVote add groupId int64;


alter table RepositoryEntry add companyId int64;
alter table RepositoryEntry add userId int64;
alter table RepositoryEntry add userName varchar(75);
alter table RepositoryEntry add createDate timestamp;
alter table RepositoryEntry add modifiedDate timestamp;
alter table RepositoryEntry add manualCheckInRequired smallint;

drop table Resource_;

drop table ResourceCode;

drop index IX_88705859;
drop index IX_C94C7708;
drop index IX_8D83D0CE;
drop index IX_4A1F4402;
drop index IX_8DB864A9;

alter table Role_ add uuid_ varchar(75);
alter table Role_ add userId int64;
alter table Role_ add userName varchar(75);
alter table Role_ add createDate timestamp;
alter table Role_ add modifiedDate timestamp;

drop table Roles_Permissions;

alter table SocialActivity add activitySetId int64;
alter table SocialActivity add parentClassNameId int64;
alter table SocialActivity add parentClassPK int64;

alter table SocialActivityCounter add active_ smallint;

commit;


create table SocialActivitySet (
	activitySetId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	createDate int64,
	modifiedDate int64,
	classNameId int64,
	classPK int64,
	type_ integer,
	extraData varchar(4000),
	activityCount integer
);

create table SystemEvent (
	systemEventId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	classNameId int64,
	classPK int64,
	classUuid varchar(75),
	referrerClassNameId int64,
	parentSystemEventId int64,
	systemEventSetKey int64,
	type_ integer,
	extraData blob
);

create table TrashEntry (
	entryId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	classNameId int64,
	classPK int64,
	systemEventSetKey int64,
	typeSettings blob,
	status integer
);

create table TrashVersion (
	versionId int64 not null primary key,
	entryId int64,
	classNameId int64,
	classPK int64,
	typeSettings blob,
	status integer
);

alter table User_ add ldapServerId int64;

commit;


alter table UserGroup add uuid_ varchar(75);
alter table UserGroup add userId int64;
alter table UserGroup add userName varchar(75);
alter table UserGroup add createDate timestamp;
alter table UserGroup add modifiedDate timestamp;

create table UserNotificationDelivery (
	userNotificationDeliveryId int64 not null primary key,
	companyId int64,
	userId int64,
	portletId varchar(200),
	classNameId int64,
	notificationType integer,
	deliveryType integer,
	deliver smallint
);

alter table UserNotificationEvent add delivered smallint;

drop table Users_Permissions;

alter table Website add uuid_ varchar(75);

alter table WikiNode add status integer;
alter table WikiNode add statusByUserId int64;
alter table WikiNode add statusByUserName varchar(75);
alter table WikiNode add statusDate timestamp;

commit;


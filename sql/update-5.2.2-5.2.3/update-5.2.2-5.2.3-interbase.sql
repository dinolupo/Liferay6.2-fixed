alter table BookmarksEntry add groupId int64;

create table BrowserTracker (
	browserTrackerId int64 not null primary key,
	userId int64,
	browserKey int64
);


alter table Company add system smallint;

commit;


alter table DLFileEntry add groupId int64;

alter table DLFileRank add groupId int64;

alter table DLFileShortcut add groupId int64;

alter table DLFileVersion add groupId int64;


alter table IGImage add groupId int64;

alter table JournalArticle add urlTitle varchar(150);

commit;


alter table MBCategory add threadCount integer;
alter table MBCategory add messageCount integer;

alter table MBMessage add groupId int64;
alter table MBMessage add classNameId int64;
alter table MBMessage add classPK int64;
alter table MBMessage add priority double precision;

delete from MBMessageFlag where flag = 1;

commit;

alter table MBMessageFlag add modifiedDate timestamp;
alter table MBMessageFlag add threadId int64;

alter table MBThread add groupId int64;

alter table Organization_ add leftOrganizationId int64;
alter table Organization_ add rightOrganizationId int64;

commit;



create table ResourceAction (
	resourceActionId int64 not null primary key,
	name varchar(75),
	actionId varchar(75),
	bitwiseValue int64
);

create table ResourcePermission (
	resourcePermissionId int64 not null primary key,
	companyId int64,
	name varchar(255),
	scope integer,
	primKey varchar(255),
	roleId int64,
	actionIds int64
);

commit;

create unique index IX_8D83D0CE on ResourcePermission (companyId, name, scope, primKey, roleId);

create table Shard (
	shardId int64 not null primary key,
	classNameId int64,
	classPK int64,
	name varchar(75)
);

alter table User_ add firstName varchar(75);
alter table User_ add middleName varchar(75);
alter table User_ add lastName varchar(75);
alter table User_ add jobTitle varchar(100);

alter table WikiPage add groupId int64;

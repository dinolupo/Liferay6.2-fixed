alter table BookmarksEntry add groupId decimal(20,0)
go

create table BrowserTracker (
	browserTrackerId decimal(20,0) not null primary key,
	userId decimal(20,0),
	browserKey decimal(20,0)
)
go

update CalEvent set remindBy = '0' where remindBy = 'none';
update CalEvent set remindBy = '1' where remindBy = 'email';
update CalEvent set remindBy = '2' where remindBy = 'sms';
update CalEvent set remindBy = '3' where remindBy = 'aim';
update CalEvent set remindBy = '4' where remindBy = 'icq';
update CalEvent set remindBy = '5' where remindBy = 'msn';
update CalEvent set remindBy = '6' where remindBy = 'ym';

alter table Company add system int;

go

update Company set system = 0;

alter table DLFileEntry add groupId decimal(20,0)
go

alter table DLFileRank add groupId decimal(20,0)
go

alter table DLFileShortcut add groupId decimal(20,0)
go

alter table DLFileVersion add groupId decimal(20,0)
go

update Group_ set name = classPK where classPK > 0 and name = '';

alter table IGImage add groupId decimal(20,0)
go

alter table JournalArticle add urlTitle varchar(150) null;

go

update JournalArticle set urlTitle = articleId;

alter table MBCategory add threadCount int;
alter table MBCategory add messageCount int;

alter table MBMessage add groupId decimal(20,0)
go
alter table MBMessage add classNameId decimal(20,0)
go
alter table MBMessage add classPK decimal(20,0)
go
alter table MBMessage add priority float;

delete from MBMessageFlag where flag = 1;

go

alter table MBMessageFlag add modifiedDate datetime null;
alter table MBMessageFlag add threadId decimal(20,0)
go

alter table MBThread add groupId decimal(20,0)
go

alter table Organization_ add leftOrganizationId decimal(20,0)
go
alter table Organization_ add rightOrganizationId decimal(20,0)
go

go

update Organization_ set leftOrganizationId = 0, rightOrganizationId = 0;

update Region set regionCode = 'AB' where countryId = 1 and name = 'Alberta';

create table ResourceAction (
	resourceActionId decimal(20,0) not null primary key,
	name varchar(75) null,
	actionId varchar(75) null,
	bitwiseValue decimal(20,0)
)
go

create table ResourcePermission (
	resourcePermissionId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	name varchar(255) null,
	scope int,
	primKey varchar(255) null,
	roleId decimal(20,0),
	actionIds decimal(20,0)
)
go

go

create unique index IX_8D83D0CE on ResourcePermission (companyId, name, scope, primKey, roleId)
go

create table Shard (
	shardId decimal(20,0) not null primary key,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	name varchar(75) null
)
go

alter table User_ add firstName varchar(75) null;
alter table User_ add middleName varchar(75) null;
alter table User_ add lastName varchar(75) null;
alter table User_ add jobTitle varchar(100) null;

alter table WikiPage add groupId decimal(20,0)
go

alter table BookmarksEntry add groupId int8;

create table BrowserTracker (
	browserTrackerId int8 not null primary key,
	userId int8,
	browserKey int8
)
extent size 16 next size 16
lock mode row;

update CalEvent set remindBy = '0' where remindBy = 'none';
update CalEvent set remindBy = '1' where remindBy = 'email';
update CalEvent set remindBy = '2' where remindBy = 'sms';
update CalEvent set remindBy = '3' where remindBy = 'aim';
update CalEvent set remindBy = '4' where remindBy = 'icq';
update CalEvent set remindBy = '5' where remindBy = 'msn';
update CalEvent set remindBy = '6' where remindBy = 'ym';

alter table Company add system boolean;



update Company set system = 'F';

alter table DLFileEntry add groupId int8;

alter table DLFileRank add groupId int8;

alter table DLFileShortcut add groupId int8;

alter table DLFileVersion add groupId int8;

update Group_ set name = classPK where classPK > 0 and name = '';

alter table IGImage add groupId int8;

alter table JournalArticle add urlTitle varchar(150);



update JournalArticle set urlTitle = articleId;

alter table MBCategory add threadCount int;
alter table MBCategory add messageCount int;

alter table MBMessage add groupId int8;
alter table MBMessage add classNameId int8;
alter table MBMessage add classPK int8;
alter table MBMessage add priority float;

delete from MBMessageFlag where flag = 1;



alter table MBMessageFlag add modifiedDate datetime YEAR TO FRACTION;
alter table MBMessageFlag add threadId int8;

alter table MBThread add groupId int8;

alter table Organization_ add leftOrganizationId int8;
alter table Organization_ add rightOrganizationId int8;



update Organization_ set leftOrganizationId = 0, rightOrganizationId = 0;

update Region set regionCode = 'AB' where countryId = 1 and name = 'Alberta';

create table ResourceAction (
	resourceActionId int8 not null primary key,
	name varchar(75),
	actionId varchar(75),
	bitwiseValue int8
)
extent size 16 next size 16
lock mode row;

create table ResourcePermission (
	resourcePermissionId int8 not null primary key,
	companyId int8,
	name varchar(255),
	scope int,
	primKey varchar(255),
	roleId int8,
	actionIds int8
)
extent size 16 next size 16
lock mode row;



create unique index IX_8D83D0CE on ResourcePermission (companyId, name, scope, primKey, roleId);

create table Shard (
	shardId int8 not null primary key,
	classNameId int8,
	classPK int8,
	name varchar(75)
)
extent size 16 next size 16
lock mode row;

alter table User_ add firstName varchar(75);
alter table User_ add middleName varchar(75);
alter table User_ add lastName varchar(75);
alter table User_ add jobTitle varchar(100);

alter table WikiPage add groupId int8;

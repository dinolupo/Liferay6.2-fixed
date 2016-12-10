alter table BookmarksEntry add groupId number(30,0);

create table BrowserTracker (
	browserTrackerId number(30,0) not null primary key,
	userId number(30,0),
	browserKey number(30,0)
);

update CalEvent set remindBy = '0' where remindBy = 'none';
update CalEvent set remindBy = '1' where remindBy = 'email';
update CalEvent set remindBy = '2' where remindBy = 'sms';
update CalEvent set remindBy = '3' where remindBy = 'aim';
update CalEvent set remindBy = '4' where remindBy = 'icq';
update CalEvent set remindBy = '5' where remindBy = 'msn';
update CalEvent set remindBy = '6' where remindBy = 'ym';

alter table Company add system number(1, 0);

commit;

update Company set system = 0;

alter table DLFileEntry add groupId number(30,0);

alter table DLFileRank add groupId number(30,0);

alter table DLFileShortcut add groupId number(30,0);

alter table DLFileVersion add groupId number(30,0);

update Group_ set name = classPK where classPK > 0 and name = '';

alter table IGImage add groupId number(30,0);

alter table JournalArticle add urlTitle VARCHAR2(150 CHAR) null;

commit;

update JournalArticle set urlTitle = articleId;

alter table MBCategory add threadCount number(30,0);
alter table MBCategory add messageCount number(30,0);

alter table MBMessage add groupId number(30,0);
alter table MBMessage add classNameId number(30,0);
alter table MBMessage add classPK number(30,0);
alter table MBMessage add priority number(30,20);

delete from MBMessageFlag where flag = 1;

commit;

alter table MBMessageFlag add modifiedDate timestamp null;
alter table MBMessageFlag add threadId number(30,0);

alter table MBThread add groupId number(30,0);

alter table Organization_ add leftOrganizationId number(30,0);
alter table Organization_ add rightOrganizationId number(30,0);

commit;

update Organization_ set leftOrganizationId = 0, rightOrganizationId = 0;

update Region set regionCode = 'AB' where countryId = 1 and name = 'Alberta';

create table ResourceAction (
	resourceActionId number(30,0) not null primary key,
	name VARCHAR2(75 CHAR) null,
	actionId VARCHAR2(75 CHAR) null,
	bitwiseValue number(30,0)
);

create table ResourcePermission (
	resourcePermissionId number(30,0) not null primary key,
	companyId number(30,0),
	name VARCHAR2(255 CHAR) null,
	scope number(30,0),
	primKey VARCHAR2(255 CHAR) null,
	roleId number(30,0),
	actionIds number(30,0)
);

commit;

create unique index IX_8D83D0CE on ResourcePermission (companyId, name, scope, primKey, roleId);

create table Shard (
	shardId number(30,0) not null primary key,
	classNameId number(30,0),
	classPK number(30,0),
	name VARCHAR2(75 CHAR) null
);

alter table User_ add firstName VARCHAR2(75 CHAR) null;
alter table User_ add middleName VARCHAR2(75 CHAR) null;
alter table User_ add lastName VARCHAR2(75 CHAR) null;
alter table User_ add jobTitle VARCHAR2(100 CHAR) null;

alter table WikiPage add groupId number(30,0);

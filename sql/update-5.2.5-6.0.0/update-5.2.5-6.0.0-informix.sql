create table Lock_ (
	uuid_ varchar(75),
	lockId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	className varchar(75),
	key_ varchar(200),
	owner varchar(75),
	inheritable boolean,
	expirationDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

alter table Release_ add servletContextName varchar(75);



update Release_ set servletContextName = 'portal';

create table AssetCategory (
	uuid_ varchar(75),
	categoryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	parentCategoryId int8,
	leftCategoryId int8,
	rightCategoryId int8,
	name varchar(75),
	title lvarchar,
	vocabularyId int8
)
extent size 16 next size 16
lock mode row;

create table AssetCategoryProperty (
	categoryPropertyId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	categoryId int8,
	key_ varchar(75),
	value varchar(75)
)
extent size 16 next size 16
lock mode row;

create table AssetEntries_AssetCategories (
	entryId int8 not null,
	categoryId int8 not null,
	primary key (entryId, categoryId)
)
extent size 16 next size 16
lock mode row;

create table AssetEntries_AssetTags (
	entryId int8 not null,
	tagId int8 not null,
	primary key (entryId, tagId)
)
extent size 16 next size 16
lock mode row;

create table AssetEntry (
	entryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	visible boolean,
	startDate datetime YEAR TO FRACTION,
	endDate datetime YEAR TO FRACTION,
	publishDate datetime YEAR TO FRACTION,
	expirationDate datetime YEAR TO FRACTION,
	mimeType varchar(75),
	title varchar(255),
	description lvarchar,
	summary lvarchar,
	url lvarchar,
	height int,
	width int,
	priority float,
	viewCount int
)
extent size 16 next size 16
lock mode row;

create table AssetTag (
	tagId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name varchar(75),
	assetCount int
)
extent size 16 next size 16
lock mode row;

create table AssetTagProperty (
	tagPropertyId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	tagId int8,
	key_ varchar(75),
	value varchar(255)
)
extent size 16 next size 16
lock mode row;

create table AssetTagStats (
	tagStatsId int8 not null primary key,
	tagId int8,
	classNameId int8,
	assetCount int
)
extent size 16 next size 16
lock mode row;

create table AssetVocabulary (
	uuid_ varchar(75),
	vocabularyId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name varchar(75),
	title lvarchar,
	description lvarchar,
	settings_ lvarchar
)
extent size 16 next size 16
lock mode row;

alter table BlogsEntry add allowPingbacks boolean;
alter table BlogsEntry add status int;
alter table BlogsEntry add statusByUserId int8;
alter table BlogsEntry add statusByUserName varchar(75);
alter table BlogsEntry add statusDate datetime YEAR TO FRACTION;



update BlogsEntry set allowPingbacks = 'T';
update BlogsEntry set status = 0 where draft = 'F';
update BlogsEntry set status = 2 where draft = 'T';
update BlogsEntry set statusByUserId = userId where draft = 'F';
update BlogsEntry set statusByUserName = userName where draft = 'F';
update BlogsEntry set statusDate = createDate where draft = 'F';

alter table DLFileEntry add pendingVersion varchar(75);

alter table DLFileShortcut add status int;
alter table DLFileShortcut add statusByUserId int8;
alter table DLFileShortcut add statusByUserName varchar(75);
alter table DLFileShortcut add statusDate datetime YEAR TO FRACTION;



update DLFileShortcut set status = 0;
update DLFileShortcut set statusByUserId = userId;
update DLFileShortcut set statusByUserName = userId;
update DLFileShortcut set statusDate = createDate;

drop index IX_6C5E6512;

alter table DLFileVersion add description lvarchar;
alter table DLFileVersion add status int;
alter table DLFileVersion add statusByUserId int8;
alter table DLFileVersion add statusByUserName varchar(75);
alter table DLFileVersion add statusDate datetime YEAR TO FRACTION;



update DLFileVersion set status = 0;
update DLFileVersion set statusByUserId = userId;
update DLFileVersion set statusByUserName = userId;
update DLFileVersion set statusDate = createDate;

alter table JournalArticle add status int;
alter table JournalArticle add statusByUserId int8;
alter table JournalArticle add statusByUserName varchar(75);
alter table JournalArticle add statusDate datetime YEAR TO FRACTION;



update JournalArticle set status = 0 where approved = 'T';
update JournalArticle set status = 2 where approved = 'F';
update JournalArticle set statusByUserId = approvedByUserId;
update JournalArticle set statusByUserName = approvedByUserName;
update JournalArticle set statusDate = approvedDate where expired = 'F';
update JournalArticle set statusDate = expirationDate where expired = 'T';

alter table Layout add layoutPrototypeId int8;

create table LayoutPrototype (
	layoutPrototypeId int8 not null primary key,
	companyId int8,
	name lvarchar,
	description lvarchar,
	settings_ lvarchar,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

alter table LayoutSet add layoutSetPrototypeId int8;

create table LayoutSetPrototype (
	layoutSetPrototypeId int8 not null primary key,
	companyId int8,
	name lvarchar,
	description lvarchar,
	settings_ lvarchar,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

alter table MBMessage add allowPingbacks boolean;
alter table MBMessage add status int;
alter table MBMessage add statusByUserId int8;
alter table MBMessage add statusByUserName varchar(75);
alter table MBMessage add statusDate datetime YEAR TO FRACTION;



update MBMessage set allowPingbacks = 'T';
update MBMessage set status = 0;
update MBMessage set statusByUserId = userId;
update MBMessage set statusByUserName = userId;
update MBMessage set statusDate = createDate;

alter table MBThread add status int;
alter table MBThread add statusByUserId int8;
alter table MBThread add statusByUserName varchar(75);
alter table MBThread add statusDate datetime YEAR TO FRACTION;



update MBThread set status = 0;

alter table ShoppingItem add groupId int8;

alter table WikiPage add status int;
alter table WikiPage add statusByUserId int8;
alter table WikiPage add statusByUserName varchar(75);
alter table WikiPage add statusDate datetime YEAR TO FRACTION;



update WikiPage set status = 0;
update WikiPage set statusByUserId = userId;
update WikiPage set statusByUserName = userId;
update WikiPage set statusDate = createDate;

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	workflowDefinitionName varchar(75),
	workflowDefinitionVersion int
)
extent size 16 next size 16
lock mode row;

create table WorkflowInstanceLink (
	workflowInstanceLinkId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	workflowInstanceId int8
)
extent size 16 next size 16
lock mode row;

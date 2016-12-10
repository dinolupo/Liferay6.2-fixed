create table Lock_ (
	uuid_ varchar(75),
	lockId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	className varchar(75),
	key_ varchar(200),
	owner varchar(75),
	inheritable smallint,
	expirationDate timestamp
);

alter table Release_ add servletContextName varchar(75);

commit;

update Release_ set servletContextName = 'portal';

create table AssetCategory (
	uuid_ varchar(75),
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	parentCategoryId bigint,
	leftCategoryId bigint,
	rightCategoryId bigint,
	name varchar(75),
	title varchar(750),
	vocabularyId bigint
);

create table AssetCategoryProperty (
	categoryPropertyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	categoryId bigint,
	key_ varchar(75),
	value varchar(75)
);

create table AssetEntries_AssetCategories (
	entryId bigint not null,
	categoryId bigint not null,
	primary key (entryId, categoryId)
);

create table AssetEntries_AssetTags (
	entryId bigint not null,
	tagId bigint not null,
	primary key (entryId, tagId)
);

create table AssetEntry (
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	visible smallint,
	startDate timestamp,
	endDate timestamp,
	publishDate timestamp,
	expirationDate timestamp,
	mimeType varchar(75),
	title varchar(255),
	description varchar(750),
	summary varchar(750),
	url varchar(750),
	height integer,
	width integer,
	priority double,
	viewCount integer
);

create table AssetTag (
	tagId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	assetCount integer
);

create table AssetTagProperty (
	tagPropertyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	tagId bigint,
	key_ varchar(75),
	value varchar(255)
);

create table AssetTagStats (
	tagStatsId bigint not null primary key,
	tagId bigint,
	classNameId bigint,
	assetCount integer
);

create table AssetVocabulary (
	uuid_ varchar(75),
	vocabularyId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	title varchar(750),
	description varchar(750),
	settings_ varchar(750)
);

alter table BlogsEntry add allowPingbacks smallint;
alter table BlogsEntry add status integer;
alter table BlogsEntry add statusByUserId bigint;
alter table BlogsEntry add statusByUserName varchar(75);
alter table BlogsEntry add statusDate timestamp;

commit;

update BlogsEntry set allowPingbacks = 1;
update BlogsEntry set status = 0 where draft = 0;
update BlogsEntry set status = 2 where draft = 1;
update BlogsEntry set statusByUserId = userId where draft = 0;
update BlogsEntry set statusByUserName = userName where draft = 0;
update BlogsEntry set statusDate = createDate where draft = 0;

alter table DLFileEntry add pendingVersion varchar(75);

alter table DLFileShortcut add status integer;
alter table DLFileShortcut add statusByUserId bigint;
alter table DLFileShortcut add statusByUserName varchar(75);
alter table DLFileShortcut add statusDate timestamp;

commit;

update DLFileShortcut set status = 0;
update DLFileShortcut set statusByUserId = userId;
update DLFileShortcut set statusByUserName = userId;
update DLFileShortcut set statusDate = createDate;

drop index IX_6C5E6512;

alter table DLFileVersion add description varchar(750);
alter table DLFileVersion add status integer;
alter table DLFileVersion add statusByUserId bigint;
alter table DLFileVersion add statusByUserName varchar(75);
alter table DLFileVersion add statusDate timestamp;

commit;

update DLFileVersion set status = 0;
update DLFileVersion set statusByUserId = userId;
update DLFileVersion set statusByUserName = userId;
update DLFileVersion set statusDate = createDate;

alter table JournalArticle add status integer;
alter table JournalArticle add statusByUserId bigint;
alter table JournalArticle add statusByUserName varchar(75);
alter table JournalArticle add statusDate timestamp;

commit;

update JournalArticle set status = 0 where approved = 1;
update JournalArticle set status = 2 where approved = 0;
update JournalArticle set statusByUserId = approvedByUserId;
update JournalArticle set statusByUserName = approvedByUserName;
update JournalArticle set statusDate = approvedDate where expired = 0;
update JournalArticle set statusDate = expirationDate where expired = 1;

alter table Layout add layoutPrototypeId bigint;

create table LayoutPrototype (
	layoutPrototypeId bigint not null primary key,
	companyId bigint,
	name varchar(750),
	description varchar(750),
	settings_ varchar(750),
	active_ smallint
);

alter table LayoutSet add layoutSetPrototypeId bigint;

create table LayoutSetPrototype (
	layoutSetPrototypeId bigint not null primary key,
	companyId bigint,
	name varchar(750),
	description varchar(750),
	settings_ varchar(750),
	active_ smallint
);

alter table MBMessage add allowPingbacks smallint;
alter table MBMessage add status integer;
alter table MBMessage add statusByUserId bigint;
alter table MBMessage add statusByUserName varchar(75);
alter table MBMessage add statusDate timestamp;

commit;

update MBMessage set allowPingbacks = 1;
update MBMessage set status = 0;
update MBMessage set statusByUserId = userId;
update MBMessage set statusByUserName = userId;
update MBMessage set statusDate = createDate;

alter table MBThread add status integer;
alter table MBThread add statusByUserId bigint;
alter table MBThread add statusByUserName varchar(75);
alter table MBThread add statusDate timestamp;

commit;

update MBThread set status = 0;

alter table ShoppingItem add groupId bigint;

alter table WikiPage add status integer;
alter table WikiPage add statusByUserId bigint;
alter table WikiPage add statusByUserName varchar(75);
alter table WikiPage add statusDate timestamp;

commit;

update WikiPage set status = 0;
update WikiPage set statusByUserId = userId;
update WikiPage set statusByUserName = userId;
update WikiPage set statusDate = createDate;

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	workflowDefinitionName varchar(75),
	workflowDefinitionVersion integer
);

create table WorkflowInstanceLink (
	workflowInstanceLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	workflowInstanceId bigint
);

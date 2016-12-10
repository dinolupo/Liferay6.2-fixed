create table AssetCategory (
	uuid_ varchar(75) null,
	categoryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentCategoryId decimal(20,0),
	leftCategoryId decimal(20,0),
	rightCategoryId decimal(20,0),
	name varchar(75) null,
	title varchar(1000) null,
	vocabularyId decimal(20,0)
)
go

create table AssetCategoryProperty (
	categoryPropertyId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId decimal(20,0),
	key_ varchar(75) null,
	value varchar(75) null
)
go

create table AssetEntries_AssetCategories (
	entryId decimal(20,0) not null,
	categoryId decimal(20,0) not null,
	primary key (entryId, categoryId)
)
go

create table AssetEntries_AssetTags (
	entryId decimal(20,0) not null,
	tagId decimal(20,0) not null,
	primary key (entryId, tagId)
)
go

create table AssetEntry (
	entryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	visible int,
	startDate datetime null,
	endDate datetime null,
	publishDate datetime null,
	expirationDate datetime null,
	mimeType varchar(75) null,
	title varchar(255) null,
	description varchar(1000) null,
	summary varchar(1000) null,
	url varchar(1000) null,
	height int,
	width int,
	priority float,
	viewCount int
)
go

create table AssetTag (
	tagId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	assetCount int
)
go

create table AssetTagProperty (
	tagPropertyId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	tagId decimal(20,0),
	key_ varchar(75) null,
	value varchar(255) null
)
go

create table AssetTagStats (
	tagStatsId decimal(20,0) not null primary key,
	tagId decimal(20,0),
	classNameId decimal(20,0),
	assetCount int
)
go

create table AssetVocabulary (
	uuid_ varchar(75) null,
	vocabularyId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	title varchar(1000) null,
	description varchar(1000) null,
	settings_ varchar(1000) null
)
go

alter table BlogsEntry add allowPingbacks int;
alter table BlogsEntry add status int;
alter table BlogsEntry add statusByUserId decimal(20,0)
go
alter table BlogsEntry add statusByUserName varchar(75)
go
alter table BlogsEntry add statusDate datetime;

go

update BlogsEntry set allowPingbacks = 1;
update BlogsEntry set status = 0 where draft = 0;
update BlogsEntry set status = 2 where draft = 1;
update BlogsEntry set statusByUserId = userId where draft = 0;
update BlogsEntry set statusByUserName = userName where draft = 0;
update BlogsEntry set statusDate = createDate where draft = 0;

alter table DLFileEntry add pendingVersion varchar(75) null;

alter table DLFileShortcut add status int;
alter table DLFileShortcut add statusByUserId decimal(20,0)
go
alter table DLFileShortcut add statusByUserName varchar(75)
go
alter table DLFileShortcut add statusDate datetime;

go

update DLFileShortcut set status = 0;
update DLFileShortcut set statusByUserId = userId;
update DLFileShortcut set statusByUserName = userId;
update DLFileShortcut set statusDate = createDate;

drop index DLFileVersion.IX_6C5E6512;

alter table DLFileVersion add description varchar(1000) null;
alter table DLFileVersion add status int;
alter table DLFileVersion add statusByUserId decimal(20,0)
go
alter table DLFileVersion add statusByUserName varchar(75)
go
alter table DLFileVersion add statusDate datetime;

go

update DLFileVersion set status = 0;
update DLFileVersion set statusByUserId = userId;
update DLFileVersion set statusByUserName = userId;
update DLFileVersion set statusDate = createDate;

alter table JournalArticle add status int;
alter table JournalArticle add statusByUserId decimal(20,0)
go
alter table JournalArticle add statusByUserName varchar(75)
go
alter table JournalArticle add statusDate datetime;

go

update JournalArticle set status = 0 where approved = 1;
update JournalArticle set status = 2 where approved = 0;
update JournalArticle set statusByUserId = approvedByUserId;
update JournalArticle set statusByUserName = approvedByUserName;
update JournalArticle set statusDate = approvedDate where expired = 0;
update JournalArticle set statusDate = expirationDate where expired = 1;

alter table Layout add layoutPrototypeId decimal(20,0)
go

create table LayoutPrototype (
	layoutPrototypeId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	name varchar(1000) null,
	description varchar(1000) null,
	settings_ varchar(1000) null,
	active_ int
)
go

alter table LayoutSet add layoutSetPrototypeId decimal(20,0)
go

create table LayoutSetPrototype (
	layoutSetPrototypeId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	name varchar(1000) null,
	description varchar(1000) null,
	settings_ varchar(1000) null,
	active_ int
)
go

alter table MBMessage add allowPingbacks int;
alter table MBMessage add status int;
alter table MBMessage add statusByUserId decimal(20,0)
go
alter table MBMessage add statusByUserName varchar(75)
go
alter table MBMessage add statusDate datetime;

go

update MBMessage set allowPingbacks = 1;
update MBMessage set status = 0;
update MBMessage set statusByUserId = userId;
update MBMessage set statusByUserName = userId;
update MBMessage set statusDate = createDate;

alter table MBThread add status int;
alter table MBThread add statusByUserId decimal(20,0)
go
alter table MBThread add statusByUserName varchar(75)
go
alter table MBThread add statusDate datetime;

go

update MBThread set status = 0;

alter table ShoppingItem add groupId decimal(20,0)
go

alter table WikiPage add status int;
alter table WikiPage add statusByUserId decimal(20,0)
go
alter table WikiPage add statusByUserName varchar(75)
go
alter table WikiPage add statusDate datetime;

go

update WikiPage set status = 0;
update WikiPage set statusByUserId = userId;
update WikiPage set statusByUserName = userId;
update WikiPage set statusDate = createDate;

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	workflowDefinitionName varchar(75) null,
	workflowDefinitionVersion int
)
go

create table WorkflowInstanceLink (
	workflowInstanceLinkId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	workflowInstanceId decimal(20,0)
)
go

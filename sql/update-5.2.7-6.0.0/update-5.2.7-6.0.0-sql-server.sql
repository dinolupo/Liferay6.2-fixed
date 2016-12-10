create table AssetCategory (
	uuid_ nvarchar(75) null,
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentCategoryId bigint,
	leftCategoryId bigint,
	rightCategoryId bigint,
	name nvarchar(75) null,
	title nvarchar(2000) null,
	vocabularyId bigint
);

create table AssetCategoryProperty (
	categoryPropertyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId bigint,
	key_ nvarchar(75) null,
	value nvarchar(75) null
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
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	visible bit,
	startDate datetime null,
	endDate datetime null,
	publishDate datetime null,
	expirationDate datetime null,
	mimeType nvarchar(75) null,
	title nvarchar(255) null,
	description nvarchar(2000) null,
	summary nvarchar(2000) null,
	url nvarchar(2000) null,
	height int,
	width int,
	priority float,
	viewCount int
);

create table AssetTag (
	tagId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(75) null,
	assetCount int
);

create table AssetTagProperty (
	tagPropertyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	tagId bigint,
	key_ nvarchar(75) null,
	value nvarchar(255) null
);

create table AssetTagStats (
	tagStatsId bigint not null primary key,
	tagId bigint,
	classNameId bigint,
	assetCount int
);

create table AssetVocabulary (
	uuid_ nvarchar(75) null,
	vocabularyId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(75) null,
	title nvarchar(2000) null,
	description nvarchar(2000) null,
	settings_ nvarchar(2000) null
);

alter table BlogsEntry add allowPingbacks bit;
alter table BlogsEntry add status int;
alter table BlogsEntry add statusByUserId bigint;
alter table BlogsEntry add statusByUserName nvarchar(75);
alter table BlogsEntry add statusDate datetime;

go

update BlogsEntry set allowPingbacks = 1;
update BlogsEntry set status = 0 where draft = 0;
update BlogsEntry set status = 2 where draft = 1;
update BlogsEntry set statusByUserId = userId where draft = 0;
update BlogsEntry set statusByUserName = userName where draft = 0;
update BlogsEntry set statusDate = createDate where draft = 0;

alter table DLFileEntry add pendingVersion nvarchar(75) null;

alter table DLFileShortcut add status int;
alter table DLFileShortcut add statusByUserId bigint;
alter table DLFileShortcut add statusByUserName nvarchar(75);
alter table DLFileShortcut add statusDate datetime;

go

update DLFileShortcut set status = 0;
update DLFileShortcut set statusByUserId = userId;
update DLFileShortcut set statusByUserName = userId;
update DLFileShortcut set statusDate = createDate;

drop index DLFileVersion.IX_6C5E6512;

alter table DLFileVersion add description nvarchar(2000) null;
alter table DLFileVersion add status int;
alter table DLFileVersion add statusByUserId bigint;
alter table DLFileVersion add statusByUserName nvarchar(75);
alter table DLFileVersion add statusDate datetime;

go

update DLFileVersion set status = 0;
update DLFileVersion set statusByUserId = userId;
update DLFileVersion set statusByUserName = userId;
update DLFileVersion set statusDate = createDate;

alter table JournalArticle add status int;
alter table JournalArticle add statusByUserId bigint;
alter table JournalArticle add statusByUserName nvarchar(75);
alter table JournalArticle add statusDate datetime;

go

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
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	settings_ nvarchar(2000) null,
	active_ bit
);

alter table LayoutSet add layoutSetPrototypeId bigint;

create table LayoutSetPrototype (
	layoutSetPrototypeId bigint not null primary key,
	companyId bigint,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	settings_ nvarchar(2000) null,
	active_ bit
);

alter table MBMessage add allowPingbacks bit;
alter table MBMessage add status int;
alter table MBMessage add statusByUserId bigint;
alter table MBMessage add statusByUserName nvarchar(75);
alter table MBMessage add statusDate datetime;

go

update MBMessage set allowPingbacks = 1;
update MBMessage set status = 0;
update MBMessage set statusByUserId = userId;
update MBMessage set statusByUserName = userId;
update MBMessage set statusDate = createDate;

alter table MBThread add status int;
alter table MBThread add statusByUserId bigint;
alter table MBThread add statusByUserName nvarchar(75);
alter table MBThread add statusDate datetime;

go

update MBThread set status = 0;

alter table ShoppingItem add groupId bigint;

alter table WikiPage add status int;
alter table WikiPage add statusByUserId bigint;
alter table WikiPage add statusByUserName nvarchar(75);
alter table WikiPage add statusDate datetime;

go

update WikiPage set status = 0;
update WikiPage set statusByUserId = userId;
update WikiPage set statusByUserName = userId;
update WikiPage set statusDate = createDate;

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	workflowDefinitionName nvarchar(75) null,
	workflowDefinitionVersion int
);

create table WorkflowInstanceLink (
	workflowInstanceLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	workflowInstanceId bigint
);

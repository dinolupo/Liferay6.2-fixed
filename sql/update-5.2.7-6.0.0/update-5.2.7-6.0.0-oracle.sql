create table AssetCategory (
	uuid_ VARCHAR2(75 CHAR) null,
	categoryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	parentCategoryId number(30,0),
	leftCategoryId number(30,0),
	rightCategoryId number(30,0),
	name VARCHAR2(75 CHAR) null,
	title varchar2(4000) null,
	vocabularyId number(30,0)
);

create table AssetCategoryProperty (
	categoryPropertyId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	categoryId number(30,0),
	key_ VARCHAR2(75 CHAR) null,
	value VARCHAR2(75 CHAR) null
);

create table AssetEntries_AssetCategories (
	entryId number(30,0) not null,
	categoryId number(30,0) not null,
	primary key (entryId, categoryId)
);

create table AssetEntries_AssetTags (
	entryId number(30,0) not null,
	tagId number(30,0) not null,
	primary key (entryId, tagId)
);

create table AssetEntry (
	entryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	visible number(1, 0),
	startDate timestamp null,
	endDate timestamp null,
	publishDate timestamp null,
	expirationDate timestamp null,
	mimeType VARCHAR2(75 CHAR) null,
	title VARCHAR2(255 CHAR) null,
	description varchar2(4000) null,
	summary varchar2(4000) null,
	url varchar2(4000) null,
	height number(30,0),
	width number(30,0),
	priority number(30,20),
	viewCount number(30,0)
);

create table AssetTag (
	tagId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name VARCHAR2(75 CHAR) null,
	assetCount number(30,0)
);

create table AssetTagProperty (
	tagPropertyId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	tagId number(30,0),
	key_ VARCHAR2(75 CHAR) null,
	value VARCHAR2(255 CHAR) null
);

create table AssetTagStats (
	tagStatsId number(30,0) not null primary key,
	tagId number(30,0),
	classNameId number(30,0),
	assetCount number(30,0)
);

create table AssetVocabulary (
	uuid_ VARCHAR2(75 CHAR) null,
	vocabularyId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name VARCHAR2(75 CHAR) null,
	title varchar2(4000) null,
	description varchar2(4000) null,
	settings_ varchar2(4000) null
);

alter table BlogsEntry add allowPingbacks number(1, 0);
alter table BlogsEntry add status number(30,0);
alter table BlogsEntry add statusByUserId number(30,0);
alter table BlogsEntry add statusByUserName VARCHAR2(75 CHAR);
alter table BlogsEntry add statusDate timestamp;

commit;

update BlogsEntry set allowPingbacks = 1;
update BlogsEntry set status = 0 where draft = 0;
update BlogsEntry set status = 2 where draft = 1;
update BlogsEntry set statusByUserId = userId where draft = 0;
update BlogsEntry set statusByUserName = userName where draft = 0;
update BlogsEntry set statusDate = createDate where draft = 0;

alter table DLFileEntry add pendingVersion VARCHAR2(75 CHAR) null;

alter table DLFileShortcut add status number(30,0);
alter table DLFileShortcut add statusByUserId number(30,0);
alter table DLFileShortcut add statusByUserName VARCHAR2(75 CHAR);
alter table DLFileShortcut add statusDate timestamp;

commit;

update DLFileShortcut set status = 0;
update DLFileShortcut set statusByUserId = userId;
update DLFileShortcut set statusByUserName = userId;
update DLFileShortcut set statusDate = createDate;

drop index IX_6C5E6512;

alter table DLFileVersion add description varchar2(4000) null;
alter table DLFileVersion add status number(30,0);
alter table DLFileVersion add statusByUserId number(30,0);
alter table DLFileVersion add statusByUserName VARCHAR2(75 CHAR);
alter table DLFileVersion add statusDate timestamp;

commit;

update DLFileVersion set status = 0;
update DLFileVersion set statusByUserId = userId;
update DLFileVersion set statusByUserName = userId;
update DLFileVersion set statusDate = createDate;

alter table JournalArticle add status number(30,0);
alter table JournalArticle add statusByUserId number(30,0);
alter table JournalArticle add statusByUserName VARCHAR2(75 CHAR);
alter table JournalArticle add statusDate timestamp;

commit;

update JournalArticle set status = 0 where approved = 1;
update JournalArticle set status = 2 where approved = 0;
update JournalArticle set statusByUserId = approvedByUserId;
update JournalArticle set statusByUserName = approvedByUserName;
update JournalArticle set statusDate = approvedDate where expired = 0;
update JournalArticle set statusDate = expirationDate where expired = 1;

alter table Layout add layoutPrototypeId number(30,0);

create table LayoutPrototype (
	layoutPrototypeId number(30,0) not null primary key,
	companyId number(30,0),
	name varchar2(4000) null,
	description varchar2(4000) null,
	settings_ varchar2(4000) null,
	active_ number(1, 0)
);

alter table LayoutSet add layoutSetPrototypeId number(30,0);

create table LayoutSetPrototype (
	layoutSetPrototypeId number(30,0) not null primary key,
	companyId number(30,0),
	name varchar2(4000) null,
	description varchar2(4000) null,
	settings_ varchar2(4000) null,
	active_ number(1, 0)
);

alter table MBMessage add allowPingbacks number(1, 0);
alter table MBMessage add status number(30,0);
alter table MBMessage add statusByUserId number(30,0);
alter table MBMessage add statusByUserName VARCHAR2(75 CHAR);
alter table MBMessage add statusDate timestamp;

commit;

update MBMessage set allowPingbacks = 1;
update MBMessage set status = 0;
update MBMessage set statusByUserId = userId;
update MBMessage set statusByUserName = userId;
update MBMessage set statusDate = createDate;

alter table MBThread add status number(30,0);
alter table MBThread add statusByUserId number(30,0);
alter table MBThread add statusByUserName VARCHAR2(75 CHAR);
alter table MBThread add statusDate timestamp;

commit;

update MBThread set status = 0;

alter table ShoppingItem add groupId number(30,0);

alter table WikiPage add status number(30,0);
alter table WikiPage add statusByUserId number(30,0);
alter table WikiPage add statusByUserName VARCHAR2(75 CHAR);
alter table WikiPage add statusDate timestamp;

commit;

update WikiPage set status = 0;
update WikiPage set statusByUserId = userId;
update WikiPage set statusByUserName = userId;
update WikiPage set statusDate = createDate;

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	workflowDefinitionName VARCHAR2(75 CHAR) null,
	workflowDefinitionVersion number(30,0)
);

create table WorkflowInstanceLink (
	workflowInstanceLinkId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	workflowInstanceId number(30,0)
);

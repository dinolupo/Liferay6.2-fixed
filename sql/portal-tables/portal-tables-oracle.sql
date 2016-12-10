create table Account_ (
	accountId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	parentAccountId number(30,0),
	name VARCHAR2(75 CHAR) null,
	legalName VARCHAR2(75 CHAR) null,
	legalId VARCHAR2(75 CHAR) null,
	legalType VARCHAR2(75 CHAR) null,
	sicCode VARCHAR2(75 CHAR) null,
	tickerSymbol VARCHAR2(75 CHAR) null,
	industry VARCHAR2(75 CHAR) null,
	type_ VARCHAR2(75 CHAR) null,
	size_ VARCHAR2(75 CHAR) null
);

create table Address (
	uuid_ VARCHAR2(75 CHAR) null,
	addressId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	street1 VARCHAR2(75 CHAR) null,
	street2 VARCHAR2(75 CHAR) null,
	street3 VARCHAR2(75 CHAR) null,
	city VARCHAR2(75 CHAR) null,
	zip VARCHAR2(75 CHAR) null,
	regionId number(30,0),
	countryId number(30,0),
	typeId number(30,0),
	mailing number(1, 0),
	primary_ number(1, 0)
);

create table AnnouncementsDelivery (
	deliveryId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	type_ VARCHAR2(75 CHAR) null,
	email number(1, 0),
	sms number(1, 0),
	website number(1, 0)
);

create table AnnouncementsEntry (
	uuid_ VARCHAR2(75 CHAR) null,
	entryId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	title VARCHAR2(75 CHAR) null,
	content clob null,
	url varchar2(4000) null,
	type_ VARCHAR2(75 CHAR) null,
	displayDate timestamp null,
	expirationDate timestamp null,
	priority number(30,0),
	alert number(1, 0)
);

create table AnnouncementsFlag (
	flagId number(30,0) not null primary key,
	userId number(30,0),
	createDate timestamp null,
	entryId number(30,0),
	value number(30,0)
);

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
	description varchar2(4000) null,
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
	categoryId number(30,0) not null,
	entryId number(30,0) not null,
	primary key (categoryId, entryId)
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
	classUuid VARCHAR2(75 CHAR) null,
	classTypeId number(30,0),
	visible number(1, 0),
	startDate timestamp null,
	endDate timestamp null,
	publishDate timestamp null,
	expirationDate timestamp null,
	mimeType VARCHAR2(75 CHAR) null,
	title varchar2(4000) null,
	description clob null,
	summary clob null,
	url varchar2(4000) null,
	layoutUuid VARCHAR2(75 CHAR) null,
	height number(30,0),
	width number(30,0),
	priority number(30,20),
	viewCount number(30,0)
);

create table AssetLink (
	linkId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	entryId1 number(30,0),
	entryId2 number(30,0),
	type_ number(30,0),
	weight number(30,0)
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

create table BackgroundTask (
	backgroundTaskId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name VARCHAR2(75 CHAR) null,
	servletContextNames VARCHAR2(255 CHAR) null,
	taskExecutorClassName VARCHAR2(200 CHAR) null,
	taskContext clob null,
	completed number(1, 0),
	completionDate timestamp null,
	status number(30,0),
	statusMessage clob null
);

create table BlogsEntry (
	uuid_ VARCHAR2(75 CHAR) null,
	entryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	title VARCHAR2(150 CHAR) null,
	urlTitle VARCHAR2(150 CHAR) null,
	description varchar2(4000) null,
	content clob null,
	displayDate timestamp null,
	allowPingbacks number(1, 0),
	allowTrackbacks number(1, 0),
	trackbacks clob null,
	smallImage number(1, 0),
	smallImageId number(30,0),
	smallImageURL varchar2(4000) null,
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table BlogsStatsUser (
	statsUserId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	entryCount number(30,0),
	lastPostDate timestamp null,
	ratingsTotalEntries number(30,0),
	ratingsTotalScore number(30,20),
	ratingsAverageScore number(30,20)
);

create table BookmarksEntry (
	uuid_ VARCHAR2(75 CHAR) null,
	entryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	resourceBlockId number(30,0),
	folderId number(30,0),
	treePath varchar2(4000) null,
	name VARCHAR2(255 CHAR) null,
	url varchar2(4000) null,
	description varchar2(4000) null,
	visits number(30,0),
	priority number(30,0),
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table BookmarksFolder (
	uuid_ VARCHAR2(75 CHAR) null,
	folderId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	resourceBlockId number(30,0),
	parentFolderId number(30,0),
	treePath varchar2(4000) null,
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table BrowserTracker (
	browserTrackerId number(30,0) not null primary key,
	userId number(30,0),
	browserKey number(30,0)
);

create table CalEvent (
	uuid_ VARCHAR2(75 CHAR) null,
	eventId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	title VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	location varchar2(4000) null,
	startDate timestamp null,
	endDate timestamp null,
	durationHour number(30,0),
	durationMinute number(30,0),
	allDay number(1, 0),
	timeZoneSensitive number(1, 0),
	type_ VARCHAR2(75 CHAR) null,
	repeating number(1, 0),
	recurrence clob null,
	remindBy number(30,0),
	firstReminder number(30,0),
	secondReminder number(30,0)
);

create table ClassName_ (
	classNameId number(30,0) not null primary key,
	value VARCHAR2(200 CHAR) null
);

create table ClusterGroup (
	clusterGroupId number(30,0) not null primary key,
	name VARCHAR2(75 CHAR) null,
	clusterNodeIds VARCHAR2(75 CHAR) null,
	wholeCluster number(1, 0)
);

create table Company (
	companyId number(30,0) not null primary key,
	accountId number(30,0),
	webId VARCHAR2(75 CHAR) null,
	key_ clob null,
	mx VARCHAR2(75 CHAR) null,
	homeURL varchar2(4000) null,
	logoId number(30,0),
	system number(1, 0),
	maxUsers number(30,0),
	active_ number(1, 0)
);

create table Contact_ (
	contactId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	accountId number(30,0),
	parentContactId number(30,0),
	emailAddress VARCHAR2(75 CHAR) null,
	firstName VARCHAR2(75 CHAR) null,
	middleName VARCHAR2(75 CHAR) null,
	lastName VARCHAR2(75 CHAR) null,
	prefixId number(30,0),
	suffixId number(30,0),
	male number(1, 0),
	birthday timestamp null,
	smsSn VARCHAR2(75 CHAR) null,
	aimSn VARCHAR2(75 CHAR) null,
	facebookSn VARCHAR2(75 CHAR) null,
	icqSn VARCHAR2(75 CHAR) null,
	jabberSn VARCHAR2(75 CHAR) null,
	msnSn VARCHAR2(75 CHAR) null,
	mySpaceSn VARCHAR2(75 CHAR) null,
	skypeSn VARCHAR2(75 CHAR) null,
	twitterSn VARCHAR2(75 CHAR) null,
	ymSn VARCHAR2(75 CHAR) null,
	employeeStatusId VARCHAR2(75 CHAR) null,
	employeeNumber VARCHAR2(75 CHAR) null,
	jobTitle VARCHAR2(100 CHAR) null,
	jobClass VARCHAR2(75 CHAR) null,
	hoursOfOperation VARCHAR2(75 CHAR) null
);

create table Counter (
	name VARCHAR2(75 CHAR) not null primary key,
	currentId number(30,0)
);

create table Country (
	countryId number(30,0) not null primary key,
	name VARCHAR2(75 CHAR) null,
	a2 VARCHAR2(75 CHAR) null,
	a3 VARCHAR2(75 CHAR) null,
	number_ VARCHAR2(75 CHAR) null,
	idd_ VARCHAR2(75 CHAR) null,
	zipRequired number(1, 0),
	active_ number(1, 0)
);

create table CyrusUser (
	userId VARCHAR2(75 CHAR) not null primary key,
	password_ VARCHAR2(75 CHAR) not null
);

create table CyrusVirtual (
	emailAddress VARCHAR2(75 CHAR) not null primary key,
	userId VARCHAR2(75 CHAR) not null
);

create table DDLRecord (
	uuid_ VARCHAR2(75 CHAR) null,
	recordId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	versionUserId number(30,0),
	versionUserName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	DDMStorageId number(30,0),
	recordSetId number(30,0),
	version VARCHAR2(75 CHAR) null,
	displayIndex number(30,0)
);

create table DDLRecordSet (
	uuid_ VARCHAR2(75 CHAR) null,
	recordSetId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	DDMStructureId number(30,0),
	recordSetKey VARCHAR2(75 CHAR) null,
	name varchar2(4000) null,
	description varchar2(4000) null,
	minDisplayRows number(30,0),
	scope number(30,0)
);

create table DDLRecordVersion (
	recordVersionId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	DDMStorageId number(30,0),
	recordSetId number(30,0),
	recordId number(30,0),
	version VARCHAR2(75 CHAR) null,
	displayIndex number(30,0),
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table DDMContent (
	uuid_ VARCHAR2(75 CHAR) null,
	contentId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name varchar2(4000) null,
	description varchar2(4000) null,
	xml clob null
);

create table DDMStorageLink (
	uuid_ VARCHAR2(75 CHAR) null,
	storageLinkId number(30,0) not null primary key,
	classNameId number(30,0),
	classPK number(30,0),
	structureId number(30,0)
);

create table DDMStructure (
	uuid_ VARCHAR2(75 CHAR) null,
	structureId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	parentStructureId number(30,0),
	classNameId number(30,0),
	structureKey VARCHAR2(75 CHAR) null,
	name varchar2(4000) null,
	description varchar2(4000) null,
	xsd clob null,
	storageType VARCHAR2(75 CHAR) null,
	type_ number(30,0)
);

create table DDMStructureLink (
	structureLinkId number(30,0) not null primary key,
	classNameId number(30,0),
	classPK number(30,0),
	structureId number(30,0)
);

create table DDMTemplate (
	uuid_ VARCHAR2(75 CHAR) null,
	templateId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	templateKey VARCHAR2(75 CHAR) null,
	name varchar2(4000) null,
	description varchar2(4000) null,
	type_ VARCHAR2(75 CHAR) null,
	mode_ VARCHAR2(75 CHAR) null,
	language VARCHAR2(75 CHAR) null,
	script clob null,
	cacheable number(1, 0),
	smallImage number(1, 0),
	smallImageId number(30,0),
	smallImageURL VARCHAR2(75 CHAR) null
);

create table DLContent (
	contentId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	repositoryId number(30,0),
	path_ VARCHAR2(255 CHAR) null,
	version VARCHAR2(75 CHAR) null,
	data_ blob,
	size_ number(30,0)
);

create table DLFileEntry (
	uuid_ VARCHAR2(75 CHAR) null,
	fileEntryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	repositoryId number(30,0),
	folderId number(30,0),
	treePath varchar2(4000) null,
	name VARCHAR2(255 CHAR) null,
	extension VARCHAR2(75 CHAR) null,
	mimeType VARCHAR2(75 CHAR) null,
	title VARCHAR2(255 CHAR) null,
	description varchar2(4000) null,
	extraSettings clob null,
	fileEntryTypeId number(30,0),
	version VARCHAR2(75 CHAR) null,
	size_ number(30,0),
	readCount number(30,0),
	smallImageId number(30,0),
	largeImageId number(30,0),
	custom1ImageId number(30,0),
	custom2ImageId number(30,0),
	manualCheckInRequired number(1, 0)
);

create table DLFileEntryMetadata (
	uuid_ VARCHAR2(75 CHAR) null,
	fileEntryMetadataId number(30,0) not null primary key,
	DDMStorageId number(30,0),
	DDMStructureId number(30,0),
	fileEntryTypeId number(30,0),
	fileEntryId number(30,0),
	fileVersionId number(30,0)
);

create table DLFileEntryType (
	uuid_ VARCHAR2(75 CHAR) null,
	fileEntryTypeId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	fileEntryTypeKey VARCHAR2(75 CHAR) null,
	name varchar2(4000) null,
	description varchar2(4000) null
);

create table DLFileEntryTypes_DDMStructures (
	structureId number(30,0) not null,
	fileEntryTypeId number(30,0) not null,
	primary key (structureId, fileEntryTypeId)
);

create table DLFileEntryTypes_DLFolders (
	fileEntryTypeId number(30,0) not null,
	folderId number(30,0) not null,
	primary key (fileEntryTypeId, folderId)
);

create table DLFileRank (
	fileRankId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	createDate timestamp null,
	fileEntryId number(30,0),
	active_ number(1, 0)
);

create table DLFileShortcut (
	uuid_ VARCHAR2(75 CHAR) null,
	fileShortcutId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	repositoryId number(30,0),
	folderId number(30,0),
	toFileEntryId number(30,0),
	treePath varchar2(4000) null,
	active_ number(1, 0),
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table DLFileVersion (
	uuid_ VARCHAR2(75 CHAR) null,
	fileVersionId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	repositoryId number(30,0),
	folderId number(30,0),
	fileEntryId number(30,0),
	treePath varchar2(4000) null,
	extension VARCHAR2(75 CHAR) null,
	mimeType VARCHAR2(75 CHAR) null,
	title VARCHAR2(255 CHAR) null,
	description varchar2(4000) null,
	changeLog VARCHAR2(75 CHAR) null,
	extraSettings clob null,
	fileEntryTypeId number(30,0),
	version VARCHAR2(75 CHAR) null,
	size_ number(30,0),
	checksum VARCHAR2(75 CHAR) null,
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table DLFolder (
	uuid_ VARCHAR2(75 CHAR) null,
	folderId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	repositoryId number(30,0),
	mountPoint number(1, 0),
	parentFolderId number(30,0),
	treePath varchar2(4000) null,
	name VARCHAR2(100 CHAR) null,
	description varchar2(4000) null,
	lastPostDate timestamp null,
	defaultFileEntryTypeId number(30,0),
	hidden_ number(1, 0),
	overrideFileEntryTypes number(1, 0),
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table DLSyncEvent (
	syncEventId number(30,0) not null primary key,
	modifiedTime number(30,0),
	event VARCHAR2(75 CHAR) null,
	type_ VARCHAR2(75 CHAR) null,
	typePK number(30,0)
);

create table EmailAddress (
	uuid_ VARCHAR2(75 CHAR) null,
	emailAddressId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	address VARCHAR2(75 CHAR) null,
	typeId number(30,0),
	primary_ number(1, 0)
);

create table ExpandoColumn (
	columnId number(30,0) not null primary key,
	companyId number(30,0),
	tableId number(30,0),
	name VARCHAR2(75 CHAR) null,
	type_ number(30,0),
	defaultData varchar2(4000) null,
	typeSettings clob null
);

create table ExpandoRow (
	rowId_ number(30,0) not null primary key,
	companyId number(30,0),
	modifiedDate timestamp null,
	tableId number(30,0),
	classPK number(30,0)
);

create table ExpandoTable (
	tableId number(30,0) not null primary key,
	companyId number(30,0),
	classNameId number(30,0),
	name VARCHAR2(75 CHAR) null
);

create table ExpandoValue (
	valueId number(30,0) not null primary key,
	companyId number(30,0),
	tableId number(30,0),
	columnId number(30,0),
	rowId_ number(30,0),
	classNameId number(30,0),
	classPK number(30,0),
	data_ varchar2(4000) null
);

create table Group_ (
	uuid_ VARCHAR2(75 CHAR) null,
	groupId number(30,0) not null primary key,
	companyId number(30,0),
	creatorUserId number(30,0),
	classNameId number(30,0),
	classPK number(30,0),
	parentGroupId number(30,0),
	liveGroupId number(30,0),
	treePath varchar2(4000) null,
	name VARCHAR2(150 CHAR) null,
	description varchar2(4000) null,
	type_ number(30,0),
	typeSettings clob null,
	manualMembership number(1, 0),
	membershipRestriction number(30,0),
	friendlyURL VARCHAR2(255 CHAR) null,
	site number(1, 0),
	remoteStagingGroupCount number(30,0),
	active_ number(1, 0)
);

create table Groups_Orgs (
	groupId number(30,0) not null,
	organizationId number(30,0) not null,
	primary key (groupId, organizationId)
);

create table Groups_Roles (
	groupId number(30,0) not null,
	roleId number(30,0) not null,
	primary key (groupId, roleId)
);

create table Groups_UserGroups (
	groupId number(30,0) not null,
	userGroupId number(30,0) not null,
	primary key (groupId, userGroupId)
);

create table Image (
	imageId number(30,0) not null primary key,
	modifiedDate timestamp null,
	type_ VARCHAR2(75 CHAR) null,
	height number(30,0),
	width number(30,0),
	size_ number(30,0)
);

create table JournalArticle (
	uuid_ VARCHAR2(75 CHAR) null,
	id_ number(30,0) not null primary key,
	resourcePrimKey number(30,0),
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	folderId number(30,0),
	classNameId number(30,0),
	classPK number(30,0),
	treePath varchar2(4000) null,
	articleId VARCHAR2(75 CHAR) null,
	version number(30,20),
	title varchar2(4000) null,
	urlTitle VARCHAR2(150 CHAR) null,
	description clob null,
	content clob null,
	type_ VARCHAR2(75 CHAR) null,
	structureId VARCHAR2(75 CHAR) null,
	templateId VARCHAR2(75 CHAR) null,
	layoutUuid VARCHAR2(75 CHAR) null,
	displayDate timestamp null,
	expirationDate timestamp null,
	reviewDate timestamp null,
	indexable number(1, 0),
	smallImage number(1, 0),
	smallImageId number(30,0),
	smallImageURL varchar2(4000) null,
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table JournalArticleImage (
	articleImageId number(30,0) not null primary key,
	groupId number(30,0),
	articleId VARCHAR2(75 CHAR) null,
	version number(30,20),
	elInstanceId VARCHAR2(75 CHAR) null,
	elName VARCHAR2(75 CHAR) null,
	languageId VARCHAR2(75 CHAR) null,
	tempImage number(1, 0)
);

create table JournalArticleResource (
	uuid_ VARCHAR2(75 CHAR) null,
	resourcePrimKey number(30,0) not null primary key,
	groupId number(30,0),
	articleId VARCHAR2(75 CHAR) null
);

create table JournalContentSearch (
	contentSearchId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	privateLayout number(1, 0),
	layoutId number(30,0),
	portletId VARCHAR2(200 CHAR) null,
	articleId VARCHAR2(75 CHAR) null
);

create table JournalFeed (
	uuid_ VARCHAR2(75 CHAR) null,
	id_ number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	feedId VARCHAR2(75 CHAR) null,
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	type_ VARCHAR2(75 CHAR) null,
	structureId VARCHAR2(75 CHAR) null,
	templateId VARCHAR2(75 CHAR) null,
	rendererTemplateId VARCHAR2(75 CHAR) null,
	delta number(30,0),
	orderByCol VARCHAR2(75 CHAR) null,
	orderByType VARCHAR2(75 CHAR) null,
	targetLayoutFriendlyUrl VARCHAR2(255 CHAR) null,
	targetPortletId VARCHAR2(75 CHAR) null,
	contentField VARCHAR2(75 CHAR) null,
	feedFormat VARCHAR2(75 CHAR) null,
	feedVersion number(30,20)
);

create table JournalFolder (
	uuid_ VARCHAR2(75 CHAR) null,
	folderId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	parentFolderId number(30,0),
	treePath varchar2(4000) null,
	name VARCHAR2(100 CHAR) null,
	description varchar2(4000) null,
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table Layout (
	uuid_ VARCHAR2(75 CHAR) null,
	plid number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	privateLayout number(1, 0),
	layoutId number(30,0),
	parentLayoutId number(30,0),
	name varchar2(4000) null,
	title varchar2(4000) null,
	description varchar2(4000) null,
	keywords varchar2(4000) null,
	robots varchar2(4000) null,
	type_ VARCHAR2(75 CHAR) null,
	typeSettings clob null,
	hidden_ number(1, 0),
	friendlyURL VARCHAR2(255 CHAR) null,
	iconImage number(1, 0),
	iconImageId number(30,0),
	themeId VARCHAR2(75 CHAR) null,
	colorSchemeId VARCHAR2(75 CHAR) null,
	wapThemeId VARCHAR2(75 CHAR) null,
	wapColorSchemeId VARCHAR2(75 CHAR) null,
	css clob null,
	priority number(30,0),
	layoutPrototypeUuid VARCHAR2(75 CHAR) null,
	layoutPrototypeLinkEnabled number(1, 0),
	sourcePrototypeLayoutUuid VARCHAR2(75 CHAR) null
);

create table LayoutBranch (
	LayoutBranchId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	layoutSetBranchId number(30,0),
	plid number(30,0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	master number(1, 0)
);

create table LayoutFriendlyURL (
	uuid_ VARCHAR2(75 CHAR) null,
	layoutFriendlyURLId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	plid number(30,0),
	privateLayout number(1, 0),
	friendlyURL VARCHAR2(255 CHAR) null,
	languageId VARCHAR2(75 CHAR) null
);

create table LayoutPrototype (
	uuid_ VARCHAR2(75 CHAR) null,
	layoutPrototypeId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name varchar2(4000) null,
	description varchar2(4000) null,
	settings_ varchar2(4000) null,
	active_ number(1, 0)
);

create table LayoutRevision (
	layoutRevisionId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	layoutSetBranchId number(30,0),
	layoutBranchId number(30,0),
	parentLayoutRevisionId number(30,0),
	head number(1, 0),
	major number(1, 0),
	plid number(30,0),
	privateLayout number(1, 0),
	name varchar2(4000) null,
	title varchar2(4000) null,
	description varchar2(4000) null,
	keywords varchar2(4000) null,
	robots varchar2(4000) null,
	typeSettings clob null,
	iconImage number(1, 0),
	iconImageId number(30,0),
	themeId VARCHAR2(75 CHAR) null,
	colorSchemeId VARCHAR2(75 CHAR) null,
	wapThemeId VARCHAR2(75 CHAR) null,
	wapColorSchemeId VARCHAR2(75 CHAR) null,
	css clob null,
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table LayoutSet (
	layoutSetId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	createDate timestamp null,
	modifiedDate timestamp null,
	privateLayout number(1, 0),
	logo number(1, 0),
	logoId number(30,0),
	themeId VARCHAR2(75 CHAR) null,
	colorSchemeId VARCHAR2(75 CHAR) null,
	wapThemeId VARCHAR2(75 CHAR) null,
	wapColorSchemeId VARCHAR2(75 CHAR) null,
	css clob null,
	pageCount number(30,0),
	settings_ clob null,
	layoutSetPrototypeUuid VARCHAR2(75 CHAR) null,
	layoutSetPrototypeLinkEnabled number(1, 0)
);

create table LayoutSetBranch (
	layoutSetBranchId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	privateLayout number(1, 0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	master number(1, 0),
	logo number(1, 0),
	logoId number(30,0),
	themeId VARCHAR2(75 CHAR) null,
	colorSchemeId VARCHAR2(75 CHAR) null,
	wapThemeId VARCHAR2(75 CHAR) null,
	wapColorSchemeId VARCHAR2(75 CHAR) null,
	css clob null,
	settings_ clob null,
	layoutSetPrototypeUuid VARCHAR2(75 CHAR) null,
	layoutSetPrototypeLinkEnabled number(1, 0)
);

create table LayoutSetPrototype (
	uuid_ VARCHAR2(75 CHAR) null,
	layoutSetPrototypeId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name varchar2(4000) null,
	description varchar2(4000) null,
	settings_ varchar2(4000) null,
	active_ number(1, 0)
);

create table ListType (
	listTypeId number(30,0) not null primary key,
	name VARCHAR2(75 CHAR) null,
	type_ VARCHAR2(75 CHAR) null
);

create table Lock_ (
	uuid_ VARCHAR2(75 CHAR) null,
	lockId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	className VARCHAR2(75 CHAR) null,
	key_ VARCHAR2(200 CHAR) null,
	owner VARCHAR2(255 CHAR) null,
	inheritable number(1, 0),
	expirationDate timestamp null
);

create table MBBan (
	uuid_ VARCHAR2(75 CHAR) null,
	banId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	banUserId number(30,0)
);

create table MBCategory (
	uuid_ VARCHAR2(75 CHAR) null,
	categoryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	parentCategoryId number(30,0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	displayStyle VARCHAR2(75 CHAR) null,
	threadCount number(30,0),
	messageCount number(30,0),
	lastPostDate timestamp null,
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table MBDiscussion (
	uuid_ VARCHAR2(75 CHAR) null,
	discussionId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	threadId number(30,0)
);

create table MBMailingList (
	uuid_ VARCHAR2(75 CHAR) null,
	mailingListId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	categoryId number(30,0),
	emailAddress VARCHAR2(75 CHAR) null,
	inProtocol VARCHAR2(75 CHAR) null,
	inServerName VARCHAR2(75 CHAR) null,
	inServerPort number(30,0),
	inUseSSL number(1, 0),
	inUserName VARCHAR2(75 CHAR) null,
	inPassword VARCHAR2(75 CHAR) null,
	inReadInterval number(30,0),
	outEmailAddress VARCHAR2(75 CHAR) null,
	outCustom number(1, 0),
	outServerName VARCHAR2(75 CHAR) null,
	outServerPort number(30,0),
	outUseSSL number(1, 0),
	outUserName VARCHAR2(75 CHAR) null,
	outPassword VARCHAR2(75 CHAR) null,
	allowAnonymous number(1, 0),
	active_ number(1, 0)
);

create table MBMessage (
	uuid_ VARCHAR2(75 CHAR) null,
	messageId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	categoryId number(30,0),
	threadId number(30,0),
	rootMessageId number(30,0),
	parentMessageId number(30,0),
	subject VARCHAR2(75 CHAR) null,
	body clob null,
	format VARCHAR2(75 CHAR) null,
	anonymous number(1, 0),
	priority number(30,20),
	allowPingbacks number(1, 0),
	answer number(1, 0),
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table MBStatsUser (
	statsUserId number(30,0) not null primary key,
	groupId number(30,0),
	userId number(30,0),
	messageCount number(30,0),
	lastPostDate timestamp null
);

create table MBThread (
	uuid_ VARCHAR2(75 CHAR) null,
	threadId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	categoryId number(30,0),
	rootMessageId number(30,0),
	rootMessageUserId number(30,0),
	messageCount number(30,0),
	viewCount number(30,0),
	lastPostByUserId number(30,0),
	lastPostDate timestamp null,
	priority number(30,20),
	question number(1, 0),
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table MBThreadFlag (
	uuid_ VARCHAR2(75 CHAR) null,
	threadFlagId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	threadId number(30,0)
);

create table MDRAction (
	uuid_ VARCHAR2(75 CHAR) null,
	actionId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	ruleGroupInstanceId number(30,0),
	name varchar2(4000) null,
	description varchar2(4000) null,
	type_ VARCHAR2(255 CHAR) null,
	typeSettings clob null
);

create table MDRRule (
	uuid_ VARCHAR2(75 CHAR) null,
	ruleId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	ruleGroupId number(30,0),
	name varchar2(4000) null,
	description varchar2(4000) null,
	type_ VARCHAR2(255 CHAR) null,
	typeSettings clob null
);

create table MDRRuleGroup (
	uuid_ VARCHAR2(75 CHAR) null,
	ruleGroupId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name varchar2(4000) null,
	description varchar2(4000) null
);

create table MDRRuleGroupInstance (
	uuid_ VARCHAR2(75 CHAR) null,
	ruleGroupInstanceId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	ruleGroupId number(30,0),
	priority number(30,0)
);

create table MembershipRequest (
	membershipRequestId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	createDate timestamp null,
	comments varchar2(4000) null,
	replyComments varchar2(4000) null,
	replyDate timestamp null,
	replierUserId number(30,0),
	statusId number(30,0)
);

create table Organization_ (
	uuid_ VARCHAR2(75 CHAR) null,
	organizationId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	parentOrganizationId number(30,0),
	treePath varchar2(4000) null,
	name VARCHAR2(100 CHAR) null,
	type_ VARCHAR2(75 CHAR) null,
	recursable number(1, 0),
	regionId number(30,0),
	countryId number(30,0),
	statusId number(30,0),
	comments varchar2(4000) null
);

create table OrgGroupRole (
	organizationId number(30,0) not null,
	groupId number(30,0) not null,
	roleId number(30,0) not null,
	primary key (organizationId, groupId, roleId)
);

create table OrgLabor (
	orgLaborId number(30,0) not null primary key,
	organizationId number(30,0),
	typeId number(30,0),
	sunOpen number(30,0),
	sunClose number(30,0),
	monOpen number(30,0),
	monClose number(30,0),
	tueOpen number(30,0),
	tueClose number(30,0),
	wedOpen number(30,0),
	wedClose number(30,0),
	thuOpen number(30,0),
	thuClose number(30,0),
	friOpen number(30,0),
	friClose number(30,0),
	satOpen number(30,0),
	satClose number(30,0)
);

create table PasswordPolicy (
	uuid_ VARCHAR2(75 CHAR) null,
	passwordPolicyId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	defaultPolicy number(1, 0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	changeable number(1, 0),
	changeRequired number(1, 0),
	minAge number(30,0),
	checkSyntax number(1, 0),
	allowDictionaryWords number(1, 0),
	minAlphanumeric number(30,0),
	minLength number(30,0),
	minLowerCase number(30,0),
	minNumbers number(30,0),
	minSymbols number(30,0),
	minUpperCase number(30,0),
	regex VARCHAR2(75 CHAR) null,
	history number(1, 0),
	historyCount number(30,0),
	expireable number(1, 0),
	maxAge number(30,0),
	warningTime number(30,0),
	graceLimit number(30,0),
	lockout number(1, 0),
	maxFailure number(30,0),
	lockoutDuration number(30,0),
	requireUnlock number(1, 0),
	resetFailureCount number(30,0),
	resetTicketMaxAge number(30,0)
);

create table PasswordPolicyRel (
	passwordPolicyRelId number(30,0) not null primary key,
	passwordPolicyId number(30,0),
	classNameId number(30,0),
	classPK number(30,0)
);

create table PasswordTracker (
	passwordTrackerId number(30,0) not null primary key,
	userId number(30,0),
	createDate timestamp null,
	password_ VARCHAR2(75 CHAR) null
);

create table Phone (
	uuid_ VARCHAR2(75 CHAR) null,
	phoneId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	number_ VARCHAR2(75 CHAR) null,
	extension VARCHAR2(75 CHAR) null,
	typeId number(30,0),
	primary_ number(1, 0)
);

create table PluginSetting (
	pluginSettingId number(30,0) not null primary key,
	companyId number(30,0),
	pluginId VARCHAR2(75 CHAR) null,
	pluginType VARCHAR2(75 CHAR) null,
	roles varchar2(4000) null,
	active_ number(1, 0)
);

create table PollsChoice (
	uuid_ VARCHAR2(75 CHAR) null,
	choiceId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	questionId number(30,0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null
);

create table PollsQuestion (
	uuid_ VARCHAR2(75 CHAR) null,
	questionId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	title varchar2(4000) null,
	description varchar2(4000) null,
	expirationDate timestamp null,
	lastVoteDate timestamp null
);

create table PollsVote (
	uuid_ VARCHAR2(75 CHAR) null,
	voteId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	questionId number(30,0),
	choiceId number(30,0),
	voteDate timestamp null
);

create table PortalPreferences (
	portalPreferencesId number(30,0) not null primary key,
	ownerId number(30,0),
	ownerType number(30,0),
	preferences clob null
);

create table Portlet (
	id_ number(30,0) not null primary key,
	companyId number(30,0),
	portletId VARCHAR2(200 CHAR) null,
	roles varchar2(4000) null,
	active_ number(1, 0)
);

create table PortletItem (
	portletItemId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name VARCHAR2(75 CHAR) null,
	portletId VARCHAR2(200 CHAR) null,
	classNameId number(30,0)
);

create table PortletPreferences (
	portletPreferencesId number(30,0) not null primary key,
	ownerId number(30,0),
	ownerType number(30,0),
	plid number(30,0),
	portletId VARCHAR2(200 CHAR) null,
	preferences clob null
);

create table RatingsEntry (
	entryId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	score number(30,20)
);

create table RatingsStats (
	statsId number(30,0) not null primary key,
	classNameId number(30,0),
	classPK number(30,0),
	totalEntries number(30,0),
	totalScore number(30,20),
	averageScore number(30,20)
);

create table Region (
	regionId number(30,0) not null primary key,
	countryId number(30,0),
	regionCode VARCHAR2(75 CHAR) null,
	name VARCHAR2(75 CHAR) null,
	active_ number(1, 0)
);

create table Release_ (
	releaseId number(30,0) not null primary key,
	createDate timestamp null,
	modifiedDate timestamp null,
	servletContextName VARCHAR2(75 CHAR) null,
	buildNumber number(30,0),
	buildDate timestamp null,
	verified number(1, 0),
	state_ number(30,0),
	testString VARCHAR2(1024 CHAR) null
);

create table Repository (
	uuid_ VARCHAR2(75 CHAR) null,
	repositoryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	portletId VARCHAR2(200 CHAR) null,
	typeSettings clob null,
	dlFolderId number(30,0)
);

create table RepositoryEntry (
	uuid_ VARCHAR2(75 CHAR) null,
	repositoryEntryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	repositoryId number(30,0),
	mappedId VARCHAR2(75 CHAR) null,
	manualCheckInRequired number(1, 0)
);

create table ResourceAction (
	resourceActionId number(30,0) not null primary key,
	name VARCHAR2(255 CHAR) null,
	actionId VARCHAR2(75 CHAR) null,
	bitwiseValue number(30,0)
);

create table ResourceBlock (
	resourceBlockId number(30,0) not null primary key,
	companyId number(30,0),
	groupId number(30,0),
	name VARCHAR2(75 CHAR) null,
	permissionsHash VARCHAR2(75 CHAR) null,
	referenceCount number(30,0)
);

create table ResourceBlockPermission (
	resourceBlockPermissionId number(30,0) not null primary key,
	resourceBlockId number(30,0),
	roleId number(30,0),
	actionIds number(30,0)
);

create table ResourcePermission (
	resourcePermissionId number(30,0) not null primary key,
	companyId number(30,0),
	name VARCHAR2(255 CHAR) null,
	scope number(30,0),
	primKey VARCHAR2(255 CHAR) null,
	roleId number(30,0),
	ownerId number(30,0),
	actionIds number(30,0)
);

create table ResourceTypePermission (
	resourceTypePermissionId number(30,0) not null primary key,
	companyId number(30,0),
	groupId number(30,0),
	name VARCHAR2(75 CHAR) null,
	roleId number(30,0),
	actionIds number(30,0)
);

create table Role_ (
	uuid_ VARCHAR2(75 CHAR) null,
	roleId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	name VARCHAR2(75 CHAR) null,
	title varchar2(4000) null,
	description varchar2(4000) null,
	type_ number(30,0),
	subtype VARCHAR2(75 CHAR) null
);

create table SCFrameworkVersi_SCProductVers (
	frameworkVersionId number(30,0) not null,
	productVersionId number(30,0) not null,
	primary key (frameworkVersionId, productVersionId)
);

create table SCFrameworkVersion (
	frameworkVersionId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name VARCHAR2(75 CHAR) null,
	url varchar2(4000) null,
	active_ number(1, 0),
	priority number(30,0)
);

create table SCLicense (
	licenseId number(30,0) not null primary key,
	name VARCHAR2(75 CHAR) null,
	url varchar2(4000) null,
	openSource number(1, 0),
	active_ number(1, 0),
	recommended number(1, 0)
);

create table SCLicenses_SCProductEntries (
	licenseId number(30,0) not null,
	productEntryId number(30,0) not null,
	primary key (licenseId, productEntryId)
);

create table SCProductEntry (
	productEntryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name VARCHAR2(75 CHAR) null,
	type_ VARCHAR2(75 CHAR) null,
	tags VARCHAR2(255 CHAR) null,
	shortDescription varchar2(4000) null,
	longDescription varchar2(4000) null,
	pageURL varchar2(4000) null,
	author VARCHAR2(75 CHAR) null,
	repoGroupId VARCHAR2(75 CHAR) null,
	repoArtifactId VARCHAR2(75 CHAR) null
);

create table SCProductScreenshot (
	productScreenshotId number(30,0) not null primary key,
	companyId number(30,0),
	groupId number(30,0),
	productEntryId number(30,0),
	thumbnailId number(30,0),
	fullImageId number(30,0),
	priority number(30,0)
);

create table SCProductVersion (
	productVersionId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	productEntryId number(30,0),
	version VARCHAR2(75 CHAR) null,
	changeLog varchar2(4000) null,
	downloadPageURL varchar2(4000) null,
	directDownloadURL VARCHAR2(2000 CHAR) null,
	repoStoreArtifact number(1, 0)
);

create table ServiceComponent (
	serviceComponentId number(30,0) not null primary key,
	buildNamespace VARCHAR2(75 CHAR) null,
	buildNumber number(30,0),
	buildDate number(30,0),
	data_ clob null
);

create table Shard (
	shardId number(30,0) not null primary key,
	classNameId number(30,0),
	classPK number(30,0),
	name VARCHAR2(75 CHAR) null
);

create table ShoppingCart (
	cartId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	itemIds varchar2(4000) null,
	couponCodes VARCHAR2(75 CHAR) null,
	altShipping number(30,0),
	insure number(1, 0)
);

create table ShoppingCategory (
	categoryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	parentCategoryId number(30,0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null
);

create table ShoppingCoupon (
	couponId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	code_ VARCHAR2(75 CHAR) null,
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	startDate timestamp null,
	endDate timestamp null,
	active_ number(1, 0),
	limitCategories varchar2(4000) null,
	limitSkus varchar2(4000) null,
	minOrder number(30,20),
	discount number(30,20),
	discountType VARCHAR2(75 CHAR) null
);

create table ShoppingItem (
	itemId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	categoryId number(30,0),
	sku VARCHAR2(75 CHAR) null,
	name VARCHAR2(200 CHAR) null,
	description varchar2(4000) null,
	properties varchar2(4000) null,
	fields_ number(1, 0),
	fieldsQuantities varchar2(4000) null,
	minQuantity number(30,0),
	maxQuantity number(30,0),
	price number(30,20),
	discount number(30,20),
	taxable number(1, 0),
	shipping number(30,20),
	useShippingFormula number(1, 0),
	requiresShipping number(1, 0),
	stockQuantity number(30,0),
	featured_ number(1, 0),
	sale_ number(1, 0),
	smallImage number(1, 0),
	smallImageId number(30,0),
	smallImageURL varchar2(4000) null,
	mediumImage number(1, 0),
	mediumImageId number(30,0),
	mediumImageURL varchar2(4000) null,
	largeImage number(1, 0),
	largeImageId number(30,0),
	largeImageURL varchar2(4000) null
);

create table ShoppingItemField (
	itemFieldId number(30,0) not null primary key,
	itemId number(30,0),
	name VARCHAR2(75 CHAR) null,
	values_ varchar2(4000) null,
	description varchar2(4000) null
);

create table ShoppingItemPrice (
	itemPriceId number(30,0) not null primary key,
	itemId number(30,0),
	minQuantity number(30,0),
	maxQuantity number(30,0),
	price number(30,20),
	discount number(30,20),
	taxable number(1, 0),
	shipping number(30,20),
	useShippingFormula number(1, 0),
	status number(30,0)
);

create table ShoppingOrder (
	orderId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	number_ VARCHAR2(75 CHAR) null,
	tax number(30,20),
	shipping number(30,20),
	altShipping VARCHAR2(75 CHAR) null,
	requiresShipping number(1, 0),
	insure number(1, 0),
	insurance number(30,20),
	couponCodes VARCHAR2(75 CHAR) null,
	couponDiscount number(30,20),
	billingFirstName VARCHAR2(75 CHAR) null,
	billingLastName VARCHAR2(75 CHAR) null,
	billingEmailAddress VARCHAR2(75 CHAR) null,
	billingCompany VARCHAR2(75 CHAR) null,
	billingStreet VARCHAR2(75 CHAR) null,
	billingCity VARCHAR2(75 CHAR) null,
	billingState VARCHAR2(75 CHAR) null,
	billingZip VARCHAR2(75 CHAR) null,
	billingCountry VARCHAR2(75 CHAR) null,
	billingPhone VARCHAR2(75 CHAR) null,
	shipToBilling number(1, 0),
	shippingFirstName VARCHAR2(75 CHAR) null,
	shippingLastName VARCHAR2(75 CHAR) null,
	shippingEmailAddress VARCHAR2(75 CHAR) null,
	shippingCompany VARCHAR2(75 CHAR) null,
	shippingStreet VARCHAR2(75 CHAR) null,
	shippingCity VARCHAR2(75 CHAR) null,
	shippingState VARCHAR2(75 CHAR) null,
	shippingZip VARCHAR2(75 CHAR) null,
	shippingCountry VARCHAR2(75 CHAR) null,
	shippingPhone VARCHAR2(75 CHAR) null,
	ccName VARCHAR2(75 CHAR) null,
	ccType VARCHAR2(75 CHAR) null,
	ccNumber VARCHAR2(75 CHAR) null,
	ccExpMonth number(30,0),
	ccExpYear number(30,0),
	ccVerNumber VARCHAR2(75 CHAR) null,
	comments varchar2(4000) null,
	ppTxnId VARCHAR2(75 CHAR) null,
	ppPaymentStatus VARCHAR2(75 CHAR) null,
	ppPaymentGross number(30,20),
	ppReceiverEmail VARCHAR2(75 CHAR) null,
	ppPayerEmail VARCHAR2(75 CHAR) null,
	sendOrderEmail number(1, 0),
	sendShippingEmail number(1, 0)
);

create table ShoppingOrderItem (
	orderItemId number(30,0) not null primary key,
	orderId number(30,0),
	itemId VARCHAR2(75 CHAR) null,
	sku VARCHAR2(75 CHAR) null,
	name VARCHAR2(200 CHAR) null,
	description varchar2(4000) null,
	properties varchar2(4000) null,
	price number(30,20),
	quantity number(30,0),
	shippedDate timestamp null
);

create table SocialActivity (
	activityId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	createDate number(30,0),
	activitySetId number(30,0),
	mirrorActivityId number(30,0),
	classNameId number(30,0),
	classPK number(30,0),
	parentClassNameId number(30,0),
	parentClassPK number(30,0),
	type_ number(30,0),
	extraData varchar2(4000) null,
	receiverUserId number(30,0)
);

create table SocialActivityAchievement (
	activityAchievementId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	createDate number(30,0),
	name VARCHAR2(75 CHAR) null,
	firstInGroup number(1, 0)
);

create table SocialActivityCounter (
	activityCounterId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	classNameId number(30,0),
	classPK number(30,0),
	name VARCHAR2(75 CHAR) null,
	ownerType number(30,0),
	currentValue number(30,0),
	totalValue number(30,0),
	graceValue number(30,0),
	startPeriod number(30,0),
	endPeriod number(30,0),
	active_ number(1, 0)
);

create table SocialActivityLimit (
	activityLimitId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	classNameId number(30,0),
	classPK number(30,0),
	activityType number(30,0),
	activityCounterName VARCHAR2(75 CHAR) null,
	value VARCHAR2(75 CHAR) null
);

create table SocialActivitySet (
	activitySetId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	createDate number(30,0),
	modifiedDate number(30,0),
	classNameId number(30,0),
	classPK number(30,0),
	type_ number(30,0),
	extraData varchar2(4000) null,
	activityCount number(30,0)
);

create table SocialActivitySetting (
	activitySettingId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	classNameId number(30,0),
	activityType number(30,0),
	name VARCHAR2(75 CHAR) null,
	value VARCHAR2(1024 CHAR) null
);

create table SocialRelation (
	uuid_ VARCHAR2(75 CHAR) null,
	relationId number(30,0) not null primary key,
	companyId number(30,0),
	createDate number(30,0),
	userId1 number(30,0),
	userId2 number(30,0),
	type_ number(30,0)
);

create table SocialRequest (
	uuid_ VARCHAR2(75 CHAR) null,
	requestId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	createDate number(30,0),
	modifiedDate number(30,0),
	classNameId number(30,0),
	classPK number(30,0),
	type_ number(30,0),
	extraData varchar2(4000) null,
	receiverUserId number(30,0),
	status number(30,0)
);

create table Subscription (
	subscriptionId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	frequency VARCHAR2(75 CHAR) null
);

create table SystemEvent (
	systemEventId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	classUuid VARCHAR2(75 CHAR) null,
	referrerClassNameId number(30,0),
	parentSystemEventId number(30,0),
	systemEventSetKey number(30,0),
	type_ number(30,0),
	extraData clob null
);

create table Team (
	teamId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	groupId number(30,0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null
);

create table Ticket (
	ticketId number(30,0) not null primary key,
	companyId number(30,0),
	createDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	key_ VARCHAR2(75 CHAR) null,
	type_ number(30,0),
	extraInfo clob null,
	expirationDate timestamp null
);

create table TrashEntry (
	entryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	systemEventSetKey number(30,0),
	typeSettings clob null,
	status number(30,0)
);

create table TrashVersion (
	versionId number(30,0) not null primary key,
	entryId number(30,0),
	classNameId number(30,0),
	classPK number(30,0),
	typeSettings clob null,
	status number(30,0)
);

create table UserNotificationDelivery (
	userNotificationDeliveryId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	portletId VARCHAR2(200 CHAR) null,
	classNameId number(30,0),
	notificationType number(30,0),
	deliveryType number(30,0),
	deliver number(1, 0)
);

create table User_ (
	uuid_ VARCHAR2(75 CHAR) null,
	userId number(30,0) not null primary key,
	companyId number(30,0),
	createDate timestamp null,
	modifiedDate timestamp null,
	defaultUser number(1, 0),
	contactId number(30,0),
	password_ VARCHAR2(75 CHAR) null,
	passwordEncrypted number(1, 0),
	passwordReset number(1, 0),
	passwordModifiedDate timestamp null,
	digest VARCHAR2(255 CHAR) null,
	reminderQueryQuestion VARCHAR2(75 CHAR) null,
	reminderQueryAnswer VARCHAR2(75 CHAR) null,
	graceLoginCount number(30,0),
	screenName VARCHAR2(75 CHAR) null,
	emailAddress VARCHAR2(75 CHAR) null,
	facebookId number(30,0),
	ldapServerId number(30,0),
	openId VARCHAR2(1024 CHAR) null,
	portraitId number(30,0),
	languageId VARCHAR2(75 CHAR) null,
	timeZoneId VARCHAR2(75 CHAR) null,
	greeting VARCHAR2(255 CHAR) null,
	comments varchar2(4000) null,
	firstName VARCHAR2(75 CHAR) null,
	middleName VARCHAR2(75 CHAR) null,
	lastName VARCHAR2(75 CHAR) null,
	jobTitle VARCHAR2(100 CHAR) null,
	loginDate timestamp null,
	loginIP VARCHAR2(75 CHAR) null,
	lastLoginDate timestamp null,
	lastLoginIP VARCHAR2(75 CHAR) null,
	lastFailedLoginDate timestamp null,
	failedLoginAttempts number(30,0),
	lockout number(1, 0),
	lockoutDate timestamp null,
	agreedToTermsOfUse number(1, 0),
	emailAddressVerified number(1, 0),
	status number(30,0)
);

create table UserGroup (
	uuid_ VARCHAR2(75 CHAR) null,
	userGroupId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	parentUserGroupId number(30,0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	addedByLDAPImport number(1, 0)
);

create table UserGroupGroupRole (
	userGroupId number(30,0) not null,
	groupId number(30,0) not null,
	roleId number(30,0) not null,
	primary key (userGroupId, groupId, roleId)
);

create table UserGroupRole (
	userId number(30,0) not null,
	groupId number(30,0) not null,
	roleId number(30,0) not null,
	primary key (userId, groupId, roleId)
);

create table UserGroups_Teams (
	teamId number(30,0) not null,
	userGroupId number(30,0) not null,
	primary key (teamId, userGroupId)
);

create table UserIdMapper (
	userIdMapperId number(30,0) not null primary key,
	userId number(30,0),
	type_ VARCHAR2(75 CHAR) null,
	description VARCHAR2(75 CHAR) null,
	externalUserId VARCHAR2(75 CHAR) null
);

create table UserNotificationEvent (
	uuid_ VARCHAR2(75 CHAR) null,
	userNotificationEventId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	type_ VARCHAR2(75 CHAR) null,
	timestamp number(30,0),
	deliverBy number(30,0),
	delivered number(1, 0),
	payload clob null,
	archived number(1, 0)
);

create table Users_Groups (
	groupId number(30,0) not null,
	userId number(30,0) not null,
	primary key (groupId, userId)
);

create table Users_Orgs (
	organizationId number(30,0) not null,
	userId number(30,0) not null,
	primary key (organizationId, userId)
);

create table Users_Roles (
	roleId number(30,0) not null,
	userId number(30,0) not null,
	primary key (roleId, userId)
);

create table Users_Teams (
	teamId number(30,0) not null,
	userId number(30,0) not null,
	primary key (teamId, userId)
);

create table Users_UserGroups (
	userId number(30,0) not null,
	userGroupId number(30,0) not null,
	primary key (userId, userGroupId)
);

create table UserTracker (
	userTrackerId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	modifiedDate timestamp null,
	sessionId VARCHAR2(200 CHAR) null,
	remoteAddr VARCHAR2(75 CHAR) null,
	remoteHost VARCHAR2(75 CHAR) null,
	userAgent VARCHAR2(200 CHAR) null
);

create table UserTrackerPath (
	userTrackerPathId number(30,0) not null primary key,
	userTrackerId number(30,0),
	path_ varchar2(4000) null,
	pathDate timestamp null
);

create table VirtualHost (
	virtualHostId number(30,0) not null primary key,
	companyId number(30,0),
	layoutSetId number(30,0),
	hostname VARCHAR2(75 CHAR) null
);

create table WebDAVProps (
	webDavPropsId number(30,0) not null primary key,
	companyId number(30,0),
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	props clob null
);

create table Website (
	uuid_ VARCHAR2(75 CHAR) null,
	websiteId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	url varchar2(4000) null,
	typeId number(30,0),
	primary_ number(1, 0)
);

create table WikiNode (
	uuid_ VARCHAR2(75 CHAR) null,
	nodeId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null,
	lastPostDate timestamp null,
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table WikiPage (
	uuid_ VARCHAR2(75 CHAR) null,
	pageId number(30,0) not null primary key,
	resourcePrimKey number(30,0),
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	nodeId number(30,0),
	title VARCHAR2(255 CHAR) null,
	version number(30,20),
	minorEdit number(1, 0),
	content clob null,
	summary varchar2(4000) null,
	format VARCHAR2(75 CHAR) null,
	head number(1, 0),
	parentTitle VARCHAR2(255 CHAR) null,
	redirectTitle VARCHAR2(255 CHAR) null,
	status number(30,0),
	statusByUserId number(30,0),
	statusByUserName VARCHAR2(75 CHAR) null,
	statusDate timestamp null
);

create table WikiPageResource (
	uuid_ VARCHAR2(75 CHAR) null,
	resourcePrimKey number(30,0) not null primary key,
	nodeId number(30,0),
	title VARCHAR2(255 CHAR) null
);

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	typePK number(30,0),
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

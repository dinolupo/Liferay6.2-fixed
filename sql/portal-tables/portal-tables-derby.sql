create table Account_ (
	accountId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	parentAccountId bigint,
	name varchar(75),
	legalName varchar(75),
	legalId varchar(75),
	legalType varchar(75),
	sicCode varchar(75),
	tickerSymbol varchar(75),
	industry varchar(75),
	type_ varchar(75),
	size_ varchar(75)
);

create table Address (
	uuid_ varchar(75),
	addressId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	street1 varchar(75),
	street2 varchar(75),
	street3 varchar(75),
	city varchar(75),
	zip varchar(75),
	regionId bigint,
	countryId bigint,
	typeId integer,
	mailing smallint,
	primary_ smallint
);

create table AnnouncementsDelivery (
	deliveryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	type_ varchar(75),
	email smallint,
	sms smallint,
	website smallint
);

create table AnnouncementsEntry (
	uuid_ varchar(75),
	entryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	title varchar(75),
	content clob,
	url varchar(4000),
	type_ varchar(75),
	displayDate timestamp,
	expirationDate timestamp,
	priority integer,
	alert smallint
);

create table AnnouncementsFlag (
	flagId bigint not null primary key,
	userId bigint,
	createDate timestamp,
	entryId bigint,
	value integer
);

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
	title varchar(4000),
	description varchar(4000),
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
	categoryId bigint not null,
	entryId bigint not null,
	primary key (categoryId, entryId)
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
	classUuid varchar(75),
	classTypeId bigint,
	visible smallint,
	startDate timestamp,
	endDate timestamp,
	publishDate timestamp,
	expirationDate timestamp,
	mimeType varchar(75),
	title varchar(4000),
	description clob,
	summary clob,
	url varchar(4000),
	layoutUuid varchar(75),
	height integer,
	width integer,
	priority double,
	viewCount integer
);

create table AssetLink (
	linkId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	entryId1 bigint,
	entryId2 bigint,
	type_ integer,
	weight integer
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
	title varchar(4000),
	description varchar(4000),
	settings_ varchar(4000)
);

create table BackgroundTask (
	backgroundTaskId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	servletContextNames varchar(255),
	taskExecutorClassName varchar(200),
	taskContext clob,
	completed smallint,
	completionDate timestamp,
	status integer,
	statusMessage clob
);

create table BlogsEntry (
	uuid_ varchar(75),
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	title varchar(150),
	urlTitle varchar(150),
	description varchar(4000),
	content clob,
	displayDate timestamp,
	allowPingbacks smallint,
	allowTrackbacks smallint,
	trackbacks clob,
	smallImage smallint,
	smallImageId bigint,
	smallImageURL varchar(4000),
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table BlogsStatsUser (
	statsUserId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	entryCount integer,
	lastPostDate timestamp,
	ratingsTotalEntries integer,
	ratingsTotalScore double,
	ratingsAverageScore double
);

create table BookmarksEntry (
	uuid_ varchar(75),
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	resourceBlockId bigint,
	folderId bigint,
	treePath varchar(4000),
	name varchar(255),
	url varchar(4000),
	description varchar(4000),
	visits integer,
	priority integer,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table BookmarksFolder (
	uuid_ varchar(75),
	folderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	resourceBlockId bigint,
	parentFolderId bigint,
	treePath varchar(4000),
	name varchar(75),
	description varchar(4000),
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table BrowserTracker (
	browserTrackerId bigint not null primary key,
	userId bigint,
	browserKey bigint
);

create table CalEvent (
	uuid_ varchar(75),
	eventId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	title varchar(75),
	description varchar(4000),
	location varchar(4000),
	startDate timestamp,
	endDate timestamp,
	durationHour integer,
	durationMinute integer,
	allDay smallint,
	timeZoneSensitive smallint,
	type_ varchar(75),
	repeating smallint,
	recurrence clob,
	remindBy integer,
	firstReminder integer,
	secondReminder integer
);

create table ClassName_ (
	classNameId bigint not null primary key,
	value varchar(200)
);

create table ClusterGroup (
	clusterGroupId bigint not null primary key,
	name varchar(75),
	clusterNodeIds varchar(75),
	wholeCluster smallint
);

create table Company (
	companyId bigint not null primary key,
	accountId bigint,
	webId varchar(75),
	key_ clob,
	mx varchar(75),
	homeURL varchar(4000),
	logoId bigint,
	system smallint,
	maxUsers integer,
	active_ smallint
);

create table Contact_ (
	contactId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	accountId bigint,
	parentContactId bigint,
	emailAddress varchar(75),
	firstName varchar(75),
	middleName varchar(75),
	lastName varchar(75),
	prefixId integer,
	suffixId integer,
	male smallint,
	birthday timestamp,
	smsSn varchar(75),
	aimSn varchar(75),
	facebookSn varchar(75),
	icqSn varchar(75),
	jabberSn varchar(75),
	msnSn varchar(75),
	mySpaceSn varchar(75),
	skypeSn varchar(75),
	twitterSn varchar(75),
	ymSn varchar(75),
	employeeStatusId varchar(75),
	employeeNumber varchar(75),
	jobTitle varchar(100),
	jobClass varchar(75),
	hoursOfOperation varchar(75)
);

create table Counter (
	name varchar(75) not null primary key,
	currentId bigint
);

create table Country (
	countryId bigint not null primary key,
	name varchar(75),
	a2 varchar(75),
	a3 varchar(75),
	number_ varchar(75),
	idd_ varchar(75),
	zipRequired smallint,
	active_ smallint
);

create table CyrusUser (
	userId varchar(75) not null primary key,
	password_ varchar(75) not null
);

create table CyrusVirtual (
	emailAddress varchar(75) not null primary key,
	userId varchar(75) not null
);

create table DDLRecord (
	uuid_ varchar(75),
	recordId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	versionUserId bigint,
	versionUserName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	DDMStorageId bigint,
	recordSetId bigint,
	version varchar(75),
	displayIndex integer
);

create table DDLRecordSet (
	uuid_ varchar(75),
	recordSetId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	DDMStructureId bigint,
	recordSetKey varchar(75),
	name varchar(4000),
	description varchar(4000),
	minDisplayRows integer,
	scope integer
);

create table DDLRecordVersion (
	recordVersionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	DDMStorageId bigint,
	recordSetId bigint,
	recordId bigint,
	version varchar(75),
	displayIndex integer,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table DDMContent (
	uuid_ varchar(75),
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(4000),
	description varchar(4000),
	xml clob
);

create table DDMStorageLink (
	uuid_ varchar(75),
	storageLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
);

create table DDMStructure (
	uuid_ varchar(75),
	structureId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	parentStructureId bigint,
	classNameId bigint,
	structureKey varchar(75),
	name varchar(4000),
	description varchar(4000),
	xsd clob,
	storageType varchar(75),
	type_ integer
);

create table DDMStructureLink (
	structureLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
);

create table DDMTemplate (
	uuid_ varchar(75),
	templateId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	templateKey varchar(75),
	name varchar(4000),
	description varchar(4000),
	type_ varchar(75),
	mode_ varchar(75),
	language varchar(75),
	script clob,
	cacheable smallint,
	smallImage smallint,
	smallImageId bigint,
	smallImageURL varchar(75)
);

create table DLContent (
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	repositoryId bigint,
	path_ varchar(255),
	version varchar(75),
	data_ blob,
	size_ bigint
);

create table DLFileEntry (
	uuid_ varchar(75),
	fileEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	repositoryId bigint,
	folderId bigint,
	treePath varchar(4000),
	name varchar(255),
	extension varchar(75),
	mimeType varchar(75),
	title varchar(255),
	description varchar(4000),
	extraSettings clob,
	fileEntryTypeId bigint,
	version varchar(75),
	size_ bigint,
	readCount integer,
	smallImageId bigint,
	largeImageId bigint,
	custom1ImageId bigint,
	custom2ImageId bigint,
	manualCheckInRequired smallint
);

create table DLFileEntryMetadata (
	uuid_ varchar(75),
	fileEntryMetadataId bigint not null primary key,
	DDMStorageId bigint,
	DDMStructureId bigint,
	fileEntryTypeId bigint,
	fileEntryId bigint,
	fileVersionId bigint
);

create table DLFileEntryType (
	uuid_ varchar(75),
	fileEntryTypeId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	fileEntryTypeKey varchar(75),
	name varchar(4000),
	description varchar(4000)
);

create table DLFileEntryTypes_DDMStructures (
	structureId bigint not null,
	fileEntryTypeId bigint not null,
	primary key (structureId, fileEntryTypeId)
);

create table DLFileEntryTypes_DLFolders (
	fileEntryTypeId bigint not null,
	folderId bigint not null,
	primary key (fileEntryTypeId, folderId)
);

create table DLFileRank (
	fileRankId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate timestamp,
	fileEntryId bigint,
	active_ smallint
);

create table DLFileShortcut (
	uuid_ varchar(75),
	fileShortcutId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	repositoryId bigint,
	folderId bigint,
	toFileEntryId bigint,
	treePath varchar(4000),
	active_ smallint,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table DLFileVersion (
	uuid_ varchar(75),
	fileVersionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	repositoryId bigint,
	folderId bigint,
	fileEntryId bigint,
	treePath varchar(4000),
	extension varchar(75),
	mimeType varchar(75),
	title varchar(255),
	description varchar(4000),
	changeLog varchar(75),
	extraSettings clob,
	fileEntryTypeId bigint,
	version varchar(75),
	size_ bigint,
	checksum varchar(75),
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table DLFolder (
	uuid_ varchar(75),
	folderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	repositoryId bigint,
	mountPoint smallint,
	parentFolderId bigint,
	treePath varchar(4000),
	name varchar(100),
	description varchar(4000),
	lastPostDate timestamp,
	defaultFileEntryTypeId bigint,
	hidden_ smallint,
	overrideFileEntryTypes smallint,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table DLSyncEvent (
	syncEventId bigint not null primary key,
	modifiedTime bigint,
	event varchar(75),
	type_ varchar(75),
	typePK bigint
);

create table EmailAddress (
	uuid_ varchar(75),
	emailAddressId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	address varchar(75),
	typeId integer,
	primary_ smallint
);

create table ExpandoColumn (
	columnId bigint not null primary key,
	companyId bigint,
	tableId bigint,
	name varchar(75),
	type_ integer,
	defaultData varchar(4000),
	typeSettings clob
);

create table ExpandoRow (
	rowId_ bigint not null primary key,
	companyId bigint,
	modifiedDate timestamp,
	tableId bigint,
	classPK bigint
);

create table ExpandoTable (
	tableId bigint not null primary key,
	companyId bigint,
	classNameId bigint,
	name varchar(75)
);

create table ExpandoValue (
	valueId bigint not null primary key,
	companyId bigint,
	tableId bigint,
	columnId bigint,
	rowId_ bigint,
	classNameId bigint,
	classPK bigint,
	data_ varchar(4000)
);

create table Group_ (
	uuid_ varchar(75),
	groupId bigint not null primary key,
	companyId bigint,
	creatorUserId bigint,
	classNameId bigint,
	classPK bigint,
	parentGroupId bigint,
	liveGroupId bigint,
	treePath varchar(4000),
	name varchar(150),
	description varchar(4000),
	type_ integer,
	typeSettings clob,
	manualMembership smallint,
	membershipRestriction integer,
	friendlyURL varchar(255),
	site smallint,
	remoteStagingGroupCount integer,
	active_ smallint
);

create table Groups_Orgs (
	groupId bigint not null,
	organizationId bigint not null,
	primary key (groupId, organizationId)
);

create table Groups_Roles (
	groupId bigint not null,
	roleId bigint not null,
	primary key (groupId, roleId)
);

create table Groups_UserGroups (
	groupId bigint not null,
	userGroupId bigint not null,
	primary key (groupId, userGroupId)
);

create table Image (
	imageId bigint not null primary key,
	modifiedDate timestamp,
	type_ varchar(75),
	height integer,
	width integer,
	size_ integer
);

create table JournalArticle (
	uuid_ varchar(75),
	id_ bigint not null primary key,
	resourcePrimKey bigint,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	folderId bigint,
	classNameId bigint,
	classPK bigint,
	treePath varchar(4000),
	articleId varchar(75),
	version double,
	title varchar(4000),
	urlTitle varchar(150),
	description clob,
	content clob,
	type_ varchar(75),
	structureId varchar(75),
	templateId varchar(75),
	layoutUuid varchar(75),
	displayDate timestamp,
	expirationDate timestamp,
	reviewDate timestamp,
	indexable smallint,
	smallImage smallint,
	smallImageId bigint,
	smallImageURL varchar(4000),
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table JournalArticleImage (
	articleImageId bigint not null primary key,
	groupId bigint,
	articleId varchar(75),
	version double,
	elInstanceId varchar(75),
	elName varchar(75),
	languageId varchar(75),
	tempImage smallint
);

create table JournalArticleResource (
	uuid_ varchar(75),
	resourcePrimKey bigint not null primary key,
	groupId bigint,
	articleId varchar(75)
);

create table JournalContentSearch (
	contentSearchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	privateLayout smallint,
	layoutId bigint,
	portletId varchar(200),
	articleId varchar(75)
);

create table JournalFeed (
	uuid_ varchar(75),
	id_ bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	feedId varchar(75),
	name varchar(75),
	description varchar(4000),
	type_ varchar(75),
	structureId varchar(75),
	templateId varchar(75),
	rendererTemplateId varchar(75),
	delta integer,
	orderByCol varchar(75),
	orderByType varchar(75),
	targetLayoutFriendlyUrl varchar(255),
	targetPortletId varchar(75),
	contentField varchar(75),
	feedFormat varchar(75),
	feedVersion double
);

create table JournalFolder (
	uuid_ varchar(75),
	folderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	parentFolderId bigint,
	treePath varchar(4000),
	name varchar(100),
	description varchar(4000),
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table Layout (
	uuid_ varchar(75),
	plid bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	privateLayout smallint,
	layoutId bigint,
	parentLayoutId bigint,
	name varchar(4000),
	title varchar(4000),
	description varchar(4000),
	keywords varchar(4000),
	robots varchar(4000),
	type_ varchar(75),
	typeSettings clob,
	hidden_ smallint,
	friendlyURL varchar(255),
	iconImage smallint,
	iconImageId bigint,
	themeId varchar(75),
	colorSchemeId varchar(75),
	wapThemeId varchar(75),
	wapColorSchemeId varchar(75),
	css clob,
	priority integer,
	layoutPrototypeUuid varchar(75),
	layoutPrototypeLinkEnabled smallint,
	sourcePrototypeLayoutUuid varchar(75)
);

create table LayoutBranch (
	LayoutBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	layoutSetBranchId bigint,
	plid bigint,
	name varchar(75),
	description varchar(4000),
	master smallint
);

create table LayoutFriendlyURL (
	uuid_ varchar(75),
	layoutFriendlyURLId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	plid bigint,
	privateLayout smallint,
	friendlyURL varchar(255),
	languageId varchar(75)
);

create table LayoutPrototype (
	uuid_ varchar(75),
	layoutPrototypeId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(4000),
	description varchar(4000),
	settings_ varchar(4000),
	active_ smallint
);

create table LayoutRevision (
	layoutRevisionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	layoutSetBranchId bigint,
	layoutBranchId bigint,
	parentLayoutRevisionId bigint,
	head smallint,
	major smallint,
	plid bigint,
	privateLayout smallint,
	name varchar(4000),
	title varchar(4000),
	description varchar(4000),
	keywords varchar(4000),
	robots varchar(4000),
	typeSettings clob,
	iconImage smallint,
	iconImageId bigint,
	themeId varchar(75),
	colorSchemeId varchar(75),
	wapThemeId varchar(75),
	wapColorSchemeId varchar(75),
	css clob,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table LayoutSet (
	layoutSetId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	createDate timestamp,
	modifiedDate timestamp,
	privateLayout smallint,
	logo smallint,
	logoId bigint,
	themeId varchar(75),
	colorSchemeId varchar(75),
	wapThemeId varchar(75),
	wapColorSchemeId varchar(75),
	css clob,
	pageCount integer,
	settings_ clob,
	layoutSetPrototypeUuid varchar(75),
	layoutSetPrototypeLinkEnabled smallint
);

create table LayoutSetBranch (
	layoutSetBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	privateLayout smallint,
	name varchar(75),
	description varchar(4000),
	master smallint,
	logo smallint,
	logoId bigint,
	themeId varchar(75),
	colorSchemeId varchar(75),
	wapThemeId varchar(75),
	wapColorSchemeId varchar(75),
	css clob,
	settings_ clob,
	layoutSetPrototypeUuid varchar(75),
	layoutSetPrototypeLinkEnabled smallint
);

create table LayoutSetPrototype (
	uuid_ varchar(75),
	layoutSetPrototypeId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(4000),
	description varchar(4000),
	settings_ varchar(4000),
	active_ smallint
);

create table ListType (
	listTypeId integer not null primary key,
	name varchar(75),
	type_ varchar(75)
);

create table Lock_ (
	uuid_ varchar(75),
	lockId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	className varchar(75),
	key_ varchar(200),
	owner varchar(255),
	inheritable smallint,
	expirationDate timestamp
);

create table MBBan (
	uuid_ varchar(75),
	banId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	banUserId bigint
);

create table MBCategory (
	uuid_ varchar(75),
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	parentCategoryId bigint,
	name varchar(75),
	description varchar(4000),
	displayStyle varchar(75),
	threadCount integer,
	messageCount integer,
	lastPostDate timestamp,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table MBDiscussion (
	uuid_ varchar(75),
	discussionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	threadId bigint
);

create table MBMailingList (
	uuid_ varchar(75),
	mailingListId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	categoryId bigint,
	emailAddress varchar(75),
	inProtocol varchar(75),
	inServerName varchar(75),
	inServerPort integer,
	inUseSSL smallint,
	inUserName varchar(75),
	inPassword varchar(75),
	inReadInterval integer,
	outEmailAddress varchar(75),
	outCustom smallint,
	outServerName varchar(75),
	outServerPort integer,
	outUseSSL smallint,
	outUserName varchar(75),
	outPassword varchar(75),
	allowAnonymous smallint,
	active_ smallint
);

create table MBMessage (
	uuid_ varchar(75),
	messageId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	categoryId bigint,
	threadId bigint,
	rootMessageId bigint,
	parentMessageId bigint,
	subject varchar(75),
	body clob,
	format varchar(75),
	anonymous smallint,
	priority double,
	allowPingbacks smallint,
	answer smallint,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table MBStatsUser (
	statsUserId bigint not null primary key,
	groupId bigint,
	userId bigint,
	messageCount integer,
	lastPostDate timestamp
);

create table MBThread (
	uuid_ varchar(75),
	threadId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	categoryId bigint,
	rootMessageId bigint,
	rootMessageUserId bigint,
	messageCount integer,
	viewCount integer,
	lastPostByUserId bigint,
	lastPostDate timestamp,
	priority double,
	question smallint,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table MBThreadFlag (
	uuid_ varchar(75),
	threadFlagId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	threadId bigint
);

create table MDRAction (
	uuid_ varchar(75),
	actionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	ruleGroupInstanceId bigint,
	name varchar(4000),
	description varchar(4000),
	type_ varchar(255),
	typeSettings clob
);

create table MDRRule (
	uuid_ varchar(75),
	ruleId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	ruleGroupId bigint,
	name varchar(4000),
	description varchar(4000),
	type_ varchar(255),
	typeSettings clob
);

create table MDRRuleGroup (
	uuid_ varchar(75),
	ruleGroupId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(4000),
	description varchar(4000)
);

create table MDRRuleGroupInstance (
	uuid_ varchar(75),
	ruleGroupInstanceId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	ruleGroupId bigint,
	priority integer
);

create table MembershipRequest (
	membershipRequestId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate timestamp,
	comments varchar(4000),
	replyComments varchar(4000),
	replyDate timestamp,
	replierUserId bigint,
	statusId integer
);

create table Organization_ (
	uuid_ varchar(75),
	organizationId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	parentOrganizationId bigint,
	treePath varchar(4000),
	name varchar(100),
	type_ varchar(75),
	recursable smallint,
	regionId bigint,
	countryId bigint,
	statusId integer,
	comments varchar(4000)
);

create table OrgGroupRole (
	organizationId bigint not null,
	groupId bigint not null,
	roleId bigint not null,
	primary key (organizationId, groupId, roleId)
);

create table OrgLabor (
	orgLaborId bigint not null primary key,
	organizationId bigint,
	typeId integer,
	sunOpen integer,
	sunClose integer,
	monOpen integer,
	monClose integer,
	tueOpen integer,
	tueClose integer,
	wedOpen integer,
	wedClose integer,
	thuOpen integer,
	thuClose integer,
	friOpen integer,
	friClose integer,
	satOpen integer,
	satClose integer
);

create table PasswordPolicy (
	uuid_ varchar(75),
	passwordPolicyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	defaultPolicy smallint,
	name varchar(75),
	description varchar(4000),
	changeable smallint,
	changeRequired smallint,
	minAge bigint,
	checkSyntax smallint,
	allowDictionaryWords smallint,
	minAlphanumeric integer,
	minLength integer,
	minLowerCase integer,
	minNumbers integer,
	minSymbols integer,
	minUpperCase integer,
	regex varchar(75),
	history smallint,
	historyCount integer,
	expireable smallint,
	maxAge bigint,
	warningTime bigint,
	graceLimit integer,
	lockout smallint,
	maxFailure integer,
	lockoutDuration bigint,
	requireUnlock smallint,
	resetFailureCount bigint,
	resetTicketMaxAge bigint
);

create table PasswordPolicyRel (
	passwordPolicyRelId bigint not null primary key,
	passwordPolicyId bigint,
	classNameId bigint,
	classPK bigint
);

create table PasswordTracker (
	passwordTrackerId bigint not null primary key,
	userId bigint,
	createDate timestamp,
	password_ varchar(75)
);

create table Phone (
	uuid_ varchar(75),
	phoneId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	number_ varchar(75),
	extension varchar(75),
	typeId integer,
	primary_ smallint
);

create table PluginSetting (
	pluginSettingId bigint not null primary key,
	companyId bigint,
	pluginId varchar(75),
	pluginType varchar(75),
	roles varchar(4000),
	active_ smallint
);

create table PollsChoice (
	uuid_ varchar(75),
	choiceId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	questionId bigint,
	name varchar(75),
	description varchar(4000)
);

create table PollsQuestion (
	uuid_ varchar(75),
	questionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	title varchar(4000),
	description varchar(4000),
	expirationDate timestamp,
	lastVoteDate timestamp
);

create table PollsVote (
	uuid_ varchar(75),
	voteId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	questionId bigint,
	choiceId bigint,
	voteDate timestamp
);

create table PortalPreferences (
	portalPreferencesId bigint not null primary key,
	ownerId bigint,
	ownerType integer,
	preferences clob
);

create table Portlet (
	id_ bigint not null primary key,
	companyId bigint,
	portletId varchar(200),
	roles varchar(4000),
	active_ smallint
);

create table PortletItem (
	portletItemId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	portletId varchar(200),
	classNameId bigint
);

create table PortletPreferences (
	portletPreferencesId bigint not null primary key,
	ownerId bigint,
	ownerType integer,
	plid bigint,
	portletId varchar(200),
	preferences clob
);

create table RatingsEntry (
	entryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	score double
);

create table RatingsStats (
	statsId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	totalEntries integer,
	totalScore double,
	averageScore double
);

create table Region (
	regionId bigint not null primary key,
	countryId bigint,
	regionCode varchar(75),
	name varchar(75),
	active_ smallint
);

create table Release_ (
	releaseId bigint not null primary key,
	createDate timestamp,
	modifiedDate timestamp,
	servletContextName varchar(75),
	buildNumber integer,
	buildDate timestamp,
	verified smallint,
	state_ integer,
	testString varchar(1024)
);

create table Repository (
	uuid_ varchar(75),
	repositoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	name varchar(75),
	description varchar(4000),
	portletId varchar(200),
	typeSettings clob,
	dlFolderId bigint
);

create table RepositoryEntry (
	uuid_ varchar(75),
	repositoryEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	repositoryId bigint,
	mappedId varchar(75),
	manualCheckInRequired smallint
);

create table ResourceAction (
	resourceActionId bigint not null primary key,
	name varchar(255),
	actionId varchar(75),
	bitwiseValue bigint
);

create table ResourceBlock (
	resourceBlockId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	name varchar(75),
	permissionsHash varchar(75),
	referenceCount bigint
);

create table ResourceBlockPermission (
	resourceBlockPermissionId bigint not null primary key,
	resourceBlockId bigint,
	roleId bigint,
	actionIds bigint
);

create table ResourcePermission (
	resourcePermissionId bigint not null primary key,
	companyId bigint,
	name varchar(255),
	scope integer,
	primKey varchar(255),
	roleId bigint,
	ownerId bigint,
	actionIds bigint
);

create table ResourceTypePermission (
	resourceTypePermissionId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	name varchar(75),
	roleId bigint,
	actionIds bigint
);

create table Role_ (
	uuid_ varchar(75),
	roleId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	name varchar(75),
	title varchar(4000),
	description varchar(4000),
	type_ integer,
	subtype varchar(75)
);

create table SCFrameworkVersi_SCProductVers (
	frameworkVersionId bigint not null,
	productVersionId bigint not null,
	primary key (frameworkVersionId, productVersionId)
);

create table SCFrameworkVersion (
	frameworkVersionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	url varchar(4000),
	active_ smallint,
	priority integer
);

create table SCLicense (
	licenseId bigint not null primary key,
	name varchar(75),
	url varchar(4000),
	openSource smallint,
	active_ smallint,
	recommended smallint
);

create table SCLicenses_SCProductEntries (
	licenseId bigint not null,
	productEntryId bigint not null,
	primary key (licenseId, productEntryId)
);

create table SCProductEntry (
	productEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	type_ varchar(75),
	tags varchar(255),
	shortDescription varchar(4000),
	longDescription varchar(4000),
	pageURL varchar(4000),
	author varchar(75),
	repoGroupId varchar(75),
	repoArtifactId varchar(75)
);

create table SCProductScreenshot (
	productScreenshotId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	productEntryId bigint,
	thumbnailId bigint,
	fullImageId bigint,
	priority integer
);

create table SCProductVersion (
	productVersionId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	productEntryId bigint,
	version varchar(75),
	changeLog varchar(4000),
	downloadPageURL varchar(4000),
	directDownloadURL varchar(2000),
	repoStoreArtifact smallint
);

create table ServiceComponent (
	serviceComponentId bigint not null primary key,
	buildNamespace varchar(75),
	buildNumber bigint,
	buildDate bigint,
	data_ clob
);

create table Shard (
	shardId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	name varchar(75)
);

create table ShoppingCart (
	cartId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	itemIds varchar(4000),
	couponCodes varchar(75),
	altShipping integer,
	insure smallint
);

create table ShoppingCategory (
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	parentCategoryId bigint,
	name varchar(75),
	description varchar(4000)
);

create table ShoppingCoupon (
	couponId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	code_ varchar(75),
	name varchar(75),
	description varchar(4000),
	startDate timestamp,
	endDate timestamp,
	active_ smallint,
	limitCategories varchar(4000),
	limitSkus varchar(4000),
	minOrder double,
	discount double,
	discountType varchar(75)
);

create table ShoppingItem (
	itemId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	categoryId bigint,
	sku varchar(75),
	name varchar(200),
	description varchar(4000),
	properties varchar(4000),
	fields_ smallint,
	fieldsQuantities varchar(4000),
	minQuantity integer,
	maxQuantity integer,
	price double,
	discount double,
	taxable smallint,
	shipping double,
	useShippingFormula smallint,
	requiresShipping smallint,
	stockQuantity integer,
	featured_ smallint,
	sale_ smallint,
	smallImage smallint,
	smallImageId bigint,
	smallImageURL varchar(4000),
	mediumImage smallint,
	mediumImageId bigint,
	mediumImageURL varchar(4000),
	largeImage smallint,
	largeImageId bigint,
	largeImageURL varchar(4000)
);

create table ShoppingItemField (
	itemFieldId bigint not null primary key,
	itemId bigint,
	name varchar(75),
	values_ varchar(4000),
	description varchar(4000)
);

create table ShoppingItemPrice (
	itemPriceId bigint not null primary key,
	itemId bigint,
	minQuantity integer,
	maxQuantity integer,
	price double,
	discount double,
	taxable smallint,
	shipping double,
	useShippingFormula smallint,
	status integer
);

create table ShoppingOrder (
	orderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	number_ varchar(75),
	tax double,
	shipping double,
	altShipping varchar(75),
	requiresShipping smallint,
	insure smallint,
	insurance double,
	couponCodes varchar(75),
	couponDiscount double,
	billingFirstName varchar(75),
	billingLastName varchar(75),
	billingEmailAddress varchar(75),
	billingCompany varchar(75),
	billingStreet varchar(75),
	billingCity varchar(75),
	billingState varchar(75),
	billingZip varchar(75),
	billingCountry varchar(75),
	billingPhone varchar(75),
	shipToBilling smallint,
	shippingFirstName varchar(75),
	shippingLastName varchar(75),
	shippingEmailAddress varchar(75),
	shippingCompany varchar(75),
	shippingStreet varchar(75),
	shippingCity varchar(75),
	shippingState varchar(75),
	shippingZip varchar(75),
	shippingCountry varchar(75),
	shippingPhone varchar(75),
	ccName varchar(75),
	ccType varchar(75),
	ccNumber varchar(75),
	ccExpMonth integer,
	ccExpYear integer,
	ccVerNumber varchar(75),
	comments varchar(4000),
	ppTxnId varchar(75),
	ppPaymentStatus varchar(75),
	ppPaymentGross double,
	ppReceiverEmail varchar(75),
	ppPayerEmail varchar(75),
	sendOrderEmail smallint,
	sendShippingEmail smallint
);

create table ShoppingOrderItem (
	orderItemId bigint not null primary key,
	orderId bigint,
	itemId varchar(75),
	sku varchar(75),
	name varchar(200),
	description varchar(4000),
	properties varchar(4000),
	price double,
	quantity integer,
	shippedDate timestamp
);

create table SocialActivity (
	activityId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate bigint,
	activitySetId bigint,
	mirrorActivityId bigint,
	classNameId bigint,
	classPK bigint,
	parentClassNameId bigint,
	parentClassPK bigint,
	type_ integer,
	extraData varchar(4000),
	receiverUserId bigint
);

create table SocialActivityAchievement (
	activityAchievementId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate bigint,
	name varchar(75),
	firstInGroup smallint
);

create table SocialActivityCounter (
	activityCounterId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	classPK bigint,
	name varchar(75),
	ownerType integer,
	currentValue integer,
	totalValue integer,
	graceValue integer,
	startPeriod integer,
	endPeriod integer,
	active_ smallint
);

create table SocialActivityLimit (
	activityLimitId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	classNameId bigint,
	classPK bigint,
	activityType integer,
	activityCounterName varchar(75),
	value varchar(75)
);

create table SocialActivitySet (
	activitySetId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate bigint,
	modifiedDate bigint,
	classNameId bigint,
	classPK bigint,
	type_ integer,
	extraData varchar(4000),
	activityCount integer
);

create table SocialActivitySetting (
	activitySettingId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	activityType integer,
	name varchar(75),
	value varchar(1024)
);

create table SocialRelation (
	uuid_ varchar(75),
	relationId bigint not null primary key,
	companyId bigint,
	createDate bigint,
	userId1 bigint,
	userId2 bigint,
	type_ integer
);

create table SocialRequest (
	uuid_ varchar(75),
	requestId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate bigint,
	modifiedDate bigint,
	classNameId bigint,
	classPK bigint,
	type_ integer,
	extraData varchar(4000),
	receiverUserId bigint,
	status integer
);

create table Subscription (
	subscriptionId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	frequency varchar(75)
);

create table SystemEvent (
	systemEventId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	classNameId bigint,
	classPK bigint,
	classUuid varchar(75),
	referrerClassNameId bigint,
	parentSystemEventId bigint,
	systemEventSetKey bigint,
	type_ integer,
	extraData clob
);

create table Team (
	teamId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	groupId bigint,
	name varchar(75),
	description varchar(4000)
);

create table Ticket (
	ticketId bigint not null primary key,
	companyId bigint,
	createDate timestamp,
	classNameId bigint,
	classPK bigint,
	key_ varchar(75),
	type_ integer,
	extraInfo clob,
	expirationDate timestamp
);

create table TrashEntry (
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	classNameId bigint,
	classPK bigint,
	systemEventSetKey bigint,
	typeSettings clob,
	status integer
);

create table TrashVersion (
	versionId bigint not null primary key,
	entryId bigint,
	classNameId bigint,
	classPK bigint,
	typeSettings clob,
	status integer
);

create table UserNotificationDelivery (
	userNotificationDeliveryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	portletId varchar(200),
	classNameId bigint,
	notificationType integer,
	deliveryType integer,
	deliver smallint
);

create table User_ (
	uuid_ varchar(75),
	userId bigint not null primary key,
	companyId bigint,
	createDate timestamp,
	modifiedDate timestamp,
	defaultUser smallint,
	contactId bigint,
	password_ varchar(75),
	passwordEncrypted smallint,
	passwordReset smallint,
	passwordModifiedDate timestamp,
	digest varchar(255),
	reminderQueryQuestion varchar(75),
	reminderQueryAnswer varchar(75),
	graceLoginCount integer,
	screenName varchar(75),
	emailAddress varchar(75),
	facebookId bigint,
	ldapServerId bigint,
	openId varchar(1024),
	portraitId bigint,
	languageId varchar(75),
	timeZoneId varchar(75),
	greeting varchar(255),
	comments varchar(4000),
	firstName varchar(75),
	middleName varchar(75),
	lastName varchar(75),
	jobTitle varchar(100),
	loginDate timestamp,
	loginIP varchar(75),
	lastLoginDate timestamp,
	lastLoginIP varchar(75),
	lastFailedLoginDate timestamp,
	failedLoginAttempts integer,
	lockout smallint,
	lockoutDate timestamp,
	agreedToTermsOfUse smallint,
	emailAddressVerified smallint,
	status integer
);

create table UserGroup (
	uuid_ varchar(75),
	userGroupId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	parentUserGroupId bigint,
	name varchar(75),
	description varchar(4000),
	addedByLDAPImport smallint
);

create table UserGroupGroupRole (
	userGroupId bigint not null,
	groupId bigint not null,
	roleId bigint not null,
	primary key (userGroupId, groupId, roleId)
);

create table UserGroupRole (
	userId bigint not null,
	groupId bigint not null,
	roleId bigint not null,
	primary key (userId, groupId, roleId)
);

create table UserGroups_Teams (
	teamId bigint not null,
	userGroupId bigint not null,
	primary key (teamId, userGroupId)
);

create table UserIdMapper (
	userIdMapperId bigint not null primary key,
	userId bigint,
	type_ varchar(75),
	description varchar(75),
	externalUserId varchar(75)
);

create table UserNotificationEvent (
	uuid_ varchar(75),
	userNotificationEventId bigint not null primary key,
	companyId bigint,
	userId bigint,
	type_ varchar(75),
	timestamp bigint,
	deliverBy bigint,
	delivered smallint,
	payload clob,
	archived smallint
);

create table Users_Groups (
	groupId bigint not null,
	userId bigint not null,
	primary key (groupId, userId)
);

create table Users_Orgs (
	organizationId bigint not null,
	userId bigint not null,
	primary key (organizationId, userId)
);

create table Users_Roles (
	roleId bigint not null,
	userId bigint not null,
	primary key (roleId, userId)
);

create table Users_Teams (
	teamId bigint not null,
	userId bigint not null,
	primary key (teamId, userId)
);

create table Users_UserGroups (
	userId bigint not null,
	userGroupId bigint not null,
	primary key (userId, userGroupId)
);

create table UserTracker (
	userTrackerId bigint not null primary key,
	companyId bigint,
	userId bigint,
	modifiedDate timestamp,
	sessionId varchar(200),
	remoteAddr varchar(75),
	remoteHost varchar(75),
	userAgent varchar(200)
);

create table UserTrackerPath (
	userTrackerPathId bigint not null primary key,
	userTrackerId bigint,
	path_ varchar(4000),
	pathDate timestamp
);

create table VirtualHost (
	virtualHostId bigint not null primary key,
	companyId bigint,
	layoutSetId bigint,
	hostname varchar(75)
);

create table WebDAVProps (
	webDavPropsId bigint not null primary key,
	companyId bigint,
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	props clob
);

create table Website (
	uuid_ varchar(75),
	websiteId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	url varchar(4000),
	typeId integer,
	primary_ smallint
);

create table WikiNode (
	uuid_ varchar(75),
	nodeId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	description varchar(4000),
	lastPostDate timestamp,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table WikiPage (
	uuid_ varchar(75),
	pageId bigint not null primary key,
	resourcePrimKey bigint,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	nodeId bigint,
	title varchar(255),
	version double,
	minorEdit smallint,
	content clob,
	summary varchar(4000),
	format varchar(75),
	head smallint,
	parentTitle varchar(255),
	redirectTitle varchar(255),
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75),
	statusDate timestamp
);

create table WikiPageResource (
	uuid_ varchar(75),
	resourcePrimKey bigint not null primary key,
	nodeId bigint,
	title varchar(255)
);

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	classNameId bigint,
	classPK bigint,
	typePK bigint,
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

create table Account_ (
	accountId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	parentAccountId int8,
	name varchar(75),
	legalName varchar(75),
	legalId varchar(75),
	legalType varchar(75),
	sicCode varchar(75),
	tickerSymbol varchar(75),
	industry varchar(75),
	type_ varchar(75),
	size_ varchar(75)
)
extent size 16 next size 16
lock mode row;

create table Address (
	uuid_ varchar(75),
	addressId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	street1 varchar(75),
	street2 varchar(75),
	street3 varchar(75),
	city varchar(75),
	zip varchar(75),
	regionId int8,
	countryId int8,
	typeId int,
	mailing boolean,
	primary_ boolean
)
extent size 16 next size 16
lock mode row;

create table AnnouncementsDelivery (
	deliveryId int8 not null primary key,
	companyId int8,
	userId int8,
	type_ varchar(75),
	email boolean,
	sms boolean,
	website boolean
)
extent size 16 next size 16
lock mode row;

create table AnnouncementsEntry (
	uuid_ varchar(75),
	entryId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	title varchar(75),
	content text,
	url lvarchar,
	type_ varchar(75),
	displayDate datetime YEAR TO FRACTION,
	expirationDate datetime YEAR TO FRACTION,
	priority int,
	alert boolean
)
extent size 16 next size 16
lock mode row;

create table AnnouncementsFlag (
	flagId int8 not null primary key,
	userId int8,
	createDate datetime YEAR TO FRACTION,
	entryId int8,
	value int
)
extent size 16 next size 16
lock mode row;

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
	description lvarchar,
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
	categoryId int8 not null,
	entryId int8 not null,
	primary key (categoryId, entryId)
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
	classUuid varchar(75),
	classTypeId int8,
	visible boolean,
	startDate datetime YEAR TO FRACTION,
	endDate datetime YEAR TO FRACTION,
	publishDate datetime YEAR TO FRACTION,
	expirationDate datetime YEAR TO FRACTION,
	mimeType varchar(75),
	title lvarchar,
	description text,
	summary text,
	url lvarchar,
	layoutUuid varchar(75),
	height int,
	width int,
	priority float,
	viewCount int
)
extent size 16 next size 16
lock mode row;

create table AssetLink (
	linkId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	entryId1 int8,
	entryId2 int8,
	type_ int,
	weight int
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

create table BackgroundTask (
	backgroundTaskId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name varchar(75),
	servletContextNames varchar(255),
	taskExecutorClassName varchar(200),
	taskContext text,
	completed boolean,
	completionDate datetime YEAR TO FRACTION,
	status int,
	statusMessage text
)
extent size 16 next size 16
lock mode row;

create table BlogsEntry (
	uuid_ varchar(75),
	entryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	title varchar(150),
	urlTitle varchar(150),
	description lvarchar,
	content text,
	displayDate datetime YEAR TO FRACTION,
	allowPingbacks boolean,
	allowTrackbacks boolean,
	trackbacks text,
	smallImage boolean,
	smallImageId int8,
	smallImageURL lvarchar,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table BlogsStatsUser (
	statsUserId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	entryCount int,
	lastPostDate datetime YEAR TO FRACTION,
	ratingsTotalEntries int,
	ratingsTotalScore float,
	ratingsAverageScore float
)
extent size 16 next size 16
lock mode row;

create table BookmarksEntry (
	uuid_ varchar(75),
	entryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	resourceBlockId int8,
	folderId int8,
	treePath lvarchar,
	name varchar(255),
	url lvarchar,
	description lvarchar,
	visits int,
	priority int,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table BookmarksFolder (
	uuid_ varchar(75),
	folderId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	resourceBlockId int8,
	parentFolderId int8,
	treePath lvarchar,
	name varchar(75),
	description lvarchar,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table BrowserTracker (
	browserTrackerId int8 not null primary key,
	userId int8,
	browserKey int8
)
extent size 16 next size 16
lock mode row;

create table CalEvent (
	uuid_ varchar(75),
	eventId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	title varchar(75),
	description lvarchar,
	location lvarchar,
	startDate datetime YEAR TO FRACTION,
	endDate datetime YEAR TO FRACTION,
	durationHour int,
	durationMinute int,
	allDay boolean,
	timeZoneSensitive boolean,
	type_ varchar(75),
	repeating boolean,
	recurrence text,
	remindBy int,
	firstReminder int,
	secondReminder int
)
extent size 16 next size 16
lock mode row;

create table ClassName_ (
	classNameId int8 not null primary key,
	value varchar(200)
)
extent size 16 next size 16
lock mode row;

create table ClusterGroup (
	clusterGroupId int8 not null primary key,
	name varchar(75),
	clusterNodeIds varchar(75),
	wholeCluster boolean
)
extent size 16 next size 16
lock mode row;

create table Company (
	companyId int8 not null primary key,
	accountId int8,
	webId varchar(75),
	key_ text,
	mx varchar(75),
	homeURL lvarchar,
	logoId int8,
	system boolean,
	maxUsers int,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

create table Contact_ (
	contactId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	accountId int8,
	parentContactId int8,
	emailAddress varchar(75),
	firstName varchar(75),
	middleName varchar(75),
	lastName varchar(75),
	prefixId int,
	suffixId int,
	male boolean,
	birthday datetime YEAR TO FRACTION,
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
)
extent size 16 next size 16
lock mode row;

create table Counter (
	name varchar(75) not null primary key,
	currentId int8
)
extent size 16 next size 16
lock mode row;

create table Country (
	countryId int8 not null primary key,
	name varchar(75),
	a2 varchar(75),
	a3 varchar(75),
	number_ varchar(75),
	idd_ varchar(75),
	zipRequired boolean,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

create table CyrusUser (
	userId varchar(75) not null primary key,
	password_ varchar(75) not null
)
extent size 16 next size 16
lock mode row;

create table CyrusVirtual (
	emailAddress varchar(75) not null primary key,
	userId varchar(75) not null
)
extent size 16 next size 16
lock mode row;

create table DDLRecord (
	uuid_ varchar(75),
	recordId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	versionUserId int8,
	versionUserName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	DDMStorageId int8,
	recordSetId int8,
	version varchar(75),
	displayIndex int
)
extent size 16 next size 16
lock mode row;

create table DDLRecordSet (
	uuid_ varchar(75),
	recordSetId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	DDMStructureId int8,
	recordSetKey varchar(75),
	name lvarchar,
	description lvarchar,
	minDisplayRows int,
	scope int
)
extent size 16 next size 16
lock mode row;

create table DDLRecordVersion (
	recordVersionId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	DDMStorageId int8,
	recordSetId int8,
	recordId int8,
	version varchar(75),
	displayIndex int,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table DDMContent (
	uuid_ varchar(75),
	contentId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name lvarchar,
	description lvarchar,
	xml text
)
extent size 16 next size 16
lock mode row;

create table DDMStorageLink (
	uuid_ varchar(75),
	storageLinkId int8 not null primary key,
	classNameId int8,
	classPK int8,
	structureId int8
)
extent size 16 next size 16
lock mode row;

create table DDMStructure (
	uuid_ varchar(75),
	structureId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	parentStructureId int8,
	classNameId int8,
	structureKey varchar(75),
	name lvarchar,
	description lvarchar,
	xsd text,
	storageType varchar(75),
	type_ int
)
extent size 16 next size 16
lock mode row;

create table DDMStructureLink (
	structureLinkId int8 not null primary key,
	classNameId int8,
	classPK int8,
	structureId int8
)
extent size 16 next size 16
lock mode row;

create table DDMTemplate (
	uuid_ varchar(75),
	templateId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	templateKey varchar(75),
	name lvarchar,
	description lvarchar,
	type_ varchar(75),
	mode_ varchar(75),
	language varchar(75),
	script text,
	cacheable boolean,
	smallImage boolean,
	smallImageId int8,
	smallImageURL varchar(75)
)
extent size 16 next size 16
lock mode row;

create table DLContent (
	contentId int8 not null primary key,
	groupId int8,
	companyId int8,
	repositoryId int8,
	path_ varchar(255),
	version varchar(75),
	data_ blob,
	size_ int8
)
extent size 16 next size 16
lock mode row;

create table DLFileEntry (
	uuid_ varchar(75),
	fileEntryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	repositoryId int8,
	folderId int8,
	treePath lvarchar,
	name varchar(255),
	extension varchar(75),
	mimeType varchar(75),
	title varchar(255),
	description lvarchar,
	extraSettings text,
	fileEntryTypeId int8,
	version varchar(75),
	size_ int8,
	readCount int,
	smallImageId int8,
	largeImageId int8,
	custom1ImageId int8,
	custom2ImageId int8,
	manualCheckInRequired boolean
)
extent size 16 next size 16
lock mode row;

create table DLFileEntryMetadata (
	uuid_ varchar(75),
	fileEntryMetadataId int8 not null primary key,
	DDMStorageId int8,
	DDMStructureId int8,
	fileEntryTypeId int8,
	fileEntryId int8,
	fileVersionId int8
)
extent size 16 next size 16
lock mode row;

create table DLFileEntryType (
	uuid_ varchar(75),
	fileEntryTypeId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	fileEntryTypeKey varchar(75),
	name lvarchar,
	description lvarchar
)
extent size 16 next size 16
lock mode row;

create table DLFileEntryTypes_DDMStructures (
	structureId int8 not null,
	fileEntryTypeId int8 not null,
	primary key (structureId, fileEntryTypeId)
)
extent size 16 next size 16
lock mode row;

create table DLFileEntryTypes_DLFolders (
	fileEntryTypeId int8 not null,
	folderId int8 not null,
	primary key (fileEntryTypeId, folderId)
)
extent size 16 next size 16
lock mode row;

create table DLFileRank (
	fileRankId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	createDate datetime YEAR TO FRACTION,
	fileEntryId int8,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

create table DLFileShortcut (
	uuid_ varchar(75),
	fileShortcutId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	repositoryId int8,
	folderId int8,
	toFileEntryId int8,
	treePath lvarchar,
	active_ boolean,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table DLFileVersion (
	uuid_ varchar(75),
	fileVersionId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	repositoryId int8,
	folderId int8,
	fileEntryId int8,
	treePath lvarchar,
	extension varchar(75),
	mimeType varchar(75),
	title varchar(255),
	description lvarchar,
	changeLog varchar(75),
	extraSettings text,
	fileEntryTypeId int8,
	version varchar(75),
	size_ int8,
	checksum varchar(75),
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table DLFolder (
	uuid_ varchar(75),
	folderId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	repositoryId int8,
	mountPoint boolean,
	parentFolderId int8,
	treePath lvarchar,
	name varchar(100),
	description lvarchar,
	lastPostDate datetime YEAR TO FRACTION,
	defaultFileEntryTypeId int8,
	hidden_ boolean,
	overrideFileEntryTypes boolean,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table DLSyncEvent (
	syncEventId int8 not null primary key,
	modifiedTime int8,
	event varchar(75),
	type_ varchar(75),
	typePK int8
)
extent size 16 next size 16
lock mode row;

create table EmailAddress (
	uuid_ varchar(75),
	emailAddressId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	address varchar(75),
	typeId int,
	primary_ boolean
)
extent size 16 next size 16
lock mode row;

create table ExpandoColumn (
	columnId int8 not null primary key,
	companyId int8,
	tableId int8,
	name varchar(75),
	type_ int,
	defaultData lvarchar,
	typeSettings lvarchar(4096)
)
extent size 16 next size 16
lock mode row;

create table ExpandoRow (
	rowId_ int8 not null primary key,
	companyId int8,
	modifiedDate datetime YEAR TO FRACTION,
	tableId int8,
	classPK int8
)
extent size 16 next size 16
lock mode row;

create table ExpandoTable (
	tableId int8 not null primary key,
	companyId int8,
	classNameId int8,
	name varchar(75)
)
extent size 16 next size 16
lock mode row;

create table ExpandoValue (
	valueId int8 not null primary key,
	companyId int8,
	tableId int8,
	columnId int8,
	rowId_ int8,
	classNameId int8,
	classPK int8,
	data_ lvarchar
)
extent size 16 next size 16
lock mode row;

create table Group_ (
	uuid_ varchar(75),
	groupId int8 not null primary key,
	companyId int8,
	creatorUserId int8,
	classNameId int8,
	classPK int8,
	parentGroupId int8,
	liveGroupId int8,
	treePath lvarchar,
	name varchar(150),
	description lvarchar,
	type_ int,
	typeSettings lvarchar(4096),
	manualMembership boolean,
	membershipRestriction int,
	friendlyURL varchar(255),
	site boolean,
	remoteStagingGroupCount int,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

create table Groups_Orgs (
	groupId int8 not null,
	organizationId int8 not null,
	primary key (groupId, organizationId)
)
extent size 16 next size 16
lock mode row;

create table Groups_Roles (
	groupId int8 not null,
	roleId int8 not null,
	primary key (groupId, roleId)
)
extent size 16 next size 16
lock mode row;

create table Groups_UserGroups (
	groupId int8 not null,
	userGroupId int8 not null,
	primary key (groupId, userGroupId)
)
extent size 16 next size 16
lock mode row;

create table Image (
	imageId int8 not null primary key,
	modifiedDate datetime YEAR TO FRACTION,
	type_ varchar(75),
	height int,
	width int,
	size_ int
)
extent size 16 next size 16
lock mode row;

create table JournalArticle (
	uuid_ varchar(75),
	id_ int8 not null primary key,
	resourcePrimKey int8,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	folderId int8,
	classNameId int8,
	classPK int8,
	treePath lvarchar,
	articleId varchar(75),
	version float,
	title lvarchar,
	urlTitle varchar(150),
	description text,
	content text,
	type_ varchar(75),
	structureId varchar(75),
	templateId varchar(75),
	layoutUuid varchar(75),
	displayDate datetime YEAR TO FRACTION,
	expirationDate datetime YEAR TO FRACTION,
	reviewDate datetime YEAR TO FRACTION,
	indexable boolean,
	smallImage boolean,
	smallImageId int8,
	smallImageURL lvarchar,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table JournalArticleImage (
	articleImageId int8 not null primary key,
	groupId int8,
	articleId varchar(75),
	version float,
	elInstanceId varchar(75),
	elName varchar(75),
	languageId varchar(75),
	tempImage boolean
)
extent size 16 next size 16
lock mode row;

create table JournalArticleResource (
	uuid_ varchar(75),
	resourcePrimKey int8 not null primary key,
	groupId int8,
	articleId varchar(75)
)
extent size 16 next size 16
lock mode row;

create table JournalContentSearch (
	contentSearchId int8 not null primary key,
	groupId int8,
	companyId int8,
	privateLayout boolean,
	layoutId int8,
	portletId varchar(200),
	articleId varchar(75)
)
extent size 16 next size 16
lock mode row;

create table JournalFeed (
	uuid_ varchar(75),
	id_ int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	feedId varchar(75),
	name varchar(75),
	description lvarchar,
	type_ varchar(75),
	structureId varchar(75),
	templateId varchar(75),
	rendererTemplateId varchar(75),
	delta int,
	orderByCol varchar(75),
	orderByType varchar(75),
	targetLayoutFriendlyUrl varchar(255),
	targetPortletId varchar(75),
	contentField varchar(75),
	feedFormat varchar(75),
	feedVersion float
)
extent size 16 next size 16
lock mode row;

create table JournalFolder (
	uuid_ varchar(75),
	folderId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	parentFolderId int8,
	treePath lvarchar,
	name varchar(100),
	description lvarchar,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table Layout (
	uuid_ varchar(75),
	plid int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	privateLayout boolean,
	layoutId int8,
	parentLayoutId int8,
	name lvarchar,
	title lvarchar,
	description lvarchar,
	keywords lvarchar,
	robots lvarchar,
	type_ varchar(75),
	typeSettings lvarchar(4096),
	hidden_ boolean,
	friendlyURL varchar(255),
	iconImage boolean,
	iconImageId int8,
	themeId varchar(75),
	colorSchemeId varchar(75),
	wapThemeId varchar(75),
	wapColorSchemeId varchar(75),
	css text,
	priority int,
	layoutPrototypeUuid varchar(75),
	layoutPrototypeLinkEnabled boolean,
	sourcePrototypeLayoutUuid varchar(75)
)
extent size 16 next size 16
lock mode row;

create table LayoutBranch (
	LayoutBranchId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	layoutSetBranchId int8,
	plid int8,
	name varchar(75),
	description lvarchar,
	master boolean
)
extent size 16 next size 16
lock mode row;

create table LayoutFriendlyURL (
	uuid_ varchar(75),
	layoutFriendlyURLId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	plid int8,
	privateLayout boolean,
	friendlyURL varchar(255),
	languageId varchar(75)
)
extent size 16 next size 16
lock mode row;

create table LayoutPrototype (
	uuid_ varchar(75),
	layoutPrototypeId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name lvarchar,
	description lvarchar,
	settings_ lvarchar,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

create table LayoutRevision (
	layoutRevisionId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	layoutSetBranchId int8,
	layoutBranchId int8,
	parentLayoutRevisionId int8,
	head boolean,
	major boolean,
	plid int8,
	privateLayout boolean,
	name lvarchar,
	title lvarchar,
	description lvarchar,
	keywords lvarchar,
	robots lvarchar,
	typeSettings lvarchar(4096),
	iconImage boolean,
	iconImageId int8,
	themeId varchar(75),
	colorSchemeId varchar(75),
	wapThemeId varchar(75),
	wapColorSchemeId varchar(75),
	css text,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table LayoutSet (
	layoutSetId int8 not null primary key,
	groupId int8,
	companyId int8,
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	privateLayout boolean,
	logo boolean,
	logoId int8,
	themeId varchar(75),
	colorSchemeId varchar(75),
	wapThemeId varchar(75),
	wapColorSchemeId varchar(75),
	css text,
	pageCount int,
	settings_ text,
	layoutSetPrototypeUuid varchar(75),
	layoutSetPrototypeLinkEnabled boolean
)
extent size 16 next size 16
lock mode row;

create table LayoutSetBranch (
	layoutSetBranchId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	privateLayout boolean,
	name varchar(75),
	description lvarchar,
	master boolean,
	logo boolean,
	logoId int8,
	themeId varchar(75),
	colorSchemeId varchar(75),
	wapThemeId varchar(75),
	wapColorSchemeId varchar(75),
	css text,
	settings_ text,
	layoutSetPrototypeUuid varchar(75),
	layoutSetPrototypeLinkEnabled boolean
)
extent size 16 next size 16
lock mode row;

create table LayoutSetPrototype (
	uuid_ varchar(75),
	layoutSetPrototypeId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name lvarchar,
	description lvarchar,
	settings_ lvarchar,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

create table ListType (
	listTypeId int not null primary key,
	name varchar(75),
	type_ varchar(75)
)
extent size 16 next size 16
lock mode row;

create table Lock_ (
	uuid_ varchar(75),
	lockId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	className varchar(75),
	key_ varchar(200),
	owner varchar(255),
	inheritable boolean,
	expirationDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table MBBan (
	uuid_ varchar(75),
	banId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	banUserId int8
)
extent size 16 next size 16
lock mode row;

create table MBCategory (
	uuid_ varchar(75),
	categoryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	parentCategoryId int8,
	name varchar(75),
	description lvarchar,
	displayStyle varchar(75),
	threadCount int,
	messageCount int,
	lastPostDate datetime YEAR TO FRACTION,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table MBDiscussion (
	uuid_ varchar(75),
	discussionId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	threadId int8
)
extent size 16 next size 16
lock mode row;

create table MBMailingList (
	uuid_ varchar(75),
	mailingListId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	categoryId int8,
	emailAddress varchar(75),
	inProtocol varchar(75),
	inServerName varchar(75),
	inServerPort int,
	inUseSSL boolean,
	inUserName varchar(75),
	inPassword varchar(75),
	inReadInterval int,
	outEmailAddress varchar(75),
	outCustom boolean,
	outServerName varchar(75),
	outServerPort int,
	outUseSSL boolean,
	outUserName varchar(75),
	outPassword varchar(75),
	allowAnonymous boolean,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

create table MBMessage (
	uuid_ varchar(75),
	messageId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	categoryId int8,
	threadId int8,
	rootMessageId int8,
	parentMessageId int8,
	subject varchar(75),
	body text,
	format varchar(75),
	anonymous boolean,
	priority float,
	allowPingbacks boolean,
	answer boolean,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table MBStatsUser (
	statsUserId int8 not null primary key,
	groupId int8,
	userId int8,
	messageCount int,
	lastPostDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table MBThread (
	uuid_ varchar(75),
	threadId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	categoryId int8,
	rootMessageId int8,
	rootMessageUserId int8,
	messageCount int,
	viewCount int,
	lastPostByUserId int8,
	lastPostDate datetime YEAR TO FRACTION,
	priority float,
	question boolean,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table MBThreadFlag (
	uuid_ varchar(75),
	threadFlagId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	threadId int8
)
extent size 16 next size 16
lock mode row;

create table MDRAction (
	uuid_ varchar(75),
	actionId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	ruleGroupInstanceId int8,
	name lvarchar,
	description lvarchar,
	type_ varchar(255),
	typeSettings lvarchar(4096)
)
extent size 16 next size 16
lock mode row;

create table MDRRule (
	uuid_ varchar(75),
	ruleId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	ruleGroupId int8,
	name lvarchar,
	description lvarchar,
	type_ varchar(255),
	typeSettings lvarchar(4096)
)
extent size 16 next size 16
lock mode row;

create table MDRRuleGroup (
	uuid_ varchar(75),
	ruleGroupId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name lvarchar,
	description lvarchar
)
extent size 16 next size 16
lock mode row;

create table MDRRuleGroupInstance (
	uuid_ varchar(75),
	ruleGroupInstanceId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	ruleGroupId int8,
	priority int
)
extent size 16 next size 16
lock mode row;

create table MembershipRequest (
	membershipRequestId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	createDate datetime YEAR TO FRACTION,
	comments lvarchar,
	replyComments lvarchar,
	replyDate datetime YEAR TO FRACTION,
	replierUserId int8,
	statusId int
)
extent size 16 next size 16
lock mode row;

create table Organization_ (
	uuid_ varchar(75),
	organizationId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	parentOrganizationId int8,
	treePath lvarchar,
	name varchar(100),
	type_ varchar(75),
	recursable boolean,
	regionId int8,
	countryId int8,
	statusId int,
	comments lvarchar
)
extent size 16 next size 16
lock mode row;

create table OrgGroupRole (
	organizationId int8 not null,
	groupId int8 not null,
	roleId int8 not null,
	primary key (organizationId, groupId, roleId)
)
extent size 16 next size 16
lock mode row;

create table OrgLabor (
	orgLaborId int8 not null primary key,
	organizationId int8,
	typeId int,
	sunOpen int,
	sunClose int,
	monOpen int,
	monClose int,
	tueOpen int,
	tueClose int,
	wedOpen int,
	wedClose int,
	thuOpen int,
	thuClose int,
	friOpen int,
	friClose int,
	satOpen int,
	satClose int
)
extent size 16 next size 16
lock mode row;

create table PasswordPolicy (
	uuid_ varchar(75),
	passwordPolicyId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	defaultPolicy boolean,
	name varchar(75),
	description lvarchar,
	changeable boolean,
	changeRequired boolean,
	minAge int8,
	checkSyntax boolean,
	allowDictionaryWords boolean,
	minAlphanumeric int,
	minLength int,
	minLowerCase int,
	minNumbers int,
	minSymbols int,
	minUpperCase int,
	regex varchar(75),
	history boolean,
	historyCount int,
	expireable boolean,
	maxAge int8,
	warningTime int8,
	graceLimit int,
	lockout boolean,
	maxFailure int,
	lockoutDuration int8,
	requireUnlock boolean,
	resetFailureCount int8,
	resetTicketMaxAge int8
)
extent size 16 next size 16
lock mode row;

create table PasswordPolicyRel (
	passwordPolicyRelId int8 not null primary key,
	passwordPolicyId int8,
	classNameId int8,
	classPK int8
)
extent size 16 next size 16
lock mode row;

create table PasswordTracker (
	passwordTrackerId int8 not null primary key,
	userId int8,
	createDate datetime YEAR TO FRACTION,
	password_ varchar(75)
)
extent size 16 next size 16
lock mode row;

create table Phone (
	uuid_ varchar(75),
	phoneId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	number_ varchar(75),
	extension varchar(75),
	typeId int,
	primary_ boolean
)
extent size 16 next size 16
lock mode row;

create table PluginSetting (
	pluginSettingId int8 not null primary key,
	companyId int8,
	pluginId varchar(75),
	pluginType varchar(75),
	roles lvarchar,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

create table PollsChoice (
	uuid_ varchar(75),
	choiceId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	questionId int8,
	name varchar(75),
	description lvarchar
)
extent size 16 next size 16
lock mode row;

create table PollsQuestion (
	uuid_ varchar(75),
	questionId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	title lvarchar,
	description lvarchar,
	expirationDate datetime YEAR TO FRACTION,
	lastVoteDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table PollsVote (
	uuid_ varchar(75),
	voteId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	questionId int8,
	choiceId int8,
	voteDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table PortalPreferences (
	portalPreferencesId int8 not null primary key,
	ownerId int8,
	ownerType int,
	preferences text
)
extent size 16 next size 16
lock mode row;

create table Portlet (
	id_ int8 not null primary key,
	companyId int8,
	portletId varchar(200),
	roles lvarchar,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

create table PortletItem (
	portletItemId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name varchar(75),
	portletId varchar(200),
	classNameId int8
)
extent size 16 next size 16
lock mode row;

create table PortletPreferences (
	portletPreferencesId int8 not null primary key,
	ownerId int8,
	ownerType int,
	plid int8,
	portletId varchar(200),
	preferences text
)
extent size 16 next size 16
lock mode row;

create table RatingsEntry (
	entryId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	score float
)
extent size 16 next size 16
lock mode row;

create table RatingsStats (
	statsId int8 not null primary key,
	classNameId int8,
	classPK int8,
	totalEntries int,
	totalScore float,
	averageScore float
)
extent size 16 next size 16
lock mode row;

create table Region (
	regionId int8 not null primary key,
	countryId int8,
	regionCode varchar(75),
	name varchar(75),
	active_ boolean
)
extent size 16 next size 16
lock mode row;

create table Release_ (
	releaseId int8 not null primary key,
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	servletContextName varchar(75),
	buildNumber int,
	buildDate datetime YEAR TO FRACTION,
	verified boolean,
	state_ int,
	testString lvarchar(1024)
)
extent size 16 next size 16
lock mode row;

create table Repository (
	uuid_ varchar(75),
	repositoryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	name varchar(75),
	description lvarchar,
	portletId varchar(200),
	typeSettings lvarchar(4096),
	dlFolderId int8
)
extent size 16 next size 16
lock mode row;

create table RepositoryEntry (
	uuid_ varchar(75),
	repositoryEntryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	repositoryId int8,
	mappedId varchar(75),
	manualCheckInRequired boolean
)
extent size 16 next size 16
lock mode row;

create table ResourceAction (
	resourceActionId int8 not null primary key,
	name varchar(255),
	actionId varchar(75),
	bitwiseValue int8
)
extent size 16 next size 16
lock mode row;

create table ResourceBlock (
	resourceBlockId int8 not null primary key,
	companyId int8,
	groupId int8,
	name varchar(75),
	permissionsHash varchar(75),
	referenceCount int8
)
extent size 16 next size 16
lock mode row;

create table ResourceBlockPermission (
	resourceBlockPermissionId int8 not null primary key,
	resourceBlockId int8,
	roleId int8,
	actionIds int8
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
	ownerId int8,
	actionIds int8
)
extent size 16 next size 16
lock mode row;

create table ResourceTypePermission (
	resourceTypePermissionId int8 not null primary key,
	companyId int8,
	groupId int8,
	name varchar(75),
	roleId int8,
	actionIds int8
)
extent size 16 next size 16
lock mode row;

create table Role_ (
	uuid_ varchar(75),
	roleId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	name varchar(75),
	title lvarchar,
	description lvarchar,
	type_ int,
	subtype varchar(75)
)
extent size 16 next size 16
lock mode row;

create table SCFrameworkVersi_SCProductVers (
	frameworkVersionId int8 not null,
	productVersionId int8 not null,
	primary key (frameworkVersionId, productVersionId)
)
extent size 16 next size 16
lock mode row;

create table SCFrameworkVersion (
	frameworkVersionId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name varchar(75),
	url lvarchar,
	active_ boolean,
	priority int
)
extent size 16 next size 16
lock mode row;

create table SCLicense (
	licenseId int8 not null primary key,
	name varchar(75),
	url lvarchar,
	openSource boolean,
	active_ boolean,
	recommended boolean
)
extent size 16 next size 16
lock mode row;

create table SCLicenses_SCProductEntries (
	licenseId int8 not null,
	productEntryId int8 not null,
	primary key (licenseId, productEntryId)
)
extent size 16 next size 16
lock mode row;

create table SCProductEntry (
	productEntryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name varchar(75),
	type_ varchar(75),
	tags varchar(255),
	shortDescription lvarchar,
	longDescription lvarchar,
	pageURL lvarchar,
	author varchar(75),
	repoGroupId varchar(75),
	repoArtifactId varchar(75)
)
extent size 16 next size 16
lock mode row;

create table SCProductScreenshot (
	productScreenshotId int8 not null primary key,
	companyId int8,
	groupId int8,
	productEntryId int8,
	thumbnailId int8,
	fullImageId int8,
	priority int
)
extent size 16 next size 16
lock mode row;

create table SCProductVersion (
	productVersionId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	productEntryId int8,
	version varchar(75),
	changeLog lvarchar,
	downloadPageURL lvarchar,
	directDownloadURL varchar(2000),
	repoStoreArtifact boolean
)
extent size 16 next size 16
lock mode row;

create table ServiceComponent (
	serviceComponentId int8 not null primary key,
	buildNamespace varchar(75),
	buildNumber int8,
	buildDate int8,
	data_ text
)
extent size 16 next size 16
lock mode row;

create table Shard (
	shardId int8 not null primary key,
	classNameId int8,
	classPK int8,
	name varchar(75)
)
extent size 16 next size 16
lock mode row;

create table ShoppingCart (
	cartId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	itemIds lvarchar,
	couponCodes varchar(75),
	altShipping int,
	insure boolean
)
extent size 16 next size 16
lock mode row;

create table ShoppingCategory (
	categoryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	parentCategoryId int8,
	name varchar(75),
	description lvarchar
)
extent size 16 next size 16
lock mode row;

create table ShoppingCoupon (
	couponId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	code_ varchar(75),
	name varchar(75),
	description lvarchar,
	startDate datetime YEAR TO FRACTION,
	endDate datetime YEAR TO FRACTION,
	active_ boolean,
	limitCategories lvarchar,
	limitSkus lvarchar,
	minOrder float,
	discount float,
	discountType varchar(75)
)
extent size 16 next size 16
lock mode row;

create table ShoppingItem (
	itemId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	categoryId int8,
	sku varchar(75),
	name varchar(200),
	description lvarchar,
	properties lvarchar,
	fields_ boolean,
	fieldsQuantities lvarchar,
	minQuantity int,
	maxQuantity int,
	price float,
	discount float,
	taxable boolean,
	shipping float,
	useShippingFormula boolean,
	requiresShipping boolean,
	stockQuantity int,
	featured_ boolean,
	sale_ boolean,
	smallImage boolean,
	smallImageId int8,
	smallImageURL lvarchar,
	mediumImage boolean,
	mediumImageId int8,
	mediumImageURL lvarchar,
	largeImage boolean,
	largeImageId int8,
	largeImageURL lvarchar
)
extent size 16 next size 16
lock mode row;

create table ShoppingItemField (
	itemFieldId int8 not null primary key,
	itemId int8,
	name varchar(75),
	values_ lvarchar,
	description lvarchar
)
extent size 16 next size 16
lock mode row;

create table ShoppingItemPrice (
	itemPriceId int8 not null primary key,
	itemId int8,
	minQuantity int,
	maxQuantity int,
	price float,
	discount float,
	taxable boolean,
	shipping float,
	useShippingFormula boolean,
	status int
)
extent size 16 next size 16
lock mode row;

create table ShoppingOrder (
	orderId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	number_ varchar(75),
	tax float,
	shipping float,
	altShipping varchar(75),
	requiresShipping boolean,
	insure boolean,
	insurance float,
	couponCodes varchar(75),
	couponDiscount float,
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
	shipToBilling boolean,
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
	ccExpMonth int,
	ccExpYear int,
	ccVerNumber varchar(75),
	comments lvarchar,
	ppTxnId varchar(75),
	ppPaymentStatus varchar(75),
	ppPaymentGross float,
	ppReceiverEmail varchar(75),
	ppPayerEmail varchar(75),
	sendOrderEmail boolean,
	sendShippingEmail boolean
)
extent size 16 next size 16
lock mode row;

create table ShoppingOrderItem (
	orderItemId int8 not null primary key,
	orderId int8,
	itemId varchar(75),
	sku varchar(75),
	name varchar(200),
	description lvarchar,
	properties lvarchar,
	price float,
	quantity int,
	shippedDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table SocialActivity (
	activityId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	createDate int8,
	activitySetId int8,
	mirrorActivityId int8,
	classNameId int8,
	classPK int8,
	parentClassNameId int8,
	parentClassPK int8,
	type_ int,
	extraData lvarchar,
	receiverUserId int8
)
extent size 16 next size 16
lock mode row;

create table SocialActivityAchievement (
	activityAchievementId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	createDate int8,
	name varchar(75),
	firstInGroup boolean
)
extent size 16 next size 16
lock mode row;

create table SocialActivityCounter (
	activityCounterId int8 not null primary key,
	groupId int8,
	companyId int8,
	classNameId int8,
	classPK int8,
	name varchar(75),
	ownerType int,
	currentValue int,
	totalValue int,
	graceValue int,
	startPeriod int,
	endPeriod int,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

create table SocialActivityLimit (
	activityLimitId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	classNameId int8,
	classPK int8,
	activityType int,
	activityCounterName varchar(75),
	value varchar(75)
)
extent size 16 next size 16
lock mode row;

create table SocialActivitySet (
	activitySetId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	createDate int8,
	modifiedDate int8,
	classNameId int8,
	classPK int8,
	type_ int,
	extraData lvarchar,
	activityCount int
)
extent size 16 next size 16
lock mode row;

create table SocialActivitySetting (
	activitySettingId int8 not null primary key,
	groupId int8,
	companyId int8,
	classNameId int8,
	activityType int,
	name varchar(75),
	value lvarchar(1024)
)
extent size 16 next size 16
lock mode row;

create table SocialRelation (
	uuid_ varchar(75),
	relationId int8 not null primary key,
	companyId int8,
	createDate int8,
	userId1 int8,
	userId2 int8,
	type_ int
)
extent size 16 next size 16
lock mode row;

create table SocialRequest (
	uuid_ varchar(75),
	requestId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	createDate int8,
	modifiedDate int8,
	classNameId int8,
	classPK int8,
	type_ int,
	extraData lvarchar,
	receiverUserId int8,
	status int
)
extent size 16 next size 16
lock mode row;

create table Subscription (
	subscriptionId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	frequency varchar(75)
)
extent size 16 next size 16
lock mode row;

create table SystemEvent (
	systemEventId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	classUuid varchar(75),
	referrerClassNameId int8,
	parentSystemEventId int8,
	systemEventSetKey int8,
	type_ int,
	extraData text
)
extent size 16 next size 16
lock mode row;

create table Team (
	teamId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	groupId int8,
	name varchar(75),
	description lvarchar
)
extent size 16 next size 16
lock mode row;

create table Ticket (
	ticketId int8 not null primary key,
	companyId int8,
	createDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	key_ varchar(75),
	type_ int,
	extraInfo text,
	expirationDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table TrashEntry (
	entryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	systemEventSetKey int8,
	typeSettings lvarchar(4096),
	status int
)
extent size 16 next size 16
lock mode row;

create table TrashVersion (
	versionId int8 not null primary key,
	entryId int8,
	classNameId int8,
	classPK int8,
	typeSettings lvarchar(4096),
	status int
)
extent size 16 next size 16
lock mode row;

create table UserNotificationDelivery (
	userNotificationDeliveryId int8 not null primary key,
	companyId int8,
	userId int8,
	portletId varchar(200),
	classNameId int8,
	notificationType int,
	deliveryType int,
	deliver boolean
)
extent size 16 next size 16
lock mode row;

create table User_ (
	uuid_ varchar(75),
	userId int8 not null primary key,
	companyId int8,
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	defaultUser boolean,
	contactId int8,
	password_ varchar(75),
	passwordEncrypted boolean,
	passwordReset boolean,
	passwordModifiedDate datetime YEAR TO FRACTION,
	digest varchar(255),
	reminderQueryQuestion varchar(75),
	reminderQueryAnswer varchar(75),
	graceLoginCount int,
	screenName varchar(75),
	emailAddress varchar(75),
	facebookId int8,
	ldapServerId int8,
	openId lvarchar(1024),
	portraitId int8,
	languageId varchar(75),
	timeZoneId varchar(75),
	greeting varchar(255),
	comments lvarchar,
	firstName varchar(75),
	middleName varchar(75),
	lastName varchar(75),
	jobTitle varchar(100),
	loginDate datetime YEAR TO FRACTION,
	loginIP varchar(75),
	lastLoginDate datetime YEAR TO FRACTION,
	lastLoginIP varchar(75),
	lastFailedLoginDate datetime YEAR TO FRACTION,
	failedLoginAttempts int,
	lockout boolean,
	lockoutDate datetime YEAR TO FRACTION,
	agreedToTermsOfUse boolean,
	emailAddressVerified boolean,
	status int
)
extent size 16 next size 16
lock mode row;

create table UserGroup (
	uuid_ varchar(75),
	userGroupId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	parentUserGroupId int8,
	name varchar(75),
	description lvarchar,
	addedByLDAPImport boolean
)
extent size 16 next size 16
lock mode row;

create table UserGroupGroupRole (
	userGroupId int8 not null,
	groupId int8 not null,
	roleId int8 not null,
	primary key (userGroupId, groupId, roleId)
)
extent size 16 next size 16
lock mode row;

create table UserGroupRole (
	userId int8 not null,
	groupId int8 not null,
	roleId int8 not null,
	primary key (userId, groupId, roleId)
)
extent size 16 next size 16
lock mode row;

create table UserGroups_Teams (
	teamId int8 not null,
	userGroupId int8 not null,
	primary key (teamId, userGroupId)
)
extent size 16 next size 16
lock mode row;

create table UserIdMapper (
	userIdMapperId int8 not null primary key,
	userId int8,
	type_ varchar(75),
	description varchar(75),
	externalUserId varchar(75)
)
extent size 16 next size 16
lock mode row;

create table UserNotificationEvent (
	uuid_ varchar(75),
	userNotificationEventId int8 not null primary key,
	companyId int8,
	userId int8,
	type_ varchar(75),
	timestamp int8,
	deliverBy int8,
	delivered boolean,
	payload text,
	archived boolean
)
extent size 16 next size 16
lock mode row;

create table Users_Groups (
	groupId int8 not null,
	userId int8 not null,
	primary key (groupId, userId)
)
extent size 16 next size 16
lock mode row;

create table Users_Orgs (
	organizationId int8 not null,
	userId int8 not null,
	primary key (organizationId, userId)
)
extent size 16 next size 16
lock mode row;

create table Users_Roles (
	roleId int8 not null,
	userId int8 not null,
	primary key (roleId, userId)
)
extent size 16 next size 16
lock mode row;

create table Users_Teams (
	teamId int8 not null,
	userId int8 not null,
	primary key (teamId, userId)
)
extent size 16 next size 16
lock mode row;

create table Users_UserGroups (
	userId int8 not null,
	userGroupId int8 not null,
	primary key (userId, userGroupId)
)
extent size 16 next size 16
lock mode row;

create table UserTracker (
	userTrackerId int8 not null primary key,
	companyId int8,
	userId int8,
	modifiedDate datetime YEAR TO FRACTION,
	sessionId varchar(200),
	remoteAddr varchar(75),
	remoteHost varchar(75),
	userAgent varchar(200)
)
extent size 16 next size 16
lock mode row;

create table UserTrackerPath (
	userTrackerPathId int8 not null primary key,
	userTrackerId int8,
	path_ lvarchar,
	pathDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table VirtualHost (
	virtualHostId int8 not null primary key,
	companyId int8,
	layoutSetId int8,
	hostname varchar(75)
)
extent size 16 next size 16
lock mode row;

create table WebDAVProps (
	webDavPropsId int8 not null primary key,
	companyId int8,
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	props text
)
extent size 16 next size 16
lock mode row;

create table Website (
	uuid_ varchar(75),
	websiteId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	url lvarchar,
	typeId int,
	primary_ boolean
)
extent size 16 next size 16
lock mode row;

create table WikiNode (
	uuid_ varchar(75),
	nodeId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name varchar(75),
	description lvarchar,
	lastPostDate datetime YEAR TO FRACTION,
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table WikiPage (
	uuid_ varchar(75),
	pageId int8 not null primary key,
	resourcePrimKey int8,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	nodeId int8,
	title varchar(255),
	version float,
	minorEdit boolean,
	content text,
	summary lvarchar,
	format varchar(75),
	head boolean,
	parentTitle varchar(255),
	redirectTitle varchar(255),
	status int,
	statusByUserId int8,
	statusByUserName varchar(75),
	statusDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

create table WikiPageResource (
	uuid_ varchar(75),
	resourcePrimKey int8 not null primary key,
	nodeId int8,
	title varchar(255)
)
extent size 16 next size 16
lock mode row;

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	typePK int8,
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


insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (1, 'canada', 'CA', 'CAN', '124', '001', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (2, 'china', 'CN', 'CHN', '156', '086', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (3, 'france', 'FR', 'FRA', '250', '033', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (4, 'germany', 'DE', 'DEU', '276', '049', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (5, 'hong-kong', 'HK', 'HKG', '344', '852', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (6, 'hungary', 'HU', 'HUN', '348', '036', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (7, 'israel', 'IL', 'ISR', '376', '972', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (8, 'italy', 'IT', 'ITA', '380', '039', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (9, 'japan', 'JP', 'JPN', '392', '081', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (10, 'south-korea', 'KR', 'KOR', '410', '082', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (11, 'netherlands', 'NL', 'NLD', '528', '031', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (12, 'portugal', 'PT', 'PRT', '620', '351', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (13, 'russia', 'RU', 'RUS', '643', '007', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (14, 'singapore', 'SG', 'SGP', '702', '065', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (15, 'spain', 'ES', 'ESP', '724', '034', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (16, 'turkey', 'TR', 'TUR', '792', '090', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (17, 'vietnam', 'VN', 'VNM', '704', '084', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (18, 'united-kingdom', 'GB', 'GBR', '826', '044', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (19, 'united-states', 'US', 'USA', '840', '001', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (20, 'afghanistan', 'AF', 'AFG', '4', '093', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (21, 'albania', 'AL', 'ALB', '8', '355', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (22, 'algeria', 'DZ', 'DZA', '12', '213', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (23, 'american-samoa', 'AS', 'ASM', '16', '684', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (24, 'andorra', 'AD', 'AND', '20', '376', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (25, 'angola', 'AO', 'AGO', '24', '244', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (26, 'anguilla', 'AI', 'AIA', '660', '264', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (27, 'antarctica', 'AQ', 'ATA', '10', '672', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (28, 'antigua-barbuda', 'AG', 'ATG', '28', '268', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (29, 'argentina', 'AR', 'ARG', '32', '054', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (30, 'armenia', 'AM', 'ARM', '51', '374', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (31, 'aruba', 'AW', 'ABW', '533', '297', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (32, 'australia', 'AU', 'AUS', '36', '061', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (33, 'austria', 'AT', 'AUT', '40', '043', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (34, 'azerbaijan', 'AZ', 'AZE', '31', '994', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (35, 'bahamas', 'BS', 'BHS', '44', '242', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (36, 'bahrain', 'BH', 'BHR', '48', '973', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (37, 'bangladesh', 'BD', 'BGD', '50', '880', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (38, 'barbados', 'BB', 'BRB', '52', '246', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (39, 'belarus', 'BY', 'BLR', '112', '375', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (40, 'belgium', 'BE', 'BEL', '56', '032', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (41, 'belize', 'BZ', 'BLZ', '84', '501', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (42, 'benin', 'BJ', 'BEN', '204', '229', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (43, 'bermuda', 'BM', 'BMU', '60', '441', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (44, 'bhutan', 'BT', 'BTN', '64', '975', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (45, 'bolivia', 'BO', 'BOL', '68', '591', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (46, 'bosnia-herzegovina', 'BA', 'BIH', '70', '387', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (47, 'botswana', 'BW', 'BWA', '72', '267', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (48, 'brazil', 'BR', 'BRA', '76', '055', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (49, 'british-virgin-islands', 'VG', 'VGB', '92', '284', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (50, 'brunei', 'BN', 'BRN', '96', '673', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (51, 'bulgaria', 'BG', 'BGR', '100', '359', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (52, 'burkina-faso', 'BF', 'BFA', '854', '226', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (53, 'burma-myanmar', 'MM', 'MMR', '104', '095', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (54, 'burundi', 'BI', 'BDI', '108', '257', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (55, 'cambodia', 'KH', 'KHM', '116', '855', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (56, 'cameroon', 'CM', 'CMR', '120', '237', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (57, 'cape-verde-island', 'CV', 'CPV', '132', '238', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (58, 'cayman-islands', 'KY', 'CYM', '136', '345', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (59, 'central-african-republic', 'CF', 'CAF', '140', '236', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (60, 'chad', 'TD', 'TCD', '148', '235', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (61, 'chile', 'CL', 'CHL', '152', '056', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (62, 'christmas-island', 'CX', 'CXR', '162', '061', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (63, 'cocos-islands', 'CC', 'CCK', '166', '061', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (64, 'colombia', 'CO', 'COL', '170', '057', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (65, 'comoros', 'KM', 'COM', '174', '269', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (66, 'republic-of-congo', 'CD', 'COD', '180', '242', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (67, 'democratic-republic-of-congo', 'CG', 'COG', '178', '243', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (68, 'cook-islands', 'CK', 'COK', '184', '682', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (69, 'costa-rica', 'CR', 'CRI', '188', '506', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (70, 'croatia', 'HR', 'HRV', '191', '385', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (71, 'cuba', 'CU', 'CUB', '192', '053', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (72, 'cyprus', 'CY', 'CYP', '196', '357', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (73, 'czech-republic', 'CZ', 'CZE', '203', '420', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (74, 'denmark', 'DK', 'DNK', '208', '045', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (75, 'djibouti', 'DJ', 'DJI', '262', '253', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (76, 'dominica', 'DM', 'DMA', '212', '767', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (77, 'dominican-republic', 'DO', 'DOM', '214', '809', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (78, 'ecuador', 'EC', 'ECU', '218', '593', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (79, 'egypt', 'EG', 'EGY', '818', '020', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (80, 'el-salvador', 'SV', 'SLV', '222', '503', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (81, 'equatorial-guinea', 'GQ', 'GNQ', '226', '240', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (82, 'eritrea', 'ER', 'ERI', '232', '291', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (83, 'estonia', 'EE', 'EST', '233', '372', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (84, 'ethiopia', 'ET', 'ETH', '231', '251', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (85, 'faeroe-islands', 'FO', 'FRO', '234', '298', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (86, 'falkland-islands', 'FK', 'FLK', '238', '500', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (87, 'fiji-islands', 'FJ', 'FJI', '242', '679', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (88, 'finland', 'FI', 'FIN', '246', '358', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (89, 'french-guiana', 'GF', 'GUF', '254', '594', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (90, 'french-polynesia', 'PF', 'PYF', '258', '689', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (91, 'gabon', 'GA', 'GAB', '266', '241', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (92, 'gambia', 'GM', 'GMB', '270', '220', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (93, 'georgia', 'GE', 'GEO', '268', '995', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (94, 'ghana', 'GH', 'GHA', '288', '233', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (95, 'gibraltar', 'GI', 'GIB', '292', '350', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (96, 'greece', 'GR', 'GRC', '300', '030', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (97, 'greenland', 'GL', 'GRL', '304', '299', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (98, 'grenada', 'GD', 'GRD', '308', '473', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (99, 'guadeloupe', 'GP', 'GLP', '312', '590', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (100, 'guam', 'GU', 'GUM', '316', '671', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (101, 'guatemala', 'GT', 'GTM', '320', '502', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (102, 'guinea', 'GN', 'GIN', '324', '224', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (103, 'guinea-bissau', 'GW', 'GNB', '624', '245', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (104, 'guyana', 'GY', 'GUY', '328', '592', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (105, 'haiti', 'HT', 'HTI', '332', '509', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (106, 'honduras', 'HN', 'HND', '340', '504', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (107, 'iceland', 'IS', 'ISL', '352', '354', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (108, 'india', 'IN', 'IND', '356', '091', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (109, 'indonesia', 'ID', 'IDN', '360', '062', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (110, 'iran', 'IR', 'IRN', '364', '098', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (111, 'iraq', 'IQ', 'IRQ', '368', '964', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (112, 'ireland', 'IE', 'IRL', '372', '353', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (113, 'ivory-coast', 'CI', 'CIV', '384', '225', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (114, 'jamaica', 'JM', 'JAM', '388', '876', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (115, 'jordan', 'JO', 'JOR', '400', '962', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (116, 'kazakhstan', 'KZ', 'KAZ', '398', '007', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (117, 'kenya', 'KE', 'KEN', '404', '254', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (118, 'kiribati', 'KI', 'KIR', '408', '686', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (119, 'kuwait', 'KW', 'KWT', '414', '965', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (120, 'north-korea', 'KP', 'PRK', '408', '850', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (121, 'kyrgyzstan', 'KG', 'KGZ', '471', '996', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (122, 'laos', 'LA', 'LAO', '418', '856', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (123, 'latvia', 'LV', 'LVA', '428', '371', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (124, 'lebanon', 'LB', 'LBN', '422', '961', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (125, 'lesotho', 'LS', 'LSO', '426', '266', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (126, 'liberia', 'LR', 'LBR', '430', '231', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (127, 'libya', 'LY', 'LBY', '434', '218', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (128, 'liechtenstein', 'LI', 'LIE', '438', '423', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (129, 'lithuania', 'LT', 'LTU', '440', '370', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (130, 'luxembourg', 'LU', 'LUX', '442', '352', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (131, 'macau', 'MO', 'MAC', '446', '853', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (132, 'macedonia', 'MK', 'MKD', '807', '389', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (133, 'madagascar', 'MG', 'MDG', '450', '261', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (134, 'malawi', 'MW', 'MWI', '454', '265', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (135, 'malaysia', 'MY', 'MYS', '458', '060', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (136, 'maldives', 'MV', 'MDV', '462', '960', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (137, 'mali', 'ML', 'MLI', '466', '223', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (138, 'malta', 'MT', 'MLT', '470', '356', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (139, 'marshall-islands', 'MH', 'MHL', '584', '692', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (140, 'martinique', 'MQ', 'MTQ', '474', '596', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (141, 'mauritania', 'MR', 'MRT', '478', '222', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (142, 'mauritius', 'MU', 'MUS', '480', '230', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (143, 'mayotte-island', 'YT', 'MYT', '175', '269', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (144, 'mexico', 'MX', 'MEX', '484', '052', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (145, 'micronesia', 'FM', 'FSM', '583', '691', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (146, 'moldova', 'MD', 'MDA', '498', '373', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (147, 'monaco', 'MC', 'MCO', '492', '377', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (148, 'mongolia', 'MN', 'MNG', '496', '976', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (149, 'montenegro', 'ME', 'MNE', '499', '382', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (150, 'montserrat', 'MS', 'MSR', '500', '664', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (151, 'morocco', 'MA', 'MAR', '504', '212', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (152, 'mozambique', 'MZ', 'MOZ', '508', '258', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (153, 'namibia', 'NA', 'NAM', '516', '264', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (154, 'nauru', 'NR', 'NRU', '520', '674', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (155, 'nepal', 'NP', 'NPL', '524', '977', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (156, 'netherlands-antilles', 'AN', 'ANT', '530', '599', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (157, 'new-caledonia', 'NC', 'NCL', '540', '687', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (158, 'new-zealand', 'NZ', 'NZL', '554', '064', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (159, 'nicaragua', 'NI', 'NIC', '558', '505', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (160, 'niger', 'NE', 'NER', '562', '227', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (161, 'nigeria', 'NG', 'NGA', '566', '234', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (162, 'niue', 'NU', 'NIU', '570', '683', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (163, 'norfolk-island', 'NF', 'NFK', '574', '672', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (164, 'norway', 'NO', 'NOR', '578', '047', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (165, 'oman', 'OM', 'OMN', '512', '968', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (166, 'pakistan', 'PK', 'PAK', '586', '092', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (167, 'palau', 'PW', 'PLW', '585', '680', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (168, 'palestine', 'PS', 'PSE', '275', '970', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (169, 'panama', 'PA', 'PAN', '591', '507', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (170, 'papua-new-guinea', 'PG', 'PNG', '598', '675', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (171, 'paraguay', 'PY', 'PRY', '600', '595', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (172, 'peru', 'PE', 'PER', '604', '051', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (173, 'philippines', 'PH', 'PHL', '608', '063', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (174, 'poland', 'PL', 'POL', '616', '048', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (175, 'puerto-rico', 'PR', 'PRI', '630', '787', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (176, 'qatar', 'QA', 'QAT', '634', '974', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (177, 'reunion-island', 'RE', 'REU', '638', '262', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (178, 'romania', 'RO', 'ROU', '642', '040', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (179, 'rwanda', 'RW', 'RWA', '646', '250', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (180, 'st-helena', 'SH', 'SHN', '654', '290', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (181, 'st-kitts', 'KN', 'KNA', '659', '869', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (182, 'st-lucia', 'LC', 'LCA', '662', '758', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (183, 'st-pierre-miquelon', 'PM', 'SPM', '666', '508', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (184, 'st-vincent', 'VC', 'VCT', '670', '784', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (185, 'san-marino', 'SM', 'SMR', '674', '378', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (186, 'sao-tome-principe', 'ST', 'STP', '678', '239', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (187, 'saudi-arabia', 'SA', 'SAU', '682', '966', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (188, 'senegal', 'SN', 'SEN', '686', '221', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (189, 'serbia', 'RS', 'SRB', '688', '381', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (190, 'seychelles', 'SC', 'SYC', '690', '248', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (191, 'sierra-leone', 'SL', 'SLE', '694', '249', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (192, 'slovakia', 'SK', 'SVK', '703', '421', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (193, 'slovenia', 'SI', 'SVN', '705', '386', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (194, 'solomon-islands', 'SB', 'SLB', '90', '677', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (195, 'somalia', 'SO', 'SOM', '706', '252', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (196, 'south-africa', 'ZA', 'ZAF', '710', '027', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (197, 'sri-lanka', 'LK', 'LKA', '144', '094', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (198, 'sudan', 'SD', 'SDN', '736', '095', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (199, 'suriname', 'SR', 'SUR', '740', '597', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (200, 'swaziland', 'SZ', 'SWZ', '748', '268', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (201, 'sweden', 'SE', 'SWE', '752', '046', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (202, 'switzerland', 'CH', 'CHE', '756', '041', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (203, 'syria', 'SY', 'SYR', '760', '963', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (204, 'taiwan', 'TW', 'TWN', '158', '886', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (205, 'tajikistan', 'TJ', 'TJK', '762', '992', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (206, 'tanzania', 'TZ', 'TZA', '834', '255', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (207, 'thailand', 'TH', 'THA', '764', '066', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (208, 'togo', 'TG', 'TGO', '768', '228', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (209, 'tonga', 'TO', 'TON', '776', '676', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (210, 'trinidad-tobago', 'TT', 'TTO', '780', '868', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (211, 'tunisia', 'TN', 'TUN', '788', '216', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (212, 'turkmenistan', 'TM', 'TKM', '795', '993', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (213, 'turks-caicos', 'TC', 'TCA', '796', '649', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (214, 'tuvalu', 'TV', 'TUV', '798', '688', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (215, 'uganda', 'UG', 'UGA', '800', '256', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (216, 'ukraine', 'UA', 'UKR', '804', '380', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (217, 'united-arab-emirates', 'AE', 'ARE', '784', '971', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (218, 'uruguay', 'UY', 'URY', '858', '598', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (219, 'uzbekistan', 'UZ', 'UZB', '860', '998', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (220, 'vanuatu', 'VU', 'VUT', '548', '678', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (221, 'vatican-city', 'VA', 'VAT', '336', '039', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (222, 'venezuela', 'VE', 'VEN', '862', '058', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (223, 'wallis-futuna', 'WF', 'WLF', '876', '681', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (224, 'western-samoa', 'WS', 'WSM', '882', '685', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (225, 'yemen', 'YE', 'YEM', '887', '967', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (226, 'zambia', 'ZM', 'ZMB', '894', '260', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (227, 'zimbabwe', 'ZW', 'ZWE', '716', '263', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (228, 'aland-islands', 'AX', 'ALA', '248', '359', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (229, 'bonaire-st-eustatius-saba', 'BQ', 'BES', '535', '599', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (230, 'bouvet-island', 'BV', 'BVT', '74', '047', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (231, 'british-indian-ocean-territory', 'IO', 'IOT', '86', '246', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (232, 'curacao', 'CW', 'CUW', '531', '599', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (233, 'french-southern-territories', 'TF', 'ATF', '260', '033', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (234, 'guernsey', 'GG', 'GGY', '831', '044', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (235, 'heard-island-mcdonald-islands', 'HM', 'HMD', '334', '061', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (236, 'isle-of-man', 'IM', 'IMN', '833', '044', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (237, 'jersey', 'JE', 'JEY', '832', '044', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (238, 'northern-mariana-islands', 'MP', 'MNP', '580', '670', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (239, 'pitcairn', 'PN', 'PCN', '612', '649', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (240, 'south-georgia-south-sandwich-islands', 'GS', 'SGS', '239', '044', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (241, 'south-sudan', 'SS', 'SSD', '728', '211', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (242, 'sint-maarten', 'SX', 'SXM', '534', '721', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (243, 'st-barthelemy', 'BL', 'BLM', '652', '590', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (244, 'st-martin', 'MF', 'MAF', '663', '590', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (245, 'tokelau', 'TK', 'TKL', '772', '690', 'F', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (246, 'timor-leste', 'TL', 'TLS', '626', '670', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (247, 'united-states-minor-outlying-islands', 'UM', 'UMI', '581', '699', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (248, 'united-states-virgin-islands', 'VI', 'VIR', '850', '340', 'T', 'T');
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (249, 'western-sahara', 'EH', 'ESH', '732', '212', 'T', 'T');

insert into Region (regionId, countryId, regionCode, name, active_) values (1001, 1, 'AB', 'Alberta', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (1002, 1, 'BC', 'British Columbia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (1003, 1, 'MB', 'Manitoba', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (1004, 1, 'NB', 'New Brunswick', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (1005, 1, 'NL', 'Newfoundland and Labrador', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (1006, 1, 'NT', 'Northwest Territories', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (1007, 1, 'NS', 'Nova Scotia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (1008, 1, 'NU', 'Nunavut', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (1009, 1, 'ON', 'Ontario', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (1010, 1, 'PE', 'Prince Edward Island', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (1011, 1, 'QC', 'Quebec', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (1012, 1, 'SK', 'Saskatchewan', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (1013, 1, 'YT', 'Yukon', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2001, 2, 'CN-34', 'Anhui', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2002, 2, 'CN-92', 'Aomen', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2003, 2, 'CN-11', 'Beijing', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2004, 2, 'CN-50', 'Chongqing', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2005, 2, 'CN-35', 'Fujian', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2006, 2, 'CN-62', 'Gansu', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2007, 2, 'CN-44', 'Guangdong', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2008, 2, 'CN-45', 'Guangxi', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2009, 2, 'CN-52', 'Guizhou', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2010, 2, 'CN-46', 'Hainan', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2011, 2, 'CN-13', 'Hebei', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2012, 2, 'CN-23', 'Heilongjiang', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2013, 2, 'CN-41', 'Henan', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2014, 2, 'CN-42', 'Hubei', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2015, 2, 'CN-43', 'Hunan', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2016, 2, 'CN-32', 'Jiangsu', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2017, 2, 'CN-36', 'Jiangxi', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2018, 2, 'CN-22', 'Jilin', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2019, 2, 'CN-21', 'Liaoning', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2020, 2, 'CN-15', 'Nei Mongol', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2021, 2, 'CN-64', 'Ningxia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2022, 2, 'CN-63', 'Qinghai', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2023, 2, 'CN-61', 'Shaanxi', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2024, 2, 'CN-37', 'Shandong', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2025, 2, 'CN-31', 'Shanghai', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2026, 2, 'CN-14', 'Shanxi', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2027, 2, 'CN-51', 'Sichuan', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2028, 2, 'CN-71', 'Taiwan', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2029, 2, 'CN-12', 'Tianjin', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2030, 2, 'CN-91', 'Xianggang', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2031, 2, 'CN-65', 'Xinjiang', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2032, 2, 'CN-54', 'Xizang', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2033, 2, 'CN-53', 'Yunnan', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (2034, 2, 'CN-33', 'Zhejiang', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3001, 3, 'A', 'Alsace', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3002, 3, 'B', 'Aquitaine', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3003, 3, 'C', 'Auvergne', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3004, 3, 'P', 'Basse-Normandie', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3005, 3, 'D', 'Bourgogne', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3006, 3, 'E', 'Bretagne', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3007, 3, 'F', 'Centre', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3008, 3, 'G', 'Champagne-Ardenne', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3009, 3, 'H', 'Corse', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3010, 3, 'GF', 'Guyane', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3011, 3, 'I', 'Franche Comté', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3012, 3, 'GP', 'Guadeloupe', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3013, 3, 'Q', 'Haute-Normandie', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3014, 3, 'J', 'Île-de-France', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3015, 3, 'K', 'Languedoc-Roussillon', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3016, 3, 'L', 'Limousin', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3017, 3, 'M', 'Lorraine', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3018, 3, 'MQ', 'Martinique', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3019, 3, 'N', 'Midi-Pyrénées', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3020, 3, 'O', 'Nord Pas de Calais', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3021, 3, 'R', 'Pays de la Loire', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3022, 3, 'S', 'Picardie', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3023, 3, 'T', 'Poitou-Charentes', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3024, 3, 'U', 'Provence-Alpes-Côte-d''Azur', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3025, 3, 'RE', 'Réunion', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (3026, 3, 'V', 'Rhône-Alpes', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4001, 4, 'BW', 'Baden-Württemberg', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4002, 4, 'BY', 'Bayern', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4003, 4, 'BE', 'Berlin', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4004, 4, 'BB', 'Brandenburg', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4005, 4, 'HB', 'Bremen', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4006, 4, 'HH', 'Hamburg', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4007, 4, 'HE', 'Hessen', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4008, 4, 'MV', 'Mecklenburg-Vorpommern', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4009, 4, 'NI', 'Niedersachsen', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4010, 4, 'NW', 'Nordrhein-Westfalen', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4011, 4, 'RP', 'Rheinland-Pfalz', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4012, 4, 'SL', 'Saarland', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4013, 4, 'SN', 'Sachsen', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4014, 4, 'ST', 'Sachsen-Anhalt', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4015, 4, 'SH', 'Schleswig-Holstein', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (4016, 4, 'TH', 'Thüringen', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8001, 8, 'AG', 'Agrigento', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8002, 8, 'AL', 'Alessandria', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8003, 8, 'AN', 'Ancona', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8004, 8, 'AO', 'Aosta', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8005, 8, 'AR', 'Arezzo', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8006, 8, 'AP', 'Ascoli Piceno', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8007, 8, 'AT', 'Asti', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8008, 8, 'AV', 'Avellino', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8009, 8, 'BA', 'Bari', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8010, 8, 'BT', 'Barletta-Andria-Trani', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8011, 8, 'BL', 'Belluno', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8012, 8, 'BN', 'Benevento', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8013, 8, 'BG', 'Bergamo', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8014, 8, 'BI', 'Biella', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8015, 8, 'BO', 'Bologna', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8016, 8, 'BZ', 'Bolzano', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8017, 8, 'BS', 'Brescia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8018, 8, 'BR', 'Brindisi', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8019, 8, 'CA', 'Cagliari', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8020, 8, 'CL', 'Caltanissetta', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8021, 8, 'CB', 'Campobasso', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8022, 8, 'CI', 'Carbonia-Iglesias', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8023, 8, 'CE', 'Caserta', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8024, 8, 'CT', 'Catania', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8025, 8, 'CZ', 'Catanzaro', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8026, 8, 'CH', 'Chieti', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8027, 8, 'CO', 'Como', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8028, 8, 'CS', 'Cosenza', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8029, 8, 'CR', 'Cremona', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8030, 8, 'KR', 'Crotone', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8031, 8, 'CN', 'Cuneo', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8032, 8, 'EN', 'Enna', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8033, 8, 'FM', 'Fermo', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8034, 8, 'FE', 'Ferrara', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8035, 8, 'FI', 'Firenze', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8036, 8, 'FG', 'Foggia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8037, 8, 'FC', 'Forli-Cesena', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8038, 8, 'FR', 'Frosinone', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8039, 8, 'GE', 'Genova', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8040, 8, 'GO', 'Gorizia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8041, 8, 'GR', 'Grosseto', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8042, 8, 'IM', 'Imperia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8043, 8, 'IS', 'Isernia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8044, 8, 'AQ', 'L''Aquila', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8045, 8, 'SP', 'La Spezia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8046, 8, 'LT', 'Latina', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8047, 8, 'LE', 'Lecce', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8048, 8, 'LC', 'Lecco', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8049, 8, 'LI', 'Livorno', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8050, 8, 'LO', 'Lodi', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8051, 8, 'LU', 'Lucca', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8052, 8, 'MC', 'Macerata', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8053, 8, 'MN', 'Mantova', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8054, 8, 'MS', 'Massa-Carrara', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8055, 8, 'MT', 'Matera', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8056, 8, 'MA', 'Medio Campidano', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8057, 8, 'ME', 'Messina', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8058, 8, 'MI', 'Milano', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8059, 8, 'MO', 'Modena', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8060, 8, 'MB', 'Monza e Brianza', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8061, 8, 'NA', 'Napoli', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8062, 8, 'NO', 'Novara', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8063, 8, 'NU', 'Nuoro', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8064, 8, 'OG', 'Ogliastra', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8065, 8, 'OT', 'Olbia-Tempio', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8066, 8, 'OR', 'Oristano', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8067, 8, 'PD', 'Padova', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8068, 8, 'PA', 'Palermo', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8069, 8, 'PR', 'Parma', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8070, 8, 'PV', 'Pavia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8071, 8, 'PG', 'Perugia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8072, 8, 'PU', 'Pesaro e Urbino', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8073, 8, 'PE', 'Pescara', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8074, 8, 'PC', 'Piacenza', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8075, 8, 'PI', 'Pisa', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8076, 8, 'PT', 'Pistoia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8077, 8, 'PN', 'Pordenone', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8078, 8, 'PZ', 'Potenza', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8079, 8, 'PO', 'Prato', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8080, 8, 'RG', 'Ragusa', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8081, 8, 'RA', 'Ravenna', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8082, 8, 'RC', 'Reggio Calabria', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8083, 8, 'RE', 'Reggio Emilia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8084, 8, 'RI', 'Rieti', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8085, 8, 'RN', 'Rimini', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8086, 8, 'RM', 'Roma', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8087, 8, 'RO', 'Rovigo', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8088, 8, 'SA', 'Salerno', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8089, 8, 'SS', 'Sassari', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8090, 8, 'SV', 'Savona', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8091, 8, 'SI', 'Siena', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8092, 8, 'SR', 'Siracusa', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8093, 8, 'SO', 'Sondrio', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8094, 8, 'TA', 'Taranto', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8095, 8, 'TE', 'Teramo', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8096, 8, 'TR', 'Terni', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8097, 8, 'TO', 'Torino', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8098, 8, 'TP', 'Trapani', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8099, 8, 'TN', 'Trento', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8100, 8, 'TV', 'Treviso', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8101, 8, 'TS', 'Trieste', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8102, 8, 'UD', 'Udine', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8103, 8, 'VA', 'Varese', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8104, 8, 'VE', 'Venezia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8105, 8, 'VB', 'Verbano-Cusio-Ossola', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8106, 8, 'VC', 'Vercelli', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8107, 8, 'VR', 'Verona', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8108, 8, 'VV', 'Vibo Valentia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8109, 8, 'VI', 'Vicenza', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (8110, 8, 'VT', 'Viterbo', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (11001, 11, 'DR', 'Drenthe', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (11002, 11, 'FL', 'Flevoland', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (11003, 11, 'FR', 'Friesland', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (11004, 11, 'GE', 'Gelderland', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (11005, 11, 'GR', 'Groningen', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (11006, 11, 'LI', 'Limburg', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (11007, 11, 'NB', 'Noord-Brabant', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (11008, 11, 'NH', 'Noord-Holland', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (11009, 11, 'OV', 'Overijssel', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (11010, 11, 'UT', 'Utrecht', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (11011, 11, 'ZE', 'Zeeland', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (11012, 11, 'ZH', 'Zuid-Holland', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15001, 15, 'AN', 'Andalusia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15002, 15, 'AR', 'Aragon', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15003, 15, 'AS', 'Asturias', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15004, 15, 'IB', 'Balearic Islands', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15005, 15, 'PV', 'Basque Country', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15006, 15, 'CN', 'Canary Islands', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15007, 15, 'CB', 'Cantabria', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15008, 15, 'CL', 'Castile and Leon', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15009, 15, 'CM', 'Castile-La Mancha', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15010, 15, 'CT', 'Catalonia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15011, 15, 'CE', 'Ceuta', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15012, 15, 'EX', 'Extremadura', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15013, 15, 'GA', 'Galicia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15014, 15, 'LO', 'La Rioja', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15015, 15, 'M', 'Madrid', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15016, 15, 'ML', 'Melilla', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15017, 15, 'MU', 'Murcia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15018, 15, 'NA', 'Navarra', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (15019, 15, 'VC', 'Valencia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19001, 19, 'AL', 'Alabama', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19002, 19, 'AK', 'Alaska', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19003, 19, 'AZ', 'Arizona', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19004, 19, 'AR', 'Arkansas', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19005, 19, 'CA', 'California', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19006, 19, 'CO', 'Colorado', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19007, 19, 'CT', 'Connecticut', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19008, 19, 'DC', 'District of Columbia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19009, 19, 'DE', 'Delaware', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19010, 19, 'FL', 'Florida', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19011, 19, 'GA', 'Georgia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19012, 19, 'HI', 'Hawaii', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19013, 19, 'ID', 'Idaho', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19014, 19, 'IL', 'Illinois', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19015, 19, 'IN', 'Indiana', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19016, 19, 'IA', 'Iowa', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19017, 19, 'KS', 'Kansas', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19018, 19, 'KY', 'Kentucky ', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19019, 19, 'LA', 'Louisiana ', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19020, 19, 'ME', 'Maine', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19021, 19, 'MD', 'Maryland', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19022, 19, 'MA', 'Massachusetts', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19023, 19, 'MI', 'Michigan', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19024, 19, 'MN', 'Minnesota', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19025, 19, 'MS', 'Mississippi', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19026, 19, 'MO', 'Missouri', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19027, 19, 'MT', 'Montana', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19028, 19, 'NE', 'Nebraska', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19029, 19, 'NV', 'Nevada', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19030, 19, 'NH', 'New Hampshire', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19031, 19, 'NJ', 'New Jersey', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19032, 19, 'NM', 'New Mexico', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19033, 19, 'NY', 'New York', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19034, 19, 'NC', 'North Carolina', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19035, 19, 'ND', 'North Dakota', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19036, 19, 'OH', 'Ohio', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19037, 19, 'OK', 'Oklahoma ', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19038, 19, 'OR', 'Oregon', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19039, 19, 'PA', 'Pennsylvania', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19040, 19, 'PR', 'Puerto Rico', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19041, 19, 'RI', 'Rhode Island', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19042, 19, 'SC', 'South Carolina', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19043, 19, 'SD', 'South Dakota', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19044, 19, 'TN', 'Tennessee', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19045, 19, 'TX', 'Texas', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19046, 19, 'UT', 'Utah', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19047, 19, 'VT', 'Vermont', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19048, 19, 'VA', 'Virginia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19049, 19, 'WA', 'Washington', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19050, 19, 'WV', 'West Virginia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19051, 19, 'WI', 'Wisconsin', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (19052, 19, 'WY', 'Wyoming', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (32001, 32, 'ACT', 'Australian Capital Territory', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (32002, 32, 'NSW', 'New South Wales', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (32003, 32, 'NT', 'Northern Territory', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (32004, 32, 'QLD', 'Queensland', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (32005, 32, 'SA', 'South Australia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (32006, 32, 'TAS', 'Tasmania', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (32007, 32, 'VIC', 'Victoria', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (32008, 32, 'WA', 'Western Australia', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144001, 144, 'MX-AGS', 'Aguascalientes', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144002, 144, 'MX-BCN', 'Baja California', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144003, 144, 'MX-BCS', 'Baja California Sur', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144004, 144, 'MX-CAM', 'Campeche', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144005, 144, 'MX-CHP', 'Chiapas', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144006, 144, 'MX-CHI', 'Chihuahua', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144007, 144, 'MX-COA', 'Coahuila', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144008, 144, 'MX-COL', 'Colima', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144009, 144, 'MX-DUR', 'Durango', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144010, 144, 'MX-GTO', 'Guanajuato', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144011, 144, 'MX-GRO', 'Guerrero', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144012, 144, 'MX-HGO', 'Hidalgo', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144013, 144, 'MX-JAL', 'Jalisco', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144014, 144, 'MX-MEX', 'Mexico', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144015, 144, 'MX-MIC', 'Michoacan', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144016, 144, 'MX-MOR', 'Morelos', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144017, 144, 'MX-NAY', 'Nayarit', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144018, 144, 'MX-NLE', 'Nuevo Leon', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144019, 144, 'MX-OAX', 'Oaxaca', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144020, 144, 'MX-PUE', 'Puebla', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144021, 144, 'MX-QRO', 'Queretaro', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144023, 144, 'MX-ROO', 'Quintana Roo', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144024, 144, 'MX-SLP', 'San Luis Potosí', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144025, 144, 'MX-SIN', 'Sinaloa', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144026, 144, 'MX-SON', 'Sonora', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144027, 144, 'MX-TAB', 'Tabasco', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144028, 144, 'MX-TAM', 'Tamaulipas', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144029, 144, 'MX-TLX', 'Tlaxcala', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144030, 144, 'MX-VER', 'Veracruz', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144031, 144, 'MX-YUC', 'Yucatan', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (144032, 144, 'MX-ZAC', 'Zacatecas', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164001, 164, '01', 'Østfold', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164002, 164, '02', 'Akershus', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164003, 164, '03', 'Oslo', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164004, 164, '04', 'Hedmark', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164005, 164, '05', 'Oppland', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164006, 164, '06', 'Buskerud', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164007, 164, '07', 'Vestfold', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164008, 164, '08', 'Telemark', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164009, 164, '09', 'Aust-Agder', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164010, 164, '10', 'Vest-Agder', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164011, 164, '11', 'Rogaland', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164012, 164, '12', 'Hordaland', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164013, 164, '14', 'Sogn og Fjordane', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164014, 164, '15', 'Møre of Romsdal', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164015, 164, '16', 'Sør-Trøndelag', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164016, 164, '17', 'Nord-Trøndelag', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164017, 164, '18', 'Nordland', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164018, 164, '19', 'Troms', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (164019, 164, '20', 'Finnmark', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202001, 202, 'AG', 'Aargau', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202002, 202, 'AR', 'Appenzell Ausserrhoden', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202003, 202, 'AI', 'Appenzell Innerrhoden', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202004, 202, 'BL', 'Basel-Landschaft', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202005, 202, 'BS', 'Basel-Stadt', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202006, 202, 'BE', 'Bern', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202007, 202, 'FR', 'Fribourg', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202008, 202, 'GE', 'Geneva', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202009, 202, 'GL', 'Glarus', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202010, 202, 'GR', 'Graubünden', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202011, 202, 'JU', 'Jura', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202012, 202, 'LU', 'Lucerne', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202013, 202, 'NE', 'Neuchâtel', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202014, 202, 'NW', 'Nidwalden', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202015, 202, 'OW', 'Obwalden', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202016, 202, 'SH', 'Schaffhausen', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202017, 202, 'SZ', 'Schwyz', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202018, 202, 'SO', 'Solothurn', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202019, 202, 'SG', 'St. Gallen', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202020, 202, 'TG', 'Thurgau', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202021, 202, 'TI', 'Ticino', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202022, 202, 'UR', 'Uri', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202023, 202, 'VS', 'Valais', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202024, 202, 'VD', 'Vaud', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202025, 202, 'ZG', 'Zug', 'T');
insert into Region (regionId, countryId, regionCode, name, active_) values (202026, 202, 'ZH', 'Zürich', 'T');

--
-- List types for accounts
--

insert into ListType (listTypeId, name, type_) values (10000, 'billing', 'com.liferay.portal.model.Account.address');
insert into ListType (listTypeId, name, type_) values (10001, 'other', 'com.liferay.portal.model.Account.address');
insert into ListType (listTypeId, name, type_) values (10002, 'p-o-box', 'com.liferay.portal.model.Account.address');
insert into ListType (listTypeId, name, type_) values (10003, 'shipping', 'com.liferay.portal.model.Account.address');

insert into ListType (listTypeId, name, type_) values (10004, 'email-address', 'com.liferay.portal.model.Account.emailAddress');
insert into ListType (listTypeId, name, type_) values (10005, 'email-address-2', 'com.liferay.portal.model.Account.emailAddress');
insert into ListType (listTypeId, name, type_) values (10006, 'email-address-3', 'com.liferay.portal.model.Account.emailAddress');

insert into ListType (listTypeId, name, type_) values (10007, 'fax', 'com.liferay.portal.model.Account.phone');
insert into ListType (listTypeId, name, type_) values (10008, 'local', 'com.liferay.portal.model.Account.phone');
insert into ListType (listTypeId, name, type_) values (10009, 'other', 'com.liferay.portal.model.Account.phone');
insert into ListType (listTypeId, name, type_) values (10010, 'toll-free', 'com.liferay.portal.model.Account.phone');
insert into ListType (listTypeId, name, type_) values (10011, 'tty', 'com.liferay.portal.model.Account.phone');

insert into ListType (listTypeId, name, type_) values (10012, 'intranet', 'com.liferay.portal.model.Account.website');
insert into ListType (listTypeId, name, type_) values (10013, 'public', 'com.liferay.portal.model.Account.website');

--
-- List types for contacts
--

insert into ListType (listTypeId, name, type_) values (11000, 'business', 'com.liferay.portal.model.Contact.address');
insert into ListType (listTypeId, name, type_) values (11001, 'other', 'com.liferay.portal.model.Contact.address');
insert into ListType (listTypeId, name, type_) values (11002, 'personal', 'com.liferay.portal.model.Contact.address');

insert into ListType (listTypeId, name, type_) values (11003, 'email-address', 'com.liferay.portal.model.Contact.emailAddress');
insert into ListType (listTypeId, name, type_) values (11004, 'email-address-2', 'com.liferay.portal.model.Contact.emailAddress');
insert into ListType (listTypeId, name, type_) values (11005, 'email-address-3', 'com.liferay.portal.model.Contact.emailAddress');

insert into ListType (listTypeId, name, type_) values (11006, 'business', 'com.liferay.portal.model.Contact.phone');
insert into ListType (listTypeId, name, type_) values (11007, 'business-fax', 'com.liferay.portal.model.Contact.phone');
insert into ListType (listTypeId, name, type_) values (11008, 'mobile-phone', 'com.liferay.portal.model.Contact.phone');
insert into ListType (listTypeId, name, type_) values (11009, 'other', 'com.liferay.portal.model.Contact.phone');
insert into ListType (listTypeId, name, type_) values (11010, 'pager', 'com.liferay.portal.model.Contact.phone');
insert into ListType (listTypeId, name, type_) values (11011, 'personal', 'com.liferay.portal.model.Contact.phone');
insert into ListType (listTypeId, name, type_) values (11012, 'personal-fax', 'com.liferay.portal.model.Contact.phone');
insert into ListType (listTypeId, name, type_) values (11013, 'tty', 'com.liferay.portal.model.Contact.phone');

insert into ListType (listTypeId, name, type_) values (11014, 'dr', 'com.liferay.portal.model.Contact.prefix');
insert into ListType (listTypeId, name, type_) values (11015, 'mr', 'com.liferay.portal.model.Contact.prefix');
insert into ListType (listTypeId, name, type_) values (11016, 'mrs', 'com.liferay.portal.model.Contact.prefix');
insert into ListType (listTypeId, name, type_) values (11017, 'ms', 'com.liferay.portal.model.Contact.prefix');

insert into ListType (listTypeId, name, type_) values (11020, 'ii', 'com.liferay.portal.model.Contact.suffix');
insert into ListType (listTypeId, name, type_) values (11021, 'iii', 'com.liferay.portal.model.Contact.suffix');
insert into ListType (listTypeId, name, type_) values (11022, 'iv', 'com.liferay.portal.model.Contact.suffix');
insert into ListType (listTypeId, name, type_) values (11023, 'jr', 'com.liferay.portal.model.Contact.suffix');
insert into ListType (listTypeId, name, type_) values (11024, 'phd', 'com.liferay.portal.model.Contact.suffix');
insert into ListType (listTypeId, name, type_) values (11025, 'sr', 'com.liferay.portal.model.Contact.suffix');

insert into ListType (listTypeId, name, type_) values (11026, 'blog', 'com.liferay.portal.model.Contact.website');
insert into ListType (listTypeId, name, type_) values (11027, 'business', 'com.liferay.portal.model.Contact.website');
insert into ListType (listTypeId, name, type_) values (11028, 'other', 'com.liferay.portal.model.Contact.website');
insert into ListType (listTypeId, name, type_) values (11029, 'personal', 'com.liferay.portal.model.Contact.website');

--
-- List types for organizations
--

insert into ListType (listTypeId, name, type_) values (12000, 'billing', 'com.liferay.portal.model.Organization.address');
insert into ListType (listTypeId, name, type_) values (12001, 'other', 'com.liferay.portal.model.Organization.address');
insert into ListType (listTypeId, name, type_) values (12002, 'p-o-box', 'com.liferay.portal.model.Organization.address');
insert into ListType (listTypeId, name, type_) values (12003, 'shipping', 'com.liferay.portal.model.Organization.address');

insert into ListType (listTypeId, name, type_) values (12004, 'email-address', 'com.liferay.portal.model.Organization.emailAddress');
insert into ListType (listTypeId, name, type_) values (12005, 'email-address-2', 'com.liferay.portal.model.Organization.emailAddress');
insert into ListType (listTypeId, name, type_) values (12006, 'email-address-3', 'com.liferay.portal.model.Organization.emailAddress');

insert into ListType (listTypeId, name, type_) values (12007, 'fax', 'com.liferay.portal.model.Organization.phone');
insert into ListType (listTypeId, name, type_) values (12008, 'local', 'com.liferay.portal.model.Organization.phone');
insert into ListType (listTypeId, name, type_) values (12009, 'other', 'com.liferay.portal.model.Organization.phone');
insert into ListType (listTypeId, name, type_) values (12010, 'toll-free', 'com.liferay.portal.model.Organization.phone');
insert into ListType (listTypeId, name, type_) values (12011, 'tty', 'com.liferay.portal.model.Organization.phone');

insert into ListType (listTypeId, name, type_) values (12012, 'administrative', 'com.liferay.portal.model.Organization.service');
insert into ListType (listTypeId, name, type_) values (12013, 'contracts', 'com.liferay.portal.model.Organization.service');
insert into ListType (listTypeId, name, type_) values (12014, 'donation', 'com.liferay.portal.model.Organization.service');
insert into ListType (listTypeId, name, type_) values (12015, 'retail', 'com.liferay.portal.model.Organization.service');
insert into ListType (listTypeId, name, type_) values (12016, 'training', 'com.liferay.portal.model.Organization.service');

insert into ListType (listTypeId, name, type_) values (12017, 'full-member', 'com.liferay.portal.model.Organization.status');
insert into ListType (listTypeId, name, type_) values (12018, 'provisional-member', 'com.liferay.portal.model.Organization.status');

insert into ListType (listTypeId, name, type_) values (12019, 'intranet', 'com.liferay.portal.model.Organization.website');
insert into ListType (listTypeId, name, type_) values (12020, 'public', 'com.liferay.portal.model.Organization.website');


insert into Counter (name, currentId) values ('com.liferay.counter.model.Counter', 20000);


insert into Release_ (releaseId, createDate, modifiedDate, servletContextName, buildNumber, verified) values (1, CURRENT YEAR TO FRACTION, CURRENT YEAR TO FRACTION, 'portal', 6200, 'F');


create table QUARTZ_BLOB_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	BLOB_DATA blob,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_CALENDARS (
	SCHED_NAME varchar(120) not null,
	CALENDAR_NAME varchar(200) not null,
	CALENDAR blob not null,
	primary key (SCHED_NAME,CALENDAR_NAME)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_CRON_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	CRON_EXPRESSION varchar(200) not null,
	TIME_ZONE_ID varchar(80),
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_FIRED_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	ENTRY_ID varchar(95) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	INSTANCE_NAME varchar(200) not null,
	FIRED_TIME int8 not null,
	PRIORITY int not null,
	STATE varchar(16) not null,
	JOB_NAME varchar(200),
	JOB_GROUP varchar(200),
	IS_NONCONCURRENT boolean,
	REQUESTS_RECOVERY boolean,
	primary key (SCHED_NAME, ENTRY_ID)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_JOB_DETAILS (
	SCHED_NAME varchar(120) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250),
	JOB_CLASS_NAME varchar(250) not null,
	IS_DURABLE boolean not null,
	IS_NONCONCURRENT boolean not null,
	IS_UPDATE_DATA boolean not null,
	REQUESTS_RECOVERY boolean not null,
	JOB_DATA blob,
	primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_LOCKS (
	SCHED_NAME varchar(120) not null,
	LOCK_NAME varchar(40) not null ,
	primary key (SCHED_NAME, LOCK_NAME)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_PAUSED_TRIGGER_GRPS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_GROUP varchar(200) not null,
	primary key (SCHED_NAME, TRIGGER_GROUP)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_SCHEDULER_STATE (
	SCHED_NAME varchar(120) not null,
	INSTANCE_NAME varchar(200) not null,
	LAST_CHECKIN_TIME int8 not null,
	CHECKIN_INTERVAL int8 not null,
	primary key (SCHED_NAME, INSTANCE_NAME)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_SIMPLE_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	REPEAT_COUNT int8 not null,
	REPEAT_INTERVAL int8 not null,
	TIMES_TRIGGERED int8 not null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_SIMPROP_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	STR_PROP_1 varchar(512),
	STR_PROP_2 varchar(512),
	STR_PROP_3 varchar(512),
	INT_PROP_1 int,
	INT_PROP_2 int,
	LONG_PROP_1 int8,
	LONG_PROP_2 int8,
	DEC_PROP_1 NUMERIC(13,4),
	DEC_PROP_2 NUMERIC(13,4),
	BOOL_PROP_1 boolean,
	BOOL_PROP_2 boolean,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
extent size 16 next size 16
lock mode row;

create table QUARTZ_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250),
	NEXT_FIRE_TIME int8,
	PREV_FIRE_TIME int8,
	PRIORITY int,
	TRIGGER_STATE varchar(16) not null,
	TRIGGER_TYPE varchar(8) not null,
	START_TIME int8 not null,
	END_TIME int8,
	CALENDAR_NAME varchar(200),
	MISFIRE_INSTR int,
	JOB_DATA blob,
	primary key  (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
extent size 16 next size 16
lock mode row;



create index IX_88328984 on QUARTZ_JOB_DETAILS (SCHED_NAME, JOB_GROUP);
create index IX_779BCA37 on QUARTZ_JOB_DETAILS (SCHED_NAME, REQUESTS_RECOVERY);

create index IX_BE3835E5 on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
create index IX_4BD722BM on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_GROUP);
create index IX_204D31E8 on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME);
create index IX_339E078M on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME, REQUESTS_RECOVERY);
create index IX_5005E3AF on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);
create index IX_BC2F03B0 on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_GROUP);

create index IX_186442A4 on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_STATE);
create index IX_1BA1F9DC on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP);
create index IX_91CA7CCE on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP, NEXT_FIRE_TIME, TRIGGER_STATE, MISFIRE_INSTR);
create index IX_D219AFDE on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP, TRIGGER_STATE);
create index IX_A85822A0 on QUARTZ_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);
create index IX_8AA50BE1 on QUARTZ_TRIGGERS (SCHED_NAME, JOB_GROUP);
create index IX_EEFE382A on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME);
create index IX_F026CF4C on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME, TRIGGER_STATE);
create index IX_F2DD7C7E on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME, TRIGGER_STATE, MISFIRE_INSTR);
create index IX_1F92813C on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME, MISFIRE_INSTR);
create index IX_99108B6E on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_STATE);
create index IX_CD7132D0 on QUARTZ_TRIGGERS (SCHED_NAME, CALENDAR_NAME);




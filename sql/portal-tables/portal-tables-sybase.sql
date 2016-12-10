create table Account_ (
	accountId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentAccountId decimal(20,0),
	name varchar(75) null,
	legalName varchar(75) null,
	legalId varchar(75) null,
	legalType varchar(75) null,
	sicCode varchar(75) null,
	tickerSymbol varchar(75) null,
	industry varchar(75) null,
	type_ varchar(75) null,
	size_ varchar(75) null
)
go

create table Address (
	uuid_ varchar(75) null,
	addressId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	street1 varchar(75) null,
	street2 varchar(75) null,
	street3 varchar(75) null,
	city varchar(75) null,
	zip varchar(75) null,
	regionId decimal(20,0),
	countryId decimal(20,0),
	typeId int,
	mailing int,
	primary_ int
)
go

create table AnnouncementsDelivery (
	deliveryId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	type_ varchar(75) null,
	email int,
	sms int,
	website int
)
go

create table AnnouncementsEntry (
	uuid_ varchar(75) null,
	entryId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	title varchar(75) null,
	content text null,
	url varchar(1000) null,
	type_ varchar(75) null,
	displayDate datetime null,
	expirationDate datetime null,
	priority int,
	alert int
)
go

create table AnnouncementsFlag (
	flagId decimal(20,0) not null primary key,
	userId decimal(20,0),
	createDate datetime null,
	entryId decimal(20,0),
	value int
)
go

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
	description varchar(1000) null,
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
	categoryId decimal(20,0) not null,
	entryId decimal(20,0) not null,
	primary key (categoryId, entryId)
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
	classUuid varchar(75) null,
	classTypeId decimal(20,0),
	visible int,
	startDate datetime null,
	endDate datetime null,
	publishDate datetime null,
	expirationDate datetime null,
	mimeType varchar(75) null,
	title varchar(1000) null,
	description text null,
	summary text null,
	url varchar(1000) null,
	layoutUuid varchar(75) null,
	height int,
	width int,
	priority float,
	viewCount int
)
go

create table AssetLink (
	linkId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	entryId1 decimal(20,0),
	entryId2 decimal(20,0),
	type_ int,
	weight int
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

create table BackgroundTask (
	backgroundTaskId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	servletContextNames varchar(255) null,
	taskExecutorClassName varchar(200) null,
	taskContext text null,
	completed int,
	completionDate datetime null,
	status int,
	statusMessage text null
)
go

create table BlogsEntry (
	uuid_ varchar(75) null,
	entryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	title varchar(150) null,
	urlTitle varchar(150) null,
	description varchar(1000) null,
	content text null,
	displayDate datetime null,
	allowPingbacks int,
	allowTrackbacks int,
	trackbacks text null,
	smallImage int,
	smallImageId decimal(20,0),
	smallImageURL varchar(1000) null,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table BlogsStatsUser (
	statsUserId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	entryCount int,
	lastPostDate datetime null,
	ratingsTotalEntries int,
	ratingsTotalScore float,
	ratingsAverageScore float
)
go

create table BookmarksEntry (
	uuid_ varchar(75) null,
	entryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	resourceBlockId decimal(20,0),
	folderId decimal(20,0),
	treePath varchar(1000) null,
	name varchar(255) null,
	url varchar(1000) null,
	description varchar(1000) null,
	visits int,
	priority int,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table BookmarksFolder (
	uuid_ varchar(75) null,
	folderId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	resourceBlockId decimal(20,0),
	parentFolderId decimal(20,0),
	treePath varchar(1000) null,
	name varchar(75) null,
	description varchar(1000) null,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table BrowserTracker (
	browserTrackerId decimal(20,0) not null primary key,
	userId decimal(20,0),
	browserKey decimal(20,0)
)
go

create table CalEvent (
	uuid_ varchar(75) null,
	eventId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	title varchar(75) null,
	description varchar(1000) null,
	location varchar(1000) null,
	startDate datetime null,
	endDate datetime null,
	durationHour int,
	durationMinute int,
	allDay int,
	timeZoneSensitive int,
	type_ varchar(75) null,
	repeating int,
	recurrence text null,
	remindBy int,
	firstReminder int,
	secondReminder int
)
go

create table ClassName_ (
	classNameId decimal(20,0) not null primary key,
	value varchar(200) null
)
go

create table ClusterGroup (
	clusterGroupId decimal(20,0) not null primary key,
	name varchar(75) null,
	clusterNodeIds varchar(75) null,
	wholeCluster int
)
go

create table Company (
	companyId decimal(20,0) not null primary key,
	accountId decimal(20,0),
	webId varchar(75) null,
	key_ text null,
	mx varchar(75) null,
	homeURL varchar(1000) null,
	logoId decimal(20,0),
	system int,
	maxUsers int,
	active_ int
)
go

create table Contact_ (
	contactId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	accountId decimal(20,0),
	parentContactId decimal(20,0),
	emailAddress varchar(75) null,
	firstName varchar(75) null,
	middleName varchar(75) null,
	lastName varchar(75) null,
	prefixId int,
	suffixId int,
	male int,
	birthday datetime null,
	smsSn varchar(75) null,
	aimSn varchar(75) null,
	facebookSn varchar(75) null,
	icqSn varchar(75) null,
	jabberSn varchar(75) null,
	msnSn varchar(75) null,
	mySpaceSn varchar(75) null,
	skypeSn varchar(75) null,
	twitterSn varchar(75) null,
	ymSn varchar(75) null,
	employeeStatusId varchar(75) null,
	employeeNumber varchar(75) null,
	jobTitle varchar(100) null,
	jobClass varchar(75) null,
	hoursOfOperation varchar(75) null
)
go

create table Counter (
	name varchar(75) not null primary key,
	currentId decimal(20,0)
)
go

create table Country (
	countryId decimal(20,0) not null primary key,
	name varchar(75) null,
	a2 varchar(75) null,
	a3 varchar(75) null,
	number_ varchar(75) null,
	idd_ varchar(75) null,
	zipRequired int,
	active_ int
)
go

create table CyrusUser (
	userId varchar(75) not null primary key,
	password_ varchar(75) not null
)
go

create table CyrusVirtual (
	emailAddress varchar(75) not null primary key,
	userId varchar(75) not null
)
go

create table DDLRecord (
	uuid_ varchar(75) null,
	recordId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	versionUserId decimal(20,0),
	versionUserName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	DDMStorageId decimal(20,0),
	recordSetId decimal(20,0),
	version varchar(75) null,
	displayIndex int
)
go

create table DDLRecordSet (
	uuid_ varchar(75) null,
	recordSetId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	DDMStructureId decimal(20,0),
	recordSetKey varchar(75) null,
	name varchar(1000) null,
	description varchar(1000) null,
	minDisplayRows int,
	scope int
)
go

create table DDLRecordVersion (
	recordVersionId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	DDMStorageId decimal(20,0),
	recordSetId decimal(20,0),
	recordId decimal(20,0),
	version varchar(75) null,
	displayIndex int,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table DDMContent (
	uuid_ varchar(75) null,
	contentId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(1000) null,
	description varchar(1000) null,
	xml text null
)
go

create table DDMStorageLink (
	uuid_ varchar(75) null,
	storageLinkId decimal(20,0) not null primary key,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	structureId decimal(20,0)
)
go

create table DDMStructure (
	uuid_ varchar(75) null,
	structureId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentStructureId decimal(20,0),
	classNameId decimal(20,0),
	structureKey varchar(75) null,
	name varchar(1000) null,
	description varchar(1000) null,
	xsd text null,
	storageType varchar(75) null,
	type_ int
)
go

create table DDMStructureLink (
	structureLinkId decimal(20,0) not null primary key,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	structureId decimal(20,0)
)
go

create table DDMTemplate (
	uuid_ varchar(75) null,
	templateId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	templateKey varchar(75) null,
	name varchar(1000) null,
	description varchar(1000) null,
	type_ varchar(75) null,
	mode_ varchar(75) null,
	language varchar(75) null,
	script text null,
	cacheable int,
	smallImage int,
	smallImageId decimal(20,0),
	smallImageURL varchar(75) null
)
go

create table DLContent (
	contentId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	repositoryId decimal(20,0),
	path_ varchar(255) null,
	version varchar(75) null,
	data_ image,
	size_ decimal(20,0)
)
go

create table DLFileEntry (
	uuid_ varchar(75) null,
	fileEntryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	repositoryId decimal(20,0),
	folderId decimal(20,0),
	treePath varchar(1000) null,
	name varchar(255) null,
	extension varchar(75) null,
	mimeType varchar(75) null,
	title varchar(255) null,
	description varchar(1000) null,
	extraSettings text null,
	fileEntryTypeId decimal(20,0),
	version varchar(75) null,
	size_ decimal(20,0),
	readCount int,
	smallImageId decimal(20,0),
	largeImageId decimal(20,0),
	custom1ImageId decimal(20,0),
	custom2ImageId decimal(20,0),
	manualCheckInRequired int
)
go

create table DLFileEntryMetadata (
	uuid_ varchar(75) null,
	fileEntryMetadataId decimal(20,0) not null primary key,
	DDMStorageId decimal(20,0),
	DDMStructureId decimal(20,0),
	fileEntryTypeId decimal(20,0),
	fileEntryId decimal(20,0),
	fileVersionId decimal(20,0)
)
go

create table DLFileEntryType (
	uuid_ varchar(75) null,
	fileEntryTypeId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	fileEntryTypeKey varchar(75) null,
	name varchar(1000) null,
	description varchar(1000) null
)
go

create table DLFileEntryTypes_DDMStructures (
	structureId decimal(20,0) not null,
	fileEntryTypeId decimal(20,0) not null,
	primary key (structureId, fileEntryTypeId)
)
go

create table DLFileEntryTypes_DLFolders (
	fileEntryTypeId decimal(20,0) not null,
	folderId decimal(20,0) not null,
	primary key (fileEntryTypeId, folderId)
)
go

create table DLFileRank (
	fileRankId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	createDate datetime null,
	fileEntryId decimal(20,0),
	active_ int
)
go

create table DLFileShortcut (
	uuid_ varchar(75) null,
	fileShortcutId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	repositoryId decimal(20,0),
	folderId decimal(20,0),
	toFileEntryId decimal(20,0),
	treePath varchar(1000) null,
	active_ int,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table DLFileVersion (
	uuid_ varchar(75) null,
	fileVersionId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	repositoryId decimal(20,0),
	folderId decimal(20,0),
	fileEntryId decimal(20,0),
	treePath varchar(1000) null,
	extension varchar(75) null,
	mimeType varchar(75) null,
	title varchar(255) null,
	description varchar(1000) null,
	changeLog varchar(75) null,
	extraSettings text null,
	fileEntryTypeId decimal(20,0),
	version varchar(75) null,
	size_ decimal(20,0),
	checksum varchar(75) null,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table DLFolder (
	uuid_ varchar(75) null,
	folderId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	repositoryId decimal(20,0),
	mountPoint int,
	parentFolderId decimal(20,0),
	treePath varchar(1000) null,
	name varchar(100) null,
	description varchar(1000) null,
	lastPostDate datetime null,
	defaultFileEntryTypeId decimal(20,0),
	hidden_ int,
	overrideFileEntryTypes int,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table DLSyncEvent (
	syncEventId decimal(20,0) not null primary key,
	modifiedTime decimal(20,0),
	event varchar(75) null,
	type_ varchar(75) null,
	typePK decimal(20,0)
)
go

create table EmailAddress (
	uuid_ varchar(75) null,
	emailAddressId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	address varchar(75) null,
	typeId int,
	primary_ int
)
go

create table ExpandoColumn (
	columnId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	tableId decimal(20,0),
	name varchar(75) null,
	type_ int,
	defaultData varchar(1000) null,
	typeSettings text null
)
go

create table ExpandoRow (
	rowId_ decimal(20,0) not null primary key,
	companyId decimal(20,0),
	modifiedDate datetime null,
	tableId decimal(20,0),
	classPK decimal(20,0)
)
go

create table ExpandoTable (
	tableId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	classNameId decimal(20,0),
	name varchar(75) null
)
go

create table ExpandoValue (
	valueId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	tableId decimal(20,0),
	columnId decimal(20,0),
	rowId_ decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0),
	data_ varchar(1000) null
)
go

create table Group_ (
	uuid_ varchar(75) null,
	groupId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	creatorUserId decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0),
	parentGroupId decimal(20,0),
	liveGroupId decimal(20,0),
	treePath varchar(1000) null,
	name varchar(150) null,
	description varchar(1000) null,
	type_ int,
	typeSettings text null,
	manualMembership int,
	membershipRestriction int,
	friendlyURL varchar(255) null,
	site int,
	remoteStagingGroupCount int,
	active_ int
)
go

create table Groups_Orgs (
	groupId decimal(20,0) not null,
	organizationId decimal(20,0) not null,
	primary key (groupId, organizationId)
)
go

create table Groups_Roles (
	groupId decimal(20,0) not null,
	roleId decimal(20,0) not null,
	primary key (groupId, roleId)
)
go

create table Groups_UserGroups (
	groupId decimal(20,0) not null,
	userGroupId decimal(20,0) not null,
	primary key (groupId, userGroupId)
)
go

create table Image (
	imageId decimal(20,0) not null primary key,
	modifiedDate datetime null,
	type_ varchar(75) null,
	height int,
	width int,
	size_ int
)
go

create table JournalArticle (
	uuid_ varchar(75) null,
	id_ decimal(20,0) not null primary key,
	resourcePrimKey decimal(20,0),
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	folderId decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0),
	treePath varchar(1000) null,
	articleId varchar(75) null,
	version float,
	title varchar(1000) null,
	urlTitle varchar(150) null,
	description text null,
	content text null,
	type_ varchar(75) null,
	structureId varchar(75) null,
	templateId varchar(75) null,
	layoutUuid varchar(75) null,
	displayDate datetime null,
	expirationDate datetime null,
	reviewDate datetime null,
	indexable int,
	smallImage int,
	smallImageId decimal(20,0),
	smallImageURL varchar(1000) null,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table JournalArticleImage (
	articleImageId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	articleId varchar(75) null,
	version float,
	elInstanceId varchar(75) null,
	elName varchar(75) null,
	languageId varchar(75) null,
	tempImage int
)
go

create table JournalArticleResource (
	uuid_ varchar(75) null,
	resourcePrimKey decimal(20,0) not null primary key,
	groupId decimal(20,0),
	articleId varchar(75) null
)
go

create table JournalContentSearch (
	contentSearchId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	privateLayout int,
	layoutId decimal(20,0),
	portletId varchar(200) null,
	articleId varchar(75) null
)
go

create table JournalFeed (
	uuid_ varchar(75) null,
	id_ decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	feedId varchar(75) null,
	name varchar(75) null,
	description varchar(1000) null,
	type_ varchar(75) null,
	structureId varchar(75) null,
	templateId varchar(75) null,
	rendererTemplateId varchar(75) null,
	delta int,
	orderByCol varchar(75) null,
	orderByType varchar(75) null,
	targetLayoutFriendlyUrl varchar(255) null,
	targetPortletId varchar(75) null,
	contentField varchar(75) null,
	feedFormat varchar(75) null,
	feedVersion float
)
go

create table JournalFolder (
	uuid_ varchar(75) null,
	folderId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentFolderId decimal(20,0),
	treePath varchar(1000) null,
	name varchar(100) null,
	description varchar(1000) null,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table Layout (
	uuid_ varchar(75) null,
	plid decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	privateLayout int,
	layoutId decimal(20,0),
	parentLayoutId decimal(20,0),
	name varchar(1000) null,
	title varchar(1000) null,
	description varchar(1000) null,
	keywords varchar(1000) null,
	robots varchar(1000) null,
	type_ varchar(75) null,
	typeSettings text null,
	hidden_ int,
	friendlyURL varchar(255) null,
	iconImage int,
	iconImageId decimal(20,0),
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css text null,
	priority int,
	layoutPrototypeUuid varchar(75) null,
	layoutPrototypeLinkEnabled int,
	sourcePrototypeLayoutUuid varchar(75) null
)
go

create table LayoutBranch (
	LayoutBranchId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	layoutSetBranchId decimal(20,0),
	plid decimal(20,0),
	name varchar(75) null,
	description varchar(1000) null,
	master int
)
go

create table LayoutFriendlyURL (
	uuid_ varchar(75) null,
	layoutFriendlyURLId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	plid decimal(20,0),
	privateLayout int,
	friendlyURL varchar(255) null,
	languageId varchar(75) null
)
go

create table LayoutPrototype (
	uuid_ varchar(75) null,
	layoutPrototypeId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(1000) null,
	description varchar(1000) null,
	settings_ varchar(1000) null,
	active_ int
)
go

create table LayoutRevision (
	layoutRevisionId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	layoutSetBranchId decimal(20,0),
	layoutBranchId decimal(20,0),
	parentLayoutRevisionId decimal(20,0),
	head int,
	major int,
	plid decimal(20,0),
	privateLayout int,
	name varchar(1000) null,
	title varchar(1000) null,
	description varchar(1000) null,
	keywords varchar(1000) null,
	robots varchar(1000) null,
	typeSettings text null,
	iconImage int,
	iconImageId decimal(20,0),
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css text null,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table LayoutSet (
	layoutSetId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	createDate datetime null,
	modifiedDate datetime null,
	privateLayout int,
	logo int,
	logoId decimal(20,0),
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css text null,
	pageCount int,
	settings_ text null,
	layoutSetPrototypeUuid varchar(75) null,
	layoutSetPrototypeLinkEnabled int
)
go

create table LayoutSetBranch (
	layoutSetBranchId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	privateLayout int,
	name varchar(75) null,
	description varchar(1000) null,
	master int,
	logo int,
	logoId decimal(20,0),
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css text null,
	settings_ text null,
	layoutSetPrototypeUuid varchar(75) null,
	layoutSetPrototypeLinkEnabled int
)
go

create table LayoutSetPrototype (
	uuid_ varchar(75) null,
	layoutSetPrototypeId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(1000) null,
	description varchar(1000) null,
	settings_ varchar(1000) null,
	active_ int
)
go

create table ListType (
	listTypeId int not null primary key,
	name varchar(75) null,
	type_ varchar(75) null
)
go

create table Lock_ (
	uuid_ varchar(75) null,
	lockId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	className varchar(75) null,
	key_ varchar(200) null,
	owner varchar(255) null,
	inheritable int,
	expirationDate datetime null
)
go

create table MBBan (
	uuid_ varchar(75) null,
	banId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	banUserId decimal(20,0)
)
go

create table MBCategory (
	uuid_ varchar(75) null,
	categoryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentCategoryId decimal(20,0),
	name varchar(75) null,
	description varchar(1000) null,
	displayStyle varchar(75) null,
	threadCount int,
	messageCount int,
	lastPostDate datetime null,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table MBDiscussion (
	uuid_ varchar(75) null,
	discussionId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	threadId decimal(20,0)
)
go

create table MBMailingList (
	uuid_ varchar(75) null,
	mailingListId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId decimal(20,0),
	emailAddress varchar(75) null,
	inProtocol varchar(75) null,
	inServerName varchar(75) null,
	inServerPort int,
	inUseSSL int,
	inUserName varchar(75) null,
	inPassword varchar(75) null,
	inReadInterval int,
	outEmailAddress varchar(75) null,
	outCustom int,
	outServerName varchar(75) null,
	outServerPort int,
	outUseSSL int,
	outUserName varchar(75) null,
	outPassword varchar(75) null,
	allowAnonymous int,
	active_ int
)
go

create table MBMessage (
	uuid_ varchar(75) null,
	messageId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	categoryId decimal(20,0),
	threadId decimal(20,0),
	rootMessageId decimal(20,0),
	parentMessageId decimal(20,0),
	subject varchar(75) null,
	body text null,
	format varchar(75) null,
	anonymous int,
	priority float,
	allowPingbacks int,
	answer int,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table MBStatsUser (
	statsUserId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	userId decimal(20,0),
	messageCount int,
	lastPostDate datetime null
)
go

create table MBThread (
	uuid_ varchar(75) null,
	threadId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId decimal(20,0),
	rootMessageId decimal(20,0),
	rootMessageUserId decimal(20,0),
	messageCount int,
	viewCount int,
	lastPostByUserId decimal(20,0),
	lastPostDate datetime null,
	priority float,
	question int,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table MBThreadFlag (
	uuid_ varchar(75) null,
	threadFlagId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	threadId decimal(20,0)
)
go

create table MDRAction (
	uuid_ varchar(75) null,
	actionId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	ruleGroupInstanceId decimal(20,0),
	name varchar(1000) null,
	description varchar(1000) null,
	type_ varchar(255) null,
	typeSettings text null
)
go

create table MDRRule (
	uuid_ varchar(75) null,
	ruleId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	ruleGroupId decimal(20,0),
	name varchar(1000) null,
	description varchar(1000) null,
	type_ varchar(255) null,
	typeSettings text null
)
go

create table MDRRuleGroup (
	uuid_ varchar(75) null,
	ruleGroupId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(1000) null,
	description varchar(1000) null
)
go

create table MDRRuleGroupInstance (
	uuid_ varchar(75) null,
	ruleGroupInstanceId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	ruleGroupId decimal(20,0),
	priority int
)
go

create table MembershipRequest (
	membershipRequestId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	createDate datetime null,
	comments varchar(1000) null,
	replyComments varchar(1000) null,
	replyDate datetime null,
	replierUserId decimal(20,0),
	statusId int
)
go

create table Organization_ (
	uuid_ varchar(75) null,
	organizationId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentOrganizationId decimal(20,0),
	treePath varchar(1000) null,
	name varchar(100) null,
	type_ varchar(75) null,
	recursable int,
	regionId decimal(20,0),
	countryId decimal(20,0),
	statusId int,
	comments varchar(1000) null
)
go

create table OrgGroupRole (
	organizationId decimal(20,0) not null,
	groupId decimal(20,0) not null,
	roleId decimal(20,0) not null,
	primary key (organizationId, groupId, roleId)
)
go

create table OrgLabor (
	orgLaborId decimal(20,0) not null primary key,
	organizationId decimal(20,0),
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
go

create table PasswordPolicy (
	uuid_ varchar(75) null,
	passwordPolicyId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	defaultPolicy int,
	name varchar(75) null,
	description varchar(1000) null,
	changeable int,
	changeRequired int,
	minAge decimal(20,0),
	checkSyntax int,
	allowDictionaryWords int,
	minAlphanumeric int,
	minLength int,
	minLowerCase int,
	minNumbers int,
	minSymbols int,
	minUpperCase int,
	regex varchar(75) null,
	history int,
	historyCount int,
	expireable int,
	maxAge decimal(20,0),
	warningTime decimal(20,0),
	graceLimit int,
	lockout int,
	maxFailure int,
	lockoutDuration decimal(20,0),
	requireUnlock int,
	resetFailureCount decimal(20,0),
	resetTicketMaxAge decimal(20,0)
)
go

create table PasswordPolicyRel (
	passwordPolicyRelId decimal(20,0) not null primary key,
	passwordPolicyId decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0)
)
go

create table PasswordTracker (
	passwordTrackerId decimal(20,0) not null primary key,
	userId decimal(20,0),
	createDate datetime null,
	password_ varchar(75) null
)
go

create table Phone (
	uuid_ varchar(75) null,
	phoneId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	number_ varchar(75) null,
	extension varchar(75) null,
	typeId int,
	primary_ int
)
go

create table PluginSetting (
	pluginSettingId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	pluginId varchar(75) null,
	pluginType varchar(75) null,
	roles varchar(1000) null,
	active_ int
)
go

create table PollsChoice (
	uuid_ varchar(75) null,
	choiceId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	questionId decimal(20,0),
	name varchar(75) null,
	description varchar(1000) null
)
go

create table PollsQuestion (
	uuid_ varchar(75) null,
	questionId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	title varchar(1000) null,
	description varchar(1000) null,
	expirationDate datetime null,
	lastVoteDate datetime null
)
go

create table PollsVote (
	uuid_ varchar(75) null,
	voteId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	questionId decimal(20,0),
	choiceId decimal(20,0),
	voteDate datetime null
)
go

create table PortalPreferences (
	portalPreferencesId decimal(20,0) not null primary key,
	ownerId decimal(20,0),
	ownerType int,
	preferences text null
)
go

create table Portlet (
	id_ decimal(20,0) not null primary key,
	companyId decimal(20,0),
	portletId varchar(200) null,
	roles varchar(1000) null,
	active_ int
)
go

create table PortletItem (
	portletItemId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	portletId varchar(200) null,
	classNameId decimal(20,0)
)
go

create table PortletPreferences (
	portletPreferencesId decimal(20,0) not null primary key,
	ownerId decimal(20,0),
	ownerType int,
	plid decimal(20,0),
	portletId varchar(200) null,
	preferences text null
)
go

create table RatingsEntry (
	entryId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	score float
)
go

create table RatingsStats (
	statsId decimal(20,0) not null primary key,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	totalEntries int,
	totalScore float,
	averageScore float
)
go

create table Region (
	regionId decimal(20,0) not null primary key,
	countryId decimal(20,0),
	regionCode varchar(75) null,
	name varchar(75) null,
	active_ int
)
go

create table Release_ (
	releaseId decimal(20,0) not null primary key,
	createDate datetime null,
	modifiedDate datetime null,
	servletContextName varchar(75) null,
	buildNumber int,
	buildDate datetime null,
	verified int,
	state_ int,
	testString varchar(1024) null
)
go

create table Repository (
	uuid_ varchar(75) null,
	repositoryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	name varchar(75) null,
	description varchar(1000) null,
	portletId varchar(200) null,
	typeSettings text null,
	dlFolderId decimal(20,0)
)
go

create table RepositoryEntry (
	uuid_ varchar(75) null,
	repositoryEntryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	repositoryId decimal(20,0),
	mappedId varchar(75) null,
	manualCheckInRequired int
)
go

create table ResourceAction (
	resourceActionId decimal(20,0) not null primary key,
	name varchar(255) null,
	actionId varchar(75) null,
	bitwiseValue decimal(20,0)
)
go

create table ResourceBlock (
	resourceBlockId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	groupId decimal(20,0),
	name varchar(75) null,
	permissionsHash varchar(75) null,
	referenceCount decimal(20,0)
)
go

create table ResourceBlockPermission (
	resourceBlockPermissionId decimal(20,0) not null primary key,
	resourceBlockId decimal(20,0),
	roleId decimal(20,0),
	actionIds decimal(20,0)
)
go

create table ResourcePermission (
	resourcePermissionId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	name varchar(255) null,
	scope int,
	primKey varchar(255) null,
	roleId decimal(20,0),
	ownerId decimal(20,0),
	actionIds decimal(20,0)
)
go

create table ResourceTypePermission (
	resourceTypePermissionId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	groupId decimal(20,0),
	name varchar(75) null,
	roleId decimal(20,0),
	actionIds decimal(20,0)
)
go

create table Role_ (
	uuid_ varchar(75) null,
	roleId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	name varchar(75) null,
	title varchar(1000) null,
	description varchar(1000) null,
	type_ int,
	subtype varchar(75) null
)
go

create table SCFrameworkVersi_SCProductVers (
	frameworkVersionId decimal(20,0) not null,
	productVersionId decimal(20,0) not null,
	primary key (frameworkVersionId, productVersionId)
)
go

create table SCFrameworkVersion (
	frameworkVersionId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	url varchar(1000) null,
	active_ int,
	priority int
)
go

create table SCLicense (
	licenseId decimal(20,0) not null primary key,
	name varchar(75) null,
	url varchar(1000) null,
	openSource int,
	active_ int,
	recommended int
)
go

create table SCLicenses_SCProductEntries (
	licenseId decimal(20,0) not null,
	productEntryId decimal(20,0) not null,
	primary key (licenseId, productEntryId)
)
go

create table SCProductEntry (
	productEntryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	type_ varchar(75) null,
	tags varchar(255) null,
	shortDescription varchar(1000) null,
	longDescription varchar(1000) null,
	pageURL varchar(1000) null,
	author varchar(75) null,
	repoGroupId varchar(75) null,
	repoArtifactId varchar(75) null
)
go

create table SCProductScreenshot (
	productScreenshotId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	groupId decimal(20,0),
	productEntryId decimal(20,0),
	thumbnailId decimal(20,0),
	fullImageId decimal(20,0),
	priority int
)
go

create table SCProductVersion (
	productVersionId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	productEntryId decimal(20,0),
	version varchar(75) null,
	changeLog varchar(1000) null,
	downloadPageURL varchar(1000) null,
	directDownloadURL varchar(2000) null,
	repoStoreArtifact int
)
go

create table ServiceComponent (
	serviceComponentId decimal(20,0) not null primary key,
	buildNamespace varchar(75) null,
	buildNumber decimal(20,0),
	buildDate decimal(20,0),
	data_ text null
)
go

create table Shard (
	shardId decimal(20,0) not null primary key,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	name varchar(75) null
)
go

create table ShoppingCart (
	cartId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	itemIds varchar(1000) null,
	couponCodes varchar(75) null,
	altShipping int,
	insure int
)
go

create table ShoppingCategory (
	categoryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentCategoryId decimal(20,0),
	name varchar(75) null,
	description varchar(1000) null
)
go

create table ShoppingCoupon (
	couponId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	code_ varchar(75) null,
	name varchar(75) null,
	description varchar(1000) null,
	startDate datetime null,
	endDate datetime null,
	active_ int,
	limitCategories varchar(1000) null,
	limitSkus varchar(1000) null,
	minOrder float,
	discount float,
	discountType varchar(75) null
)
go

create table ShoppingItem (
	itemId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId decimal(20,0),
	sku varchar(75) null,
	name varchar(200) null,
	description varchar(1000) null,
	properties varchar(1000) null,
	fields_ int,
	fieldsQuantities varchar(1000) null,
	minQuantity int,
	maxQuantity int,
	price float,
	discount float,
	taxable int,
	shipping float,
	useShippingFormula int,
	requiresShipping int,
	stockQuantity int,
	featured_ int,
	sale_ int,
	smallImage int,
	smallImageId decimal(20,0),
	smallImageURL varchar(1000) null,
	mediumImage int,
	mediumImageId decimal(20,0),
	mediumImageURL varchar(1000) null,
	largeImage int,
	largeImageId decimal(20,0),
	largeImageURL varchar(1000) null
)
go

create table ShoppingItemField (
	itemFieldId decimal(20,0) not null primary key,
	itemId decimal(20,0),
	name varchar(75) null,
	values_ varchar(1000) null,
	description varchar(1000) null
)
go

create table ShoppingItemPrice (
	itemPriceId decimal(20,0) not null primary key,
	itemId decimal(20,0),
	minQuantity int,
	maxQuantity int,
	price float,
	discount float,
	taxable int,
	shipping float,
	useShippingFormula int,
	status int
)
go

create table ShoppingOrder (
	orderId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	number_ varchar(75) null,
	tax float,
	shipping float,
	altShipping varchar(75) null,
	requiresShipping int,
	insure int,
	insurance float,
	couponCodes varchar(75) null,
	couponDiscount float,
	billingFirstName varchar(75) null,
	billingLastName varchar(75) null,
	billingEmailAddress varchar(75) null,
	billingCompany varchar(75) null,
	billingStreet varchar(75) null,
	billingCity varchar(75) null,
	billingState varchar(75) null,
	billingZip varchar(75) null,
	billingCountry varchar(75) null,
	billingPhone varchar(75) null,
	shipToBilling int,
	shippingFirstName varchar(75) null,
	shippingLastName varchar(75) null,
	shippingEmailAddress varchar(75) null,
	shippingCompany varchar(75) null,
	shippingStreet varchar(75) null,
	shippingCity varchar(75) null,
	shippingState varchar(75) null,
	shippingZip varchar(75) null,
	shippingCountry varchar(75) null,
	shippingPhone varchar(75) null,
	ccName varchar(75) null,
	ccType varchar(75) null,
	ccNumber varchar(75) null,
	ccExpMonth int,
	ccExpYear int,
	ccVerNumber varchar(75) null,
	comments varchar(1000) null,
	ppTxnId varchar(75) null,
	ppPaymentStatus varchar(75) null,
	ppPaymentGross float,
	ppReceiverEmail varchar(75) null,
	ppPayerEmail varchar(75) null,
	sendOrderEmail int,
	sendShippingEmail int
)
go

create table ShoppingOrderItem (
	orderItemId decimal(20,0) not null primary key,
	orderId decimal(20,0),
	itemId varchar(75) null,
	sku varchar(75) null,
	name varchar(200) null,
	description varchar(1000) null,
	properties varchar(1000) null,
	price float,
	quantity int,
	shippedDate datetime null
)
go

create table SocialActivity (
	activityId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	createDate decimal(20,0),
	activitySetId decimal(20,0),
	mirrorActivityId decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0),
	parentClassNameId decimal(20,0),
	parentClassPK decimal(20,0),
	type_ int,
	extraData varchar(1000) null,
	receiverUserId decimal(20,0)
)
go

create table SocialActivityAchievement (
	activityAchievementId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	createDate decimal(20,0),
	name varchar(75) null,
	firstInGroup int
)
go

create table SocialActivityCounter (
	activityCounterId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0),
	name varchar(75) null,
	ownerType int,
	currentValue int,
	totalValue int,
	graceValue int,
	startPeriod int,
	endPeriod int,
	active_ int
)
go

create table SocialActivityLimit (
	activityLimitId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0),
	activityType int,
	activityCounterName varchar(75) null,
	value varchar(75) null
)
go

create table SocialActivitySet (
	activitySetId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	createDate decimal(20,0),
	modifiedDate decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0),
	type_ int,
	extraData varchar(1000) null,
	activityCount int
)
go

create table SocialActivitySetting (
	activitySettingId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	classNameId decimal(20,0),
	activityType int,
	name varchar(75) null,
	value varchar(1024) null
)
go

create table SocialRelation (
	uuid_ varchar(75) null,
	relationId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	createDate decimal(20,0),
	userId1 decimal(20,0),
	userId2 decimal(20,0),
	type_ int
)
go

create table SocialRequest (
	uuid_ varchar(75) null,
	requestId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	createDate decimal(20,0),
	modifiedDate decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0),
	type_ int,
	extraData varchar(1000) null,
	receiverUserId decimal(20,0),
	status int
)
go

create table Subscription (
	subscriptionId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	frequency varchar(75) null
)
go

create table SystemEvent (
	systemEventId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	classUuid varchar(75) null,
	referrerClassNameId decimal(20,0),
	parentSystemEventId decimal(20,0),
	systemEventSetKey decimal(20,0),
	type_ int,
	extraData text null
)
go

create table Team (
	teamId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	groupId decimal(20,0),
	name varchar(75) null,
	description varchar(1000) null
)
go

create table Ticket (
	ticketId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	createDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	key_ varchar(75) null,
	type_ int,
	extraInfo text null,
	expirationDate datetime null
)
go

create table TrashEntry (
	entryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	systemEventSetKey decimal(20,0),
	typeSettings text null,
	status int
)
go

create table TrashVersion (
	versionId decimal(20,0) not null primary key,
	entryId decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0),
	typeSettings text null,
	status int
)
go

create table UserNotificationDelivery (
	userNotificationDeliveryId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	portletId varchar(200) null,
	classNameId decimal(20,0),
	notificationType int,
	deliveryType int,
	deliver int
)
go

create table User_ (
	uuid_ varchar(75) null,
	userId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	createDate datetime null,
	modifiedDate datetime null,
	defaultUser int,
	contactId decimal(20,0),
	password_ varchar(75) null,
	passwordEncrypted int,
	passwordReset int,
	passwordModifiedDate datetime null,
	digest varchar(255) null,
	reminderQueryQuestion varchar(75) null,
	reminderQueryAnswer varchar(75) null,
	graceLoginCount int,
	screenName varchar(75) null,
	emailAddress varchar(75) null,
	facebookId decimal(20,0),
	ldapServerId decimal(20,0),
	openId varchar(1024) null,
	portraitId decimal(20,0),
	languageId varchar(75) null,
	timeZoneId varchar(75) null,
	greeting varchar(255) null,
	comments varchar(1000) null,
	firstName varchar(75) null,
	middleName varchar(75) null,
	lastName varchar(75) null,
	jobTitle varchar(100) null,
	loginDate datetime null,
	loginIP varchar(75) null,
	lastLoginDate datetime null,
	lastLoginIP varchar(75) null,
	lastFailedLoginDate datetime null,
	failedLoginAttempts int,
	lockout int,
	lockoutDate datetime null,
	agreedToTermsOfUse int,
	emailAddressVerified int,
	status int
)
go

create table UserGroup (
	uuid_ varchar(75) null,
	userGroupId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentUserGroupId decimal(20,0),
	name varchar(75) null,
	description varchar(1000) null,
	addedByLDAPImport int
)
go

create table UserGroupGroupRole (
	userGroupId decimal(20,0) not null,
	groupId decimal(20,0) not null,
	roleId decimal(20,0) not null,
	primary key (userGroupId, groupId, roleId)
)
go

create table UserGroupRole (
	userId decimal(20,0) not null,
	groupId decimal(20,0) not null,
	roleId decimal(20,0) not null,
	primary key (userId, groupId, roleId)
)
go

create table UserGroups_Teams (
	teamId decimal(20,0) not null,
	userGroupId decimal(20,0) not null,
	primary key (teamId, userGroupId)
)
go

create table UserIdMapper (
	userIdMapperId decimal(20,0) not null primary key,
	userId decimal(20,0),
	type_ varchar(75) null,
	description varchar(75) null,
	externalUserId varchar(75) null
)
go

create table UserNotificationEvent (
	uuid_ varchar(75) null,
	userNotificationEventId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	type_ varchar(75) null,
	timestamp decimal(20,0),
	deliverBy decimal(20,0),
	delivered int,
	payload text null,
	archived int
)
go

create table Users_Groups (
	groupId decimal(20,0) not null,
	userId decimal(20,0) not null,
	primary key (groupId, userId)
)
go

create table Users_Orgs (
	organizationId decimal(20,0) not null,
	userId decimal(20,0) not null,
	primary key (organizationId, userId)
)
go

create table Users_Roles (
	roleId decimal(20,0) not null,
	userId decimal(20,0) not null,
	primary key (roleId, userId)
)
go

create table Users_Teams (
	teamId decimal(20,0) not null,
	userId decimal(20,0) not null,
	primary key (teamId, userId)
)
go

create table Users_UserGroups (
	userId decimal(20,0) not null,
	userGroupId decimal(20,0) not null,
	primary key (userId, userGroupId)
)
go

create table UserTracker (
	userTrackerId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	modifiedDate datetime null,
	sessionId varchar(200) null,
	remoteAddr varchar(75) null,
	remoteHost varchar(75) null,
	userAgent varchar(200) null
)
go

create table UserTrackerPath (
	userTrackerPathId decimal(20,0) not null primary key,
	userTrackerId decimal(20,0),
	path_ varchar(1000) null,
	pathDate datetime null
)
go

create table VirtualHost (
	virtualHostId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	layoutSetId decimal(20,0),
	hostname varchar(75) null
)
go

create table WebDAVProps (
	webDavPropsId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	props text null
)
go

create table Website (
	uuid_ varchar(75) null,
	websiteId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	url varchar(1000) null,
	typeId int,
	primary_ int
)
go

create table WikiNode (
	uuid_ varchar(75) null,
	nodeId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	description varchar(1000) null,
	lastPostDate datetime null,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table WikiPage (
	uuid_ varchar(75) null,
	pageId decimal(20,0) not null primary key,
	resourcePrimKey decimal(20,0),
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	nodeId decimal(20,0),
	title varchar(255) null,
	version float,
	minorEdit int,
	content text null,
	summary varchar(1000) null,
	format varchar(75) null,
	head int,
	parentTitle varchar(255) null,
	redirectTitle varchar(255) null,
	status int,
	statusByUserId decimal(20,0),
	statusByUserName varchar(75) null,
	statusDate datetime null
)
go

create table WikiPageResource (
	uuid_ varchar(75) null,
	resourcePrimKey decimal(20,0) not null primary key,
	nodeId decimal(20,0),
	title varchar(255) null
)
go

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	typePK decimal(20,0),
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

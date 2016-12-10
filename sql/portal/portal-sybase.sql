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


insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (1, 'canada', 'CA', 'CAN', '124', '001', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (2, 'china', 'CN', 'CHN', '156', '086', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (3, 'france', 'FR', 'FRA', '250', '033', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (4, 'germany', 'DE', 'DEU', '276', '049', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (5, 'hong-kong', 'HK', 'HKG', '344', '852', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (6, 'hungary', 'HU', 'HUN', '348', '036', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (7, 'israel', 'IL', 'ISR', '376', '972', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (8, 'italy', 'IT', 'ITA', '380', '039', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (9, 'japan', 'JP', 'JPN', '392', '081', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (10, 'south-korea', 'KR', 'KOR', '410', '082', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (11, 'netherlands', 'NL', 'NLD', '528', '031', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (12, 'portugal', 'PT', 'PRT', '620', '351', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (13, 'russia', 'RU', 'RUS', '643', '007', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (14, 'singapore', 'SG', 'SGP', '702', '065', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (15, 'spain', 'ES', 'ESP', '724', '034', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (16, 'turkey', 'TR', 'TUR', '792', '090', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (17, 'vietnam', 'VN', 'VNM', '704', '084', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (18, 'united-kingdom', 'GB', 'GBR', '826', '044', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (19, 'united-states', 'US', 'USA', '840', '001', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (20, 'afghanistan', 'AF', 'AFG', '4', '093', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (21, 'albania', 'AL', 'ALB', '8', '355', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (22, 'algeria', 'DZ', 'DZA', '12', '213', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (23, 'american-samoa', 'AS', 'ASM', '16', '684', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (24, 'andorra', 'AD', 'AND', '20', '376', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (25, 'angola', 'AO', 'AGO', '24', '244', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (26, 'anguilla', 'AI', 'AIA', '660', '264', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (27, 'antarctica', 'AQ', 'ATA', '10', '672', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (28, 'antigua-barbuda', 'AG', 'ATG', '28', '268', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (29, 'argentina', 'AR', 'ARG', '32', '054', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (30, 'armenia', 'AM', 'ARM', '51', '374', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (31, 'aruba', 'AW', 'ABW', '533', '297', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (32, 'australia', 'AU', 'AUS', '36', '061', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (33, 'austria', 'AT', 'AUT', '40', '043', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (34, 'azerbaijan', 'AZ', 'AZE', '31', '994', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (35, 'bahamas', 'BS', 'BHS', '44', '242', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (36, 'bahrain', 'BH', 'BHR', '48', '973', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (37, 'bangladesh', 'BD', 'BGD', '50', '880', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (38, 'barbados', 'BB', 'BRB', '52', '246', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (39, 'belarus', 'BY', 'BLR', '112', '375', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (40, 'belgium', 'BE', 'BEL', '56', '032', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (41, 'belize', 'BZ', 'BLZ', '84', '501', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (42, 'benin', 'BJ', 'BEN', '204', '229', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (43, 'bermuda', 'BM', 'BMU', '60', '441', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (44, 'bhutan', 'BT', 'BTN', '64', '975', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (45, 'bolivia', 'BO', 'BOL', '68', '591', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (46, 'bosnia-herzegovina', 'BA', 'BIH', '70', '387', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (47, 'botswana', 'BW', 'BWA', '72', '267', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (48, 'brazil', 'BR', 'BRA', '76', '055', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (49, 'british-virgin-islands', 'VG', 'VGB', '92', '284', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (50, 'brunei', 'BN', 'BRN', '96', '673', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (51, 'bulgaria', 'BG', 'BGR', '100', '359', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (52, 'burkina-faso', 'BF', 'BFA', '854', '226', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (53, 'burma-myanmar', 'MM', 'MMR', '104', '095', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (54, 'burundi', 'BI', 'BDI', '108', '257', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (55, 'cambodia', 'KH', 'KHM', '116', '855', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (56, 'cameroon', 'CM', 'CMR', '120', '237', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (57, 'cape-verde-island', 'CV', 'CPV', '132', '238', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (58, 'cayman-islands', 'KY', 'CYM', '136', '345', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (59, 'central-african-republic', 'CF', 'CAF', '140', '236', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (60, 'chad', 'TD', 'TCD', '148', '235', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (61, 'chile', 'CL', 'CHL', '152', '056', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (62, 'christmas-island', 'CX', 'CXR', '162', '061', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (63, 'cocos-islands', 'CC', 'CCK', '166', '061', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (64, 'colombia', 'CO', 'COL', '170', '057', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (65, 'comoros', 'KM', 'COM', '174', '269', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (66, 'republic-of-congo', 'CD', 'COD', '180', '242', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (67, 'democratic-republic-of-congo', 'CG', 'COG', '178', '243', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (68, 'cook-islands', 'CK', 'COK', '184', '682', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (69, 'costa-rica', 'CR', 'CRI', '188', '506', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (70, 'croatia', 'HR', 'HRV', '191', '385', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (71, 'cuba', 'CU', 'CUB', '192', '053', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (72, 'cyprus', 'CY', 'CYP', '196', '357', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (73, 'czech-republic', 'CZ', 'CZE', '203', '420', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (74, 'denmark', 'DK', 'DNK', '208', '045', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (75, 'djibouti', 'DJ', 'DJI', '262', '253', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (76, 'dominica', 'DM', 'DMA', '212', '767', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (77, 'dominican-republic', 'DO', 'DOM', '214', '809', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (78, 'ecuador', 'EC', 'ECU', '218', '593', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (79, 'egypt', 'EG', 'EGY', '818', '020', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (80, 'el-salvador', 'SV', 'SLV', '222', '503', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (81, 'equatorial-guinea', 'GQ', 'GNQ', '226', '240', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (82, 'eritrea', 'ER', 'ERI', '232', '291', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (83, 'estonia', 'EE', 'EST', '233', '372', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (84, 'ethiopia', 'ET', 'ETH', '231', '251', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (85, 'faeroe-islands', 'FO', 'FRO', '234', '298', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (86, 'falkland-islands', 'FK', 'FLK', '238', '500', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (87, 'fiji-islands', 'FJ', 'FJI', '242', '679', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (88, 'finland', 'FI', 'FIN', '246', '358', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (89, 'french-guiana', 'GF', 'GUF', '254', '594', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (90, 'french-polynesia', 'PF', 'PYF', '258', '689', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (91, 'gabon', 'GA', 'GAB', '266', '241', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (92, 'gambia', 'GM', 'GMB', '270', '220', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (93, 'georgia', 'GE', 'GEO', '268', '995', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (94, 'ghana', 'GH', 'GHA', '288', '233', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (95, 'gibraltar', 'GI', 'GIB', '292', '350', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (96, 'greece', 'GR', 'GRC', '300', '030', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (97, 'greenland', 'GL', 'GRL', '304', '299', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (98, 'grenada', 'GD', 'GRD', '308', '473', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (99, 'guadeloupe', 'GP', 'GLP', '312', '590', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (100, 'guam', 'GU', 'GUM', '316', '671', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (101, 'guatemala', 'GT', 'GTM', '320', '502', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (102, 'guinea', 'GN', 'GIN', '324', '224', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (103, 'guinea-bissau', 'GW', 'GNB', '624', '245', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (104, 'guyana', 'GY', 'GUY', '328', '592', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (105, 'haiti', 'HT', 'HTI', '332', '509', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (106, 'honduras', 'HN', 'HND', '340', '504', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (107, 'iceland', 'IS', 'ISL', '352', '354', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (108, 'india', 'IN', 'IND', '356', '091', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (109, 'indonesia', 'ID', 'IDN', '360', '062', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (110, 'iran', 'IR', 'IRN', '364', '098', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (111, 'iraq', 'IQ', 'IRQ', '368', '964', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (112, 'ireland', 'IE', 'IRL', '372', '353', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (113, 'ivory-coast', 'CI', 'CIV', '384', '225', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (114, 'jamaica', 'JM', 'JAM', '388', '876', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (115, 'jordan', 'JO', 'JOR', '400', '962', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (116, 'kazakhstan', 'KZ', 'KAZ', '398', '007', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (117, 'kenya', 'KE', 'KEN', '404', '254', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (118, 'kiribati', 'KI', 'KIR', '408', '686', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (119, 'kuwait', 'KW', 'KWT', '414', '965', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (120, 'north-korea', 'KP', 'PRK', '408', '850', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (121, 'kyrgyzstan', 'KG', 'KGZ', '471', '996', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (122, 'laos', 'LA', 'LAO', '418', '856', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (123, 'latvia', 'LV', 'LVA', '428', '371', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (124, 'lebanon', 'LB', 'LBN', '422', '961', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (125, 'lesotho', 'LS', 'LSO', '426', '266', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (126, 'liberia', 'LR', 'LBR', '430', '231', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (127, 'libya', 'LY', 'LBY', '434', '218', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (128, 'liechtenstein', 'LI', 'LIE', '438', '423', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (129, 'lithuania', 'LT', 'LTU', '440', '370', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (130, 'luxembourg', 'LU', 'LUX', '442', '352', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (131, 'macau', 'MO', 'MAC', '446', '853', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (132, 'macedonia', 'MK', 'MKD', '807', '389', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (133, 'madagascar', 'MG', 'MDG', '450', '261', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (134, 'malawi', 'MW', 'MWI', '454', '265', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (135, 'malaysia', 'MY', 'MYS', '458', '060', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (136, 'maldives', 'MV', 'MDV', '462', '960', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (137, 'mali', 'ML', 'MLI', '466', '223', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (138, 'malta', 'MT', 'MLT', '470', '356', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (139, 'marshall-islands', 'MH', 'MHL', '584', '692', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (140, 'martinique', 'MQ', 'MTQ', '474', '596', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (141, 'mauritania', 'MR', 'MRT', '478', '222', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (142, 'mauritius', 'MU', 'MUS', '480', '230', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (143, 'mayotte-island', 'YT', 'MYT', '175', '269', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (144, 'mexico', 'MX', 'MEX', '484', '052', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (145, 'micronesia', 'FM', 'FSM', '583', '691', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (146, 'moldova', 'MD', 'MDA', '498', '373', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (147, 'monaco', 'MC', 'MCO', '492', '377', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (148, 'mongolia', 'MN', 'MNG', '496', '976', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (149, 'montenegro', 'ME', 'MNE', '499', '382', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (150, 'montserrat', 'MS', 'MSR', '500', '664', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (151, 'morocco', 'MA', 'MAR', '504', '212', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (152, 'mozambique', 'MZ', 'MOZ', '508', '258', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (153, 'namibia', 'NA', 'NAM', '516', '264', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (154, 'nauru', 'NR', 'NRU', '520', '674', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (155, 'nepal', 'NP', 'NPL', '524', '977', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (156, 'netherlands-antilles', 'AN', 'ANT', '530', '599', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (157, 'new-caledonia', 'NC', 'NCL', '540', '687', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (158, 'new-zealand', 'NZ', 'NZL', '554', '064', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (159, 'nicaragua', 'NI', 'NIC', '558', '505', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (160, 'niger', 'NE', 'NER', '562', '227', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (161, 'nigeria', 'NG', 'NGA', '566', '234', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (162, 'niue', 'NU', 'NIU', '570', '683', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (163, 'norfolk-island', 'NF', 'NFK', '574', '672', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (164, 'norway', 'NO', 'NOR', '578', '047', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (165, 'oman', 'OM', 'OMN', '512', '968', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (166, 'pakistan', 'PK', 'PAK', '586', '092', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (167, 'palau', 'PW', 'PLW', '585', '680', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (168, 'palestine', 'PS', 'PSE', '275', '970', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (169, 'panama', 'PA', 'PAN', '591', '507', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (170, 'papua-new-guinea', 'PG', 'PNG', '598', '675', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (171, 'paraguay', 'PY', 'PRY', '600', '595', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (172, 'peru', 'PE', 'PER', '604', '051', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (173, 'philippines', 'PH', 'PHL', '608', '063', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (174, 'poland', 'PL', 'POL', '616', '048', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (175, 'puerto-rico', 'PR', 'PRI', '630', '787', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (176, 'qatar', 'QA', 'QAT', '634', '974', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (177, 'reunion-island', 'RE', 'REU', '638', '262', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (178, 'romania', 'RO', 'ROU', '642', '040', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (179, 'rwanda', 'RW', 'RWA', '646', '250', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (180, 'st-helena', 'SH', 'SHN', '654', '290', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (181, 'st-kitts', 'KN', 'KNA', '659', '869', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (182, 'st-lucia', 'LC', 'LCA', '662', '758', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (183, 'st-pierre-miquelon', 'PM', 'SPM', '666', '508', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (184, 'st-vincent', 'VC', 'VCT', '670', '784', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (185, 'san-marino', 'SM', 'SMR', '674', '378', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (186, 'sao-tome-principe', 'ST', 'STP', '678', '239', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (187, 'saudi-arabia', 'SA', 'SAU', '682', '966', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (188, 'senegal', 'SN', 'SEN', '686', '221', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (189, 'serbia', 'RS', 'SRB', '688', '381', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (190, 'seychelles', 'SC', 'SYC', '690', '248', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (191, 'sierra-leone', 'SL', 'SLE', '694', '249', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (192, 'slovakia', 'SK', 'SVK', '703', '421', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (193, 'slovenia', 'SI', 'SVN', '705', '386', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (194, 'solomon-islands', 'SB', 'SLB', '90', '677', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (195, 'somalia', 'SO', 'SOM', '706', '252', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (196, 'south-africa', 'ZA', 'ZAF', '710', '027', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (197, 'sri-lanka', 'LK', 'LKA', '144', '094', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (198, 'sudan', 'SD', 'SDN', '736', '095', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (199, 'suriname', 'SR', 'SUR', '740', '597', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (200, 'swaziland', 'SZ', 'SWZ', '748', '268', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (201, 'sweden', 'SE', 'SWE', '752', '046', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (202, 'switzerland', 'CH', 'CHE', '756', '041', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (203, 'syria', 'SY', 'SYR', '760', '963', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (204, 'taiwan', 'TW', 'TWN', '158', '886', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (205, 'tajikistan', 'TJ', 'TJK', '762', '992', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (206, 'tanzania', 'TZ', 'TZA', '834', '255', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (207, 'thailand', 'TH', 'THA', '764', '066', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (208, 'togo', 'TG', 'TGO', '768', '228', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (209, 'tonga', 'TO', 'TON', '776', '676', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (210, 'trinidad-tobago', 'TT', 'TTO', '780', '868', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (211, 'tunisia', 'TN', 'TUN', '788', '216', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (212, 'turkmenistan', 'TM', 'TKM', '795', '993', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (213, 'turks-caicos', 'TC', 'TCA', '796', '649', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (214, 'tuvalu', 'TV', 'TUV', '798', '688', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (215, 'uganda', 'UG', 'UGA', '800', '256', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (216, 'ukraine', 'UA', 'UKR', '804', '380', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (217, 'united-arab-emirates', 'AE', 'ARE', '784', '971', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (218, 'uruguay', 'UY', 'URY', '858', '598', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (219, 'uzbekistan', 'UZ', 'UZB', '860', '998', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (220, 'vanuatu', 'VU', 'VUT', '548', '678', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (221, 'vatican-city', 'VA', 'VAT', '336', '039', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (222, 'venezuela', 'VE', 'VEN', '862', '058', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (223, 'wallis-futuna', 'WF', 'WLF', '876', '681', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (224, 'western-samoa', 'WS', 'WSM', '882', '685', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (225, 'yemen', 'YE', 'YEM', '887', '967', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (226, 'zambia', 'ZM', 'ZMB', '894', '260', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (227, 'zimbabwe', 'ZW', 'ZWE', '716', '263', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (228, 'aland-islands', 'AX', 'ALA', '248', '359', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (229, 'bonaire-st-eustatius-saba', 'BQ', 'BES', '535', '599', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (230, 'bouvet-island', 'BV', 'BVT', '74', '047', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (231, 'british-indian-ocean-territory', 'IO', 'IOT', '86', '246', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (232, 'curacao', 'CW', 'CUW', '531', '599', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (233, 'french-southern-territories', 'TF', 'ATF', '260', '033', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (234, 'guernsey', 'GG', 'GGY', '831', '044', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (235, 'heard-island-mcdonald-islands', 'HM', 'HMD', '334', '061', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (236, 'isle-of-man', 'IM', 'IMN', '833', '044', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (237, 'jersey', 'JE', 'JEY', '832', '044', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (238, 'northern-mariana-islands', 'MP', 'MNP', '580', '670', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (239, 'pitcairn', 'PN', 'PCN', '612', '649', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (240, 'south-georgia-south-sandwich-islands', 'GS', 'SGS', '239', '044', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (241, 'south-sudan', 'SS', 'SSD', '728', '211', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (242, 'sint-maarten', 'SX', 'SXM', '534', '721', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (243, 'st-barthelemy', 'BL', 'BLM', '652', '590', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (244, 'st-martin', 'MF', 'MAF', '663', '590', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (245, 'tokelau', 'TK', 'TKL', '772', '690', 0, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (246, 'timor-leste', 'TL', 'TLS', '626', '670', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (247, 'united-states-minor-outlying-islands', 'UM', 'UMI', '581', '699', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (248, 'united-states-virgin-islands', 'VI', 'VIR', '850', '340', 1, 1)
go
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (249, 'western-sahara', 'EH', 'ESH', '732', '212', 1, 1)
go

insert into Region (regionId, countryId, regionCode, name, active_) values (1001, 1, 'AB', 'Alberta', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (1002, 1, 'BC', 'British Columbia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (1003, 1, 'MB', 'Manitoba', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (1004, 1, 'NB', 'New Brunswick', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (1005, 1, 'NL', 'Newfoundland and Labrador', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (1006, 1, 'NT', 'Northwest Territories', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (1007, 1, 'NS', 'Nova Scotia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (1008, 1, 'NU', 'Nunavut', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (1009, 1, 'ON', 'Ontario', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (1010, 1, 'PE', 'Prince Edward Island', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (1011, 1, 'QC', 'Quebec', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (1012, 1, 'SK', 'Saskatchewan', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (1013, 1, 'YT', 'Yukon', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2001, 2, 'CN-34', 'Anhui', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2002, 2, 'CN-92', 'Aomen', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2003, 2, 'CN-11', 'Beijing', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2004, 2, 'CN-50', 'Chongqing', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2005, 2, 'CN-35', 'Fujian', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2006, 2, 'CN-62', 'Gansu', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2007, 2, 'CN-44', 'Guangdong', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2008, 2, 'CN-45', 'Guangxi', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2009, 2, 'CN-52', 'Guizhou', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2010, 2, 'CN-46', 'Hainan', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2011, 2, 'CN-13', 'Hebei', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2012, 2, 'CN-23', 'Heilongjiang', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2013, 2, 'CN-41', 'Henan', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2014, 2, 'CN-42', 'Hubei', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2015, 2, 'CN-43', 'Hunan', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2016, 2, 'CN-32', 'Jiangsu', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2017, 2, 'CN-36', 'Jiangxi', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2018, 2, 'CN-22', 'Jilin', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2019, 2, 'CN-21', 'Liaoning', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2020, 2, 'CN-15', 'Nei Mongol', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2021, 2, 'CN-64', 'Ningxia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2022, 2, 'CN-63', 'Qinghai', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2023, 2, 'CN-61', 'Shaanxi', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2024, 2, 'CN-37', 'Shandong', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2025, 2, 'CN-31', 'Shanghai', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2026, 2, 'CN-14', 'Shanxi', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2027, 2, 'CN-51', 'Sichuan', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2028, 2, 'CN-71', 'Taiwan', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2029, 2, 'CN-12', 'Tianjin', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2030, 2, 'CN-91', 'Xianggang', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2031, 2, 'CN-65', 'Xinjiang', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2032, 2, 'CN-54', 'Xizang', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2033, 2, 'CN-53', 'Yunnan', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (2034, 2, 'CN-33', 'Zhejiang', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3001, 3, 'A', 'Alsace', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3002, 3, 'B', 'Aquitaine', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3003, 3, 'C', 'Auvergne', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3004, 3, 'P', 'Basse-Normandie', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3005, 3, 'D', 'Bourgogne', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3006, 3, 'E', 'Bretagne', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3007, 3, 'F', 'Centre', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3008, 3, 'G', 'Champagne-Ardenne', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3009, 3, 'H', 'Corse', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3010, 3, 'GF', 'Guyane', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3011, 3, 'I', 'Franche Comté', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3012, 3, 'GP', 'Guadeloupe', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3013, 3, 'Q', 'Haute-Normandie', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3014, 3, 'J', 'Île-de-France', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3015, 3, 'K', 'Languedoc-Roussillon', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3016, 3, 'L', 'Limousin', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3017, 3, 'M', 'Lorraine', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3018, 3, 'MQ', 'Martinique', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3019, 3, 'N', 'Midi-Pyrénées', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3020, 3, 'O', 'Nord Pas de Calais', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3021, 3, 'R', 'Pays de la Loire', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3022, 3, 'S', 'Picardie', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3023, 3, 'T', 'Poitou-Charentes', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3024, 3, 'U', 'Provence-Alpes-Côte-d''Azur', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3025, 3, 'RE', 'Réunion', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (3026, 3, 'V', 'Rhône-Alpes', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4001, 4, 'BW', 'Baden-Württemberg', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4002, 4, 'BY', 'Bayern', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4003, 4, 'BE', 'Berlin', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4004, 4, 'BB', 'Brandenburg', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4005, 4, 'HB', 'Bremen', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4006, 4, 'HH', 'Hamburg', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4007, 4, 'HE', 'Hessen', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4008, 4, 'MV', 'Mecklenburg-Vorpommern', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4009, 4, 'NI', 'Niedersachsen', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4010, 4, 'NW', 'Nordrhein-Westfalen', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4011, 4, 'RP', 'Rheinland-Pfalz', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4012, 4, 'SL', 'Saarland', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4013, 4, 'SN', 'Sachsen', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4014, 4, 'ST', 'Sachsen-Anhalt', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4015, 4, 'SH', 'Schleswig-Holstein', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (4016, 4, 'TH', 'Thüringen', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8001, 8, 'AG', 'Agrigento', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8002, 8, 'AL', 'Alessandria', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8003, 8, 'AN', 'Ancona', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8004, 8, 'AO', 'Aosta', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8005, 8, 'AR', 'Arezzo', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8006, 8, 'AP', 'Ascoli Piceno', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8007, 8, 'AT', 'Asti', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8008, 8, 'AV', 'Avellino', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8009, 8, 'BA', 'Bari', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8010, 8, 'BT', 'Barletta-Andria-Trani', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8011, 8, 'BL', 'Belluno', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8012, 8, 'BN', 'Benevento', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8013, 8, 'BG', 'Bergamo', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8014, 8, 'BI', 'Biella', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8015, 8, 'BO', 'Bologna', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8016, 8, 'BZ', 'Bolzano', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8017, 8, 'BS', 'Brescia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8018, 8, 'BR', 'Brindisi', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8019, 8, 'CA', 'Cagliari', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8020, 8, 'CL', 'Caltanissetta', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8021, 8, 'CB', 'Campobasso', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8022, 8, 'CI', 'Carbonia-Iglesias', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8023, 8, 'CE', 'Caserta', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8024, 8, 'CT', 'Catania', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8025, 8, 'CZ', 'Catanzaro', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8026, 8, 'CH', 'Chieti', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8027, 8, 'CO', 'Como', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8028, 8, 'CS', 'Cosenza', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8029, 8, 'CR', 'Cremona', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8030, 8, 'KR', 'Crotone', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8031, 8, 'CN', 'Cuneo', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8032, 8, 'EN', 'Enna', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8033, 8, 'FM', 'Fermo', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8034, 8, 'FE', 'Ferrara', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8035, 8, 'FI', 'Firenze', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8036, 8, 'FG', 'Foggia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8037, 8, 'FC', 'Forli-Cesena', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8038, 8, 'FR', 'Frosinone', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8039, 8, 'GE', 'Genova', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8040, 8, 'GO', 'Gorizia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8041, 8, 'GR', 'Grosseto', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8042, 8, 'IM', 'Imperia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8043, 8, 'IS', 'Isernia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8044, 8, 'AQ', 'L''Aquila', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8045, 8, 'SP', 'La Spezia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8046, 8, 'LT', 'Latina', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8047, 8, 'LE', 'Lecce', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8048, 8, 'LC', 'Lecco', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8049, 8, 'LI', 'Livorno', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8050, 8, 'LO', 'Lodi', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8051, 8, 'LU', 'Lucca', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8052, 8, 'MC', 'Macerata', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8053, 8, 'MN', 'Mantova', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8054, 8, 'MS', 'Massa-Carrara', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8055, 8, 'MT', 'Matera', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8056, 8, 'MA', 'Medio Campidano', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8057, 8, 'ME', 'Messina', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8058, 8, 'MI', 'Milano', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8059, 8, 'MO', 'Modena', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8060, 8, 'MB', 'Monza e Brianza', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8061, 8, 'NA', 'Napoli', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8062, 8, 'NO', 'Novara', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8063, 8, 'NU', 'Nuoro', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8064, 8, 'OG', 'Ogliastra', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8065, 8, 'OT', 'Olbia-Tempio', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8066, 8, 'OR', 'Oristano', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8067, 8, 'PD', 'Padova', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8068, 8, 'PA', 'Palermo', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8069, 8, 'PR', 'Parma', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8070, 8, 'PV', 'Pavia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8071, 8, 'PG', 'Perugia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8072, 8, 'PU', 'Pesaro e Urbino', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8073, 8, 'PE', 'Pescara', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8074, 8, 'PC', 'Piacenza', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8075, 8, 'PI', 'Pisa', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8076, 8, 'PT', 'Pistoia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8077, 8, 'PN', 'Pordenone', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8078, 8, 'PZ', 'Potenza', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8079, 8, 'PO', 'Prato', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8080, 8, 'RG', 'Ragusa', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8081, 8, 'RA', 'Ravenna', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8082, 8, 'RC', 'Reggio Calabria', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8083, 8, 'RE', 'Reggio Emilia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8084, 8, 'RI', 'Rieti', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8085, 8, 'RN', 'Rimini', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8086, 8, 'RM', 'Roma', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8087, 8, 'RO', 'Rovigo', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8088, 8, 'SA', 'Salerno', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8089, 8, 'SS', 'Sassari', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8090, 8, 'SV', 'Savona', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8091, 8, 'SI', 'Siena', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8092, 8, 'SR', 'Siracusa', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8093, 8, 'SO', 'Sondrio', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8094, 8, 'TA', 'Taranto', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8095, 8, 'TE', 'Teramo', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8096, 8, 'TR', 'Terni', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8097, 8, 'TO', 'Torino', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8098, 8, 'TP', 'Trapani', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8099, 8, 'TN', 'Trento', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8100, 8, 'TV', 'Treviso', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8101, 8, 'TS', 'Trieste', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8102, 8, 'UD', 'Udine', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8103, 8, 'VA', 'Varese', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8104, 8, 'VE', 'Venezia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8105, 8, 'VB', 'Verbano-Cusio-Ossola', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8106, 8, 'VC', 'Vercelli', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8107, 8, 'VR', 'Verona', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8108, 8, 'VV', 'Vibo Valentia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8109, 8, 'VI', 'Vicenza', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (8110, 8, 'VT', 'Viterbo', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (11001, 11, 'DR', 'Drenthe', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (11002, 11, 'FL', 'Flevoland', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (11003, 11, 'FR', 'Friesland', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (11004, 11, 'GE', 'Gelderland', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (11005, 11, 'GR', 'Groningen', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (11006, 11, 'LI', 'Limburg', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (11007, 11, 'NB', 'Noord-Brabant', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (11008, 11, 'NH', 'Noord-Holland', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (11009, 11, 'OV', 'Overijssel', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (11010, 11, 'UT', 'Utrecht', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (11011, 11, 'ZE', 'Zeeland', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (11012, 11, 'ZH', 'Zuid-Holland', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15001, 15, 'AN', 'Andalusia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15002, 15, 'AR', 'Aragon', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15003, 15, 'AS', 'Asturias', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15004, 15, 'IB', 'Balearic Islands', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15005, 15, 'PV', 'Basque Country', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15006, 15, 'CN', 'Canary Islands', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15007, 15, 'CB', 'Cantabria', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15008, 15, 'CL', 'Castile and Leon', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15009, 15, 'CM', 'Castile-La Mancha', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15010, 15, 'CT', 'Catalonia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15011, 15, 'CE', 'Ceuta', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15012, 15, 'EX', 'Extremadura', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15013, 15, 'GA', 'Galicia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15014, 15, 'LO', 'La Rioja', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15015, 15, 'M', 'Madrid', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15016, 15, 'ML', 'Melilla', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15017, 15, 'MU', 'Murcia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15018, 15, 'NA', 'Navarra', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (15019, 15, 'VC', 'Valencia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19001, 19, 'AL', 'Alabama', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19002, 19, 'AK', 'Alaska', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19003, 19, 'AZ', 'Arizona', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19004, 19, 'AR', 'Arkansas', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19005, 19, 'CA', 'California', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19006, 19, 'CO', 'Colorado', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19007, 19, 'CT', 'Connecticut', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19008, 19, 'DC', 'District of Columbia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19009, 19, 'DE', 'Delaware', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19010, 19, 'FL', 'Florida', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19011, 19, 'GA', 'Georgia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19012, 19, 'HI', 'Hawaii', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19013, 19, 'ID', 'Idaho', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19014, 19, 'IL', 'Illinois', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19015, 19, 'IN', 'Indiana', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19016, 19, 'IA', 'Iowa', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19017, 19, 'KS', 'Kansas', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19018, 19, 'KY', 'Kentucky ', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19019, 19, 'LA', 'Louisiana ', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19020, 19, 'ME', 'Maine', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19021, 19, 'MD', 'Maryland', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19022, 19, 'MA', 'Massachusetts', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19023, 19, 'MI', 'Michigan', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19024, 19, 'MN', 'Minnesota', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19025, 19, 'MS', 'Mississippi', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19026, 19, 'MO', 'Missouri', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19027, 19, 'MT', 'Montana', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19028, 19, 'NE', 'Nebraska', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19029, 19, 'NV', 'Nevada', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19030, 19, 'NH', 'New Hampshire', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19031, 19, 'NJ', 'New Jersey', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19032, 19, 'NM', 'New Mexico', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19033, 19, 'NY', 'New York', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19034, 19, 'NC', 'North Carolina', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19035, 19, 'ND', 'North Dakota', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19036, 19, 'OH', 'Ohio', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19037, 19, 'OK', 'Oklahoma ', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19038, 19, 'OR', 'Oregon', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19039, 19, 'PA', 'Pennsylvania', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19040, 19, 'PR', 'Puerto Rico', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19041, 19, 'RI', 'Rhode Island', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19042, 19, 'SC', 'South Carolina', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19043, 19, 'SD', 'South Dakota', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19044, 19, 'TN', 'Tennessee', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19045, 19, 'TX', 'Texas', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19046, 19, 'UT', 'Utah', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19047, 19, 'VT', 'Vermont', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19048, 19, 'VA', 'Virginia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19049, 19, 'WA', 'Washington', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19050, 19, 'WV', 'West Virginia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19051, 19, 'WI', 'Wisconsin', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (19052, 19, 'WY', 'Wyoming', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (32001, 32, 'ACT', 'Australian Capital Territory', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (32002, 32, 'NSW', 'New South Wales', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (32003, 32, 'NT', 'Northern Territory', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (32004, 32, 'QLD', 'Queensland', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (32005, 32, 'SA', 'South Australia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (32006, 32, 'TAS', 'Tasmania', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (32007, 32, 'VIC', 'Victoria', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (32008, 32, 'WA', 'Western Australia', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144001, 144, 'MX-AGS', 'Aguascalientes', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144002, 144, 'MX-BCN', 'Baja California', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144003, 144, 'MX-BCS', 'Baja California Sur', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144004, 144, 'MX-CAM', 'Campeche', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144005, 144, 'MX-CHP', 'Chiapas', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144006, 144, 'MX-CHI', 'Chihuahua', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144007, 144, 'MX-COA', 'Coahuila', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144008, 144, 'MX-COL', 'Colima', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144009, 144, 'MX-DUR', 'Durango', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144010, 144, 'MX-GTO', 'Guanajuato', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144011, 144, 'MX-GRO', 'Guerrero', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144012, 144, 'MX-HGO', 'Hidalgo', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144013, 144, 'MX-JAL', 'Jalisco', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144014, 144, 'MX-MEX', 'Mexico', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144015, 144, 'MX-MIC', 'Michoacan', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144016, 144, 'MX-MOR', 'Morelos', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144017, 144, 'MX-NAY', 'Nayarit', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144018, 144, 'MX-NLE', 'Nuevo Leon', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144019, 144, 'MX-OAX', 'Oaxaca', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144020, 144, 'MX-PUE', 'Puebla', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144021, 144, 'MX-QRO', 'Queretaro', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144023, 144, 'MX-ROO', 'Quintana Roo', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144024, 144, 'MX-SLP', 'San Luis Potosí', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144025, 144, 'MX-SIN', 'Sinaloa', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144026, 144, 'MX-SON', 'Sonora', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144027, 144, 'MX-TAB', 'Tabasco', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144028, 144, 'MX-TAM', 'Tamaulipas', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144029, 144, 'MX-TLX', 'Tlaxcala', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144030, 144, 'MX-VER', 'Veracruz', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144031, 144, 'MX-YUC', 'Yucatan', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (144032, 144, 'MX-ZAC', 'Zacatecas', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164001, 164, '01', 'Østfold', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164002, 164, '02', 'Akershus', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164003, 164, '03', 'Oslo', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164004, 164, '04', 'Hedmark', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164005, 164, '05', 'Oppland', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164006, 164, '06', 'Buskerud', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164007, 164, '07', 'Vestfold', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164008, 164, '08', 'Telemark', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164009, 164, '09', 'Aust-Agder', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164010, 164, '10', 'Vest-Agder', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164011, 164, '11', 'Rogaland', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164012, 164, '12', 'Hordaland', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164013, 164, '14', 'Sogn og Fjordane', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164014, 164, '15', 'Møre of Romsdal', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164015, 164, '16', 'Sør-Trøndelag', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164016, 164, '17', 'Nord-Trøndelag', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164017, 164, '18', 'Nordland', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164018, 164, '19', 'Troms', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (164019, 164, '20', 'Finnmark', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202001, 202, 'AG', 'Aargau', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202002, 202, 'AR', 'Appenzell Ausserrhoden', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202003, 202, 'AI', 'Appenzell Innerrhoden', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202004, 202, 'BL', 'Basel-Landschaft', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202005, 202, 'BS', 'Basel-Stadt', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202006, 202, 'BE', 'Bern', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202007, 202, 'FR', 'Fribourg', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202008, 202, 'GE', 'Geneva', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202009, 202, 'GL', 'Glarus', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202010, 202, 'GR', 'Graubünden', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202011, 202, 'JU', 'Jura', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202012, 202, 'LU', 'Lucerne', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202013, 202, 'NE', 'Neuchâtel', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202014, 202, 'NW', 'Nidwalden', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202015, 202, 'OW', 'Obwalden', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202016, 202, 'SH', 'Schaffhausen', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202017, 202, 'SZ', 'Schwyz', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202018, 202, 'SO', 'Solothurn', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202019, 202, 'SG', 'St. Gallen', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202020, 202, 'TG', 'Thurgau', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202021, 202, 'TI', 'Ticino', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202022, 202, 'UR', 'Uri', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202023, 202, 'VS', 'Valais', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202024, 202, 'VD', 'Vaud', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202025, 202, 'ZG', 'Zug', 1)
go
insert into Region (regionId, countryId, regionCode, name, active_) values (202026, 202, 'ZH', 'Zürich', 1)
go

--
-- List types for accounts
--

insert into ListType (listTypeId, name, type_) values (10000, 'billing', 'com.liferay.portal.model.Account.address')
go
insert into ListType (listTypeId, name, type_) values (10001, 'other', 'com.liferay.portal.model.Account.address')
go
insert into ListType (listTypeId, name, type_) values (10002, 'p-o-box', 'com.liferay.portal.model.Account.address')
go
insert into ListType (listTypeId, name, type_) values (10003, 'shipping', 'com.liferay.portal.model.Account.address')
go

insert into ListType (listTypeId, name, type_) values (10004, 'email-address', 'com.liferay.portal.model.Account.emailAddress')
go
insert into ListType (listTypeId, name, type_) values (10005, 'email-address-2', 'com.liferay.portal.model.Account.emailAddress')
go
insert into ListType (listTypeId, name, type_) values (10006, 'email-address-3', 'com.liferay.portal.model.Account.emailAddress')
go

insert into ListType (listTypeId, name, type_) values (10007, 'fax', 'com.liferay.portal.model.Account.phone')
go
insert into ListType (listTypeId, name, type_) values (10008, 'local', 'com.liferay.portal.model.Account.phone')
go
insert into ListType (listTypeId, name, type_) values (10009, 'other', 'com.liferay.portal.model.Account.phone')
go
insert into ListType (listTypeId, name, type_) values (10010, 'toll-free', 'com.liferay.portal.model.Account.phone')
go
insert into ListType (listTypeId, name, type_) values (10011, 'tty', 'com.liferay.portal.model.Account.phone')
go

insert into ListType (listTypeId, name, type_) values (10012, 'intranet', 'com.liferay.portal.model.Account.website')
go
insert into ListType (listTypeId, name, type_) values (10013, 'public', 'com.liferay.portal.model.Account.website')
go

--
-- List types for contacts
--

insert into ListType (listTypeId, name, type_) values (11000, 'business', 'com.liferay.portal.model.Contact.address')
go
insert into ListType (listTypeId, name, type_) values (11001, 'other', 'com.liferay.portal.model.Contact.address')
go
insert into ListType (listTypeId, name, type_) values (11002, 'personal', 'com.liferay.portal.model.Contact.address')
go

insert into ListType (listTypeId, name, type_) values (11003, 'email-address', 'com.liferay.portal.model.Contact.emailAddress')
go
insert into ListType (listTypeId, name, type_) values (11004, 'email-address-2', 'com.liferay.portal.model.Contact.emailAddress')
go
insert into ListType (listTypeId, name, type_) values (11005, 'email-address-3', 'com.liferay.portal.model.Contact.emailAddress')
go

insert into ListType (listTypeId, name, type_) values (11006, 'business', 'com.liferay.portal.model.Contact.phone')
go
insert into ListType (listTypeId, name, type_) values (11007, 'business-fax', 'com.liferay.portal.model.Contact.phone')
go
insert into ListType (listTypeId, name, type_) values (11008, 'mobile-phone', 'com.liferay.portal.model.Contact.phone')
go
insert into ListType (listTypeId, name, type_) values (11009, 'other', 'com.liferay.portal.model.Contact.phone')
go
insert into ListType (listTypeId, name, type_) values (11010, 'pager', 'com.liferay.portal.model.Contact.phone')
go
insert into ListType (listTypeId, name, type_) values (11011, 'personal', 'com.liferay.portal.model.Contact.phone')
go
insert into ListType (listTypeId, name, type_) values (11012, 'personal-fax', 'com.liferay.portal.model.Contact.phone')
go
insert into ListType (listTypeId, name, type_) values (11013, 'tty', 'com.liferay.portal.model.Contact.phone')
go

insert into ListType (listTypeId, name, type_) values (11014, 'dr', 'com.liferay.portal.model.Contact.prefix')
go
insert into ListType (listTypeId, name, type_) values (11015, 'mr', 'com.liferay.portal.model.Contact.prefix')
go
insert into ListType (listTypeId, name, type_) values (11016, 'mrs', 'com.liferay.portal.model.Contact.prefix')
go
insert into ListType (listTypeId, name, type_) values (11017, 'ms', 'com.liferay.portal.model.Contact.prefix')
go

insert into ListType (listTypeId, name, type_) values (11020, 'ii', 'com.liferay.portal.model.Contact.suffix')
go
insert into ListType (listTypeId, name, type_) values (11021, 'iii', 'com.liferay.portal.model.Contact.suffix')
go
insert into ListType (listTypeId, name, type_) values (11022, 'iv', 'com.liferay.portal.model.Contact.suffix')
go
insert into ListType (listTypeId, name, type_) values (11023, 'jr', 'com.liferay.portal.model.Contact.suffix')
go
insert into ListType (listTypeId, name, type_) values (11024, 'phd', 'com.liferay.portal.model.Contact.suffix')
go
insert into ListType (listTypeId, name, type_) values (11025, 'sr', 'com.liferay.portal.model.Contact.suffix')
go

insert into ListType (listTypeId, name, type_) values (11026, 'blog', 'com.liferay.portal.model.Contact.website')
go
insert into ListType (listTypeId, name, type_) values (11027, 'business', 'com.liferay.portal.model.Contact.website')
go
insert into ListType (listTypeId, name, type_) values (11028, 'other', 'com.liferay.portal.model.Contact.website')
go
insert into ListType (listTypeId, name, type_) values (11029, 'personal', 'com.liferay.portal.model.Contact.website')
go

--
-- List types for organizations
--

insert into ListType (listTypeId, name, type_) values (12000, 'billing', 'com.liferay.portal.model.Organization.address')
go
insert into ListType (listTypeId, name, type_) values (12001, 'other', 'com.liferay.portal.model.Organization.address')
go
insert into ListType (listTypeId, name, type_) values (12002, 'p-o-box', 'com.liferay.portal.model.Organization.address')
go
insert into ListType (listTypeId, name, type_) values (12003, 'shipping', 'com.liferay.portal.model.Organization.address')
go

insert into ListType (listTypeId, name, type_) values (12004, 'email-address', 'com.liferay.portal.model.Organization.emailAddress')
go
insert into ListType (listTypeId, name, type_) values (12005, 'email-address-2', 'com.liferay.portal.model.Organization.emailAddress')
go
insert into ListType (listTypeId, name, type_) values (12006, 'email-address-3', 'com.liferay.portal.model.Organization.emailAddress')
go

insert into ListType (listTypeId, name, type_) values (12007, 'fax', 'com.liferay.portal.model.Organization.phone')
go
insert into ListType (listTypeId, name, type_) values (12008, 'local', 'com.liferay.portal.model.Organization.phone')
go
insert into ListType (listTypeId, name, type_) values (12009, 'other', 'com.liferay.portal.model.Organization.phone')
go
insert into ListType (listTypeId, name, type_) values (12010, 'toll-free', 'com.liferay.portal.model.Organization.phone')
go
insert into ListType (listTypeId, name, type_) values (12011, 'tty', 'com.liferay.portal.model.Organization.phone')
go

insert into ListType (listTypeId, name, type_) values (12012, 'administrative', 'com.liferay.portal.model.Organization.service')
go
insert into ListType (listTypeId, name, type_) values (12013, 'contracts', 'com.liferay.portal.model.Organization.service')
go
insert into ListType (listTypeId, name, type_) values (12014, 'donation', 'com.liferay.portal.model.Organization.service')
go
insert into ListType (listTypeId, name, type_) values (12015, 'retail', 'com.liferay.portal.model.Organization.service')
go
insert into ListType (listTypeId, name, type_) values (12016, 'training', 'com.liferay.portal.model.Organization.service')
go

insert into ListType (listTypeId, name, type_) values (12017, 'full-member', 'com.liferay.portal.model.Organization.status')
go
insert into ListType (listTypeId, name, type_) values (12018, 'provisional-member', 'com.liferay.portal.model.Organization.status')
go

insert into ListType (listTypeId, name, type_) values (12019, 'intranet', 'com.liferay.portal.model.Organization.website')
go
insert into ListType (listTypeId, name, type_) values (12020, 'public', 'com.liferay.portal.model.Organization.website')
go


insert into Counter (name, currentId) values ('com.liferay.counter.model.Counter', 20000)
go


insert into Release_ (releaseId, createDate, modifiedDate, servletContextName, buildNumber, verified) values (1, getdate(), getdate(), 'portal', 6200, 0)
go


create table QUARTZ_BLOB_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	BLOB_DATA image null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
go

create table QUARTZ_CALENDARS (
	SCHED_NAME varchar(120) not null,
	CALENDAR_NAME varchar(200) not null,
	CALENDAR image not null,
	primary key (SCHED_NAME,CALENDAR_NAME)
)
go

create table QUARTZ_CRON_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	CRON_EXPRESSION varchar(200) not null,
	TIME_ZONE_ID varchar(80),
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
go

create table QUARTZ_FIRED_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	ENTRY_ID varchar(95) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	INSTANCE_NAME varchar(200) not null,
	FIRED_TIME decimal(20,0) not null,
	PRIORITY int not null,
	STATE varchar(16) not null,
	JOB_NAME varchar(200) null,
	JOB_GROUP varchar(200) null,
	IS_NONCONCURRENT int null,
	REQUESTS_RECOVERY int null,
	primary key (SCHED_NAME, ENTRY_ID)
)
go

create table QUARTZ_JOB_DETAILS (
	SCHED_NAME varchar(120) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250) null,
	JOB_CLASS_NAME varchar(250) not null,
	IS_DURABLE int not null,
	IS_NONCONCURRENT int not null,
	IS_UPDATE_DATA int not null,
	REQUESTS_RECOVERY int not null,
	JOB_DATA image null,
	primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
)
go

create table QUARTZ_LOCKS (
	SCHED_NAME varchar(120) not null,
	LOCK_NAME varchar(40) not null ,
	primary key (SCHED_NAME, LOCK_NAME)
)
go

create table QUARTZ_PAUSED_TRIGGER_GRPS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_GROUP varchar(200) not null,
	primary key (SCHED_NAME, TRIGGER_GROUP)
)
go

create table QUARTZ_SCHEDULER_STATE (
	SCHED_NAME varchar(120) not null,
	INSTANCE_NAME varchar(200) not null,
	LAST_CHECKIN_TIME decimal(20,0) not null,
	CHECKIN_INTERVAL decimal(20,0) not null,
	primary key (SCHED_NAME, INSTANCE_NAME)
)
go

create table QUARTZ_SIMPLE_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	REPEAT_COUNT decimal(20,0) not null,
	REPEAT_INTERVAL decimal(20,0) not null,
	TIMES_TRIGGERED decimal(20,0) not null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
go

create table QUARTZ_SIMPROP_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	STR_PROP_1 varchar(512) null,
	STR_PROP_2 varchar(512) null,
	STR_PROP_3 varchar(512) null,
	INT_PROP_1 int null,
	INT_PROP_2 int null,
	LONG_PROP_1 decimal(20,0) null,
	LONG_PROP_2 decimal(20,0) null,
	DEC_PROP_1 NUMERIC(13,4) null,
	DEC_PROP_2 NUMERIC(13,4) null,
	BOOL_PROP_1 int null,
	BOOL_PROP_2 int null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
go

create table QUARTZ_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250) null,
	NEXT_FIRE_TIME decimal(20,0) null,
	PREV_FIRE_TIME decimal(20,0) null,
	PRIORITY int null,
	TRIGGER_STATE varchar(16) not null,
	TRIGGER_TYPE varchar(8) not null,
	START_TIME decimal(20,0) not null,
	END_TIME decimal(20,0) null,
	CALENDAR_NAME varchar(200) null,
	MISFIRE_INSTR int null,
	JOB_DATA image null,
	primary key  (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
go

go

create index IX_88328984 on QUARTZ_JOB_DETAILS (SCHED_NAME, JOB_GROUP)
go
create index IX_779BCA37 on QUARTZ_JOB_DETAILS (SCHED_NAME, REQUESTS_RECOVERY)
go

create index IX_BE3835E5 on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
go
create index IX_4BD722BM on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_GROUP)
go
create index IX_204D31E8 on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME)
go
create index IX_339E078M on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME, REQUESTS_RECOVERY)
go
create index IX_5005E3AF on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP)
go
create index IX_BC2F03B0 on QUARTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_GROUP)
go

create index IX_186442A4 on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_STATE)
go
create index IX_1BA1F9DC on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP)
go
create index IX_91CA7CCE on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP, NEXT_FIRE_TIME, TRIGGER_STATE, MISFIRE_INSTR)
go
create index IX_D219AFDE on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP, TRIGGER_STATE)
go
create index IX_A85822A0 on QUARTZ_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP)
go
create index IX_8AA50BE1 on QUARTZ_TRIGGERS (SCHED_NAME, JOB_GROUP)
go
create index IX_EEFE382A on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME)
go
create index IX_F026CF4C on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME, TRIGGER_STATE)
go
create index IX_F2DD7C7E on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME, TRIGGER_STATE, MISFIRE_INSTR)
go
create index IX_1F92813C on QUARTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME, MISFIRE_INSTR)
go
create index IX_99108B6E on QUARTZ_TRIGGERS (SCHED_NAME, TRIGGER_STATE)
go
create index IX_CD7132D0 on QUARTZ_TRIGGERS (SCHED_NAME, CALENDAR_NAME)
go


go

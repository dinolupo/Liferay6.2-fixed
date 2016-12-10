create table Account_ (
	accountId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentAccountId bigint,
	name nvarchar(75) null,
	legalName nvarchar(75) null,
	legalId nvarchar(75) null,
	legalType nvarchar(75) null,
	sicCode nvarchar(75) null,
	tickerSymbol nvarchar(75) null,
	industry nvarchar(75) null,
	type_ nvarchar(75) null,
	size_ nvarchar(75) null
);

create table Address (
	uuid_ nvarchar(75) null,
	addressId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	street1 nvarchar(75) null,
	street2 nvarchar(75) null,
	street3 nvarchar(75) null,
	city nvarchar(75) null,
	zip nvarchar(75) null,
	regionId bigint,
	countryId bigint,
	typeId int,
	mailing bit,
	primary_ bit
);

create table AnnouncementsDelivery (
	deliveryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	type_ nvarchar(75) null,
	email bit,
	sms bit,
	website bit
);

create table AnnouncementsEntry (
	uuid_ nvarchar(75) null,
	entryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	title nvarchar(75) null,
	content nvarchar(max) null,
	url nvarchar(2000) null,
	type_ nvarchar(75) null,
	displayDate datetime null,
	expirationDate datetime null,
	priority int,
	alert bit
);

create table AnnouncementsFlag (
	flagId bigint not null primary key,
	userId bigint,
	createDate datetime null,
	entryId bigint,
	value int
);

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
	description nvarchar(2000) null,
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
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	classUuid nvarchar(75) null,
	classTypeId bigint,
	visible bit,
	startDate datetime null,
	endDate datetime null,
	publishDate datetime null,
	expirationDate datetime null,
	mimeType nvarchar(75) null,
	title nvarchar(2000) null,
	description nvarchar(max) null,
	summary nvarchar(max) null,
	url nvarchar(2000) null,
	layoutUuid nvarchar(75) null,
	height int,
	width int,
	priority float,
	viewCount int
);

create table AssetLink (
	linkId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	entryId1 bigint,
	entryId2 bigint,
	type_ int,
	weight int
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

create table BackgroundTask (
	backgroundTaskId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(75) null,
	servletContextNames nvarchar(255) null,
	taskExecutorClassName nvarchar(200) null,
	taskContext nvarchar(max) null,
	completed bit,
	completionDate datetime null,
	status int,
	statusMessage nvarchar(max) null
);

create table BlogsEntry (
	uuid_ nvarchar(75) null,
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	title nvarchar(150) null,
	urlTitle nvarchar(150) null,
	description nvarchar(2000) null,
	content nvarchar(max) null,
	displayDate datetime null,
	allowPingbacks bit,
	allowTrackbacks bit,
	trackbacks nvarchar(max) null,
	smallImage bit,
	smallImageId bigint,
	smallImageURL nvarchar(2000) null,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table BlogsStatsUser (
	statsUserId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	entryCount int,
	lastPostDate datetime null,
	ratingsTotalEntries int,
	ratingsTotalScore float,
	ratingsAverageScore float
);

create table BookmarksEntry (
	uuid_ nvarchar(75) null,
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	resourceBlockId bigint,
	folderId bigint,
	treePath nvarchar(2000) null,
	name nvarchar(255) null,
	url nvarchar(2000) null,
	description nvarchar(2000) null,
	visits int,
	priority int,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table BookmarksFolder (
	uuid_ nvarchar(75) null,
	folderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	resourceBlockId bigint,
	parentFolderId bigint,
	treePath nvarchar(2000) null,
	name nvarchar(75) null,
	description nvarchar(2000) null,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table BrowserTracker (
	browserTrackerId bigint not null primary key,
	userId bigint,
	browserKey bigint
);

create table CalEvent (
	uuid_ nvarchar(75) null,
	eventId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	title nvarchar(75) null,
	description nvarchar(2000) null,
	location nvarchar(2000) null,
	startDate datetime null,
	endDate datetime null,
	durationHour int,
	durationMinute int,
	allDay bit,
	timeZoneSensitive bit,
	type_ nvarchar(75) null,
	repeating bit,
	recurrence nvarchar(max) null,
	remindBy int,
	firstReminder int,
	secondReminder int
);

create table ClassName_ (
	classNameId bigint not null primary key,
	value nvarchar(200) null
);

create table ClusterGroup (
	clusterGroupId bigint not null primary key,
	name nvarchar(75) null,
	clusterNodeIds nvarchar(75) null,
	wholeCluster bit
);

create table Company (
	companyId bigint not null primary key,
	accountId bigint,
	webId nvarchar(75) null,
	key_ nvarchar(max) null,
	mx nvarchar(75) null,
	homeURL nvarchar(2000) null,
	logoId bigint,
	system bit,
	maxUsers int,
	active_ bit
);

create table Contact_ (
	contactId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	accountId bigint,
	parentContactId bigint,
	emailAddress nvarchar(75) null,
	firstName nvarchar(75) null,
	middleName nvarchar(75) null,
	lastName nvarchar(75) null,
	prefixId int,
	suffixId int,
	male bit,
	birthday datetime null,
	smsSn nvarchar(75) null,
	aimSn nvarchar(75) null,
	facebookSn nvarchar(75) null,
	icqSn nvarchar(75) null,
	jabberSn nvarchar(75) null,
	msnSn nvarchar(75) null,
	mySpaceSn nvarchar(75) null,
	skypeSn nvarchar(75) null,
	twitterSn nvarchar(75) null,
	ymSn nvarchar(75) null,
	employeeStatusId nvarchar(75) null,
	employeeNumber nvarchar(75) null,
	jobTitle nvarchar(100) null,
	jobClass nvarchar(75) null,
	hoursOfOperation nvarchar(75) null
);

create table Counter (
	name nvarchar(75) not null primary key,
	currentId bigint
);

create table Country (
	countryId bigint not null primary key,
	name nvarchar(75) null,
	a2 nvarchar(75) null,
	a3 nvarchar(75) null,
	number_ nvarchar(75) null,
	idd_ nvarchar(75) null,
	zipRequired bit,
	active_ bit
);

create table CyrusUser (
	userId nvarchar(75) not null primary key,
	password_ nvarchar(75) not null
);

create table CyrusVirtual (
	emailAddress nvarchar(75) not null primary key,
	userId nvarchar(75) not null
);

create table DDLRecord (
	uuid_ nvarchar(75) null,
	recordId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	versionUserId bigint,
	versionUserName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	DDMStorageId bigint,
	recordSetId bigint,
	version nvarchar(75) null,
	displayIndex int
);

create table DDLRecordSet (
	uuid_ nvarchar(75) null,
	recordSetId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	DDMStructureId bigint,
	recordSetKey nvarchar(75) null,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	minDisplayRows int,
	scope int
);

create table DDLRecordVersion (
	recordVersionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	DDMStorageId bigint,
	recordSetId bigint,
	recordId bigint,
	version nvarchar(75) null,
	displayIndex int,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table DDMContent (
	uuid_ nvarchar(75) null,
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	xml nvarchar(max) null
);

create table DDMStorageLink (
	uuid_ nvarchar(75) null,
	storageLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
);

create table DDMStructure (
	uuid_ nvarchar(75) null,
	structureId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentStructureId bigint,
	classNameId bigint,
	structureKey nvarchar(75) null,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	xsd nvarchar(max) null,
	storageType nvarchar(75) null,
	type_ int
);

create table DDMStructureLink (
	structureLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
);

create table DDMTemplate (
	uuid_ nvarchar(75) null,
	templateId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	templateKey nvarchar(75) null,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	type_ nvarchar(75) null,
	mode_ nvarchar(75) null,
	language nvarchar(75) null,
	script nvarchar(max) null,
	cacheable bit,
	smallImage bit,
	smallImageId bigint,
	smallImageURL nvarchar(75) null
);

create table DLContent (
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	repositoryId bigint,
	path_ nvarchar(255) null,
	version nvarchar(75) null,
	data_ image,
	size_ bigint
);

create table DLFileEntry (
	uuid_ nvarchar(75) null,
	fileEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	repositoryId bigint,
	folderId bigint,
	treePath nvarchar(2000) null,
	name nvarchar(255) null,
	extension nvarchar(75) null,
	mimeType nvarchar(75) null,
	title nvarchar(255) null,
	description nvarchar(2000) null,
	extraSettings nvarchar(max) null,
	fileEntryTypeId bigint,
	version nvarchar(75) null,
	size_ bigint,
	readCount int,
	smallImageId bigint,
	largeImageId bigint,
	custom1ImageId bigint,
	custom2ImageId bigint,
	manualCheckInRequired bit
);

create table DLFileEntryMetadata (
	uuid_ nvarchar(75) null,
	fileEntryMetadataId bigint not null primary key,
	DDMStorageId bigint,
	DDMStructureId bigint,
	fileEntryTypeId bigint,
	fileEntryId bigint,
	fileVersionId bigint
);

create table DLFileEntryType (
	uuid_ nvarchar(75) null,
	fileEntryTypeId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	fileEntryTypeKey nvarchar(75) null,
	name nvarchar(2000) null,
	description nvarchar(2000) null
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
	createDate datetime null,
	fileEntryId bigint,
	active_ bit
);

create table DLFileShortcut (
	uuid_ nvarchar(75) null,
	fileShortcutId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	repositoryId bigint,
	folderId bigint,
	toFileEntryId bigint,
	treePath nvarchar(2000) null,
	active_ bit,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table DLFileVersion (
	uuid_ nvarchar(75) null,
	fileVersionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	repositoryId bigint,
	folderId bigint,
	fileEntryId bigint,
	treePath nvarchar(2000) null,
	extension nvarchar(75) null,
	mimeType nvarchar(75) null,
	title nvarchar(255) null,
	description nvarchar(2000) null,
	changeLog nvarchar(75) null,
	extraSettings nvarchar(max) null,
	fileEntryTypeId bigint,
	version nvarchar(75) null,
	size_ bigint,
	checksum nvarchar(75) null,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table DLFolder (
	uuid_ nvarchar(75) null,
	folderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	repositoryId bigint,
	mountPoint bit,
	parentFolderId bigint,
	treePath nvarchar(2000) null,
	name nvarchar(100) null,
	description nvarchar(2000) null,
	lastPostDate datetime null,
	defaultFileEntryTypeId bigint,
	hidden_ bit,
	overrideFileEntryTypes bit,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table DLSyncEvent (
	syncEventId bigint not null primary key,
	modifiedTime bigint,
	event nvarchar(75) null,
	type_ nvarchar(75) null,
	typePK bigint
);

create table EmailAddress (
	uuid_ nvarchar(75) null,
	emailAddressId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	address nvarchar(75) null,
	typeId int,
	primary_ bit
);

create table ExpandoColumn (
	columnId bigint not null primary key,
	companyId bigint,
	tableId bigint,
	name nvarchar(75) null,
	type_ int,
	defaultData nvarchar(2000) null,
	typeSettings nvarchar(max) null
);

create table ExpandoRow (
	rowId_ bigint not null primary key,
	companyId bigint,
	modifiedDate datetime null,
	tableId bigint,
	classPK bigint
);

create table ExpandoTable (
	tableId bigint not null primary key,
	companyId bigint,
	classNameId bigint,
	name nvarchar(75) null
);

create table ExpandoValue (
	valueId bigint not null primary key,
	companyId bigint,
	tableId bigint,
	columnId bigint,
	rowId_ bigint,
	classNameId bigint,
	classPK bigint,
	data_ nvarchar(2000) null
);

create table Group_ (
	uuid_ nvarchar(75) null,
	groupId bigint not null primary key,
	companyId bigint,
	creatorUserId bigint,
	classNameId bigint,
	classPK bigint,
	parentGroupId bigint,
	liveGroupId bigint,
	treePath nvarchar(2000) null,
	name nvarchar(150) null,
	description nvarchar(2000) null,
	type_ int,
	typeSettings nvarchar(max) null,
	manualMembership bit,
	membershipRestriction int,
	friendlyURL nvarchar(255) null,
	site bit,
	remoteStagingGroupCount int,
	active_ bit
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
	modifiedDate datetime null,
	type_ nvarchar(75) null,
	height int,
	width int,
	size_ int
);

create table JournalArticle (
	uuid_ nvarchar(75) null,
	id_ bigint not null primary key,
	resourcePrimKey bigint,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	folderId bigint,
	classNameId bigint,
	classPK bigint,
	treePath nvarchar(2000) null,
	articleId nvarchar(75) null,
	version float,
	title nvarchar(2000) null,
	urlTitle nvarchar(150) null,
	description nvarchar(max) null,
	content nvarchar(max) null,
	type_ nvarchar(75) null,
	structureId nvarchar(75) null,
	templateId nvarchar(75) null,
	layoutUuid nvarchar(75) null,
	displayDate datetime null,
	expirationDate datetime null,
	reviewDate datetime null,
	indexable bit,
	smallImage bit,
	smallImageId bigint,
	smallImageURL nvarchar(2000) null,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table JournalArticleImage (
	articleImageId bigint not null primary key,
	groupId bigint,
	articleId nvarchar(75) null,
	version float,
	elInstanceId nvarchar(75) null,
	elName nvarchar(75) null,
	languageId nvarchar(75) null,
	tempImage bit
);

create table JournalArticleResource (
	uuid_ nvarchar(75) null,
	resourcePrimKey bigint not null primary key,
	groupId bigint,
	articleId nvarchar(75) null
);

create table JournalContentSearch (
	contentSearchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	privateLayout bit,
	layoutId bigint,
	portletId nvarchar(200) null,
	articleId nvarchar(75) null
);

create table JournalFeed (
	uuid_ nvarchar(75) null,
	id_ bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	feedId nvarchar(75) null,
	name nvarchar(75) null,
	description nvarchar(2000) null,
	type_ nvarchar(75) null,
	structureId nvarchar(75) null,
	templateId nvarchar(75) null,
	rendererTemplateId nvarchar(75) null,
	delta int,
	orderByCol nvarchar(75) null,
	orderByType nvarchar(75) null,
	targetLayoutFriendlyUrl nvarchar(255) null,
	targetPortletId nvarchar(75) null,
	contentField nvarchar(75) null,
	feedFormat nvarchar(75) null,
	feedVersion float
);

create table JournalFolder (
	uuid_ nvarchar(75) null,
	folderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentFolderId bigint,
	treePath nvarchar(2000) null,
	name nvarchar(100) null,
	description nvarchar(2000) null,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table Layout (
	uuid_ nvarchar(75) null,
	plid bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	privateLayout bit,
	layoutId bigint,
	parentLayoutId bigint,
	name nvarchar(2000) null,
	title nvarchar(2000) null,
	description nvarchar(2000) null,
	keywords nvarchar(2000) null,
	robots nvarchar(2000) null,
	type_ nvarchar(75) null,
	typeSettings nvarchar(max) null,
	hidden_ bit,
	friendlyURL nvarchar(255) null,
	iconImage bit,
	iconImageId bigint,
	themeId nvarchar(75) null,
	colorSchemeId nvarchar(75) null,
	wapThemeId nvarchar(75) null,
	wapColorSchemeId nvarchar(75) null,
	css nvarchar(max) null,
	priority int,
	layoutPrototypeUuid nvarchar(75) null,
	layoutPrototypeLinkEnabled bit,
	sourcePrototypeLayoutUuid nvarchar(75) null
);

create table LayoutBranch (
	LayoutBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	layoutSetBranchId bigint,
	plid bigint,
	name nvarchar(75) null,
	description nvarchar(2000) null,
	master bit
);

create table LayoutFriendlyURL (
	uuid_ nvarchar(75) null,
	layoutFriendlyURLId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	plid bigint,
	privateLayout bit,
	friendlyURL nvarchar(255) null,
	languageId nvarchar(75) null
);

create table LayoutPrototype (
	uuid_ nvarchar(75) null,
	layoutPrototypeId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	settings_ nvarchar(2000) null,
	active_ bit
);

create table LayoutRevision (
	layoutRevisionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	layoutSetBranchId bigint,
	layoutBranchId bigint,
	parentLayoutRevisionId bigint,
	head bit,
	major bit,
	plid bigint,
	privateLayout bit,
	name nvarchar(2000) null,
	title nvarchar(2000) null,
	description nvarchar(2000) null,
	keywords nvarchar(2000) null,
	robots nvarchar(2000) null,
	typeSettings nvarchar(max) null,
	iconImage bit,
	iconImageId bigint,
	themeId nvarchar(75) null,
	colorSchemeId nvarchar(75) null,
	wapThemeId nvarchar(75) null,
	wapColorSchemeId nvarchar(75) null,
	css nvarchar(max) null,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table LayoutSet (
	layoutSetId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	createDate datetime null,
	modifiedDate datetime null,
	privateLayout bit,
	logo bit,
	logoId bigint,
	themeId nvarchar(75) null,
	colorSchemeId nvarchar(75) null,
	wapThemeId nvarchar(75) null,
	wapColorSchemeId nvarchar(75) null,
	css nvarchar(max) null,
	pageCount int,
	settings_ nvarchar(max) null,
	layoutSetPrototypeUuid nvarchar(75) null,
	layoutSetPrototypeLinkEnabled bit
);

create table LayoutSetBranch (
	layoutSetBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	privateLayout bit,
	name nvarchar(75) null,
	description nvarchar(2000) null,
	master bit,
	logo bit,
	logoId bigint,
	themeId nvarchar(75) null,
	colorSchemeId nvarchar(75) null,
	wapThemeId nvarchar(75) null,
	wapColorSchemeId nvarchar(75) null,
	css nvarchar(max) null,
	settings_ nvarchar(max) null,
	layoutSetPrototypeUuid nvarchar(75) null,
	layoutSetPrototypeLinkEnabled bit
);

create table LayoutSetPrototype (
	uuid_ nvarchar(75) null,
	layoutSetPrototypeId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	settings_ nvarchar(2000) null,
	active_ bit
);

create table ListType (
	listTypeId int not null primary key,
	name nvarchar(75) null,
	type_ nvarchar(75) null
);

create table Lock_ (
	uuid_ nvarchar(75) null,
	lockId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	className nvarchar(75) null,
	key_ nvarchar(200) null,
	owner nvarchar(255) null,
	inheritable bit,
	expirationDate datetime null
);

create table MBBan (
	uuid_ nvarchar(75) null,
	banId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	banUserId bigint
);

create table MBCategory (
	uuid_ nvarchar(75) null,
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentCategoryId bigint,
	name nvarchar(75) null,
	description nvarchar(2000) null,
	displayStyle nvarchar(75) null,
	threadCount int,
	messageCount int,
	lastPostDate datetime null,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table MBDiscussion (
	uuid_ nvarchar(75) null,
	discussionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	threadId bigint
);

create table MBMailingList (
	uuid_ nvarchar(75) null,
	mailingListId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId bigint,
	emailAddress nvarchar(75) null,
	inProtocol nvarchar(75) null,
	inServerName nvarchar(75) null,
	inServerPort int,
	inUseSSL bit,
	inUserName nvarchar(75) null,
	inPassword nvarchar(75) null,
	inReadInterval int,
	outEmailAddress nvarchar(75) null,
	outCustom bit,
	outServerName nvarchar(75) null,
	outServerPort int,
	outUseSSL bit,
	outUserName nvarchar(75) null,
	outPassword nvarchar(75) null,
	allowAnonymous bit,
	active_ bit
);

create table MBMessage (
	uuid_ nvarchar(75) null,
	messageId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	categoryId bigint,
	threadId bigint,
	rootMessageId bigint,
	parentMessageId bigint,
	subject nvarchar(75) null,
	body nvarchar(max) null,
	format nvarchar(75) null,
	anonymous bit,
	priority float,
	allowPingbacks bit,
	answer bit,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table MBStatsUser (
	statsUserId bigint not null primary key,
	groupId bigint,
	userId bigint,
	messageCount int,
	lastPostDate datetime null
);

create table MBThread (
	uuid_ nvarchar(75) null,
	threadId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId bigint,
	rootMessageId bigint,
	rootMessageUserId bigint,
	messageCount int,
	viewCount int,
	lastPostByUserId bigint,
	lastPostDate datetime null,
	priority float,
	question bit,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table MBThreadFlag (
	uuid_ nvarchar(75) null,
	threadFlagId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	threadId bigint
);

create table MDRAction (
	uuid_ nvarchar(75) null,
	actionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	ruleGroupInstanceId bigint,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	type_ nvarchar(255) null,
	typeSettings nvarchar(max) null
);

create table MDRRule (
	uuid_ nvarchar(75) null,
	ruleId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	ruleGroupId bigint,
	name nvarchar(2000) null,
	description nvarchar(2000) null,
	type_ nvarchar(255) null,
	typeSettings nvarchar(max) null
);

create table MDRRuleGroup (
	uuid_ nvarchar(75) null,
	ruleGroupId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(2000) null,
	description nvarchar(2000) null
);

create table MDRRuleGroupInstance (
	uuid_ nvarchar(75) null,
	ruleGroupInstanceId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	ruleGroupId bigint,
	priority int
);

create table MembershipRequest (
	membershipRequestId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate datetime null,
	comments nvarchar(2000) null,
	replyComments nvarchar(2000) null,
	replyDate datetime null,
	replierUserId bigint,
	statusId int
);

create table Organization_ (
	uuid_ nvarchar(75) null,
	organizationId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentOrganizationId bigint,
	treePath nvarchar(2000) null,
	name nvarchar(100) null,
	type_ nvarchar(75) null,
	recursable bit,
	regionId bigint,
	countryId bigint,
	statusId int,
	comments nvarchar(2000) null
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
);

create table PasswordPolicy (
	uuid_ nvarchar(75) null,
	passwordPolicyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	defaultPolicy bit,
	name nvarchar(75) null,
	description nvarchar(2000) null,
	changeable bit,
	changeRequired bit,
	minAge bigint,
	checkSyntax bit,
	allowDictionaryWords bit,
	minAlphanumeric int,
	minLength int,
	minLowerCase int,
	minNumbers int,
	minSymbols int,
	minUpperCase int,
	regex nvarchar(75) null,
	history bit,
	historyCount int,
	expireable bit,
	maxAge bigint,
	warningTime bigint,
	graceLimit int,
	lockout bit,
	maxFailure int,
	lockoutDuration bigint,
	requireUnlock bit,
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
	createDate datetime null,
	password_ nvarchar(75) null
);

create table Phone (
	uuid_ nvarchar(75) null,
	phoneId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	number_ nvarchar(75) null,
	extension nvarchar(75) null,
	typeId int,
	primary_ bit
);

create table PluginSetting (
	pluginSettingId bigint not null primary key,
	companyId bigint,
	pluginId nvarchar(75) null,
	pluginType nvarchar(75) null,
	roles nvarchar(2000) null,
	active_ bit
);

create table PollsChoice (
	uuid_ nvarchar(75) null,
	choiceId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	questionId bigint,
	name nvarchar(75) null,
	description nvarchar(2000) null
);

create table PollsQuestion (
	uuid_ nvarchar(75) null,
	questionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	title nvarchar(2000) null,
	description nvarchar(2000) null,
	expirationDate datetime null,
	lastVoteDate datetime null
);

create table PollsVote (
	uuid_ nvarchar(75) null,
	voteId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	questionId bigint,
	choiceId bigint,
	voteDate datetime null
);

create table PortalPreferences (
	portalPreferencesId bigint not null primary key,
	ownerId bigint,
	ownerType int,
	preferences nvarchar(max) null
);

create table Portlet (
	id_ bigint not null primary key,
	companyId bigint,
	portletId nvarchar(200) null,
	roles nvarchar(2000) null,
	active_ bit
);

create table PortletItem (
	portletItemId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(75) null,
	portletId nvarchar(200) null,
	classNameId bigint
);

create table PortletPreferences (
	portletPreferencesId bigint not null primary key,
	ownerId bigint,
	ownerType int,
	plid bigint,
	portletId nvarchar(200) null,
	preferences nvarchar(max) null
);

create table RatingsEntry (
	entryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	score float
);

create table RatingsStats (
	statsId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	totalEntries int,
	totalScore float,
	averageScore float
);

create table Region (
	regionId bigint not null primary key,
	countryId bigint,
	regionCode nvarchar(75) null,
	name nvarchar(75) null,
	active_ bit
);

create table Release_ (
	releaseId bigint not null primary key,
	createDate datetime null,
	modifiedDate datetime null,
	servletContextName nvarchar(75) null,
	buildNumber int,
	buildDate datetime null,
	verified bit,
	state_ int,
	testString nvarchar(1024) null
);

create table Repository (
	uuid_ nvarchar(75) null,
	repositoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	name nvarchar(75) null,
	description nvarchar(2000) null,
	portletId nvarchar(200) null,
	typeSettings nvarchar(max) null,
	dlFolderId bigint
);

create table RepositoryEntry (
	uuid_ nvarchar(75) null,
	repositoryEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	repositoryId bigint,
	mappedId nvarchar(75) null,
	manualCheckInRequired bit
);

create table ResourceAction (
	resourceActionId bigint not null primary key,
	name nvarchar(255) null,
	actionId nvarchar(75) null,
	bitwiseValue bigint
);

create table ResourceBlock (
	resourceBlockId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	name nvarchar(75) null,
	permissionsHash nvarchar(75) null,
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
	name nvarchar(255) null,
	scope int,
	primKey nvarchar(255) null,
	roleId bigint,
	ownerId bigint,
	actionIds bigint
);

create table ResourceTypePermission (
	resourceTypePermissionId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	name nvarchar(75) null,
	roleId bigint,
	actionIds bigint
);

create table Role_ (
	uuid_ nvarchar(75) null,
	roleId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	name nvarchar(75) null,
	title nvarchar(2000) null,
	description nvarchar(2000) null,
	type_ int,
	subtype nvarchar(75) null
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
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(75) null,
	url nvarchar(2000) null,
	active_ bit,
	priority int
);

create table SCLicense (
	licenseId bigint not null primary key,
	name nvarchar(75) null,
	url nvarchar(2000) null,
	openSource bit,
	active_ bit,
	recommended bit
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
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(75) null,
	type_ nvarchar(75) null,
	tags nvarchar(255) null,
	shortDescription nvarchar(2000) null,
	longDescription nvarchar(2000) null,
	pageURL nvarchar(2000) null,
	author nvarchar(75) null,
	repoGroupId nvarchar(75) null,
	repoArtifactId nvarchar(75) null
);

create table SCProductScreenshot (
	productScreenshotId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	productEntryId bigint,
	thumbnailId bigint,
	fullImageId bigint,
	priority int
);

create table SCProductVersion (
	productVersionId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	productEntryId bigint,
	version nvarchar(75) null,
	changeLog nvarchar(2000) null,
	downloadPageURL nvarchar(2000) null,
	directDownloadURL nvarchar(2000) null,
	repoStoreArtifact bit
);

create table ServiceComponent (
	serviceComponentId bigint not null primary key,
	buildNamespace nvarchar(75) null,
	buildNumber bigint,
	buildDate bigint,
	data_ nvarchar(max) null
);

create table Shard (
	shardId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	name nvarchar(75) null
);

create table ShoppingCart (
	cartId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	itemIds nvarchar(2000) null,
	couponCodes nvarchar(75) null,
	altShipping int,
	insure bit
);

create table ShoppingCategory (
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentCategoryId bigint,
	name nvarchar(75) null,
	description nvarchar(2000) null
);

create table ShoppingCoupon (
	couponId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	code_ nvarchar(75) null,
	name nvarchar(75) null,
	description nvarchar(2000) null,
	startDate datetime null,
	endDate datetime null,
	active_ bit,
	limitCategories nvarchar(2000) null,
	limitSkus nvarchar(2000) null,
	minOrder float,
	discount float,
	discountType nvarchar(75) null
);

create table ShoppingItem (
	itemId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId bigint,
	sku nvarchar(75) null,
	name nvarchar(200) null,
	description nvarchar(2000) null,
	properties nvarchar(2000) null,
	fields_ bit,
	fieldsQuantities nvarchar(2000) null,
	minQuantity int,
	maxQuantity int,
	price float,
	discount float,
	taxable bit,
	shipping float,
	useShippingFormula bit,
	requiresShipping bit,
	stockQuantity int,
	featured_ bit,
	sale_ bit,
	smallImage bit,
	smallImageId bigint,
	smallImageURL nvarchar(2000) null,
	mediumImage bit,
	mediumImageId bigint,
	mediumImageURL nvarchar(2000) null,
	largeImage bit,
	largeImageId bigint,
	largeImageURL nvarchar(2000) null
);

create table ShoppingItemField (
	itemFieldId bigint not null primary key,
	itemId bigint,
	name nvarchar(75) null,
	values_ nvarchar(2000) null,
	description nvarchar(2000) null
);

create table ShoppingItemPrice (
	itemPriceId bigint not null primary key,
	itemId bigint,
	minQuantity int,
	maxQuantity int,
	price float,
	discount float,
	taxable bit,
	shipping float,
	useShippingFormula bit,
	status int
);

create table ShoppingOrder (
	orderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	number_ nvarchar(75) null,
	tax float,
	shipping float,
	altShipping nvarchar(75) null,
	requiresShipping bit,
	insure bit,
	insurance float,
	couponCodes nvarchar(75) null,
	couponDiscount float,
	billingFirstName nvarchar(75) null,
	billingLastName nvarchar(75) null,
	billingEmailAddress nvarchar(75) null,
	billingCompany nvarchar(75) null,
	billingStreet nvarchar(75) null,
	billingCity nvarchar(75) null,
	billingState nvarchar(75) null,
	billingZip nvarchar(75) null,
	billingCountry nvarchar(75) null,
	billingPhone nvarchar(75) null,
	shipToBilling bit,
	shippingFirstName nvarchar(75) null,
	shippingLastName nvarchar(75) null,
	shippingEmailAddress nvarchar(75) null,
	shippingCompany nvarchar(75) null,
	shippingStreet nvarchar(75) null,
	shippingCity nvarchar(75) null,
	shippingState nvarchar(75) null,
	shippingZip nvarchar(75) null,
	shippingCountry nvarchar(75) null,
	shippingPhone nvarchar(75) null,
	ccName nvarchar(75) null,
	ccType nvarchar(75) null,
	ccNumber nvarchar(75) null,
	ccExpMonth int,
	ccExpYear int,
	ccVerNumber nvarchar(75) null,
	comments nvarchar(2000) null,
	ppTxnId nvarchar(75) null,
	ppPaymentStatus nvarchar(75) null,
	ppPaymentGross float,
	ppReceiverEmail nvarchar(75) null,
	ppPayerEmail nvarchar(75) null,
	sendOrderEmail bit,
	sendShippingEmail bit
);

create table ShoppingOrderItem (
	orderItemId bigint not null primary key,
	orderId bigint,
	itemId nvarchar(75) null,
	sku nvarchar(75) null,
	name nvarchar(200) null,
	description nvarchar(2000) null,
	properties nvarchar(2000) null,
	price float,
	quantity int,
	shippedDate datetime null
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
	type_ int,
	extraData nvarchar(2000) null,
	receiverUserId bigint
);

create table SocialActivityAchievement (
	activityAchievementId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate bigint,
	name nvarchar(75) null,
	firstInGroup bit
);

create table SocialActivityCounter (
	activityCounterId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	classPK bigint,
	name nvarchar(75) null,
	ownerType int,
	currentValue int,
	totalValue int,
	graceValue int,
	startPeriod int,
	endPeriod int,
	active_ bit
);

create table SocialActivityLimit (
	activityLimitId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	classNameId bigint,
	classPK bigint,
	activityType int,
	activityCounterName nvarchar(75) null,
	value nvarchar(75) null
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
	type_ int,
	extraData nvarchar(2000) null,
	activityCount int
);

create table SocialActivitySetting (
	activitySettingId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	activityType int,
	name nvarchar(75) null,
	value nvarchar(1024) null
);

create table SocialRelation (
	uuid_ nvarchar(75) null,
	relationId bigint not null primary key,
	companyId bigint,
	createDate bigint,
	userId1 bigint,
	userId2 bigint,
	type_ int
);

create table SocialRequest (
	uuid_ nvarchar(75) null,
	requestId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate bigint,
	modifiedDate bigint,
	classNameId bigint,
	classPK bigint,
	type_ int,
	extraData nvarchar(2000) null,
	receiverUserId bigint,
	status int
);

create table Subscription (
	subscriptionId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	frequency nvarchar(75) null
);

create table SystemEvent (
	systemEventId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	classNameId bigint,
	classPK bigint,
	classUuid nvarchar(75) null,
	referrerClassNameId bigint,
	parentSystemEventId bigint,
	systemEventSetKey bigint,
	type_ int,
	extraData nvarchar(max) null
);

create table Team (
	teamId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	groupId bigint,
	name nvarchar(75) null,
	description nvarchar(2000) null
);

create table Ticket (
	ticketId bigint not null primary key,
	companyId bigint,
	createDate datetime null,
	classNameId bigint,
	classPK bigint,
	key_ nvarchar(75) null,
	type_ int,
	extraInfo nvarchar(max) null,
	expirationDate datetime null
);

create table TrashEntry (
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	classNameId bigint,
	classPK bigint,
	systemEventSetKey bigint,
	typeSettings nvarchar(max) null,
	status int
);

create table TrashVersion (
	versionId bigint not null primary key,
	entryId bigint,
	classNameId bigint,
	classPK bigint,
	typeSettings nvarchar(max) null,
	status int
);

create table UserNotificationDelivery (
	userNotificationDeliveryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	portletId nvarchar(200) null,
	classNameId bigint,
	notificationType int,
	deliveryType int,
	deliver bit
);

create table User_ (
	uuid_ nvarchar(75) null,
	userId bigint not null primary key,
	companyId bigint,
	createDate datetime null,
	modifiedDate datetime null,
	defaultUser bit,
	contactId bigint,
	password_ nvarchar(75) null,
	passwordEncrypted bit,
	passwordReset bit,
	passwordModifiedDate datetime null,
	digest nvarchar(255) null,
	reminderQueryQuestion nvarchar(75) null,
	reminderQueryAnswer nvarchar(75) null,
	graceLoginCount int,
	screenName nvarchar(75) null,
	emailAddress nvarchar(75) null,
	facebookId bigint,
	ldapServerId bigint,
	openId nvarchar(1024) null,
	portraitId bigint,
	languageId nvarchar(75) null,
	timeZoneId nvarchar(75) null,
	greeting nvarchar(255) null,
	comments nvarchar(2000) null,
	firstName nvarchar(75) null,
	middleName nvarchar(75) null,
	lastName nvarchar(75) null,
	jobTitle nvarchar(100) null,
	loginDate datetime null,
	loginIP nvarchar(75) null,
	lastLoginDate datetime null,
	lastLoginIP nvarchar(75) null,
	lastFailedLoginDate datetime null,
	failedLoginAttempts int,
	lockout bit,
	lockoutDate datetime null,
	agreedToTermsOfUse bit,
	emailAddressVerified bit,
	status int
);

create table UserGroup (
	uuid_ nvarchar(75) null,
	userGroupId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentUserGroupId bigint,
	name nvarchar(75) null,
	description nvarchar(2000) null,
	addedByLDAPImport bit
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
	type_ nvarchar(75) null,
	description nvarchar(75) null,
	externalUserId nvarchar(75) null
);

create table UserNotificationEvent (
	uuid_ nvarchar(75) null,
	userNotificationEventId bigint not null primary key,
	companyId bigint,
	userId bigint,
	type_ nvarchar(75) null,
	timestamp bigint,
	deliverBy bigint,
	delivered bit,
	payload nvarchar(max) null,
	archived bit
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
	modifiedDate datetime null,
	sessionId nvarchar(200) null,
	remoteAddr nvarchar(75) null,
	remoteHost nvarchar(75) null,
	userAgent nvarchar(200) null
);

create table UserTrackerPath (
	userTrackerPathId bigint not null primary key,
	userTrackerId bigint,
	path_ nvarchar(2000) null,
	pathDate datetime null
);

create table VirtualHost (
	virtualHostId bigint not null primary key,
	companyId bigint,
	layoutSetId bigint,
	hostname nvarchar(75) null
);

create table WebDAVProps (
	webDavPropsId bigint not null primary key,
	companyId bigint,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	props nvarchar(max) null
);

create table Website (
	uuid_ nvarchar(75) null,
	websiteId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	url nvarchar(2000) null,
	typeId int,
	primary_ bit
);

create table WikiNode (
	uuid_ nvarchar(75) null,
	nodeId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(75) null,
	description nvarchar(2000) null,
	lastPostDate datetime null,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table WikiPage (
	uuid_ nvarchar(75) null,
	pageId bigint not null primary key,
	resourcePrimKey bigint,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	nodeId bigint,
	title nvarchar(255) null,
	version float,
	minorEdit bit,
	content nvarchar(max) null,
	summary nvarchar(2000) null,
	format nvarchar(75) null,
	head bit,
	parentTitle nvarchar(255) null,
	redirectTitle nvarchar(255) null,
	status int,
	statusByUserId bigint,
	statusByUserName nvarchar(75) null,
	statusDate datetime null
);

create table WikiPageResource (
	uuid_ nvarchar(75) null,
	resourcePrimKey bigint not null primary key,
	nodeId bigint,
	title nvarchar(255) null
);

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	typePK bigint,
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

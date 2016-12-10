create table Account_ (
	accountId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	parentAccountId bigint,
	name varchar(75) null,
	legalName varchar(75) null,
	legalId varchar(75) null,
	legalType varchar(75) null,
	sicCode varchar(75) null,
	tickerSymbol varchar(75) null,
	industry varchar(75) null,
	type_ varchar(75) null,
	size_ varchar(75) null
);

create table Address (
	uuid_ varchar(75) null,
	addressId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	street1 varchar(75) null,
	street2 varchar(75) null,
	street3 varchar(75) null,
	city varchar(75) null,
	zip varchar(75) null,
	regionId bigint,
	countryId bigint,
	typeId integer,
	mailing boolean,
	primary_ boolean
);

create table AnnouncementsDelivery (
	deliveryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	type_ varchar(75) null,
	email boolean,
	sms boolean,
	website boolean
);

create table AnnouncementsEntry (
	uuid_ varchar(75) null,
	entryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	title varchar(75) null,
	content long varchar null,
	url long varchar null,
	type_ varchar(75) null,
	displayDate date null,
	expirationDate date null,
	priority integer,
	alert boolean
);

create table AnnouncementsFlag (
	flagId bigint not null primary key,
	userId bigint,
	createDate date null,
	entryId bigint,
	value integer
);

create table AssetCategory (
	uuid_ varchar(75) null,
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	parentCategoryId bigint,
	leftCategoryId bigint,
	rightCategoryId bigint,
	name varchar(75) null,
	title long varchar null,
	description long varchar null,
	vocabularyId bigint
);

create table AssetCategoryProperty (
	categoryPropertyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	categoryId bigint,
	key_ varchar(75) null,
	value varchar(75) null
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
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	classUuid varchar(75) null,
	classTypeId bigint,
	visible boolean,
	startDate date null,
	endDate date null,
	publishDate date null,
	expirationDate date null,
	mimeType varchar(75) null,
	title long varchar null,
	description long varchar null,
	summary long varchar null,
	url long varchar null,
	layoutUuid varchar(75) null,
	height integer,
	width integer,
	priority double,
	viewCount integer
);

create table AssetLink (
	linkId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
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
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	name varchar(75) null,
	assetCount integer
);

create table AssetTagProperty (
	tagPropertyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	tagId bigint,
	key_ varchar(75) null,
	value varchar(255) null
);

create table AssetTagStats (
	tagStatsId bigint not null primary key,
	tagId bigint,
	classNameId bigint,
	assetCount integer
);

create table AssetVocabulary (
	uuid_ varchar(75) null,
	vocabularyId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	name varchar(75) null,
	title long varchar null,
	description long varchar null,
	settings_ long varchar null
);

create table BackgroundTask (
	backgroundTaskId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	name varchar(75) null,
	servletContextNames varchar(255) null,
	taskExecutorClassName varchar(200) null,
	taskContext long varchar null,
	completed boolean,
	completionDate date null,
	status integer,
	statusMessage long varchar null
);

create table BlogsEntry (
	uuid_ varchar(75) null,
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	title varchar(150) null,
	urlTitle varchar(150) null,
	description long varchar null,
	content long varchar null,
	displayDate date null,
	allowPingbacks boolean,
	allowTrackbacks boolean,
	trackbacks long varchar null,
	smallImage boolean,
	smallImageId bigint,
	smallImageURL long varchar null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table BlogsStatsUser (
	statsUserId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	entryCount integer,
	lastPostDate date null,
	ratingsTotalEntries integer,
	ratingsTotalScore double,
	ratingsAverageScore double
);

create table BookmarksEntry (
	uuid_ varchar(75) null,
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	resourceBlockId bigint,
	folderId bigint,
	treePath long varchar null,
	name varchar(255) null,
	url long varchar null,
	description long varchar null,
	visits integer,
	priority integer,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table BookmarksFolder (
	uuid_ varchar(75) null,
	folderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	resourceBlockId bigint,
	parentFolderId bigint,
	treePath long varchar null,
	name varchar(75) null,
	description long varchar null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table BrowserTracker (
	browserTrackerId bigint not null primary key,
	userId bigint,
	browserKey bigint
);

create table CalEvent (
	uuid_ varchar(75) null,
	eventId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	title varchar(75) null,
	description long varchar null,
	location long varchar null,
	startDate date null,
	endDate date null,
	durationHour integer,
	durationMinute integer,
	allDay boolean,
	timeZoneSensitive boolean,
	type_ varchar(75) null,
	repeating boolean,
	recurrence long varchar null,
	remindBy integer,
	firstReminder integer,
	secondReminder integer
);

create table ClassName_ (
	classNameId bigint not null primary key,
	value varchar(200) null
);

create table ClusterGroup (
	clusterGroupId bigint not null primary key,
	name varchar(75) null,
	clusterNodeIds varchar(75) null,
	wholeCluster boolean
);

create table Company (
	companyId bigint not null primary key,
	accountId bigint,
	webId varchar(75) null,
	key_ long varchar null,
	mx varchar(75) null,
	homeURL long varchar null,
	logoId bigint,
	system boolean,
	maxUsers integer,
	active_ boolean
);

create table Contact_ (
	contactId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	accountId bigint,
	parentContactId bigint,
	emailAddress varchar(75) null,
	firstName varchar(75) null,
	middleName varchar(75) null,
	lastName varchar(75) null,
	prefixId integer,
	suffixId integer,
	male boolean,
	birthday date null,
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
);

create table Counter (
	name varchar(75) not null primary key,
	currentId bigint
);

create table Country (
	countryId bigint not null primary key,
	name varchar(75) null,
	a2 varchar(75) null,
	a3 varchar(75) null,
	number_ varchar(75) null,
	idd_ varchar(75) null,
	zipRequired boolean,
	active_ boolean
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
	uuid_ varchar(75) null,
	recordId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	versionUserId bigint,
	versionUserName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	DDMStorageId bigint,
	recordSetId bigint,
	version varchar(75) null,
	displayIndex integer
);

create table DDLRecordSet (
	uuid_ varchar(75) null,
	recordSetId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	DDMStructureId bigint,
	recordSetKey varchar(75) null,
	name long varchar null,
	description long varchar null,
	minDisplayRows integer,
	scope integer
);

create table DDLRecordVersion (
	recordVersionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	DDMStorageId bigint,
	recordSetId bigint,
	recordId bigint,
	version varchar(75) null,
	displayIndex integer,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table DDMContent (
	uuid_ varchar(75) null,
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	name long varchar null,
	description long varchar null,
	xml long varchar null
);

create table DDMStorageLink (
	uuid_ varchar(75) null,
	storageLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
);

create table DDMStructure (
	uuid_ varchar(75) null,
	structureId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	parentStructureId bigint,
	classNameId bigint,
	structureKey varchar(75) null,
	name long varchar null,
	description long varchar null,
	xsd long varchar null,
	storageType varchar(75) null,
	type_ integer
);

create table DDMStructureLink (
	structureLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
);

create table DDMTemplate (
	uuid_ varchar(75) null,
	templateId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	templateKey varchar(75) null,
	name long varchar null,
	description long varchar null,
	type_ varchar(75) null,
	mode_ varchar(75) null,
	language varchar(75) null,
	script long varchar null,
	cacheable boolean,
	smallImage boolean,
	smallImageId bigint,
	smallImageURL varchar(75) null
);

create table DLContent (
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	repositoryId bigint,
	path_ varchar(255) null,
	version varchar(75) null,
	data_ binary,
	size_ bigint
);

create table DLFileEntry (
	uuid_ varchar(75) null,
	fileEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	repositoryId bigint,
	folderId bigint,
	treePath long varchar null,
	name varchar(255) null,
	extension varchar(75) null,
	mimeType varchar(75) null,
	title varchar(255) null,
	description long varchar null,
	extraSettings long varchar null,
	fileEntryTypeId bigint,
	version varchar(75) null,
	size_ bigint,
	readCount integer,
	smallImageId bigint,
	largeImageId bigint,
	custom1ImageId bigint,
	custom2ImageId bigint,
	manualCheckInRequired boolean
);

create table DLFileEntryMetadata (
	uuid_ varchar(75) null,
	fileEntryMetadataId bigint not null primary key,
	DDMStorageId bigint,
	DDMStructureId bigint,
	fileEntryTypeId bigint,
	fileEntryId bigint,
	fileVersionId bigint
);

create table DLFileEntryType (
	uuid_ varchar(75) null,
	fileEntryTypeId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	fileEntryTypeKey varchar(75) null,
	name long varchar null,
	description long varchar null
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
	createDate date null,
	fileEntryId bigint,
	active_ boolean
);

create table DLFileShortcut (
	uuid_ varchar(75) null,
	fileShortcutId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	repositoryId bigint,
	folderId bigint,
	toFileEntryId bigint,
	treePath long varchar null,
	active_ boolean,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table DLFileVersion (
	uuid_ varchar(75) null,
	fileVersionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	repositoryId bigint,
	folderId bigint,
	fileEntryId bigint,
	treePath long varchar null,
	extension varchar(75) null,
	mimeType varchar(75) null,
	title varchar(255) null,
	description long varchar null,
	changeLog varchar(75) null,
	extraSettings long varchar null,
	fileEntryTypeId bigint,
	version varchar(75) null,
	size_ bigint,
	checksum varchar(75) null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table DLFolder (
	uuid_ varchar(75) null,
	folderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	repositoryId bigint,
	mountPoint boolean,
	parentFolderId bigint,
	treePath long varchar null,
	name varchar(100) null,
	description long varchar null,
	lastPostDate date null,
	defaultFileEntryTypeId bigint,
	hidden_ boolean,
	overrideFileEntryTypes boolean,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table DLSyncEvent (
	syncEventId bigint not null primary key,
	modifiedTime bigint,
	event varchar(75) null,
	type_ varchar(75) null,
	typePK bigint
);

create table EmailAddress (
	uuid_ varchar(75) null,
	emailAddressId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	address varchar(75) null,
	typeId integer,
	primary_ boolean
);

create table ExpandoColumn (
	columnId bigint not null primary key,
	companyId bigint,
	tableId bigint,
	name varchar(75) null,
	type_ integer,
	defaultData long varchar null,
	typeSettings long varchar null
);

create table ExpandoRow (
	rowId_ bigint not null primary key,
	companyId bigint,
	modifiedDate date null,
	tableId bigint,
	classPK bigint
);

create table ExpandoTable (
	tableId bigint not null primary key,
	companyId bigint,
	classNameId bigint,
	name varchar(75) null
);

create table ExpandoValue (
	valueId bigint not null primary key,
	companyId bigint,
	tableId bigint,
	columnId bigint,
	rowId_ bigint,
	classNameId bigint,
	classPK bigint,
	data_ long varchar null
);

create table Group_ (
	uuid_ varchar(75) null,
	groupId bigint not null primary key,
	companyId bigint,
	creatorUserId bigint,
	classNameId bigint,
	classPK bigint,
	parentGroupId bigint,
	liveGroupId bigint,
	treePath long varchar null,
	name varchar(150) null,
	description long varchar null,
	type_ integer,
	typeSettings long varchar null,
	manualMembership boolean,
	membershipRestriction integer,
	friendlyURL varchar(255) null,
	site boolean,
	remoteStagingGroupCount integer,
	active_ boolean
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
	modifiedDate date null,
	type_ varchar(75) null,
	height integer,
	width integer,
	size_ integer
);

create table JournalArticle (
	uuid_ varchar(75) null,
	id_ bigint not null primary key,
	resourcePrimKey bigint,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	folderId bigint,
	classNameId bigint,
	classPK bigint,
	treePath long varchar null,
	articleId varchar(75) null,
	version double,
	title long varchar null,
	urlTitle varchar(150) null,
	description long varchar null,
	content long varchar null,
	type_ varchar(75) null,
	structureId varchar(75) null,
	templateId varchar(75) null,
	layoutUuid varchar(75) null,
	displayDate date null,
	expirationDate date null,
	reviewDate date null,
	indexable boolean,
	smallImage boolean,
	smallImageId bigint,
	smallImageURL long varchar null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table JournalArticleImage (
	articleImageId bigint not null primary key,
	groupId bigint,
	articleId varchar(75) null,
	version double,
	elInstanceId varchar(75) null,
	elName varchar(75) null,
	languageId varchar(75) null,
	tempImage boolean
);

create table JournalArticleResource (
	uuid_ varchar(75) null,
	resourcePrimKey bigint not null primary key,
	groupId bigint,
	articleId varchar(75) null
);

create table JournalContentSearch (
	contentSearchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	privateLayout boolean,
	layoutId bigint,
	portletId varchar(200) null,
	articleId varchar(75) null
);

create table JournalFeed (
	uuid_ varchar(75) null,
	id_ bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	feedId varchar(75) null,
	name varchar(75) null,
	description long varchar null,
	type_ varchar(75) null,
	structureId varchar(75) null,
	templateId varchar(75) null,
	rendererTemplateId varchar(75) null,
	delta integer,
	orderByCol varchar(75) null,
	orderByType varchar(75) null,
	targetLayoutFriendlyUrl varchar(255) null,
	targetPortletId varchar(75) null,
	contentField varchar(75) null,
	feedFormat varchar(75) null,
	feedVersion double
);

create table JournalFolder (
	uuid_ varchar(75) null,
	folderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	parentFolderId bigint,
	treePath long varchar null,
	name varchar(100) null,
	description long varchar null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table Layout (
	uuid_ varchar(75) null,
	plid bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	privateLayout boolean,
	layoutId bigint,
	parentLayoutId bigint,
	name long varchar null,
	title long varchar null,
	description long varchar null,
	keywords long varchar null,
	robots long varchar null,
	type_ varchar(75) null,
	typeSettings long varchar null,
	hidden_ boolean,
	friendlyURL varchar(255) null,
	iconImage boolean,
	iconImageId bigint,
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css long varchar null,
	priority integer,
	layoutPrototypeUuid varchar(75) null,
	layoutPrototypeLinkEnabled boolean,
	sourcePrototypeLayoutUuid varchar(75) null
);

create table LayoutBranch (
	LayoutBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	layoutSetBranchId bigint,
	plid bigint,
	name varchar(75) null,
	description long varchar null,
	master boolean
);

create table LayoutFriendlyURL (
	uuid_ varchar(75) null,
	layoutFriendlyURLId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	plid bigint,
	privateLayout boolean,
	friendlyURL varchar(255) null,
	languageId varchar(75) null
);

create table LayoutPrototype (
	uuid_ varchar(75) null,
	layoutPrototypeId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	name long varchar null,
	description long varchar null,
	settings_ long varchar null,
	active_ boolean
);

create table LayoutRevision (
	layoutRevisionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	layoutSetBranchId bigint,
	layoutBranchId bigint,
	parentLayoutRevisionId bigint,
	head boolean,
	major boolean,
	plid bigint,
	privateLayout boolean,
	name long varchar null,
	title long varchar null,
	description long varchar null,
	keywords long varchar null,
	robots long varchar null,
	typeSettings long varchar null,
	iconImage boolean,
	iconImageId bigint,
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css long varchar null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table LayoutSet (
	layoutSetId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	createDate date null,
	modifiedDate date null,
	privateLayout boolean,
	logo boolean,
	logoId bigint,
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css long varchar null,
	pageCount integer,
	settings_ long varchar null,
	layoutSetPrototypeUuid varchar(75) null,
	layoutSetPrototypeLinkEnabled boolean
);

create table LayoutSetBranch (
	layoutSetBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	privateLayout boolean,
	name varchar(75) null,
	description long varchar null,
	master boolean,
	logo boolean,
	logoId bigint,
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css long varchar null,
	settings_ long varchar null,
	layoutSetPrototypeUuid varchar(75) null,
	layoutSetPrototypeLinkEnabled boolean
);

create table LayoutSetPrototype (
	uuid_ varchar(75) null,
	layoutSetPrototypeId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	name long varchar null,
	description long varchar null,
	settings_ long varchar null,
	active_ boolean
);

create table ListType (
	listTypeId integer not null primary key,
	name varchar(75) null,
	type_ varchar(75) null
);

create table Lock_ (
	uuid_ varchar(75) null,
	lockId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	className varchar(75) null,
	key_ varchar(200) null,
	owner varchar(255) null,
	inheritable boolean,
	expirationDate date null
);

create table MBBan (
	uuid_ varchar(75) null,
	banId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	banUserId bigint
);

create table MBCategory (
	uuid_ varchar(75) null,
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	parentCategoryId bigint,
	name varchar(75) null,
	description long varchar null,
	displayStyle varchar(75) null,
	threadCount integer,
	messageCount integer,
	lastPostDate date null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table MBDiscussion (
	uuid_ varchar(75) null,
	discussionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	threadId bigint
);

create table MBMailingList (
	uuid_ varchar(75) null,
	mailingListId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	categoryId bigint,
	emailAddress varchar(75) null,
	inProtocol varchar(75) null,
	inServerName varchar(75) null,
	inServerPort integer,
	inUseSSL boolean,
	inUserName varchar(75) null,
	inPassword varchar(75) null,
	inReadInterval integer,
	outEmailAddress varchar(75) null,
	outCustom boolean,
	outServerName varchar(75) null,
	outServerPort integer,
	outUseSSL boolean,
	outUserName varchar(75) null,
	outPassword varchar(75) null,
	allowAnonymous boolean,
	active_ boolean
);

create table MBMessage (
	uuid_ varchar(75) null,
	messageId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	categoryId bigint,
	threadId bigint,
	rootMessageId bigint,
	parentMessageId bigint,
	subject varchar(75) null,
	body long varchar null,
	format varchar(75) null,
	anonymous boolean,
	priority double,
	allowPingbacks boolean,
	answer boolean,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table MBStatsUser (
	statsUserId bigint not null primary key,
	groupId bigint,
	userId bigint,
	messageCount integer,
	lastPostDate date null
);

create table MBThread (
	uuid_ varchar(75) null,
	threadId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	categoryId bigint,
	rootMessageId bigint,
	rootMessageUserId bigint,
	messageCount integer,
	viewCount integer,
	lastPostByUserId bigint,
	lastPostDate date null,
	priority double,
	question boolean,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table MBThreadFlag (
	uuid_ varchar(75) null,
	threadFlagId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	threadId bigint
);

create table MDRAction (
	uuid_ varchar(75) null,
	actionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	ruleGroupInstanceId bigint,
	name long varchar null,
	description long varchar null,
	type_ varchar(255) null,
	typeSettings long varchar null
);

create table MDRRule (
	uuid_ varchar(75) null,
	ruleId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	ruleGroupId bigint,
	name long varchar null,
	description long varchar null,
	type_ varchar(255) null,
	typeSettings long varchar null
);

create table MDRRuleGroup (
	uuid_ varchar(75) null,
	ruleGroupId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	name long varchar null,
	description long varchar null
);

create table MDRRuleGroupInstance (
	uuid_ varchar(75) null,
	ruleGroupInstanceId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
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
	createDate date null,
	comments long varchar null,
	replyComments long varchar null,
	replyDate date null,
	replierUserId bigint,
	statusId integer
);

create table Organization_ (
	uuid_ varchar(75) null,
	organizationId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	parentOrganizationId bigint,
	treePath long varchar null,
	name varchar(100) null,
	type_ varchar(75) null,
	recursable boolean,
	regionId bigint,
	countryId bigint,
	statusId integer,
	comments long varchar null
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
	uuid_ varchar(75) null,
	passwordPolicyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	defaultPolicy boolean,
	name varchar(75) null,
	description long varchar null,
	changeable boolean,
	changeRequired boolean,
	minAge bigint,
	checkSyntax boolean,
	allowDictionaryWords boolean,
	minAlphanumeric integer,
	minLength integer,
	minLowerCase integer,
	minNumbers integer,
	minSymbols integer,
	minUpperCase integer,
	regex varchar(75) null,
	history boolean,
	historyCount integer,
	expireable boolean,
	maxAge bigint,
	warningTime bigint,
	graceLimit integer,
	lockout boolean,
	maxFailure integer,
	lockoutDuration bigint,
	requireUnlock boolean,
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
	createDate date null,
	password_ varchar(75) null
);

create table Phone (
	uuid_ varchar(75) null,
	phoneId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	number_ varchar(75) null,
	extension varchar(75) null,
	typeId integer,
	primary_ boolean
);

create table PluginSetting (
	pluginSettingId bigint not null primary key,
	companyId bigint,
	pluginId varchar(75) null,
	pluginType varchar(75) null,
	roles long varchar null,
	active_ boolean
);

create table PollsChoice (
	uuid_ varchar(75) null,
	choiceId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	questionId bigint,
	name varchar(75) null,
	description long varchar null
);

create table PollsQuestion (
	uuid_ varchar(75) null,
	questionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	title long varchar null,
	description long varchar null,
	expirationDate date null,
	lastVoteDate date null
);

create table PollsVote (
	uuid_ varchar(75) null,
	voteId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	questionId bigint,
	choiceId bigint,
	voteDate date null
);

create table PortalPreferences (
	portalPreferencesId bigint not null primary key,
	ownerId bigint,
	ownerType integer,
	preferences long varchar null
);

create table Portlet (
	id_ bigint not null primary key,
	companyId bigint,
	portletId varchar(200) null,
	roles long varchar null,
	active_ boolean
);

create table PortletItem (
	portletItemId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	name varchar(75) null,
	portletId varchar(200) null,
	classNameId bigint
);

create table PortletPreferences (
	portletPreferencesId bigint not null primary key,
	ownerId bigint,
	ownerType integer,
	plid bigint,
	portletId varchar(200) null,
	preferences long varchar null
);

create table RatingsEntry (
	entryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
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
	regionCode varchar(75) null,
	name varchar(75) null,
	active_ boolean
);

create table Release_ (
	releaseId bigint not null primary key,
	createDate date null,
	modifiedDate date null,
	servletContextName varchar(75) null,
	buildNumber integer,
	buildDate date null,
	verified boolean,
	state_ integer,
	testString varchar(1024) null
);

create table Repository (
	uuid_ varchar(75) null,
	repositoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	name varchar(75) null,
	description long varchar null,
	portletId varchar(200) null,
	typeSettings long varchar null,
	dlFolderId bigint
);

create table RepositoryEntry (
	uuid_ varchar(75) null,
	repositoryEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	repositoryId bigint,
	mappedId varchar(75) null,
	manualCheckInRequired boolean
);

create table ResourceAction (
	resourceActionId bigint not null primary key,
	name varchar(255) null,
	actionId varchar(75) null,
	bitwiseValue bigint
);

create table ResourceBlock (
	resourceBlockId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	name varchar(75) null,
	permissionsHash varchar(75) null,
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
	name varchar(255) null,
	scope integer,
	primKey varchar(255) null,
	roleId bigint,
	ownerId bigint,
	actionIds bigint
);

create table ResourceTypePermission (
	resourceTypePermissionId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	name varchar(75) null,
	roleId bigint,
	actionIds bigint
);

create table Role_ (
	uuid_ varchar(75) null,
	roleId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	name varchar(75) null,
	title long varchar null,
	description long varchar null,
	type_ integer,
	subtype varchar(75) null
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
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	name varchar(75) null,
	url long varchar null,
	active_ boolean,
	priority integer
);

create table SCLicense (
	licenseId bigint not null primary key,
	name varchar(75) null,
	url long varchar null,
	openSource boolean,
	active_ boolean,
	recommended boolean
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
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	name varchar(75) null,
	type_ varchar(75) null,
	tags varchar(255) null,
	shortDescription long varchar null,
	longDescription long varchar null,
	pageURL long varchar null,
	author varchar(75) null,
	repoGroupId varchar(75) null,
	repoArtifactId varchar(75) null
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
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	productEntryId bigint,
	version varchar(75) null,
	changeLog long varchar null,
	downloadPageURL long varchar null,
	directDownloadURL varchar(2000) null,
	repoStoreArtifact boolean
);

create table ServiceComponent (
	serviceComponentId bigint not null primary key,
	buildNamespace varchar(75) null,
	buildNumber bigint,
	buildDate bigint,
	data_ long varchar null
);

create table Shard (
	shardId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	name varchar(75) null
);

create table ShoppingCart (
	cartId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	itemIds long varchar null,
	couponCodes varchar(75) null,
	altShipping integer,
	insure boolean
);

create table ShoppingCategory (
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	parentCategoryId bigint,
	name varchar(75) null,
	description long varchar null
);

create table ShoppingCoupon (
	couponId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	code_ varchar(75) null,
	name varchar(75) null,
	description long varchar null,
	startDate date null,
	endDate date null,
	active_ boolean,
	limitCategories long varchar null,
	limitSkus long varchar null,
	minOrder double,
	discount double,
	discountType varchar(75) null
);

create table ShoppingItem (
	itemId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	categoryId bigint,
	sku varchar(75) null,
	name varchar(200) null,
	description long varchar null,
	properties long varchar null,
	fields_ boolean,
	fieldsQuantities long varchar null,
	minQuantity integer,
	maxQuantity integer,
	price double,
	discount double,
	taxable boolean,
	shipping double,
	useShippingFormula boolean,
	requiresShipping boolean,
	stockQuantity integer,
	featured_ boolean,
	sale_ boolean,
	smallImage boolean,
	smallImageId bigint,
	smallImageURL long varchar null,
	mediumImage boolean,
	mediumImageId bigint,
	mediumImageURL long varchar null,
	largeImage boolean,
	largeImageId bigint,
	largeImageURL long varchar null
);

create table ShoppingItemField (
	itemFieldId bigint not null primary key,
	itemId bigint,
	name varchar(75) null,
	values_ long varchar null,
	description long varchar null
);

create table ShoppingItemPrice (
	itemPriceId bigint not null primary key,
	itemId bigint,
	minQuantity integer,
	maxQuantity integer,
	price double,
	discount double,
	taxable boolean,
	shipping double,
	useShippingFormula boolean,
	status integer
);

create table ShoppingOrder (
	orderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	number_ varchar(75) null,
	tax double,
	shipping double,
	altShipping varchar(75) null,
	requiresShipping boolean,
	insure boolean,
	insurance double,
	couponCodes varchar(75) null,
	couponDiscount double,
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
	shipToBilling boolean,
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
	ccExpMonth integer,
	ccExpYear integer,
	ccVerNumber varchar(75) null,
	comments long varchar null,
	ppTxnId varchar(75) null,
	ppPaymentStatus varchar(75) null,
	ppPaymentGross double,
	ppReceiverEmail varchar(75) null,
	ppPayerEmail varchar(75) null,
	sendOrderEmail boolean,
	sendShippingEmail boolean
);

create table ShoppingOrderItem (
	orderItemId bigint not null primary key,
	orderId bigint,
	itemId varchar(75) null,
	sku varchar(75) null,
	name varchar(200) null,
	description long varchar null,
	properties long varchar null,
	price double,
	quantity integer,
	shippedDate date null
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
	extraData long varchar null,
	receiverUserId bigint
);

create table SocialActivityAchievement (
	activityAchievementId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate bigint,
	name varchar(75) null,
	firstInGroup boolean
);

create table SocialActivityCounter (
	activityCounterId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	classPK bigint,
	name varchar(75) null,
	ownerType integer,
	currentValue integer,
	totalValue integer,
	graceValue integer,
	startPeriod integer,
	endPeriod integer,
	active_ boolean
);

create table SocialActivityLimit (
	activityLimitId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	classNameId bigint,
	classPK bigint,
	activityType integer,
	activityCounterName varchar(75) null,
	value varchar(75) null
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
	extraData long varchar null,
	activityCount integer
);

create table SocialActivitySetting (
	activitySettingId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	activityType integer,
	name varchar(75) null,
	value varchar(1024) null
);

create table SocialRelation (
	uuid_ varchar(75) null,
	relationId bigint not null primary key,
	companyId bigint,
	createDate bigint,
	userId1 bigint,
	userId2 bigint,
	type_ integer
);

create table SocialRequest (
	uuid_ varchar(75) null,
	requestId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate bigint,
	modifiedDate bigint,
	classNameId bigint,
	classPK bigint,
	type_ integer,
	extraData long varchar null,
	receiverUserId bigint,
	status integer
);

create table Subscription (
	subscriptionId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	frequency varchar(75) null
);

create table SystemEvent (
	systemEventId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	classNameId bigint,
	classPK bigint,
	classUuid varchar(75) null,
	referrerClassNameId bigint,
	parentSystemEventId bigint,
	systemEventSetKey bigint,
	type_ integer,
	extraData long varchar null
);

create table Team (
	teamId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	groupId bigint,
	name varchar(75) null,
	description long varchar null
);

create table Ticket (
	ticketId bigint not null primary key,
	companyId bigint,
	createDate date null,
	classNameId bigint,
	classPK bigint,
	key_ varchar(75) null,
	type_ integer,
	extraInfo long varchar null,
	expirationDate date null
);

create table TrashEntry (
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	classNameId bigint,
	classPK bigint,
	systemEventSetKey bigint,
	typeSettings long varchar null,
	status integer
);

create table TrashVersion (
	versionId bigint not null primary key,
	entryId bigint,
	classNameId bigint,
	classPK bigint,
	typeSettings long varchar null,
	status integer
);

create table UserNotificationDelivery (
	userNotificationDeliveryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	portletId varchar(200) null,
	classNameId bigint,
	notificationType integer,
	deliveryType integer,
	deliver boolean
);

create table User_ (
	uuid_ varchar(75) null,
	userId bigint not null primary key,
	companyId bigint,
	createDate date null,
	modifiedDate date null,
	defaultUser boolean,
	contactId bigint,
	password_ varchar(75) null,
	passwordEncrypted boolean,
	passwordReset boolean,
	passwordModifiedDate date null,
	digest varchar(255) null,
	reminderQueryQuestion varchar(75) null,
	reminderQueryAnswer varchar(75) null,
	graceLoginCount integer,
	screenName varchar(75) null,
	emailAddress varchar(75) null,
	facebookId bigint,
	ldapServerId bigint,
	openId varchar(1024) null,
	portraitId bigint,
	languageId varchar(75) null,
	timeZoneId varchar(75) null,
	greeting varchar(255) null,
	comments long varchar null,
	firstName varchar(75) null,
	middleName varchar(75) null,
	lastName varchar(75) null,
	jobTitle varchar(100) null,
	loginDate date null,
	loginIP varchar(75) null,
	lastLoginDate date null,
	lastLoginIP varchar(75) null,
	lastFailedLoginDate date null,
	failedLoginAttempts integer,
	lockout boolean,
	lockoutDate date null,
	agreedToTermsOfUse boolean,
	emailAddressVerified boolean,
	status integer
);

create table UserGroup (
	uuid_ varchar(75) null,
	userGroupId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	parentUserGroupId bigint,
	name varchar(75) null,
	description long varchar null,
	addedByLDAPImport boolean
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
	type_ varchar(75) null,
	description varchar(75) null,
	externalUserId varchar(75) null
);

create table UserNotificationEvent (
	uuid_ varchar(75) null,
	userNotificationEventId bigint not null primary key,
	companyId bigint,
	userId bigint,
	type_ varchar(75) null,
	timestamp bigint,
	deliverBy bigint,
	delivered boolean,
	payload long varchar null,
	archived boolean
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
	modifiedDate date null,
	sessionId varchar(200) null,
	remoteAddr varchar(75) null,
	remoteHost varchar(75) null,
	userAgent varchar(200) null
);

create table UserTrackerPath (
	userTrackerPathId bigint not null primary key,
	userTrackerId bigint,
	path_ long varchar null,
	pathDate date null
);

create table VirtualHost (
	virtualHostId bigint not null primary key,
	companyId bigint,
	layoutSetId bigint,
	hostname varchar(75) null
);

create table WebDAVProps (
	webDavPropsId bigint not null primary key,
	companyId bigint,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	props long varchar null
);

create table Website (
	uuid_ varchar(75) null,
	websiteId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	url long varchar null,
	typeId integer,
	primary_ boolean
);

create table WikiNode (
	uuid_ varchar(75) null,
	nodeId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	name varchar(75) null,
	description long varchar null,
	lastPostDate date null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table WikiPage (
	uuid_ varchar(75) null,
	pageId bigint not null primary key,
	resourcePrimKey bigint,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	nodeId bigint,
	title varchar(255) null,
	version double,
	minorEdit boolean,
	content long varchar null,
	summary long varchar null,
	format varchar(75) null,
	head boolean,
	parentTitle varchar(255) null,
	redirectTitle varchar(255) null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate date null
);

create table WikiPageResource (
	uuid_ varchar(75) null,
	resourcePrimKey bigint not null primary key,
	nodeId bigint,
	title varchar(255) null
);

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	typePK bigint,
	workflowDefinitionName varchar(75) null,
	workflowDefinitionVersion integer
);

create table WorkflowInstanceLink (
	workflowInstanceLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	classNameId bigint,
	classPK bigint,
	workflowInstanceId bigint
);

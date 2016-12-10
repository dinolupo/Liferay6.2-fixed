create table Account_ (
	accountId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
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
) engine InnoDB;

create table Address (
	uuid_ varchar(75) null,
	addressId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
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
	mailing tinyint,
	primary_ tinyint
) engine InnoDB;

create table AnnouncementsDelivery (
	deliveryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	type_ varchar(75) null,
	email tinyint,
	sms tinyint,
	website tinyint
) engine InnoDB;

create table AnnouncementsEntry (
	uuid_ varchar(75) null,
	entryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	title varchar(75) null,
	content longtext null,
	url longtext null,
	type_ varchar(75) null,
	displayDate datetime null,
	expirationDate datetime null,
	priority integer,
	alert tinyint
) engine InnoDB;

create table AnnouncementsFlag (
	flagId bigint not null primary key,
	userId bigint,
	createDate datetime null,
	entryId bigint,
	value integer
) engine InnoDB;

create table AssetCategory (
	uuid_ varchar(75) null,
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentCategoryId bigint,
	leftCategoryId bigint,
	rightCategoryId bigint,
	name varchar(75) null,
	title longtext null,
	description longtext null,
	vocabularyId bigint
) engine InnoDB;

create table AssetCategoryProperty (
	categoryPropertyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId bigint,
	key_ varchar(75) null,
	value varchar(75) null
) engine InnoDB;

create table AssetEntries_AssetCategories (
	categoryId bigint not null,
	entryId bigint not null,
	primary key (categoryId, entryId)
) engine InnoDB;

create table AssetEntries_AssetTags (
	entryId bigint not null,
	tagId bigint not null,
	primary key (entryId, tagId)
) engine InnoDB;

create table AssetEntry (
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	classUuid varchar(75) null,
	classTypeId bigint,
	visible tinyint,
	startDate datetime null,
	endDate datetime null,
	publishDate datetime null,
	expirationDate datetime null,
	mimeType varchar(75) null,
	title longtext null,
	description longtext null,
	summary longtext null,
	url longtext null,
	layoutUuid varchar(75) null,
	height integer,
	width integer,
	priority double,
	viewCount integer
) engine InnoDB;

create table AssetLink (
	linkId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	entryId1 bigint,
	entryId2 bigint,
	type_ integer,
	weight integer
) engine InnoDB;

create table AssetTag (
	tagId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	assetCount integer
) engine InnoDB;

create table AssetTagProperty (
	tagPropertyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	tagId bigint,
	key_ varchar(75) null,
	value varchar(255) null
) engine InnoDB;

create table AssetTagStats (
	tagStatsId bigint not null primary key,
	tagId bigint,
	classNameId bigint,
	assetCount integer
) engine InnoDB;

create table AssetVocabulary (
	uuid_ varchar(75) null,
	vocabularyId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	title longtext null,
	description longtext null,
	settings_ longtext null
) engine InnoDB;

create table BackgroundTask (
	backgroundTaskId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	servletContextNames varchar(255) null,
	taskExecutorClassName varchar(200) null,
	taskContext longtext null,
	completed tinyint,
	completionDate datetime null,
	status integer,
	statusMessage longtext null
) engine InnoDB;

create table BlogsEntry (
	uuid_ varchar(75) null,
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	title varchar(150) null,
	urlTitle varchar(150) null,
	description longtext null,
	content longtext null,
	displayDate datetime null,
	allowPingbacks tinyint,
	allowTrackbacks tinyint,
	trackbacks longtext null,
	smallImage tinyint,
	smallImageId bigint,
	smallImageURL longtext null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table BlogsStatsUser (
	statsUserId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	entryCount integer,
	lastPostDate datetime null,
	ratingsTotalEntries integer,
	ratingsTotalScore double,
	ratingsAverageScore double
) engine InnoDB;

create table BookmarksEntry (
	uuid_ varchar(75) null,
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	resourceBlockId bigint,
	folderId bigint,
	treePath longtext null,
	name varchar(255) null,
	url longtext null,
	description longtext null,
	visits integer,
	priority integer,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table BookmarksFolder (
	uuid_ varchar(75) null,
	folderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	resourceBlockId bigint,
	parentFolderId bigint,
	treePath longtext null,
	name varchar(75) null,
	description longtext null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table BrowserTracker (
	browserTrackerId bigint not null primary key,
	userId bigint,
	browserKey bigint
) engine InnoDB;

create table CalEvent (
	uuid_ varchar(75) null,
	eventId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	title varchar(75) null,
	description longtext null,
	location longtext null,
	startDate datetime null,
	endDate datetime null,
	durationHour integer,
	durationMinute integer,
	allDay tinyint,
	timeZoneSensitive tinyint,
	type_ varchar(75) null,
	repeating tinyint,
	recurrence longtext null,
	remindBy integer,
	firstReminder integer,
	secondReminder integer
) engine InnoDB;

create table ClassName_ (
	classNameId bigint not null primary key,
	value varchar(200) null
) engine InnoDB;

create table ClusterGroup (
	clusterGroupId bigint not null primary key,
	name varchar(75) null,
	clusterNodeIds varchar(75) null,
	wholeCluster tinyint
) engine InnoDB;

create table Company (
	companyId bigint not null primary key,
	accountId bigint,
	webId varchar(75) null,
	key_ longtext null,
	mx varchar(75) null,
	homeURL longtext null,
	logoId bigint,
	system tinyint,
	maxUsers integer,
	active_ tinyint
) engine InnoDB;

create table Contact_ (
	contactId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
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
	male tinyint,
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
) engine InnoDB;

create table Counter (
	name varchar(75) not null primary key,
	currentId bigint
) engine InnoDB;

create table Country (
	countryId bigint not null primary key,
	name varchar(75) null,
	a2 varchar(75) null,
	a3 varchar(75) null,
	number_ varchar(75) null,
	idd_ varchar(75) null,
	zipRequired tinyint,
	active_ tinyint
) engine InnoDB;

create table CyrusUser (
	userId varchar(75) not null primary key,
	password_ varchar(75) not null
) engine InnoDB;

create table CyrusVirtual (
	emailAddress varchar(75) not null primary key,
	userId varchar(75) not null
) engine InnoDB;

create table DDLRecord (
	uuid_ varchar(75) null,
	recordId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	versionUserId bigint,
	versionUserName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	DDMStorageId bigint,
	recordSetId bigint,
	version varchar(75) null,
	displayIndex integer
) engine InnoDB;

create table DDLRecordSet (
	uuid_ varchar(75) null,
	recordSetId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	DDMStructureId bigint,
	recordSetKey varchar(75) null,
	name longtext null,
	description longtext null,
	minDisplayRows integer,
	scope integer
) engine InnoDB;

create table DDLRecordVersion (
	recordVersionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	DDMStorageId bigint,
	recordSetId bigint,
	recordId bigint,
	version varchar(75) null,
	displayIndex integer,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table DDMContent (
	uuid_ varchar(75) null,
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name longtext null,
	description longtext null,
	xml longtext null
) engine InnoDB;

create table DDMStorageLink (
	uuid_ varchar(75) null,
	storageLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
) engine InnoDB;

create table DDMStructure (
	uuid_ varchar(75) null,
	structureId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentStructureId bigint,
	classNameId bigint,
	structureKey varchar(75) null,
	name longtext null,
	description longtext null,
	xsd longtext null,
	storageType varchar(75) null,
	type_ integer
) engine InnoDB;

create table DDMStructureLink (
	structureLinkId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	structureId bigint
) engine InnoDB;

create table DDMTemplate (
	uuid_ varchar(75) null,
	templateId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	templateKey varchar(75) null,
	name longtext null,
	description longtext null,
	type_ varchar(75) null,
	mode_ varchar(75) null,
	language varchar(75) null,
	script longtext null,
	cacheable tinyint,
	smallImage tinyint,
	smallImageId bigint,
	smallImageURL varchar(75) null
) engine InnoDB;

create table DLContent (
	contentId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	repositoryId bigint,
	path_ varchar(255) null,
	version varchar(75) null,
	data_ longblob,
	size_ bigint
) engine InnoDB;

create table DLFileEntry (
	uuid_ varchar(75) null,
	fileEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	repositoryId bigint,
	folderId bigint,
	treePath longtext null,
	name varchar(255) null,
	extension varchar(75) null,
	mimeType varchar(75) null,
	title varchar(255) null,
	description longtext null,
	extraSettings longtext null,
	fileEntryTypeId bigint,
	version varchar(75) null,
	size_ bigint,
	readCount integer,
	smallImageId bigint,
	largeImageId bigint,
	custom1ImageId bigint,
	custom2ImageId bigint,
	manualCheckInRequired tinyint
) engine InnoDB;

create table DLFileEntryMetadata (
	uuid_ varchar(75) null,
	fileEntryMetadataId bigint not null primary key,
	DDMStorageId bigint,
	DDMStructureId bigint,
	fileEntryTypeId bigint,
	fileEntryId bigint,
	fileVersionId bigint
) engine InnoDB;

create table DLFileEntryType (
	uuid_ varchar(75) null,
	fileEntryTypeId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	fileEntryTypeKey varchar(75) null,
	name longtext null,
	description longtext null
) engine InnoDB;

create table DLFileEntryTypes_DDMStructures (
	structureId bigint not null,
	fileEntryTypeId bigint not null,
	primary key (structureId, fileEntryTypeId)
) engine InnoDB;

create table DLFileEntryTypes_DLFolders (
	fileEntryTypeId bigint not null,
	folderId bigint not null,
	primary key (fileEntryTypeId, folderId)
) engine InnoDB;

create table DLFileRank (
	fileRankId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate datetime null,
	fileEntryId bigint,
	active_ tinyint
) engine InnoDB;

create table DLFileShortcut (
	uuid_ varchar(75) null,
	fileShortcutId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	repositoryId bigint,
	folderId bigint,
	toFileEntryId bigint,
	treePath longtext null,
	active_ tinyint,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table DLFileVersion (
	uuid_ varchar(75) null,
	fileVersionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	repositoryId bigint,
	folderId bigint,
	fileEntryId bigint,
	treePath longtext null,
	extension varchar(75) null,
	mimeType varchar(75) null,
	title varchar(255) null,
	description longtext null,
	changeLog varchar(75) null,
	extraSettings longtext null,
	fileEntryTypeId bigint,
	version varchar(75) null,
	size_ bigint,
	checksum varchar(75) null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table DLFolder (
	uuid_ varchar(75) null,
	folderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	repositoryId bigint,
	mountPoint tinyint,
	parentFolderId bigint,
	treePath longtext null,
	name varchar(100) null,
	description longtext null,
	lastPostDate datetime null,
	defaultFileEntryTypeId bigint,
	hidden_ tinyint,
	overrideFileEntryTypes tinyint,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table DLSyncEvent (
	syncEventId bigint not null primary key,
	modifiedTime bigint,
	event varchar(75) null,
	type_ varchar(75) null,
	typePK bigint
) engine InnoDB;

create table EmailAddress (
	uuid_ varchar(75) null,
	emailAddressId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	address varchar(75) null,
	typeId integer,
	primary_ tinyint
) engine InnoDB;

create table ExpandoColumn (
	columnId bigint not null primary key,
	companyId bigint,
	tableId bigint,
	name varchar(75) null,
	type_ integer,
	defaultData longtext null,
	typeSettings longtext null
) engine InnoDB;

create table ExpandoRow (
	rowId_ bigint not null primary key,
	companyId bigint,
	modifiedDate datetime null,
	tableId bigint,
	classPK bigint
) engine InnoDB;

create table ExpandoTable (
	tableId bigint not null primary key,
	companyId bigint,
	classNameId bigint,
	name varchar(75) null
) engine InnoDB;

create table ExpandoValue (
	valueId bigint not null primary key,
	companyId bigint,
	tableId bigint,
	columnId bigint,
	rowId_ bigint,
	classNameId bigint,
	classPK bigint,
	data_ longtext null
) engine InnoDB;

create table Group_ (
	uuid_ varchar(75) null,
	groupId bigint not null primary key,
	companyId bigint,
	creatorUserId bigint,
	classNameId bigint,
	classPK bigint,
	parentGroupId bigint,
	liveGroupId bigint,
	treePath longtext null,
	name varchar(150) null,
	description longtext null,
	type_ integer,
	typeSettings longtext null,
	manualMembership tinyint,
	membershipRestriction integer,
	friendlyURL varchar(255) null,
	site tinyint,
	remoteStagingGroupCount integer,
	active_ tinyint
) engine InnoDB;

create table Groups_Orgs (
	groupId bigint not null,
	organizationId bigint not null,
	primary key (groupId, organizationId)
) engine InnoDB;

create table Groups_Roles (
	groupId bigint not null,
	roleId bigint not null,
	primary key (groupId, roleId)
) engine InnoDB;

create table Groups_UserGroups (
	groupId bigint not null,
	userGroupId bigint not null,
	primary key (groupId, userGroupId)
) engine InnoDB;

create table Image (
	imageId bigint not null primary key,
	modifiedDate datetime null,
	type_ varchar(75) null,
	height integer,
	width integer,
	size_ integer
) engine InnoDB;

create table JournalArticle (
	uuid_ varchar(75) null,
	id_ bigint not null primary key,
	resourcePrimKey bigint,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	folderId bigint,
	classNameId bigint,
	classPK bigint,
	treePath longtext null,
	articleId varchar(75) null,
	version double,
	title longtext null,
	urlTitle varchar(150) null,
	description longtext null,
	content longtext null,
	type_ varchar(75) null,
	structureId varchar(75) null,
	templateId varchar(75) null,
	layoutUuid varchar(75) null,
	displayDate datetime null,
	expirationDate datetime null,
	reviewDate datetime null,
	indexable tinyint,
	smallImage tinyint,
	smallImageId bigint,
	smallImageURL longtext null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table JournalArticleImage (
	articleImageId bigint not null primary key,
	groupId bigint,
	articleId varchar(75) null,
	version double,
	elInstanceId varchar(75) null,
	elName varchar(75) null,
	languageId varchar(75) null,
	tempImage tinyint
) engine InnoDB;

create table JournalArticleResource (
	uuid_ varchar(75) null,
	resourcePrimKey bigint not null primary key,
	groupId bigint,
	articleId varchar(75) null
) engine InnoDB;

create table JournalContentSearch (
	contentSearchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	privateLayout tinyint,
	layoutId bigint,
	portletId varchar(200) null,
	articleId varchar(75) null
) engine InnoDB;

create table JournalFeed (
	uuid_ varchar(75) null,
	id_ bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	feedId varchar(75) null,
	name varchar(75) null,
	description longtext null,
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
) engine InnoDB;

create table JournalFolder (
	uuid_ varchar(75) null,
	folderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentFolderId bigint,
	treePath longtext null,
	name varchar(100) null,
	description longtext null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table Layout (
	uuid_ varchar(75) null,
	plid bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	privateLayout tinyint,
	layoutId bigint,
	parentLayoutId bigint,
	name longtext null,
	title longtext null,
	description longtext null,
	keywords longtext null,
	robots longtext null,
	type_ varchar(75) null,
	typeSettings longtext null,
	hidden_ tinyint,
	friendlyURL varchar(255) null,
	iconImage tinyint,
	iconImageId bigint,
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css longtext null,
	priority integer,
	layoutPrototypeUuid varchar(75) null,
	layoutPrototypeLinkEnabled tinyint,
	sourcePrototypeLayoutUuid varchar(75) null
) engine InnoDB;

create table LayoutBranch (
	LayoutBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	layoutSetBranchId bigint,
	plid bigint,
	name varchar(75) null,
	description longtext null,
	master tinyint
) engine InnoDB;

create table LayoutFriendlyURL (
	uuid_ varchar(75) null,
	layoutFriendlyURLId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	plid bigint,
	privateLayout tinyint,
	friendlyURL varchar(255) null,
	languageId varchar(75) null
) engine InnoDB;

create table LayoutPrototype (
	uuid_ varchar(75) null,
	layoutPrototypeId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name longtext null,
	description longtext null,
	settings_ longtext null,
	active_ tinyint
) engine InnoDB;

create table LayoutRevision (
	layoutRevisionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	layoutSetBranchId bigint,
	layoutBranchId bigint,
	parentLayoutRevisionId bigint,
	head tinyint,
	major tinyint,
	plid bigint,
	privateLayout tinyint,
	name longtext null,
	title longtext null,
	description longtext null,
	keywords longtext null,
	robots longtext null,
	typeSettings longtext null,
	iconImage tinyint,
	iconImageId bigint,
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css longtext null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table LayoutSet (
	layoutSetId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	createDate datetime null,
	modifiedDate datetime null,
	privateLayout tinyint,
	logo tinyint,
	logoId bigint,
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css longtext null,
	pageCount integer,
	settings_ longtext null,
	layoutSetPrototypeUuid varchar(75) null,
	layoutSetPrototypeLinkEnabled tinyint
) engine InnoDB;

create table LayoutSetBranch (
	layoutSetBranchId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	privateLayout tinyint,
	name varchar(75) null,
	description longtext null,
	master tinyint,
	logo tinyint,
	logoId bigint,
	themeId varchar(75) null,
	colorSchemeId varchar(75) null,
	wapThemeId varchar(75) null,
	wapColorSchemeId varchar(75) null,
	css longtext null,
	settings_ longtext null,
	layoutSetPrototypeUuid varchar(75) null,
	layoutSetPrototypeLinkEnabled tinyint
) engine InnoDB;

create table LayoutSetPrototype (
	uuid_ varchar(75) null,
	layoutSetPrototypeId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name longtext null,
	description longtext null,
	settings_ longtext null,
	active_ tinyint
) engine InnoDB;

create table ListType (
	listTypeId integer not null primary key,
	name varchar(75) null,
	type_ varchar(75) null
) engine InnoDB;

create table Lock_ (
	uuid_ varchar(75) null,
	lockId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	className varchar(75) null,
	key_ varchar(200) null,
	owner varchar(255) null,
	inheritable tinyint,
	expirationDate datetime null
) engine InnoDB;

create table MBBan (
	uuid_ varchar(75) null,
	banId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	banUserId bigint
) engine InnoDB;

create table MBCategory (
	uuid_ varchar(75) null,
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentCategoryId bigint,
	name varchar(75) null,
	description longtext null,
	displayStyle varchar(75) null,
	threadCount integer,
	messageCount integer,
	lastPostDate datetime null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table MBDiscussion (
	uuid_ varchar(75) null,
	discussionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	threadId bigint
) engine InnoDB;

create table MBMailingList (
	uuid_ varchar(75) null,
	mailingListId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId bigint,
	emailAddress varchar(75) null,
	inProtocol varchar(75) null,
	inServerName varchar(75) null,
	inServerPort integer,
	inUseSSL tinyint,
	inUserName varchar(75) null,
	inPassword varchar(75) null,
	inReadInterval integer,
	outEmailAddress varchar(75) null,
	outCustom tinyint,
	outServerName varchar(75) null,
	outServerPort integer,
	outUseSSL tinyint,
	outUserName varchar(75) null,
	outPassword varchar(75) null,
	allowAnonymous tinyint,
	active_ tinyint
) engine InnoDB;

create table MBMessage (
	uuid_ varchar(75) null,
	messageId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	categoryId bigint,
	threadId bigint,
	rootMessageId bigint,
	parentMessageId bigint,
	subject varchar(75) null,
	body longtext null,
	format varchar(75) null,
	anonymous tinyint,
	priority double,
	allowPingbacks tinyint,
	answer tinyint,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table MBStatsUser (
	statsUserId bigint not null primary key,
	groupId bigint,
	userId bigint,
	messageCount integer,
	lastPostDate datetime null
) engine InnoDB;

create table MBThread (
	uuid_ varchar(75) null,
	threadId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId bigint,
	rootMessageId bigint,
	rootMessageUserId bigint,
	messageCount integer,
	viewCount integer,
	lastPostByUserId bigint,
	lastPostDate datetime null,
	priority double,
	question tinyint,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table MBThreadFlag (
	uuid_ varchar(75) null,
	threadFlagId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	threadId bigint
) engine InnoDB;

create table MDRAction (
	uuid_ varchar(75) null,
	actionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	ruleGroupInstanceId bigint,
	name longtext null,
	description longtext null,
	type_ varchar(255) null,
	typeSettings longtext null
) engine InnoDB;

create table MDRRule (
	uuid_ varchar(75) null,
	ruleId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	ruleGroupId bigint,
	name longtext null,
	description longtext null,
	type_ varchar(255) null,
	typeSettings longtext null
) engine InnoDB;

create table MDRRuleGroup (
	uuid_ varchar(75) null,
	ruleGroupId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name longtext null,
	description longtext null
) engine InnoDB;

create table MDRRuleGroupInstance (
	uuid_ varchar(75) null,
	ruleGroupInstanceId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	ruleGroupId bigint,
	priority integer
) engine InnoDB;

create table MembershipRequest (
	membershipRequestId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate datetime null,
	comments longtext null,
	replyComments longtext null,
	replyDate datetime null,
	replierUserId bigint,
	statusId integer
) engine InnoDB;

create table Organization_ (
	uuid_ varchar(75) null,
	organizationId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentOrganizationId bigint,
	treePath longtext null,
	name varchar(100) null,
	type_ varchar(75) null,
	recursable tinyint,
	regionId bigint,
	countryId bigint,
	statusId integer,
	comments longtext null
) engine InnoDB;

create table OrgGroupRole (
	organizationId bigint not null,
	groupId bigint not null,
	roleId bigint not null,
	primary key (organizationId, groupId, roleId)
) engine InnoDB;

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
) engine InnoDB;

create table PasswordPolicy (
	uuid_ varchar(75) null,
	passwordPolicyId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	defaultPolicy tinyint,
	name varchar(75) null,
	description longtext null,
	changeable tinyint,
	changeRequired tinyint,
	minAge bigint,
	checkSyntax tinyint,
	allowDictionaryWords tinyint,
	minAlphanumeric integer,
	minLength integer,
	minLowerCase integer,
	minNumbers integer,
	minSymbols integer,
	minUpperCase integer,
	regex varchar(75) null,
	history tinyint,
	historyCount integer,
	expireable tinyint,
	maxAge bigint,
	warningTime bigint,
	graceLimit integer,
	lockout tinyint,
	maxFailure integer,
	lockoutDuration bigint,
	requireUnlock tinyint,
	resetFailureCount bigint,
	resetTicketMaxAge bigint
) engine InnoDB;

create table PasswordPolicyRel (
	passwordPolicyRelId bigint not null primary key,
	passwordPolicyId bigint,
	classNameId bigint,
	classPK bigint
) engine InnoDB;

create table PasswordTracker (
	passwordTrackerId bigint not null primary key,
	userId bigint,
	createDate datetime null,
	password_ varchar(75) null
) engine InnoDB;

create table Phone (
	uuid_ varchar(75) null,
	phoneId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	number_ varchar(75) null,
	extension varchar(75) null,
	typeId integer,
	primary_ tinyint
) engine InnoDB;

create table PluginSetting (
	pluginSettingId bigint not null primary key,
	companyId bigint,
	pluginId varchar(75) null,
	pluginType varchar(75) null,
	roles longtext null,
	active_ tinyint
) engine InnoDB;

create table PollsChoice (
	uuid_ varchar(75) null,
	choiceId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	questionId bigint,
	name varchar(75) null,
	description longtext null
) engine InnoDB;

create table PollsQuestion (
	uuid_ varchar(75) null,
	questionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	title longtext null,
	description longtext null,
	expirationDate datetime null,
	lastVoteDate datetime null
) engine InnoDB;

create table PollsVote (
	uuid_ varchar(75) null,
	voteId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	questionId bigint,
	choiceId bigint,
	voteDate datetime null
) engine InnoDB;

create table PortalPreferences (
	portalPreferencesId bigint not null primary key,
	ownerId bigint,
	ownerType integer,
	preferences longtext null
) engine InnoDB;

create table Portlet (
	id_ bigint not null primary key,
	companyId bigint,
	portletId varchar(200) null,
	roles longtext null,
	active_ tinyint
) engine InnoDB;

create table PortletItem (
	portletItemId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	portletId varchar(200) null,
	classNameId bigint
) engine InnoDB;

create table PortletPreferences (
	portletPreferencesId bigint not null primary key,
	ownerId bigint,
	ownerType integer,
	plid bigint,
	portletId varchar(200) null,
	preferences longtext null
) engine InnoDB;

create table RatingsEntry (
	entryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	score double
) engine InnoDB;

create table RatingsStats (
	statsId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	totalEntries integer,
	totalScore double,
	averageScore double
) engine InnoDB;

create table Region (
	regionId bigint not null primary key,
	countryId bigint,
	regionCode varchar(75) null,
	name varchar(75) null,
	active_ tinyint
) engine InnoDB;

create table Release_ (
	releaseId bigint not null primary key,
	createDate datetime null,
	modifiedDate datetime null,
	servletContextName varchar(75) null,
	buildNumber integer,
	buildDate datetime null,
	verified tinyint,
	state_ integer,
	testString varchar(1024) null
) engine InnoDB;

create table Repository (
	uuid_ varchar(75) null,
	repositoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	name varchar(75) null,
	description longtext null,
	portletId varchar(200) null,
	typeSettings longtext null,
	dlFolderId bigint
) engine InnoDB;

create table RepositoryEntry (
	uuid_ varchar(75) null,
	repositoryEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	repositoryId bigint,
	mappedId varchar(75) null,
	manualCheckInRequired tinyint
) engine InnoDB;

create table ResourceAction (
	resourceActionId bigint not null primary key,
	name varchar(255) null,
	actionId varchar(75) null,
	bitwiseValue bigint
) engine InnoDB;

create table ResourceBlock (
	resourceBlockId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	name varchar(75) null,
	permissionsHash varchar(75) null,
	referenceCount bigint
) engine InnoDB;

create table ResourceBlockPermission (
	resourceBlockPermissionId bigint not null primary key,
	resourceBlockId bigint,
	roleId bigint,
	actionIds bigint
) engine InnoDB;

create table ResourcePermission (
	resourcePermissionId bigint not null primary key,
	companyId bigint,
	name varchar(255) null,
	scope integer,
	primKey varchar(255) null,
	roleId bigint,
	ownerId bigint,
	actionIds bigint
) engine InnoDB;

create table ResourceTypePermission (
	resourceTypePermissionId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	name varchar(75) null,
	roleId bigint,
	actionIds bigint
) engine InnoDB;

create table Role_ (
	uuid_ varchar(75) null,
	roleId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	name varchar(75) null,
	title longtext null,
	description longtext null,
	type_ integer,
	subtype varchar(75) null
) engine InnoDB;

create table SCFrameworkVersi_SCProductVers (
	frameworkVersionId bigint not null,
	productVersionId bigint not null,
	primary key (frameworkVersionId, productVersionId)
) engine InnoDB;

create table SCFrameworkVersion (
	frameworkVersionId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	url longtext null,
	active_ tinyint,
	priority integer
) engine InnoDB;

create table SCLicense (
	licenseId bigint not null primary key,
	name varchar(75) null,
	url longtext null,
	openSource tinyint,
	active_ tinyint,
	recommended tinyint
) engine InnoDB;

create table SCLicenses_SCProductEntries (
	licenseId bigint not null,
	productEntryId bigint not null,
	primary key (licenseId, productEntryId)
) engine InnoDB;

create table SCProductEntry (
	productEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	type_ varchar(75) null,
	tags varchar(255) null,
	shortDescription longtext null,
	longDescription longtext null,
	pageURL longtext null,
	author varchar(75) null,
	repoGroupId varchar(75) null,
	repoArtifactId varchar(75) null
) engine InnoDB;

create table SCProductScreenshot (
	productScreenshotId bigint not null primary key,
	companyId bigint,
	groupId bigint,
	productEntryId bigint,
	thumbnailId bigint,
	fullImageId bigint,
	priority integer
) engine InnoDB;

create table SCProductVersion (
	productVersionId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	productEntryId bigint,
	version varchar(75) null,
	changeLog longtext null,
	downloadPageURL longtext null,
	directDownloadURL varchar(2000) null,
	repoStoreArtifact tinyint
) engine InnoDB;

create table ServiceComponent (
	serviceComponentId bigint not null primary key,
	buildNamespace varchar(75) null,
	buildNumber bigint,
	buildDate bigint,
	data_ longtext null
) engine InnoDB;

create table Shard (
	shardId bigint not null primary key,
	classNameId bigint,
	classPK bigint,
	name varchar(75) null
) engine InnoDB;

create table ShoppingCart (
	cartId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	itemIds longtext null,
	couponCodes varchar(75) null,
	altShipping integer,
	insure tinyint
) engine InnoDB;

create table ShoppingCategory (
	categoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentCategoryId bigint,
	name varchar(75) null,
	description longtext null
) engine InnoDB;

create table ShoppingCoupon (
	couponId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	code_ varchar(75) null,
	name varchar(75) null,
	description longtext null,
	startDate datetime null,
	endDate datetime null,
	active_ tinyint,
	limitCategories longtext null,
	limitSkus longtext null,
	minOrder double,
	discount double,
	discountType varchar(75) null
) engine InnoDB;

create table ShoppingItem (
	itemId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	categoryId bigint,
	sku varchar(75) null,
	name varchar(200) null,
	description longtext null,
	properties longtext null,
	fields_ tinyint,
	fieldsQuantities longtext null,
	minQuantity integer,
	maxQuantity integer,
	price double,
	discount double,
	taxable tinyint,
	shipping double,
	useShippingFormula tinyint,
	requiresShipping tinyint,
	stockQuantity integer,
	featured_ tinyint,
	sale_ tinyint,
	smallImage tinyint,
	smallImageId bigint,
	smallImageURL longtext null,
	mediumImage tinyint,
	mediumImageId bigint,
	mediumImageURL longtext null,
	largeImage tinyint,
	largeImageId bigint,
	largeImageURL longtext null
) engine InnoDB;

create table ShoppingItemField (
	itemFieldId bigint not null primary key,
	itemId bigint,
	name varchar(75) null,
	values_ longtext null,
	description longtext null
) engine InnoDB;

create table ShoppingItemPrice (
	itemPriceId bigint not null primary key,
	itemId bigint,
	minQuantity integer,
	maxQuantity integer,
	price double,
	discount double,
	taxable tinyint,
	shipping double,
	useShippingFormula tinyint,
	status integer
) engine InnoDB;

create table ShoppingOrder (
	orderId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	number_ varchar(75) null,
	tax double,
	shipping double,
	altShipping varchar(75) null,
	requiresShipping tinyint,
	insure tinyint,
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
	shipToBilling tinyint,
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
	comments longtext null,
	ppTxnId varchar(75) null,
	ppPaymentStatus varchar(75) null,
	ppPaymentGross double,
	ppReceiverEmail varchar(75) null,
	ppPayerEmail varchar(75) null,
	sendOrderEmail tinyint,
	sendShippingEmail tinyint
) engine InnoDB;

create table ShoppingOrderItem (
	orderItemId bigint not null primary key,
	orderId bigint,
	itemId varchar(75) null,
	sku varchar(75) null,
	name varchar(200) null,
	description longtext null,
	properties longtext null,
	price double,
	quantity integer,
	shippedDate datetime null
) engine InnoDB;

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
	extraData longtext null,
	receiverUserId bigint
) engine InnoDB;

create table SocialActivityAchievement (
	activityAchievementId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate bigint,
	name varchar(75) null,
	firstInGroup tinyint
) engine InnoDB;

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
	active_ tinyint
) engine InnoDB;

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
) engine InnoDB;

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
	extraData longtext null,
	activityCount integer
) engine InnoDB;

create table SocialActivitySetting (
	activitySettingId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	activityType integer,
	name varchar(75) null,
	value varchar(1024) null
) engine InnoDB;

create table SocialRelation (
	uuid_ varchar(75) null,
	relationId bigint not null primary key,
	companyId bigint,
	createDate bigint,
	userId1 bigint,
	userId2 bigint,
	type_ integer
) engine InnoDB;

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
	extraData longtext null,
	receiverUserId bigint,
	status integer
) engine InnoDB;

create table Subscription (
	subscriptionId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	frequency varchar(75) null
) engine InnoDB;

create table SystemEvent (
	systemEventId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	classNameId bigint,
	classPK bigint,
	classUuid varchar(75) null,
	referrerClassNameId bigint,
	parentSystemEventId bigint,
	systemEventSetKey bigint,
	type_ integer,
	extraData longtext null
) engine InnoDB;

create table Team (
	teamId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	groupId bigint,
	name varchar(75) null,
	description longtext null
) engine InnoDB;

create table Ticket (
	ticketId bigint not null primary key,
	companyId bigint,
	createDate datetime null,
	classNameId bigint,
	classPK bigint,
	key_ varchar(75) null,
	type_ integer,
	extraInfo longtext null,
	expirationDate datetime null
) engine InnoDB;

create table TrashEntry (
	entryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	classNameId bigint,
	classPK bigint,
	systemEventSetKey bigint,
	typeSettings longtext null,
	status integer
) engine InnoDB;

create table TrashVersion (
	versionId bigint not null primary key,
	entryId bigint,
	classNameId bigint,
	classPK bigint,
	typeSettings longtext null,
	status integer
) engine InnoDB;

create table UserNotificationDelivery (
	userNotificationDeliveryId bigint not null primary key,
	companyId bigint,
	userId bigint,
	portletId varchar(200) null,
	classNameId bigint,
	notificationType integer,
	deliveryType integer,
	deliver tinyint
) engine InnoDB;

create table User_ (
	uuid_ varchar(75) null,
	userId bigint not null primary key,
	companyId bigint,
	createDate datetime null,
	modifiedDate datetime null,
	defaultUser tinyint,
	contactId bigint,
	password_ varchar(75) null,
	passwordEncrypted tinyint,
	passwordReset tinyint,
	passwordModifiedDate datetime null,
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
	comments longtext null,
	firstName varchar(75) null,
	middleName varchar(75) null,
	lastName varchar(75) null,
	jobTitle varchar(100) null,
	loginDate datetime null,
	loginIP varchar(75) null,
	lastLoginDate datetime null,
	lastLoginIP varchar(75) null,
	lastFailedLoginDate datetime null,
	failedLoginAttempts integer,
	lockout tinyint,
	lockoutDate datetime null,
	agreedToTermsOfUse tinyint,
	emailAddressVerified tinyint,
	status integer
) engine InnoDB;

create table UserGroup (
	uuid_ varchar(75) null,
	userGroupId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	parentUserGroupId bigint,
	name varchar(75) null,
	description longtext null,
	addedByLDAPImport tinyint
) engine InnoDB;

create table UserGroupGroupRole (
	userGroupId bigint not null,
	groupId bigint not null,
	roleId bigint not null,
	primary key (userGroupId, groupId, roleId)
) engine InnoDB;

create table UserGroupRole (
	userId bigint not null,
	groupId bigint not null,
	roleId bigint not null,
	primary key (userId, groupId, roleId)
) engine InnoDB;

create table UserGroups_Teams (
	teamId bigint not null,
	userGroupId bigint not null,
	primary key (teamId, userGroupId)
) engine InnoDB;

create table UserIdMapper (
	userIdMapperId bigint not null primary key,
	userId bigint,
	type_ varchar(75) null,
	description varchar(75) null,
	externalUserId varchar(75) null
) engine InnoDB;

create table UserNotificationEvent (
	uuid_ varchar(75) null,
	userNotificationEventId bigint not null primary key,
	companyId bigint,
	userId bigint,
	type_ varchar(75) null,
	timestamp bigint,
	deliverBy bigint,
	delivered tinyint,
	payload longtext null,
	archived tinyint
) engine InnoDB;

create table Users_Groups (
	groupId bigint not null,
	userId bigint not null,
	primary key (groupId, userId)
) engine InnoDB;

create table Users_Orgs (
	organizationId bigint not null,
	userId bigint not null,
	primary key (organizationId, userId)
) engine InnoDB;

create table Users_Roles (
	roleId bigint not null,
	userId bigint not null,
	primary key (roleId, userId)
) engine InnoDB;

create table Users_Teams (
	teamId bigint not null,
	userId bigint not null,
	primary key (teamId, userId)
) engine InnoDB;

create table Users_UserGroups (
	userId bigint not null,
	userGroupId bigint not null,
	primary key (userId, userGroupId)
) engine InnoDB;

create table UserTracker (
	userTrackerId bigint not null primary key,
	companyId bigint,
	userId bigint,
	modifiedDate datetime null,
	sessionId varchar(200) null,
	remoteAddr varchar(75) null,
	remoteHost varchar(75) null,
	userAgent varchar(200) null
) engine InnoDB;

create table UserTrackerPath (
	userTrackerPathId bigint not null primary key,
	userTrackerId bigint,
	path_ longtext null,
	pathDate datetime null
) engine InnoDB;

create table VirtualHost (
	virtualHostId bigint not null primary key,
	companyId bigint,
	layoutSetId bigint,
	hostname varchar(75) null
) engine InnoDB;

create table WebDAVProps (
	webDavPropsId bigint not null primary key,
	companyId bigint,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	props longtext null
) engine InnoDB;

create table Website (
	uuid_ varchar(75) null,
	websiteId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	url longtext null,
	typeId integer,
	primary_ tinyint
) engine InnoDB;

create table WikiNode (
	uuid_ varchar(75) null,
	nodeId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	description longtext null,
	lastPostDate datetime null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table WikiPage (
	uuid_ varchar(75) null,
	pageId bigint not null primary key,
	resourcePrimKey bigint,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	nodeId bigint,
	title varchar(255) null,
	version double,
	minorEdit tinyint,
	content longtext null,
	summary longtext null,
	format varchar(75) null,
	head tinyint,
	parentTitle varchar(255) null,
	redirectTitle varchar(255) null,
	status integer,
	statusByUserId bigint,
	statusByUserName varchar(75) null,
	statusDate datetime null
) engine InnoDB;

create table WikiPageResource (
	uuid_ varchar(75) null,
	resourcePrimKey bigint not null primary key,
	nodeId bigint,
	title varchar(255) null
) engine InnoDB;

create table WorkflowDefinitionLink (
	workflowDefinitionLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	typePK bigint,
	workflowDefinitionName varchar(75) null,
	workflowDefinitionVersion integer
) engine InnoDB;

create table WorkflowInstanceLink (
	workflowInstanceLinkId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	workflowInstanceId bigint
) engine InnoDB;


insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (1, 'canada', 'CA', 'CAN', '124', '001', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (2, 'china', 'CN', 'CHN', '156', '086', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (3, 'france', 'FR', 'FRA', '250', '033', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (4, 'germany', 'DE', 'DEU', '276', '049', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (5, 'hong-kong', 'HK', 'HKG', '344', '852', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (6, 'hungary', 'HU', 'HUN', '348', '036', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (7, 'israel', 'IL', 'ISR', '376', '972', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (8, 'italy', 'IT', 'ITA', '380', '039', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (9, 'japan', 'JP', 'JPN', '392', '081', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (10, 'south-korea', 'KR', 'KOR', '410', '082', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (11, 'netherlands', 'NL', 'NLD', '528', '031', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (12, 'portugal', 'PT', 'PRT', '620', '351', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (13, 'russia', 'RU', 'RUS', '643', '007', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (14, 'singapore', 'SG', 'SGP', '702', '065', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (15, 'spain', 'ES', 'ESP', '724', '034', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (16, 'turkey', 'TR', 'TUR', '792', '090', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (17, 'vietnam', 'VN', 'VNM', '704', '084', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (18, 'united-kingdom', 'GB', 'GBR', '826', '044', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (19, 'united-states', 'US', 'USA', '840', '001', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (20, 'afghanistan', 'AF', 'AFG', '4', '093', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (21, 'albania', 'AL', 'ALB', '8', '355', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (22, 'algeria', 'DZ', 'DZA', '12', '213', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (23, 'american-samoa', 'AS', 'ASM', '16', '684', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (24, 'andorra', 'AD', 'AND', '20', '376', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (25, 'angola', 'AO', 'AGO', '24', '244', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (26, 'anguilla', 'AI', 'AIA', '660', '264', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (27, 'antarctica', 'AQ', 'ATA', '10', '672', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (28, 'antigua-barbuda', 'AG', 'ATG', '28', '268', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (29, 'argentina', 'AR', 'ARG', '32', '054', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (30, 'armenia', 'AM', 'ARM', '51', '374', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (31, 'aruba', 'AW', 'ABW', '533', '297', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (32, 'australia', 'AU', 'AUS', '36', '061', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (33, 'austria', 'AT', 'AUT', '40', '043', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (34, 'azerbaijan', 'AZ', 'AZE', '31', '994', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (35, 'bahamas', 'BS', 'BHS', '44', '242', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (36, 'bahrain', 'BH', 'BHR', '48', '973', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (37, 'bangladesh', 'BD', 'BGD', '50', '880', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (38, 'barbados', 'BB', 'BRB', '52', '246', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (39, 'belarus', 'BY', 'BLR', '112', '375', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (40, 'belgium', 'BE', 'BEL', '56', '032', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (41, 'belize', 'BZ', 'BLZ', '84', '501', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (42, 'benin', 'BJ', 'BEN', '204', '229', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (43, 'bermuda', 'BM', 'BMU', '60', '441', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (44, 'bhutan', 'BT', 'BTN', '64', '975', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (45, 'bolivia', 'BO', 'BOL', '68', '591', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (46, 'bosnia-herzegovina', 'BA', 'BIH', '70', '387', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (47, 'botswana', 'BW', 'BWA', '72', '267', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (48, 'brazil', 'BR', 'BRA', '76', '055', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (49, 'british-virgin-islands', 'VG', 'VGB', '92', '284', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (50, 'brunei', 'BN', 'BRN', '96', '673', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (51, 'bulgaria', 'BG', 'BGR', '100', '359', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (52, 'burkina-faso', 'BF', 'BFA', '854', '226', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (53, 'burma-myanmar', 'MM', 'MMR', '104', '095', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (54, 'burundi', 'BI', 'BDI', '108', '257', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (55, 'cambodia', 'KH', 'KHM', '116', '855', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (56, 'cameroon', 'CM', 'CMR', '120', '237', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (57, 'cape-verde-island', 'CV', 'CPV', '132', '238', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (58, 'cayman-islands', 'KY', 'CYM', '136', '345', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (59, 'central-african-republic', 'CF', 'CAF', '140', '236', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (60, 'chad', 'TD', 'TCD', '148', '235', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (61, 'chile', 'CL', 'CHL', '152', '056', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (62, 'christmas-island', 'CX', 'CXR', '162', '061', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (63, 'cocos-islands', 'CC', 'CCK', '166', '061', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (64, 'colombia', 'CO', 'COL', '170', '057', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (65, 'comoros', 'KM', 'COM', '174', '269', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (66, 'republic-of-congo', 'CD', 'COD', '180', '242', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (67, 'democratic-republic-of-congo', 'CG', 'COG', '178', '243', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (68, 'cook-islands', 'CK', 'COK', '184', '682', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (69, 'costa-rica', 'CR', 'CRI', '188', '506', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (70, 'croatia', 'HR', 'HRV', '191', '385', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (71, 'cuba', 'CU', 'CUB', '192', '053', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (72, 'cyprus', 'CY', 'CYP', '196', '357', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (73, 'czech-republic', 'CZ', 'CZE', '203', '420', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (74, 'denmark', 'DK', 'DNK', '208', '045', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (75, 'djibouti', 'DJ', 'DJI', '262', '253', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (76, 'dominica', 'DM', 'DMA', '212', '767', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (77, 'dominican-republic', 'DO', 'DOM', '214', '809', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (78, 'ecuador', 'EC', 'ECU', '218', '593', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (79, 'egypt', 'EG', 'EGY', '818', '020', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (80, 'el-salvador', 'SV', 'SLV', '222', '503', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (81, 'equatorial-guinea', 'GQ', 'GNQ', '226', '240', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (82, 'eritrea', 'ER', 'ERI', '232', '291', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (83, 'estonia', 'EE', 'EST', '233', '372', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (84, 'ethiopia', 'ET', 'ETH', '231', '251', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (85, 'faeroe-islands', 'FO', 'FRO', '234', '298', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (86, 'falkland-islands', 'FK', 'FLK', '238', '500', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (87, 'fiji-islands', 'FJ', 'FJI', '242', '679', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (88, 'finland', 'FI', 'FIN', '246', '358', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (89, 'french-guiana', 'GF', 'GUF', '254', '594', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (90, 'french-polynesia', 'PF', 'PYF', '258', '689', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (91, 'gabon', 'GA', 'GAB', '266', '241', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (92, 'gambia', 'GM', 'GMB', '270', '220', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (93, 'georgia', 'GE', 'GEO', '268', '995', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (94, 'ghana', 'GH', 'GHA', '288', '233', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (95, 'gibraltar', 'GI', 'GIB', '292', '350', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (96, 'greece', 'GR', 'GRC', '300', '030', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (97, 'greenland', 'GL', 'GRL', '304', '299', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (98, 'grenada', 'GD', 'GRD', '308', '473', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (99, 'guadeloupe', 'GP', 'GLP', '312', '590', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (100, 'guam', 'GU', 'GUM', '316', '671', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (101, 'guatemala', 'GT', 'GTM', '320', '502', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (102, 'guinea', 'GN', 'GIN', '324', '224', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (103, 'guinea-bissau', 'GW', 'GNB', '624', '245', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (104, 'guyana', 'GY', 'GUY', '328', '592', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (105, 'haiti', 'HT', 'HTI', '332', '509', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (106, 'honduras', 'HN', 'HND', '340', '504', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (107, 'iceland', 'IS', 'ISL', '352', '354', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (108, 'india', 'IN', 'IND', '356', '091', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (109, 'indonesia', 'ID', 'IDN', '360', '062', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (110, 'iran', 'IR', 'IRN', '364', '098', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (111, 'iraq', 'IQ', 'IRQ', '368', '964', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (112, 'ireland', 'IE', 'IRL', '372', '353', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (113, 'ivory-coast', 'CI', 'CIV', '384', '225', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (114, 'jamaica', 'JM', 'JAM', '388', '876', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (115, 'jordan', 'JO', 'JOR', '400', '962', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (116, 'kazakhstan', 'KZ', 'KAZ', '398', '007', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (117, 'kenya', 'KE', 'KEN', '404', '254', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (118, 'kiribati', 'KI', 'KIR', '408', '686', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (119, 'kuwait', 'KW', 'KWT', '414', '965', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (120, 'north-korea', 'KP', 'PRK', '408', '850', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (121, 'kyrgyzstan', 'KG', 'KGZ', '471', '996', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (122, 'laos', 'LA', 'LAO', '418', '856', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (123, 'latvia', 'LV', 'LVA', '428', '371', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (124, 'lebanon', 'LB', 'LBN', '422', '961', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (125, 'lesotho', 'LS', 'LSO', '426', '266', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (126, 'liberia', 'LR', 'LBR', '430', '231', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (127, 'libya', 'LY', 'LBY', '434', '218', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (128, 'liechtenstein', 'LI', 'LIE', '438', '423', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (129, 'lithuania', 'LT', 'LTU', '440', '370', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (130, 'luxembourg', 'LU', 'LUX', '442', '352', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (131, 'macau', 'MO', 'MAC', '446', '853', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (132, 'macedonia', 'MK', 'MKD', '807', '389', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (133, 'madagascar', 'MG', 'MDG', '450', '261', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (134, 'malawi', 'MW', 'MWI', '454', '265', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (135, 'malaysia', 'MY', 'MYS', '458', '060', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (136, 'maldives', 'MV', 'MDV', '462', '960', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (137, 'mali', 'ML', 'MLI', '466', '223', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (138, 'malta', 'MT', 'MLT', '470', '356', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (139, 'marshall-islands', 'MH', 'MHL', '584', '692', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (140, 'martinique', 'MQ', 'MTQ', '474', '596', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (141, 'mauritania', 'MR', 'MRT', '478', '222', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (142, 'mauritius', 'MU', 'MUS', '480', '230', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (143, 'mayotte-island', 'YT', 'MYT', '175', '269', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (144, 'mexico', 'MX', 'MEX', '484', '052', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (145, 'micronesia', 'FM', 'FSM', '583', '691', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (146, 'moldova', 'MD', 'MDA', '498', '373', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (147, 'monaco', 'MC', 'MCO', '492', '377', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (148, 'mongolia', 'MN', 'MNG', '496', '976', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (149, 'montenegro', 'ME', 'MNE', '499', '382', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (150, 'montserrat', 'MS', 'MSR', '500', '664', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (151, 'morocco', 'MA', 'MAR', '504', '212', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (152, 'mozambique', 'MZ', 'MOZ', '508', '258', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (153, 'namibia', 'NA', 'NAM', '516', '264', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (154, 'nauru', 'NR', 'NRU', '520', '674', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (155, 'nepal', 'NP', 'NPL', '524', '977', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (156, 'netherlands-antilles', 'AN', 'ANT', '530', '599', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (157, 'new-caledonia', 'NC', 'NCL', '540', '687', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (158, 'new-zealand', 'NZ', 'NZL', '554', '064', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (159, 'nicaragua', 'NI', 'NIC', '558', '505', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (160, 'niger', 'NE', 'NER', '562', '227', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (161, 'nigeria', 'NG', 'NGA', '566', '234', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (162, 'niue', 'NU', 'NIU', '570', '683', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (163, 'norfolk-island', 'NF', 'NFK', '574', '672', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (164, 'norway', 'NO', 'NOR', '578', '047', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (165, 'oman', 'OM', 'OMN', '512', '968', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (166, 'pakistan', 'PK', 'PAK', '586', '092', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (167, 'palau', 'PW', 'PLW', '585', '680', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (168, 'palestine', 'PS', 'PSE', '275', '970', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (169, 'panama', 'PA', 'PAN', '591', '507', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (170, 'papua-new-guinea', 'PG', 'PNG', '598', '675', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (171, 'paraguay', 'PY', 'PRY', '600', '595', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (172, 'peru', 'PE', 'PER', '604', '051', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (173, 'philippines', 'PH', 'PHL', '608', '063', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (174, 'poland', 'PL', 'POL', '616', '048', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (175, 'puerto-rico', 'PR', 'PRI', '630', '787', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (176, 'qatar', 'QA', 'QAT', '634', '974', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (177, 'reunion-island', 'RE', 'REU', '638', '262', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (178, 'romania', 'RO', 'ROU', '642', '040', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (179, 'rwanda', 'RW', 'RWA', '646', '250', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (180, 'st-helena', 'SH', 'SHN', '654', '290', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (181, 'st-kitts', 'KN', 'KNA', '659', '869', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (182, 'st-lucia', 'LC', 'LCA', '662', '758', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (183, 'st-pierre-miquelon', 'PM', 'SPM', '666', '508', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (184, 'st-vincent', 'VC', 'VCT', '670', '784', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (185, 'san-marino', 'SM', 'SMR', '674', '378', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (186, 'sao-tome-principe', 'ST', 'STP', '678', '239', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (187, 'saudi-arabia', 'SA', 'SAU', '682', '966', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (188, 'senegal', 'SN', 'SEN', '686', '221', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (189, 'serbia', 'RS', 'SRB', '688', '381', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (190, 'seychelles', 'SC', 'SYC', '690', '248', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (191, 'sierra-leone', 'SL', 'SLE', '694', '249', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (192, 'slovakia', 'SK', 'SVK', '703', '421', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (193, 'slovenia', 'SI', 'SVN', '705', '386', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (194, 'solomon-islands', 'SB', 'SLB', '90', '677', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (195, 'somalia', 'SO', 'SOM', '706', '252', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (196, 'south-africa', 'ZA', 'ZAF', '710', '027', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (197, 'sri-lanka', 'LK', 'LKA', '144', '094', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (198, 'sudan', 'SD', 'SDN', '736', '095', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (199, 'suriname', 'SR', 'SUR', '740', '597', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (200, 'swaziland', 'SZ', 'SWZ', '748', '268', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (201, 'sweden', 'SE', 'SWE', '752', '046', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (202, 'switzerland', 'CH', 'CHE', '756', '041', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (203, 'syria', 'SY', 'SYR', '760', '963', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (204, 'taiwan', 'TW', 'TWN', '158', '886', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (205, 'tajikistan', 'TJ', 'TJK', '762', '992', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (206, 'tanzania', 'TZ', 'TZA', '834', '255', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (207, 'thailand', 'TH', 'THA', '764', '066', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (208, 'togo', 'TG', 'TGO', '768', '228', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (209, 'tonga', 'TO', 'TON', '776', '676', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (210, 'trinidad-tobago', 'TT', 'TTO', '780', '868', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (211, 'tunisia', 'TN', 'TUN', '788', '216', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (212, 'turkmenistan', 'TM', 'TKM', '795', '993', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (213, 'turks-caicos', 'TC', 'TCA', '796', '649', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (214, 'tuvalu', 'TV', 'TUV', '798', '688', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (215, 'uganda', 'UG', 'UGA', '800', '256', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (216, 'ukraine', 'UA', 'UKR', '804', '380', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (217, 'united-arab-emirates', 'AE', 'ARE', '784', '971', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (218, 'uruguay', 'UY', 'URY', '858', '598', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (219, 'uzbekistan', 'UZ', 'UZB', '860', '998', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (220, 'vanuatu', 'VU', 'VUT', '548', '678', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (221, 'vatican-city', 'VA', 'VAT', '336', '039', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (222, 'venezuela', 'VE', 'VEN', '862', '058', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (223, 'wallis-futuna', 'WF', 'WLF', '876', '681', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (224, 'western-samoa', 'WS', 'WSM', '882', '685', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (225, 'yemen', 'YE', 'YEM', '887', '967', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (226, 'zambia', 'ZM', 'ZMB', '894', '260', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (227, 'zimbabwe', 'ZW', 'ZWE', '716', '263', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (228, 'aland-islands', 'AX', 'ALA', '248', '359', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (229, 'bonaire-st-eustatius-saba', 'BQ', 'BES', '535', '599', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (230, 'bouvet-island', 'BV', 'BVT', '74', '047', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (231, 'british-indian-ocean-territory', 'IO', 'IOT', '86', '246', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (232, 'curacao', 'CW', 'CUW', '531', '599', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (233, 'french-southern-territories', 'TF', 'ATF', '260', '033', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (234, 'guernsey', 'GG', 'GGY', '831', '044', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (235, 'heard-island-mcdonald-islands', 'HM', 'HMD', '334', '061', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (236, 'isle-of-man', 'IM', 'IMN', '833', '044', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (237, 'jersey', 'JE', 'JEY', '832', '044', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (238, 'northern-mariana-islands', 'MP', 'MNP', '580', '670', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (239, 'pitcairn', 'PN', 'PCN', '612', '649', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (240, 'south-georgia-south-sandwich-islands', 'GS', 'SGS', '239', '044', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (241, 'south-sudan', 'SS', 'SSD', '728', '211', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (242, 'sint-maarten', 'SX', 'SXM', '534', '721', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (243, 'st-barthelemy', 'BL', 'BLM', '652', '590', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (244, 'st-martin', 'MF', 'MAF', '663', '590', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (245, 'tokelau', 'TK', 'TKL', '772', '690', 0, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (246, 'timor-leste', 'TL', 'TLS', '626', '670', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (247, 'united-states-minor-outlying-islands', 'UM', 'UMI', '581', '699', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (248, 'united-states-virgin-islands', 'VI', 'VIR', '850', '340', 1, 1);
insert into Country (countryId, name, a2, a3, number_, idd_, zipRequired, active_) values (249, 'western-sahara', 'EH', 'ESH', '732', '212', 1, 1);

insert into Region (regionId, countryId, regionCode, name, active_) values (1001, 1, 'AB', 'Alberta', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (1002, 1, 'BC', 'British Columbia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (1003, 1, 'MB', 'Manitoba', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (1004, 1, 'NB', 'New Brunswick', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (1005, 1, 'NL', 'Newfoundland and Labrador', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (1006, 1, 'NT', 'Northwest Territories', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (1007, 1, 'NS', 'Nova Scotia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (1008, 1, 'NU', 'Nunavut', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (1009, 1, 'ON', 'Ontario', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (1010, 1, 'PE', 'Prince Edward Island', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (1011, 1, 'QC', 'Quebec', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (1012, 1, 'SK', 'Saskatchewan', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (1013, 1, 'YT', 'Yukon', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2001, 2, 'CN-34', 'Anhui', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2002, 2, 'CN-92', 'Aomen', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2003, 2, 'CN-11', 'Beijing', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2004, 2, 'CN-50', 'Chongqing', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2005, 2, 'CN-35', 'Fujian', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2006, 2, 'CN-62', 'Gansu', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2007, 2, 'CN-44', 'Guangdong', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2008, 2, 'CN-45', 'Guangxi', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2009, 2, 'CN-52', 'Guizhou', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2010, 2, 'CN-46', 'Hainan', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2011, 2, 'CN-13', 'Hebei', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2012, 2, 'CN-23', 'Heilongjiang', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2013, 2, 'CN-41', 'Henan', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2014, 2, 'CN-42', 'Hubei', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2015, 2, 'CN-43', 'Hunan', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2016, 2, 'CN-32', 'Jiangsu', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2017, 2, 'CN-36', 'Jiangxi', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2018, 2, 'CN-22', 'Jilin', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2019, 2, 'CN-21', 'Liaoning', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2020, 2, 'CN-15', 'Nei Mongol', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2021, 2, 'CN-64', 'Ningxia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2022, 2, 'CN-63', 'Qinghai', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2023, 2, 'CN-61', 'Shaanxi', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2024, 2, 'CN-37', 'Shandong', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2025, 2, 'CN-31', 'Shanghai', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2026, 2, 'CN-14', 'Shanxi', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2027, 2, 'CN-51', 'Sichuan', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2028, 2, 'CN-71', 'Taiwan', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2029, 2, 'CN-12', 'Tianjin', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2030, 2, 'CN-91', 'Xianggang', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2031, 2, 'CN-65', 'Xinjiang', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2032, 2, 'CN-54', 'Xizang', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2033, 2, 'CN-53', 'Yunnan', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (2034, 2, 'CN-33', 'Zhejiang', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3001, 3, 'A', 'Alsace', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3002, 3, 'B', 'Aquitaine', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3003, 3, 'C', 'Auvergne', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3004, 3, 'P', 'Basse-Normandie', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3005, 3, 'D', 'Bourgogne', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3006, 3, 'E', 'Bretagne', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3007, 3, 'F', 'Centre', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3008, 3, 'G', 'Champagne-Ardenne', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3009, 3, 'H', 'Corse', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3010, 3, 'GF', 'Guyane', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3011, 3, 'I', 'Franche Comté', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3012, 3, 'GP', 'Guadeloupe', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3013, 3, 'Q', 'Haute-Normandie', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3014, 3, 'J', 'Île-de-France', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3015, 3, 'K', 'Languedoc-Roussillon', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3016, 3, 'L', 'Limousin', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3017, 3, 'M', 'Lorraine', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3018, 3, 'MQ', 'Martinique', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3019, 3, 'N', 'Midi-Pyrénées', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3020, 3, 'O', 'Nord Pas de Calais', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3021, 3, 'R', 'Pays de la Loire', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3022, 3, 'S', 'Picardie', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3023, 3, 'T', 'Poitou-Charentes', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3024, 3, 'U', 'Provence-Alpes-Côte-d''Azur', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3025, 3, 'RE', 'Réunion', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (3026, 3, 'V', 'Rhône-Alpes', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4001, 4, 'BW', 'Baden-Württemberg', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4002, 4, 'BY', 'Bayern', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4003, 4, 'BE', 'Berlin', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4004, 4, 'BB', 'Brandenburg', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4005, 4, 'HB', 'Bremen', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4006, 4, 'HH', 'Hamburg', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4007, 4, 'HE', 'Hessen', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4008, 4, 'MV', 'Mecklenburg-Vorpommern', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4009, 4, 'NI', 'Niedersachsen', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4010, 4, 'NW', 'Nordrhein-Westfalen', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4011, 4, 'RP', 'Rheinland-Pfalz', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4012, 4, 'SL', 'Saarland', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4013, 4, 'SN', 'Sachsen', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4014, 4, 'ST', 'Sachsen-Anhalt', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4015, 4, 'SH', 'Schleswig-Holstein', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (4016, 4, 'TH', 'Thüringen', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8001, 8, 'AG', 'Agrigento', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8002, 8, 'AL', 'Alessandria', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8003, 8, 'AN', 'Ancona', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8004, 8, 'AO', 'Aosta', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8005, 8, 'AR', 'Arezzo', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8006, 8, 'AP', 'Ascoli Piceno', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8007, 8, 'AT', 'Asti', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8008, 8, 'AV', 'Avellino', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8009, 8, 'BA', 'Bari', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8010, 8, 'BT', 'Barletta-Andria-Trani', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8011, 8, 'BL', 'Belluno', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8012, 8, 'BN', 'Benevento', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8013, 8, 'BG', 'Bergamo', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8014, 8, 'BI', 'Biella', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8015, 8, 'BO', 'Bologna', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8016, 8, 'BZ', 'Bolzano', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8017, 8, 'BS', 'Brescia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8018, 8, 'BR', 'Brindisi', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8019, 8, 'CA', 'Cagliari', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8020, 8, 'CL', 'Caltanissetta', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8021, 8, 'CB', 'Campobasso', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8022, 8, 'CI', 'Carbonia-Iglesias', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8023, 8, 'CE', 'Caserta', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8024, 8, 'CT', 'Catania', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8025, 8, 'CZ', 'Catanzaro', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8026, 8, 'CH', 'Chieti', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8027, 8, 'CO', 'Como', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8028, 8, 'CS', 'Cosenza', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8029, 8, 'CR', 'Cremona', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8030, 8, 'KR', 'Crotone', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8031, 8, 'CN', 'Cuneo', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8032, 8, 'EN', 'Enna', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8033, 8, 'FM', 'Fermo', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8034, 8, 'FE', 'Ferrara', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8035, 8, 'FI', 'Firenze', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8036, 8, 'FG', 'Foggia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8037, 8, 'FC', 'Forli-Cesena', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8038, 8, 'FR', 'Frosinone', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8039, 8, 'GE', 'Genova', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8040, 8, 'GO', 'Gorizia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8041, 8, 'GR', 'Grosseto', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8042, 8, 'IM', 'Imperia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8043, 8, 'IS', 'Isernia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8044, 8, 'AQ', 'L''Aquila', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8045, 8, 'SP', 'La Spezia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8046, 8, 'LT', 'Latina', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8047, 8, 'LE', 'Lecce', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8048, 8, 'LC', 'Lecco', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8049, 8, 'LI', 'Livorno', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8050, 8, 'LO', 'Lodi', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8051, 8, 'LU', 'Lucca', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8052, 8, 'MC', 'Macerata', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8053, 8, 'MN', 'Mantova', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8054, 8, 'MS', 'Massa-Carrara', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8055, 8, 'MT', 'Matera', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8056, 8, 'MA', 'Medio Campidano', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8057, 8, 'ME', 'Messina', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8058, 8, 'MI', 'Milano', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8059, 8, 'MO', 'Modena', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8060, 8, 'MB', 'Monza e Brianza', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8061, 8, 'NA', 'Napoli', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8062, 8, 'NO', 'Novara', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8063, 8, 'NU', 'Nuoro', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8064, 8, 'OG', 'Ogliastra', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8065, 8, 'OT', 'Olbia-Tempio', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8066, 8, 'OR', 'Oristano', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8067, 8, 'PD', 'Padova', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8068, 8, 'PA', 'Palermo', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8069, 8, 'PR', 'Parma', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8070, 8, 'PV', 'Pavia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8071, 8, 'PG', 'Perugia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8072, 8, 'PU', 'Pesaro e Urbino', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8073, 8, 'PE', 'Pescara', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8074, 8, 'PC', 'Piacenza', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8075, 8, 'PI', 'Pisa', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8076, 8, 'PT', 'Pistoia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8077, 8, 'PN', 'Pordenone', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8078, 8, 'PZ', 'Potenza', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8079, 8, 'PO', 'Prato', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8080, 8, 'RG', 'Ragusa', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8081, 8, 'RA', 'Ravenna', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8082, 8, 'RC', 'Reggio Calabria', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8083, 8, 'RE', 'Reggio Emilia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8084, 8, 'RI', 'Rieti', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8085, 8, 'RN', 'Rimini', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8086, 8, 'RM', 'Roma', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8087, 8, 'RO', 'Rovigo', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8088, 8, 'SA', 'Salerno', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8089, 8, 'SS', 'Sassari', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8090, 8, 'SV', 'Savona', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8091, 8, 'SI', 'Siena', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8092, 8, 'SR', 'Siracusa', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8093, 8, 'SO', 'Sondrio', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8094, 8, 'TA', 'Taranto', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8095, 8, 'TE', 'Teramo', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8096, 8, 'TR', 'Terni', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8097, 8, 'TO', 'Torino', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8098, 8, 'TP', 'Trapani', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8099, 8, 'TN', 'Trento', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8100, 8, 'TV', 'Treviso', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8101, 8, 'TS', 'Trieste', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8102, 8, 'UD', 'Udine', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8103, 8, 'VA', 'Varese', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8104, 8, 'VE', 'Venezia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8105, 8, 'VB', 'Verbano-Cusio-Ossola', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8106, 8, 'VC', 'Vercelli', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8107, 8, 'VR', 'Verona', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8108, 8, 'VV', 'Vibo Valentia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8109, 8, 'VI', 'Vicenza', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (8110, 8, 'VT', 'Viterbo', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (11001, 11, 'DR', 'Drenthe', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (11002, 11, 'FL', 'Flevoland', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (11003, 11, 'FR', 'Friesland', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (11004, 11, 'GE', 'Gelderland', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (11005, 11, 'GR', 'Groningen', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (11006, 11, 'LI', 'Limburg', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (11007, 11, 'NB', 'Noord-Brabant', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (11008, 11, 'NH', 'Noord-Holland', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (11009, 11, 'OV', 'Overijssel', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (11010, 11, 'UT', 'Utrecht', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (11011, 11, 'ZE', 'Zeeland', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (11012, 11, 'ZH', 'Zuid-Holland', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15001, 15, 'AN', 'Andalusia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15002, 15, 'AR', 'Aragon', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15003, 15, 'AS', 'Asturias', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15004, 15, 'IB', 'Balearic Islands', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15005, 15, 'PV', 'Basque Country', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15006, 15, 'CN', 'Canary Islands', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15007, 15, 'CB', 'Cantabria', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15008, 15, 'CL', 'Castile and Leon', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15009, 15, 'CM', 'Castile-La Mancha', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15010, 15, 'CT', 'Catalonia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15011, 15, 'CE', 'Ceuta', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15012, 15, 'EX', 'Extremadura', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15013, 15, 'GA', 'Galicia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15014, 15, 'LO', 'La Rioja', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15015, 15, 'M', 'Madrid', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15016, 15, 'ML', 'Melilla', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15017, 15, 'MU', 'Murcia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15018, 15, 'NA', 'Navarra', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (15019, 15, 'VC', 'Valencia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19001, 19, 'AL', 'Alabama', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19002, 19, 'AK', 'Alaska', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19003, 19, 'AZ', 'Arizona', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19004, 19, 'AR', 'Arkansas', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19005, 19, 'CA', 'California', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19006, 19, 'CO', 'Colorado', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19007, 19, 'CT', 'Connecticut', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19008, 19, 'DC', 'District of Columbia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19009, 19, 'DE', 'Delaware', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19010, 19, 'FL', 'Florida', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19011, 19, 'GA', 'Georgia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19012, 19, 'HI', 'Hawaii', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19013, 19, 'ID', 'Idaho', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19014, 19, 'IL', 'Illinois', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19015, 19, 'IN', 'Indiana', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19016, 19, 'IA', 'Iowa', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19017, 19, 'KS', 'Kansas', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19018, 19, 'KY', 'Kentucky ', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19019, 19, 'LA', 'Louisiana ', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19020, 19, 'ME', 'Maine', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19021, 19, 'MD', 'Maryland', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19022, 19, 'MA', 'Massachusetts', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19023, 19, 'MI', 'Michigan', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19024, 19, 'MN', 'Minnesota', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19025, 19, 'MS', 'Mississippi', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19026, 19, 'MO', 'Missouri', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19027, 19, 'MT', 'Montana', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19028, 19, 'NE', 'Nebraska', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19029, 19, 'NV', 'Nevada', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19030, 19, 'NH', 'New Hampshire', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19031, 19, 'NJ', 'New Jersey', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19032, 19, 'NM', 'New Mexico', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19033, 19, 'NY', 'New York', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19034, 19, 'NC', 'North Carolina', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19035, 19, 'ND', 'North Dakota', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19036, 19, 'OH', 'Ohio', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19037, 19, 'OK', 'Oklahoma ', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19038, 19, 'OR', 'Oregon', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19039, 19, 'PA', 'Pennsylvania', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19040, 19, 'PR', 'Puerto Rico', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19041, 19, 'RI', 'Rhode Island', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19042, 19, 'SC', 'South Carolina', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19043, 19, 'SD', 'South Dakota', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19044, 19, 'TN', 'Tennessee', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19045, 19, 'TX', 'Texas', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19046, 19, 'UT', 'Utah', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19047, 19, 'VT', 'Vermont', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19048, 19, 'VA', 'Virginia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19049, 19, 'WA', 'Washington', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19050, 19, 'WV', 'West Virginia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19051, 19, 'WI', 'Wisconsin', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (19052, 19, 'WY', 'Wyoming', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (32001, 32, 'ACT', 'Australian Capital Territory', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (32002, 32, 'NSW', 'New South Wales', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (32003, 32, 'NT', 'Northern Territory', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (32004, 32, 'QLD', 'Queensland', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (32005, 32, 'SA', 'South Australia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (32006, 32, 'TAS', 'Tasmania', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (32007, 32, 'VIC', 'Victoria', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (32008, 32, 'WA', 'Western Australia', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144001, 144, 'MX-AGS', 'Aguascalientes', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144002, 144, 'MX-BCN', 'Baja California', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144003, 144, 'MX-BCS', 'Baja California Sur', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144004, 144, 'MX-CAM', 'Campeche', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144005, 144, 'MX-CHP', 'Chiapas', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144006, 144, 'MX-CHI', 'Chihuahua', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144007, 144, 'MX-COA', 'Coahuila', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144008, 144, 'MX-COL', 'Colima', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144009, 144, 'MX-DUR', 'Durango', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144010, 144, 'MX-GTO', 'Guanajuato', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144011, 144, 'MX-GRO', 'Guerrero', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144012, 144, 'MX-HGO', 'Hidalgo', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144013, 144, 'MX-JAL', 'Jalisco', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144014, 144, 'MX-MEX', 'Mexico', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144015, 144, 'MX-MIC', 'Michoacan', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144016, 144, 'MX-MOR', 'Morelos', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144017, 144, 'MX-NAY', 'Nayarit', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144018, 144, 'MX-NLE', 'Nuevo Leon', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144019, 144, 'MX-OAX', 'Oaxaca', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144020, 144, 'MX-PUE', 'Puebla', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144021, 144, 'MX-QRO', 'Queretaro', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144023, 144, 'MX-ROO', 'Quintana Roo', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144024, 144, 'MX-SLP', 'San Luis Potosí', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144025, 144, 'MX-SIN', 'Sinaloa', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144026, 144, 'MX-SON', 'Sonora', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144027, 144, 'MX-TAB', 'Tabasco', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144028, 144, 'MX-TAM', 'Tamaulipas', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144029, 144, 'MX-TLX', 'Tlaxcala', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144030, 144, 'MX-VER', 'Veracruz', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144031, 144, 'MX-YUC', 'Yucatan', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (144032, 144, 'MX-ZAC', 'Zacatecas', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164001, 164, '01', 'Østfold', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164002, 164, '02', 'Akershus', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164003, 164, '03', 'Oslo', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164004, 164, '04', 'Hedmark', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164005, 164, '05', 'Oppland', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164006, 164, '06', 'Buskerud', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164007, 164, '07', 'Vestfold', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164008, 164, '08', 'Telemark', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164009, 164, '09', 'Aust-Agder', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164010, 164, '10', 'Vest-Agder', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164011, 164, '11', 'Rogaland', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164012, 164, '12', 'Hordaland', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164013, 164, '14', 'Sogn og Fjordane', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164014, 164, '15', 'Møre of Romsdal', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164015, 164, '16', 'Sør-Trøndelag', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164016, 164, '17', 'Nord-Trøndelag', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164017, 164, '18', 'Nordland', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164018, 164, '19', 'Troms', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (164019, 164, '20', 'Finnmark', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202001, 202, 'AG', 'Aargau', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202002, 202, 'AR', 'Appenzell Ausserrhoden', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202003, 202, 'AI', 'Appenzell Innerrhoden', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202004, 202, 'BL', 'Basel-Landschaft', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202005, 202, 'BS', 'Basel-Stadt', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202006, 202, 'BE', 'Bern', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202007, 202, 'FR', 'Fribourg', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202008, 202, 'GE', 'Geneva', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202009, 202, 'GL', 'Glarus', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202010, 202, 'GR', 'Graubünden', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202011, 202, 'JU', 'Jura', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202012, 202, 'LU', 'Lucerne', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202013, 202, 'NE', 'Neuchâtel', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202014, 202, 'NW', 'Nidwalden', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202015, 202, 'OW', 'Obwalden', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202016, 202, 'SH', 'Schaffhausen', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202017, 202, 'SZ', 'Schwyz', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202018, 202, 'SO', 'Solothurn', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202019, 202, 'SG', 'St. Gallen', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202020, 202, 'TG', 'Thurgau', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202021, 202, 'TI', 'Ticino', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202022, 202, 'UR', 'Uri', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202023, 202, 'VS', 'Valais', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202024, 202, 'VD', 'Vaud', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202025, 202, 'ZG', 'Zug', 1);
insert into Region (regionId, countryId, regionCode, name, active_) values (202026, 202, 'ZH', 'Zürich', 1);

##
## List types for accounts
##

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

##
## List types for contacts
##

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

##
## List types for organizations
##

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


insert into Release_ (releaseId, createDate, modifiedDate, servletContextName, buildNumber, verified) values (1, now(), now(), 'portal', 6200, 0);


create table QUARTZ_BLOB_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	BLOB_DATA longblob null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
) engine InnoDB;

create table QUARTZ_CALENDARS (
	SCHED_NAME varchar(120) not null,
	CALENDAR_NAME varchar(200) not null,
	CALENDAR longblob not null,
	primary key (SCHED_NAME,CALENDAR_NAME)
) engine InnoDB;

create table QUARTZ_CRON_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	CRON_EXPRESSION varchar(200) not null,
	TIME_ZONE_ID varchar(80),
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
) engine InnoDB;

create table QUARTZ_FIRED_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	ENTRY_ID varchar(95) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	INSTANCE_NAME varchar(200) not null,
	FIRED_TIME bigint not null,
	PRIORITY integer not null,
	STATE varchar(16) not null,
	JOB_NAME varchar(200) null,
	JOB_GROUP varchar(200) null,
	IS_NONCONCURRENT tinyint null,
	REQUESTS_RECOVERY tinyint null,
	primary key (SCHED_NAME, ENTRY_ID)
) engine InnoDB;

create table QUARTZ_JOB_DETAILS (
	SCHED_NAME varchar(120) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250) null,
	JOB_CLASS_NAME varchar(250) not null,
	IS_DURABLE tinyint not null,
	IS_NONCONCURRENT tinyint not null,
	IS_UPDATE_DATA tinyint not null,
	REQUESTS_RECOVERY tinyint not null,
	JOB_DATA longblob null,
	primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
) engine InnoDB;

create table QUARTZ_LOCKS (
	SCHED_NAME varchar(120) not null,
	LOCK_NAME varchar(40) not null ,
	primary key (SCHED_NAME, LOCK_NAME)
) engine InnoDB;

create table QUARTZ_PAUSED_TRIGGER_GRPS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_GROUP varchar(200) not null,
	primary key (SCHED_NAME, TRIGGER_GROUP)
) engine InnoDB;

create table QUARTZ_SCHEDULER_STATE (
	SCHED_NAME varchar(120) not null,
	INSTANCE_NAME varchar(200) not null,
	LAST_CHECKIN_TIME bigint not null,
	CHECKIN_INTERVAL bigint not null,
	primary key (SCHED_NAME, INSTANCE_NAME)
) engine InnoDB;

create table QUARTZ_SIMPLE_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	REPEAT_COUNT bigint not null,
	REPEAT_INTERVAL bigint not null,
	TIMES_TRIGGERED bigint not null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
) engine InnoDB;

create table QUARTZ_SIMPROP_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	STR_PROP_1 varchar(512) null,
	STR_PROP_2 varchar(512) null,
	STR_PROP_3 varchar(512) null,
	INT_PROP_1 integer null,
	INT_PROP_2 integer null,
	LONG_PROP_1 bigint null,
	LONG_PROP_2 bigint null,
	DEC_PROP_1 NUMERIC(13,4) null,
	DEC_PROP_2 NUMERIC(13,4) null,
	BOOL_PROP_1 tinyint null,
	BOOL_PROP_2 tinyint null,
	primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
) engine InnoDB;

create table QUARTZ_TRIGGERS (
	SCHED_NAME varchar(120) not null,
	TRIGGER_NAME varchar(200) not null,
	TRIGGER_GROUP varchar(200) not null,
	JOB_NAME varchar(200) not null,
	JOB_GROUP varchar(200) not null,
	DESCRIPTION varchar(250) null,
	NEXT_FIRE_TIME bigint null,
	PREV_FIRE_TIME bigint null,
	PRIORITY integer null,
	TRIGGER_STATE varchar(16) not null,
	TRIGGER_TYPE varchar(8) not null,
	START_TIME bigint not null,
	END_TIME bigint null,
	CALENDAR_NAME varchar(200) null,
	MISFIRE_INSTR integer null,
	JOB_DATA longblob null,
	primary key  (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
) engine InnoDB;

commit;

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


commit;

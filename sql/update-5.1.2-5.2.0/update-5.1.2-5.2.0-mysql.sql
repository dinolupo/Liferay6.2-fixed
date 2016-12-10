alter table Company add homeURL longtext null;

alter table ExpandoColumn add companyId bigint;
alter table ExpandoColumn add defaultData longtext null;
alter table ExpandoColumn add typeSettings longtext null;

alter table ExpandoRow add companyId bigint;

alter table ExpandoTable add companyId bigint;

alter table ExpandoValue add companyId bigint;

alter table JournalArticleImage add elInstanceId varchar(75) null;

alter table JournalStructure add parentStructureId varchar(75);

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
	active_ tinyint
) engine InnoDB;

alter table Organization_ add type_ varchar(75);

alter table Role_ add title longtext null;
alter table Role_ add subtype varchar(75);

alter table TagsAsset add visible tinyint;

commit;

update TagsAsset set visible = 1;

alter table TagsEntry add groupId bigint;
alter table TagsEntry add parentEntryId bigint;
alter table TagsEntry add vocabularyId bigint;

commit;

update TagsEntry set groupId = 0;
update TagsEntry set parentEntryId = 0;
update TagsEntry set vocabularyId = 0;

create table TagsVocabulary (
	vocabularyId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	description varchar(75) null,
	folksonomy tinyint
) engine InnoDB;

alter table User_ add reminderQueryQuestion varchar(75) null;
alter table User_ add reminderQueryAnswer varchar(75) null;

create table WSRPConfiguredProducer (
	configuredProducerId bigint not null primary key,
	name varchar(75) null,
	portalId varchar(75) null,
	namespace varchar(75) null,
	producerURL varchar(256) null,
	producerVersion varchar(75) null,
	producerMarkupURL varchar(256) null,
	status integer,
	registrationData longtext null,
	registrationContext longtext null,
	serviceDescription longtext null,
	userCategoryMapping longtext null,
	customUserProfile longtext null,
	identityPropagationType varchar(75) null,
	lifetimeTerminationTime varchar(75) null,
	sdLastModified bigint,
	entityVersion integer
) engine InnoDB;

create table WSRPConsumerRegistration (
	consumerRegistrationId bigint not null primary key,
	consumerName varchar(100) null,
	status tinyint,
	registrationHandle varchar(75) null,
	registrationData longtext null,
	lifetimeTerminationTime varchar(75) null,
	producerKey varchar(75) null
) engine InnoDB;

create table WSRPPortlet (
	portletId bigint not null primary key,
	name varchar(75) null,
	channelName varchar(75) null,
	title varchar(75) null,
	shortTitle varchar(75) null,
	displayName varchar(75) null,
	keywords varchar(75) null,
	status integer,
	producerEntityId varchar(75) null,
	consumerId varchar(75) null,
	portletHandle varchar(75) null,
	mimeTypes longtext null
) engine InnoDB;

create table WSRPProducer (
	producerId bigint not null primary key,
	portalId varchar(75) null,
	status tinyint,
	namespace varchar(75) null,
	instanceName varchar(75) null,
	requiresRegistration tinyint,
	supportsInbandRegistration tinyint,
	version varchar(75) null,
	offeredPortlets longtext null,
	producerProfileMap varchar(75) null,
	registrationProperties longtext null,
	registrationValidatorClass varchar(200) null
) engine InnoDB;

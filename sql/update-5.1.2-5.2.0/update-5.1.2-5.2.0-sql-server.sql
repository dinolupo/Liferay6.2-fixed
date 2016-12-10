alter table Company add homeURL nvarchar(2000) null;

alter table ExpandoColumn add companyId bigint;
alter table ExpandoColumn add defaultData nvarchar(2000) null;
alter table ExpandoColumn add typeSettings nvarchar(max) null;

alter table ExpandoRow add companyId bigint;

alter table ExpandoTable add companyId bigint;

alter table ExpandoValue add companyId bigint;

alter table JournalArticleImage add elInstanceId nvarchar(75) null;

alter table JournalStructure add parentStructureId nvarchar(75);

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
	active_ bit
);

alter table Organization_ add type_ nvarchar(75);

alter table Role_ add title nvarchar(2000) null;
alter table Role_ add subtype nvarchar(75);

alter table TagsAsset add visible bit;

go

update TagsAsset set visible = 1;

alter table TagsEntry add groupId bigint;
alter table TagsEntry add parentEntryId bigint;
alter table TagsEntry add vocabularyId bigint;

go

update TagsEntry set groupId = 0;
update TagsEntry set parentEntryId = 0;
update TagsEntry set vocabularyId = 0;

create table TagsVocabulary (
	vocabularyId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(75) null,
	description nvarchar(75) null,
	folksonomy bit
);

alter table User_ add reminderQueryQuestion nvarchar(75) null;
alter table User_ add reminderQueryAnswer nvarchar(75) null;

create table WSRPConfiguredProducer (
	configuredProducerId bigint not null primary key,
	name nvarchar(75) null,
	portalId nvarchar(75) null,
	namespace nvarchar(75) null,
	producerURL nvarchar(256) null,
	producerVersion nvarchar(75) null,
	producerMarkupURL nvarchar(256) null,
	status int,
	registrationData nvarchar(max) null,
	registrationContext nvarchar(max) null,
	serviceDescription nvarchar(max) null,
	userCategoryMapping nvarchar(max) null,
	customUserProfile nvarchar(max) null,
	identityPropagationType nvarchar(75) null,
	lifetimeTerminationTime nvarchar(75) null,
	sdLastModified bigint,
	entityVersion int
);

create table WSRPConsumerRegistration (
	consumerRegistrationId bigint not null primary key,
	consumerName nvarchar(100) null,
	status bit,
	registrationHandle nvarchar(75) null,
	registrationData nvarchar(max) null,
	lifetimeTerminationTime nvarchar(75) null,
	producerKey nvarchar(75) null
);

create table WSRPPortlet (
	portletId bigint not null primary key,
	name nvarchar(75) null,
	channelName nvarchar(75) null,
	title nvarchar(75) null,
	shortTitle nvarchar(75) null,
	displayName nvarchar(75) null,
	keywords nvarchar(75) null,
	status int,
	producerEntityId nvarchar(75) null,
	consumerId nvarchar(75) null,
	portletHandle nvarchar(75) null,
	mimeTypes nvarchar(2000) null
);

create table WSRPProducer (
	producerId bigint not null primary key,
	portalId nvarchar(75) null,
	status bit,
	namespace nvarchar(75) null,
	instanceName nvarchar(75) null,
	requiresRegistration bit,
	supportsInbandRegistration bit,
	version nvarchar(75) null,
	offeredPortlets nvarchar(2000) null,
	producerProfileMap nvarchar(75) null,
	registrationProperties nvarchar(2000) null,
	registrationValidatorClass nvarchar(200) null
);

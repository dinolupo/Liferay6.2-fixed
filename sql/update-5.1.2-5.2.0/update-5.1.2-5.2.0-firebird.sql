alter table Company add homeURL varchar(4000);

alter table ExpandoColumn add companyId int64;
alter table ExpandoColumn add defaultData varchar(4000);
alter table ExpandoColumn add typeSettings blob;

alter table ExpandoRow add companyId int64;

alter table ExpandoTable add companyId int64;

alter table ExpandoValue add companyId int64;

alter table JournalArticleImage add elInstanceId varchar(75);

alter table JournalStructure add parentStructureId varchar(75);

create table MBMailingList (
	uuid_ varchar(75),
	mailingListId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	categoryId int64,
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
	active_ smallint
);

alter table Organization_ add type_ varchar(75);

alter table Role_ add title varchar(4000);
alter table Role_ add subtype varchar(75);

alter table TagsAsset add visible smallint;

commit;


alter table TagsEntry add groupId int64;
alter table TagsEntry add parentEntryId int64;
alter table TagsEntry add vocabularyId int64;

commit;


create table TagsVocabulary (
	vocabularyId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	description varchar(75),
	folksonomy smallint
);

alter table User_ add reminderQueryQuestion varchar(75);
alter table User_ add reminderQueryAnswer varchar(75);

create table WSRPConfiguredProducer (
	configuredProducerId int64 not null primary key,
	name varchar(75),
	portalId varchar(75),
	namespace varchar(75),
	producerURL varchar(256),
	producerVersion varchar(75),
	producerMarkupURL varchar(256),
	status integer,
	registrationData blob,
	registrationContext blob,
	serviceDescription blob,
	userCategoryMapping blob,
	customUserProfile blob,
	identityPropagationType varchar(75),
	lifetimeTerminationTime varchar(75),
	sdLastModified int64,
	entityVersion integer
);

create table WSRPConsumerRegistration (
	consumerRegistrationId int64 not null primary key,
	consumerName varchar(100),
	status smallint,
	registrationHandle varchar(75),
	registrationData blob,
	lifetimeTerminationTime varchar(75),
	producerKey varchar(75)
);

create table WSRPPortlet (
	portletId int64 not null primary key,
	name varchar(75),
	channelName varchar(75),
	title varchar(75),
	shortTitle varchar(75),
	displayName varchar(75),
	keywords varchar(75),
	status integer,
	producerEntityId varchar(75),
	consumerId varchar(75),
	portletHandle varchar(75),
	mimeTypes varchar(4000)
);

create table WSRPProducer (
	producerId int64 not null primary key,
	portalId varchar(75),
	status smallint,
	namespace varchar(75),
	instanceName varchar(75),
	requiresRegistration smallint,
	supportsInbandRegistration smallint,
	version varchar(75),
	offeredPortlets varchar(4000),
	producerProfileMap varchar(75),
	registrationProperties varchar(4000),
	registrationValidatorClass varchar(200)
);

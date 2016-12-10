alter table Company add homeURL lvarchar;

alter table ExpandoColumn add companyId int8;
alter table ExpandoColumn add defaultData lvarchar;
alter table ExpandoColumn add typeSettings lvarchar(4096);

alter table ExpandoRow add companyId int8;

alter table ExpandoTable add companyId int8;

alter table ExpandoValue add companyId int8;

alter table JournalArticleImage add elInstanceId varchar(75);

alter table JournalStructure add parentStructureId varchar(75);

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
	active_ boolean
)
extent size 16 next size 16
lock mode row;

alter table Organization_ add type_ varchar(75);

alter table Role_ add title lvarchar;
alter table Role_ add subtype varchar(75);

alter table TagsAsset add visible boolean;



update TagsAsset set visible = 'T';

alter table TagsEntry add groupId int8;
alter table TagsEntry add parentEntryId int8;
alter table TagsEntry add vocabularyId int8;



update TagsEntry set groupId = 0;
update TagsEntry set parentEntryId = 0;
update TagsEntry set vocabularyId = 0;

create table TagsVocabulary (
	vocabularyId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	name varchar(75),
	description varchar(75),
	folksonomy boolean
)
extent size 16 next size 16
lock mode row;

alter table User_ add reminderQueryQuestion varchar(75);
alter table User_ add reminderQueryAnswer varchar(75);

create table WSRPConfiguredProducer (
	configuredProducerId int8 not null primary key,
	name varchar(75),
	portalId varchar(75),
	namespace varchar(75),
	producerURL varchar(256),
	producerVersion varchar(75),
	producerMarkupURL varchar(256),
	status int,
	registrationData text,
	registrationContext text,
	serviceDescription text,
	userCategoryMapping text,
	customUserProfile text,
	identityPropagationType varchar(75),
	lifetimeTerminationTime varchar(75),
	sdLastModified int8,
	entityVersion int
)
extent size 16 next size 16
lock mode row;

create table WSRPConsumerRegistration (
	consumerRegistrationId int8 not null primary key,
	consumerName varchar(100),
	status boolean,
	registrationHandle varchar(75),
	registrationData text,
	lifetimeTerminationTime varchar(75),
	producerKey varchar(75)
)
extent size 16 next size 16
lock mode row;

create table WSRPPortlet (
	portletId int8 not null primary key,
	name varchar(75),
	channelName varchar(75),
	title varchar(75),
	shortTitle varchar(75),
	displayName varchar(75),
	keywords varchar(75),
	status int,
	producerEntityId varchar(75),
	consumerId varchar(75),
	portletHandle varchar(75),
	mimeTypes lvarchar
)
extent size 16 next size 16
lock mode row;

create table WSRPProducer (
	producerId int8 not null primary key,
	portalId varchar(75),
	status boolean,
	namespace varchar(75),
	instanceName varchar(75),
	requiresRegistration boolean,
	supportsInbandRegistration boolean,
	version varchar(75),
	offeredPortlets lvarchar,
	producerProfileMap varchar(75),
	registrationProperties lvarchar,
	registrationValidatorClass varchar(200)
)
extent size 16 next size 16
lock mode row;

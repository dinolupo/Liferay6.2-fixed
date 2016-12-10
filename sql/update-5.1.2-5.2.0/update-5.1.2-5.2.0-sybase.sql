alter table Company add homeURL varchar(1000) null;

alter table ExpandoColumn add companyId decimal(20,0)
go
alter table ExpandoColumn add defaultData varchar(1000) null;
alter table ExpandoColumn add typeSettings text null;

alter table ExpandoRow add companyId decimal(20,0)
go

alter table ExpandoTable add companyId decimal(20,0)
go

alter table ExpandoValue add companyId decimal(20,0)
go

alter table JournalArticleImage add elInstanceId varchar(75) null;

alter table JournalStructure add parentStructureId varchar(75)
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
	active_ int
)
go

alter table Organization_ add type_ varchar(75)
go

alter table Role_ add title varchar(1000) null;
alter table Role_ add subtype varchar(75)
go

alter table TagsAsset add visible int;

go

update TagsAsset set visible = 1;

alter table TagsEntry add groupId decimal(20,0)
go
alter table TagsEntry add parentEntryId decimal(20,0)
go
alter table TagsEntry add vocabularyId decimal(20,0)
go

go

update TagsEntry set groupId = 0;
update TagsEntry set parentEntryId = 0;
update TagsEntry set vocabularyId = 0;

create table TagsVocabulary (
	vocabularyId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	description varchar(75) null,
	folksonomy int
)
go

alter table User_ add reminderQueryQuestion varchar(75) null;
alter table User_ add reminderQueryAnswer varchar(75) null;

create table WSRPConfiguredProducer (
	configuredProducerId decimal(20,0) not null primary key,
	name varchar(75) null,
	portalId varchar(75) null,
	namespace varchar(75) null,
	producerURL varchar(256) null,
	producerVersion varchar(75) null,
	producerMarkupURL varchar(256) null,
	status int,
	registrationData text null,
	registrationContext text null,
	serviceDescription text null,
	userCategoryMapping text null,
	customUserProfile text null,
	identityPropagationType varchar(75) null,
	lifetimeTerminationTime varchar(75) null,
	sdLastModified decimal(20,0),
	entityVersion int
)
go

create table WSRPConsumerRegistration (
	consumerRegistrationId decimal(20,0) not null primary key,
	consumerName varchar(100) null,
	status int,
	registrationHandle varchar(75) null,
	registrationData text null,
	lifetimeTerminationTime varchar(75) null,
	producerKey varchar(75) null
)
go

create table WSRPPortlet (
	portletId decimal(20,0) not null primary key,
	name varchar(75) null,
	channelName varchar(75) null,
	title varchar(75) null,
	shortTitle varchar(75) null,
	displayName varchar(75) null,
	keywords varchar(75) null,
	status int,
	producerEntityId varchar(75) null,
	consumerId varchar(75) null,
	portletHandle varchar(75) null,
	mimeTypes varchar(1000) null
)
go

create table WSRPProducer (
	producerId decimal(20,0) not null primary key,
	portalId varchar(75) null,
	status int,
	namespace varchar(75) null,
	instanceName varchar(75) null,
	requiresRegistration int,
	supportsInbandRegistration int,
	version varchar(75) null,
	offeredPortlets varchar(1000) null,
	producerProfileMap varchar(75) null,
	registrationProperties varchar(1000) null,
	registrationValidatorClass varchar(200) null
)
go

alter table Company add homeURL varchar2(4000) null;

alter table ExpandoColumn add companyId number(30,0);
alter table ExpandoColumn add defaultData varchar2(4000) null;
alter table ExpandoColumn add typeSettings clob null;

alter table ExpandoRow add companyId number(30,0);

alter table ExpandoTable add companyId number(30,0);

alter table ExpandoValue add companyId number(30,0);

alter table JournalArticleImage add elInstanceId VARCHAR2(75 CHAR) null;

alter table JournalStructure add parentStructureId VARCHAR2(75 CHAR);

create table MBMailingList (
	uuid_ VARCHAR2(75 CHAR) null,
	mailingListId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	categoryId number(30,0),
	emailAddress VARCHAR2(75 CHAR) null,
	inProtocol VARCHAR2(75 CHAR) null,
	inServerName VARCHAR2(75 CHAR) null,
	inServerPort number(30,0),
	inUseSSL number(1, 0),
	inUserName VARCHAR2(75 CHAR) null,
	inPassword VARCHAR2(75 CHAR) null,
	inReadInterval number(30,0),
	outEmailAddress VARCHAR2(75 CHAR) null,
	outCustom number(1, 0),
	outServerName VARCHAR2(75 CHAR) null,
	outServerPort number(30,0),
	outUseSSL number(1, 0),
	outUserName VARCHAR2(75 CHAR) null,
	outPassword VARCHAR2(75 CHAR) null,
	active_ number(1, 0)
);

alter table Organization_ add type_ VARCHAR2(75 CHAR);

alter table Role_ add title varchar2(4000) null;
alter table Role_ add subtype VARCHAR2(75 CHAR);

alter table TagsAsset add visible number(1, 0);

commit;

update TagsAsset set visible = 1;

alter table TagsEntry add groupId number(30,0);
alter table TagsEntry add parentEntryId number(30,0);
alter table TagsEntry add vocabularyId number(30,0);

commit;

update TagsEntry set groupId = 0;
update TagsEntry set parentEntryId = 0;
update TagsEntry set vocabularyId = 0;

create table TagsVocabulary (
	vocabularyId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	name VARCHAR2(75 CHAR) null,
	description VARCHAR2(75 CHAR) null,
	folksonomy number(1, 0)
);

alter table User_ add reminderQueryQuestion VARCHAR2(75 CHAR) null;
alter table User_ add reminderQueryAnswer VARCHAR2(75 CHAR) null;

create table WSRPConfiguredProducer (
	configuredProducerId number(30,0) not null primary key,
	name VARCHAR2(75 CHAR) null,
	portalId VARCHAR2(75 CHAR) null,
	namespace VARCHAR2(75 CHAR) null,
	producerURL VARCHAR2(256 CHAR) null,
	producerVersion VARCHAR2(75 CHAR) null,
	producerMarkupURL VARCHAR2(256 CHAR) null,
	status number(30,0),
	registrationData clob null,
	registrationContext clob null,
	serviceDescription clob null,
	userCategoryMapping clob null,
	customUserProfile clob null,
	identityPropagationType VARCHAR2(75 CHAR) null,
	lifetimeTerminationTime VARCHAR2(75 CHAR) null,
	sdLastModified number(30,0),
	entityVersion number(30,0)
);

create table WSRPConsumerRegistration (
	consumerRegistrationId number(30,0) not null primary key,
	consumerName VARCHAR2(100 CHAR) null,
	status number(1, 0),
	registrationHandle VARCHAR2(75 CHAR) null,
	registrationData clob null,
	lifetimeTerminationTime VARCHAR2(75 CHAR) null,
	producerKey VARCHAR2(75 CHAR) null
);

create table WSRPPortlet (
	portletId number(30,0) not null primary key,
	name VARCHAR2(75 CHAR) null,
	channelName VARCHAR2(75 CHAR) null,
	title VARCHAR2(75 CHAR) null,
	shortTitle VARCHAR2(75 CHAR) null,
	displayName VARCHAR2(75 CHAR) null,
	keywords VARCHAR2(75 CHAR) null,
	status number(30,0),
	producerEntityId VARCHAR2(75 CHAR) null,
	consumerId VARCHAR2(75 CHAR) null,
	portletHandle VARCHAR2(75 CHAR) null,
	mimeTypes varchar2(4000) null
);

create table WSRPProducer (
	producerId number(30,0) not null primary key,
	portalId VARCHAR2(75 CHAR) null,
	status number(1, 0),
	namespace VARCHAR2(75 CHAR) null,
	instanceName VARCHAR2(75 CHAR) null,
	requiresRegistration number(1, 0),
	supportsInbandRegistration number(1, 0),
	version VARCHAR2(75 CHAR) null,
	offeredPortlets varchar2(4000) null,
	producerProfileMap VARCHAR2(75 CHAR) null,
	registrationProperties varchar2(4000) null,
	registrationValidatorClass VARCHAR2(200 CHAR) null
);

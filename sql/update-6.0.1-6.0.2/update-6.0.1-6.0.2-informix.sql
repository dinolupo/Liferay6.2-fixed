alter table AssetEntry add socialInformationEquity float;

alter table Company add maxUsers int;



update Company set maxUsers = 0;

update ExpandoTable set name = 'CUSTOM_FIELDS' where name = 'DEFAULT_TABLE';

alter table LayoutSet add settings_ text;

update MBMessage set categoryId = -1 where groupId = 0;

update MBThread set categoryId = -1 where groupId = 0;

alter table PasswordPolicy add minAlphanumeric int;
alter table PasswordPolicy add minLowerCase int;
alter table PasswordPolicy add minNumbers int;
alter table PasswordPolicy add minSymbols int;
alter table PasswordPolicy add minUpperCase int;
alter table PasswordPolicy add resetTicketMaxAge int8;



update PasswordPolicy set minAlphanumeric = 0;
update PasswordPolicy set minLowerCase = 0;
update PasswordPolicy set minNumbers = 0;
update PasswordPolicy set minSymbols = 0;
update PasswordPolicy set minUpperCase = 0;
update PasswordPolicy set resetTicketMaxAge = 86400;

create table SocialEquityAssetEntry (
	equityAssetEntryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	assetEntryId int8,
	informationK float,
	informationB float,
	informationEquity float
)
extent size 16 next size 16
lock mode row;

create table SocialEquityHistory (
	equityHistoryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	createDate datetime YEAR TO FRACTION,
	personalEquity int
)
extent size 16 next size 16
lock mode row;

create table SocialEquityLog (
	equityLogId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	assetEntryId int8,
	actionId varchar(75),
	actionDate int,
	type_ int,
	value int,
	validity int,
	active_ boolean
)
extent size 16 next size 16
lock mode row;

create table SocialEquitySetting (
	equitySettingId int8 not null primary key,
	groupId int8,
	companyId int8,
	classNameId int8,
	actionId varchar(75),
	type_ int,
	value int,
	validity int
)
extent size 16 next size 16
lock mode row;

create table SocialEquityUser (
	equityUserId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	contributionEquity float,
	participationK float,
	participationB float,
	participationEquity float,
	personalEquity float
)
extent size 16 next size 16
lock mode row;

create table Ticket (
	ticketId int8 not null primary key,
	companyId int8,
	createDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	key_ varchar(75),
	expirationDate datetime YEAR TO FRACTION
)
extent size 16 next size 16
lock mode row;

alter table User_ add socialContributionEquity float;
alter table User_ add socialParticipationEquity float;
alter table User_ add socialPersonalEquity float;

alter table AssetEntry add socialInformationEquity float;

alter table Company add maxUsers int;

go

update Company set maxUsers = 0;

update ExpandoTable set name = 'CUSTOM_FIELDS' where name = 'DEFAULT_TABLE';

alter table LayoutSet add settings_ text null;

update MBMessage set categoryId = -1 where groupId = 0;

update MBThread set categoryId = -1 where groupId = 0;

alter table PasswordPolicy add minAlphanumeric int;
alter table PasswordPolicy add minLowerCase int;
alter table PasswordPolicy add minNumbers int;
alter table PasswordPolicy add minSymbols int;
alter table PasswordPolicy add minUpperCase int;
alter table PasswordPolicy add resetTicketMaxAge decimal(20,0)
go

go

update PasswordPolicy set minAlphanumeric = 0;
update PasswordPolicy set minLowerCase = 0;
update PasswordPolicy set minNumbers = 0;
update PasswordPolicy set minSymbols = 0;
update PasswordPolicy set minUpperCase = 0;
update PasswordPolicy set resetTicketMaxAge = 86400;

create table SocialEquityAssetEntry (
	equityAssetEntryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	assetEntryId decimal(20,0),
	informationK float,
	informationB float,
	informationEquity float
)
go

create table SocialEquityHistory (
	equityHistoryId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	createDate datetime null,
	personalEquity int
)
go

create table SocialEquityLog (
	equityLogId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	assetEntryId decimal(20,0),
	actionId varchar(75) null,
	actionDate int,
	type_ int,
	value int,
	validity int,
	active_ int
)
go

create table SocialEquitySetting (
	equitySettingId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	classNameId decimal(20,0),
	actionId varchar(75) null,
	type_ int,
	value int,
	validity int
)
go

create table SocialEquityUser (
	equityUserId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	contributionEquity float,
	participationK float,
	participationB float,
	participationEquity float,
	personalEquity float
)
go

create table Ticket (
	ticketId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	createDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	key_ varchar(75) null,
	expirationDate datetime null
)
go

alter table User_ add socialContributionEquity float;
alter table User_ add socialParticipationEquity float;
alter table User_ add socialPersonalEquity float;

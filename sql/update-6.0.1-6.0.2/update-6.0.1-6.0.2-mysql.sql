alter table AssetEntry add socialInformationEquity double;

alter table Company add maxUsers integer;

commit;

update Company set maxUsers = 0;

update ExpandoTable set name = 'CUSTOM_FIELDS' where name = 'DEFAULT_TABLE';

alter table LayoutSet add settings_ longtext null;

update MBMessage set categoryId = -1 where groupId = 0;

update MBThread set categoryId = -1 where groupId = 0;

alter table PasswordPolicy add minAlphanumeric integer;
alter table PasswordPolicy add minLowerCase integer;
alter table PasswordPolicy add minNumbers integer;
alter table PasswordPolicy add minSymbols integer;
alter table PasswordPolicy add minUpperCase integer;
alter table PasswordPolicy add resetTicketMaxAge bigint;

commit;

update PasswordPolicy set minAlphanumeric = 0;
update PasswordPolicy set minLowerCase = 0;
update PasswordPolicy set minNumbers = 0;
update PasswordPolicy set minSymbols = 0;
update PasswordPolicy set minUpperCase = 0;
update PasswordPolicy set resetTicketMaxAge = 86400;

create table SocialEquityAssetEntry (
	equityAssetEntryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	assetEntryId bigint,
	informationK double,
	informationB double,
	informationEquity double
) engine InnoDB;

create table SocialEquityHistory (
	equityHistoryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate datetime null,
	personalEquity integer
) engine InnoDB;

create table SocialEquityLog (
	equityLogId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	assetEntryId bigint,
	actionId varchar(75) null,
	actionDate integer,
	type_ integer,
	value integer,
	validity integer,
	active_ tinyint
) engine InnoDB;

create table SocialEquitySetting (
	equitySettingId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	actionId varchar(75) null,
	type_ integer,
	value integer,
	validity integer
) engine InnoDB;

create table SocialEquityUser (
	equityUserId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	contributionEquity double,
	participationK double,
	participationB double,
	participationEquity double,
	personalEquity double
) engine InnoDB;

create table Ticket (
	ticketId bigint not null primary key,
	companyId bigint,
	createDate datetime null,
	classNameId bigint,
	classPK bigint,
	key_ varchar(75) null,
	expirationDate datetime null
) engine InnoDB;

alter table User_ add socialContributionEquity double;
alter table User_ add socialParticipationEquity double;
alter table User_ add socialPersonalEquity double;

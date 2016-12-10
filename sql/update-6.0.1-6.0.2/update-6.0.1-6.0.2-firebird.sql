alter table AssetEntry add socialInformationEquity double precision;

alter table Company add maxUsers integer;

commit;



alter table LayoutSet add settings_ blob;



alter table PasswordPolicy add minAlphanumeric integer;
alter table PasswordPolicy add minLowerCase integer;
alter table PasswordPolicy add minNumbers integer;
alter table PasswordPolicy add minSymbols integer;
alter table PasswordPolicy add minUpperCase integer;
alter table PasswordPolicy add resetTicketMaxAge int64;

commit;


create table SocialEquityAssetEntry (
	equityAssetEntryId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	assetEntryId int64,
	informationK double precision,
	informationB double precision,
	informationEquity double precision
);

create table SocialEquityHistory (
	equityHistoryId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	createDate timestamp,
	personalEquity integer
);

create table SocialEquityLog (
	equityLogId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	assetEntryId int64,
	actionId varchar(75),
	actionDate integer,
	type_ integer,
	value integer,
	validity integer,
	active_ smallint
);

create table SocialEquitySetting (
	equitySettingId int64 not null primary key,
	groupId int64,
	companyId int64,
	classNameId int64,
	actionId varchar(75),
	type_ integer,
	value integer,
	validity integer
);

create table SocialEquityUser (
	equityUserId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	contributionEquity double precision,
	participationK double precision,
	participationB double precision,
	participationEquity double precision,
	personalEquity double precision
);

create table Ticket (
	ticketId int64 not null primary key,
	companyId int64,
	createDate timestamp,
	classNameId int64,
	classPK int64,
	key_ varchar(75),
	expirationDate timestamp
);

alter table User_ add socialContributionEquity double precision;
alter table User_ add socialParticipationEquity double precision;
alter table User_ add socialPersonalEquity double precision;

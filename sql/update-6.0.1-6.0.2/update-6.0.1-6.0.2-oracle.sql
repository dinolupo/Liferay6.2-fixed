alter table AssetEntry add socialInformationEquity number(30,20);

alter table Company add maxUsers number(30,0);

commit;

update Company set maxUsers = 0;

update ExpandoTable set name = 'CUSTOM_FIELDS' where name = 'DEFAULT_TABLE';

alter table LayoutSet add settings_ clob null;

update MBMessage set categoryId = -1 where groupId = 0;

update MBThread set categoryId = -1 where groupId = 0;

alter table PasswordPolicy add minAlphanumeric number(30,0);
alter table PasswordPolicy add minLowerCase number(30,0);
alter table PasswordPolicy add minNumbers number(30,0);
alter table PasswordPolicy add minSymbols number(30,0);
alter table PasswordPolicy add minUpperCase number(30,0);
alter table PasswordPolicy add resetTicketMaxAge number(30,0);

commit;

update PasswordPolicy set minAlphanumeric = 0;
update PasswordPolicy set minLowerCase = 0;
update PasswordPolicy set minNumbers = 0;
update PasswordPolicy set minSymbols = 0;
update PasswordPolicy set minUpperCase = 0;
update PasswordPolicy set resetTicketMaxAge = 86400;

create table SocialEquityAssetEntry (
	equityAssetEntryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	assetEntryId number(30,0),
	informationK number(30,20),
	informationB number(30,20),
	informationEquity number(30,20)
);

create table SocialEquityHistory (
	equityHistoryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	createDate timestamp null,
	personalEquity number(30,0)
);

create table SocialEquityLog (
	equityLogId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	assetEntryId number(30,0),
	actionId VARCHAR2(75 CHAR) null,
	actionDate number(30,0),
	type_ number(30,0),
	value number(30,0),
	validity number(30,0),
	active_ number(1, 0)
);

create table SocialEquitySetting (
	equitySettingId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	classNameId number(30,0),
	actionId VARCHAR2(75 CHAR) null,
	type_ number(30,0),
	value number(30,0),
	validity number(30,0)
);

create table SocialEquityUser (
	equityUserId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	contributionEquity number(30,20),
	participationK number(30,20),
	participationB number(30,20),
	participationEquity number(30,20),
	personalEquity number(30,20)
);

create table Ticket (
	ticketId number(30,0) not null primary key,
	companyId number(30,0),
	createDate timestamp null,
	classNameId number(30,0),
	classPK number(30,0),
	key_ VARCHAR2(75 CHAR) null,
	expirationDate timestamp null
);

alter table User_ add socialContributionEquity number(30,20);
alter table User_ add socialParticipationEquity number(30,20);
alter table User_ add socialPersonalEquity number(30,20);

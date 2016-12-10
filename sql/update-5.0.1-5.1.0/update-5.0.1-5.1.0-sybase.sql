alter table BlogsEntry add draft int;
alter table BlogsEntry add allowTrackbacks int;
alter table BlogsEntry add trackbacks text null;

go

update BlogsEntry set draft = 0;
update BlogsEntry set allowTrackbacks = 1;

alter table Contact_ add facebookSn varchar(75) null;
alter table Contact_ add mySpaceSn varchar(75) null;
alter table Contact_ add twitterSn varchar(75) null;

update Country set a2 = 'KR' where countryId = 10;
update Country set a2 = 'CR' where countryId = 69;
update Country set a2 = 'NI', a3 = 'NIC' where countryId = 159;
update Country set a2 = 'RS', a3 = 'SRB' where countryId = 189;

drop table ExpandoRow;

create table ExpandoRow (
	rowId_ decimal(20,0) not null primary key,
	tableId decimal(20,0),
	classPK decimal(20,0)
)
go

drop table ExpandoValue;

create table ExpandoValue (
	valueId decimal(20,0) not null primary key,
	tableId decimal(20,0),
	columnId decimal(20,0),
	rowId_ decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0),
	data_ varchar(1000) null
)
go

drop table SocialActivity;

create table SocialActivity (
	activityId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	createDate datetime null,
	mirrorActivityId decimal(20,0),
	classNameId decimal(20,0),
	classPK decimal(20,0),
	type_ int,
	extraData varchar(1000) null,
	receiverUserId decimal(20,0)
)
go

create table SocialRequest (
	uuid_ varchar(75) null,
	requestId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	userId decimal(20,0),
	createDate datetime null,
	modifiedDate datetime null,
	classNameId decimal(20,0),
	classPK decimal(20,0),
	type_ int,
	extraData varchar(1000) null,
	receiverUserId decimal(20,0),
	status int
)
go

alter table User_ add openId varchar(1024) null;

update User_ set timeZoneId = 'America/Anchorage' where timeZoneId = 'AST';
update User_ set timeZoneId = 'America/Los_Angeles' where timeZoneId = 'PST';
update User_ set timeZoneId = 'America/Denver' where timeZoneId = 'MST';
update User_ set timeZoneId = 'America/Chicago' where timeZoneId = 'CST';
update User_ set timeZoneId = 'America/New_York' where timeZoneId = 'EST';
update User_ set timeZoneId = 'America/Puerto_Rico' where timeZoneId = 'PRT';
update User_ set timeZoneId = 'America/St_Johns' where timeZoneId = 'CNT';
update User_ set timeZoneId = 'America/Sao_Paulo' where timeZoneId = 'BET';
update User_ set timeZoneId = 'UTC' where timeZoneId = 'GMT';
update User_ set timeZoneId = 'Europe/Lisbon' where timeZoneId = 'WET';
update User_ set timeZoneId = 'Europe/Paris' where timeZoneId = 'CET';
update User_ set timeZoneId = 'Europe/Istanbul' where timeZoneId = 'EET';
update User_ set timeZoneId = 'Asia/Tehran' where timeZoneId = 'Iran';
update User_ set timeZoneId = 'Asia/Calcutta' where timeZoneId = 'IST';
update User_ set timeZoneId = 'Asia/Saigon' where timeZoneId = 'VST';
update User_ set timeZoneId = 'Asia/Shanghai' where timeZoneId = 'CTT';
update User_ set timeZoneId = 'Asia/Tokyo' where timeZoneId = 'JST';
update User_ set timeZoneId = 'Asia/Seoul' where timeZoneId = 'ROK';
update User_ set timeZoneId = 'Australia/Darwin' where timeZoneId = 'ACT';
update User_ set timeZoneId = 'Australia/Sydney' where timeZoneId = 'AET';
update User_ set timeZoneId = 'Pacific/Guadalcanal' where timeZoneId = 'SST';
update User_ set timeZoneId = 'Pacific/Auckland' where timeZoneId = 'NST';

alter table WikiPage add modifiedDate datetime null;
alter table WikiPage add summary varchar(1000) null;

go

update WikiPage set modifiedDate = createDate;

alter table BlogsEntry add draft boolean;
alter table BlogsEntry add allowTrackbacks boolean;
alter table BlogsEntry add trackbacks text;



update BlogsEntry set draft = 'F';
update BlogsEntry set allowTrackbacks = 'T';

alter table Contact_ add facebookSn varchar(75);
alter table Contact_ add mySpaceSn varchar(75);
alter table Contact_ add twitterSn varchar(75);

update Country set a2 = 'KR' where countryId = 10;
update Country set a2 = 'CR' where countryId = 69;
update Country set a2 = 'NI', a3 = 'NIC' where countryId = 159;
update Country set a2 = 'RS', a3 = 'SRB' where countryId = 189;

drop table ExpandoRow;

create table ExpandoRow (
	rowId_ int8 not null primary key,
	tableId int8,
	classPK int8
)
extent size 16 next size 16
lock mode row;

drop table ExpandoValue;

create table ExpandoValue (
	valueId int8 not null primary key,
	tableId int8,
	columnId int8,
	rowId_ int8,
	classNameId int8,
	classPK int8,
	data_ lvarchar
)
extent size 16 next size 16
lock mode row;

drop table SocialActivity;

create table SocialActivity (
	activityId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	createDate datetime YEAR TO FRACTION,
	mirrorActivityId int8,
	classNameId int8,
	classPK int8,
	type_ int,
	extraData lvarchar,
	receiverUserId int8
)
extent size 16 next size 16
lock mode row;

create table SocialRequest (
	uuid_ varchar(75),
	requestId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	classNameId int8,
	classPK int8,
	type_ int,
	extraData lvarchar,
	receiverUserId int8,
	status int
)
extent size 16 next size 16
lock mode row;

alter table User_ add openId lvarchar(1024);

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

alter table WikiPage add modifiedDate datetime YEAR TO FRACTION;
alter table WikiPage add summary lvarchar;



update WikiPage set modifiedDate = createDate;

alter table BlogsEntry add draft bit;
alter table BlogsEntry add allowTrackbacks bit;
alter table BlogsEntry add trackbacks nvarchar(max) null;

go

update BlogsEntry set draft = 0;
update BlogsEntry set allowTrackbacks = 1;

alter table Contact_ add facebookSn nvarchar(75) null;
alter table Contact_ add mySpaceSn nvarchar(75) null;
alter table Contact_ add twitterSn nvarchar(75) null;

update Country set a2 = 'KR' where countryId = 10;
update Country set a2 = 'CR' where countryId = 69;
update Country set a2 = 'NI', a3 = 'NIC' where countryId = 159;
update Country set a2 = 'RS', a3 = 'SRB' where countryId = 189;

drop table ExpandoRow;

create table ExpandoRow (
	rowId_ bigint not null primary key,
	tableId bigint,
	classPK bigint
);

drop table ExpandoValue;

create table ExpandoValue (
	valueId bigint not null primary key,
	tableId bigint,
	columnId bigint,
	rowId_ bigint,
	classNameId bigint,
	classPK bigint,
	data_ nvarchar(2000) null
);

drop table SocialActivity;

create table SocialActivity (
	activityId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate datetime null,
	mirrorActivityId bigint,
	classNameId bigint,
	classPK bigint,
	type_ int,
	extraData nvarchar(2000) null,
	receiverUserId bigint
);

create table SocialRequest (
	uuid_ nvarchar(75) null,
	requestId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	createDate datetime null,
	modifiedDate datetime null,
	classNameId bigint,
	classPK bigint,
	type_ int,
	extraData nvarchar(2000) null,
	receiverUserId bigint,
	status int
);

alter table User_ add openId nvarchar(1024) null;

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
alter table WikiPage add summary nvarchar(2000) null;

go

update WikiPage set modifiedDate = createDate;

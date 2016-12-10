alter table BlogsEntry add draft smallint;
alter table BlogsEntry add allowTrackbacks smallint;
alter table BlogsEntry add trackbacks blob;

commit;


alter table Contact_ add facebookSn varchar(75);
alter table Contact_ add mySpaceSn varchar(75);
alter table Contact_ add twitterSn varchar(75);


drop table ExpandoRow;

create table ExpandoRow (
	rowId_ int64 not null primary key,
	tableId int64,
	classPK int64
);

drop table ExpandoValue;

create table ExpandoValue (
	valueId int64 not null primary key,
	tableId int64,
	columnId int64,
	rowId_ int64,
	classNameId int64,
	classPK int64,
	data_ varchar(4000)
);

drop table SocialActivity;

create table SocialActivity (
	activityId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	createDate timestamp,
	mirrorActivityId int64,
	classNameId int64,
	classPK int64,
	type_ integer,
	extraData varchar(4000),
	receiverUserId int64
);

create table SocialRequest (
	uuid_ varchar(75),
	requestId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	createDate timestamp,
	modifiedDate timestamp,
	classNameId int64,
	classPK int64,
	type_ integer,
	extraData varchar(4000),
	receiverUserId int64,
	status integer
);

alter table User_ add openId varchar(1024);


alter table WikiPage add modifiedDate timestamp;
alter table WikiPage add summary varchar(4000);

commit;


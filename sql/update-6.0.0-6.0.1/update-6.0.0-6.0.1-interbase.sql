create table AssetLink (
	linkId int64 not null primary key,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	entryId1 int64,
	entryId2 int64,
	type_ integer,
	weight integer
);

create table Team (
	teamId int64 not null primary key,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	groupId int64,
	name varchar(75),
	description varchar(4000)
);

create table Users_Teams (
	userId int64 not null,
	teamId int64 not null,
	primary key (userId, teamId)
);

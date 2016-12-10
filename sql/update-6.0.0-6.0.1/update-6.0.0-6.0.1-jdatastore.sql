create table AssetLink (
	linkId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	entryId1 bigint,
	entryId2 bigint,
	type_ integer,
	weight integer
);

create table Team (
	teamId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate date null,
	modifiedDate date null,
	groupId bigint,
	name varchar(75) null,
	description long varchar null
);

create table Users_Teams (
	userId bigint not null,
	teamId bigint not null,
	primary key (userId, teamId)
);

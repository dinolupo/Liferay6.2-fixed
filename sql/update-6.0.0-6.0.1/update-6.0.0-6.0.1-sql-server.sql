create table AssetLink (
	linkId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	entryId1 bigint,
	entryId2 bigint,
	type_ int,
	weight int
);

create table Team (
	teamId bigint not null primary key,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	groupId bigint,
	name nvarchar(75) null,
	description nvarchar(2000) null
);

create table Users_Teams (
	userId bigint not null,
	teamId bigint not null,
	primary key (userId, teamId)
);

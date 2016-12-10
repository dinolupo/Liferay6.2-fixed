create table AssetLink (
	linkId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	entryId1 int8,
	entryId2 int8,
	type_ int,
	weight int
)
extent size 16 next size 16
lock mode row;

create table Team (
	teamId int8 not null primary key,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	groupId int8,
	name varchar(75),
	description lvarchar
)
extent size 16 next size 16
lock mode row;

create table Users_Teams (
	userId int8 not null,
	teamId int8 not null,
	primary key (userId, teamId)
)
extent size 16 next size 16
lock mode row;

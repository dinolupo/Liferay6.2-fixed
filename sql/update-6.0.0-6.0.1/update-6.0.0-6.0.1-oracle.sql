create table AssetLink (
	linkId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	entryId1 number(30,0),
	entryId2 number(30,0),
	type_ number(30,0),
	weight number(30,0)
);

create table Team (
	teamId number(30,0) not null primary key,
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	groupId number(30,0),
	name VARCHAR2(75 CHAR) null,
	description varchar2(4000) null
);

create table Users_Teams (
	userId number(30,0) not null,
	teamId number(30,0) not null,
	primary key (userId, teamId)
);

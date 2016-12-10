alter table JournalArticleResource add uuid_ varchar(75);

create table SocialEquityGroupSetting (
	equityGroupSettingId int64 not null primary key,
	groupId int64,
	companyId int64,
	classNameId int64,
	type_ integer,
	enabled smallint
);

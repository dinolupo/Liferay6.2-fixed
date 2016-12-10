alter table JournalArticleResource add uuid_ varchar(75);

create table SocialEquityGroupSetting (
	equityGroupSettingId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	type_ integer,
	enabled smallint
);

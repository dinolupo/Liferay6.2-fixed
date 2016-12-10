alter table JournalArticleResource add uuid_ nvarchar(75) null;

create table SocialEquityGroupSetting (
	equityGroupSettingId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	classNameId bigint,
	type_ int,
	enabled bit
);

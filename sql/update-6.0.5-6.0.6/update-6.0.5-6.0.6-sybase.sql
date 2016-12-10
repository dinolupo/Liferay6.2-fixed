alter table JournalArticleResource add uuid_ varchar(75) null;

create table SocialEquityGroupSetting (
	equityGroupSettingId decimal(20,0) not null primary key,
	groupId decimal(20,0),
	companyId decimal(20,0),
	classNameId decimal(20,0),
	type_ int,
	enabled int
)
go

alter table JournalArticleResource add uuid_ varchar(75);

create table SocialEquityGroupSetting (
	equityGroupSettingId int8 not null primary key,
	groupId int8,
	companyId int8,
	classNameId int8,
	type_ int,
	enabled boolean
)
extent size 16 next size 16
lock mode row;

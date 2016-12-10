alter table JournalArticleResource add uuid_ VARCHAR2(75 CHAR) null;

create table SocialEquityGroupSetting (
	equityGroupSettingId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	classNameId number(30,0),
	type_ number(30,0),
	enabled number(1, 0)
);

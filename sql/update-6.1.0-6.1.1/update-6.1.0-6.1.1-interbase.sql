alter table DLFileVersion add uuid_ varchar(75);

alter table DLSync add description varchar(4000);

alter table LayoutSetBranch add logo smallint;
alter table LayoutSetBranch add logoId int64;
alter table LayoutSetBranch add themeId varchar(75);
alter table LayoutSetBranch add colorSchemeId varchar(75);
alter table LayoutSetBranch add wapThemeId varchar(75);
alter table LayoutSetBranch add wapColorSchemeId varchar(75);
alter table LayoutSetBranch add css varchar(4000);
alter table LayoutSetBranch add settings_ varchar(4000);
alter table LayoutSetBranch add layoutSetPrototypeUuid varchar(75);
alter table LayoutSetBranch add layoutSetPrototypeLinkEnabled smallint;

commit;



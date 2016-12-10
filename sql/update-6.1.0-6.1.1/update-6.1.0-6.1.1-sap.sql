alter table DLFileVersion add uuid_ varchar(75) null;

alter table DLSync add description varchar null;

alter table LayoutSetBranch add logo boolean;
alter table LayoutSetBranch add logoId bigint;
alter table LayoutSetBranch add themeId varchar(75) null;
alter table LayoutSetBranch add colorSchemeId varchar(75) null;
alter table LayoutSetBranch add wapThemeId varchar(75) null;
alter table LayoutSetBranch add wapColorSchemeId varchar(75) null;
alter table LayoutSetBranch add css varchar null;
alter table LayoutSetBranch add settings_ varchar null;
alter table LayoutSetBranch add layoutSetPrototypeUuid varchar(75) null;
alter table LayoutSetBranch add layoutSetPrototypeLinkEnabled boolean;

commit;

update LayoutSetBranch set logo = TRUE;
update LayoutSetBranch set logoId = 0;
update LayoutSetBranch set layoutSetPrototypeLinkEnabled = FALSE;

update PluginSetting set pluginType = 'layouttpl' where pluginType = 'layout-template';

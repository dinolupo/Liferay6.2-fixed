alter table DLFileVersion add uuid_ varchar(75) null;

alter table DLSync add description text null;

alter table LayoutSetBranch add logo bool;
alter table LayoutSetBranch add logoId bigint;
alter table LayoutSetBranch add themeId varchar(75) null;
alter table LayoutSetBranch add colorSchemeId varchar(75) null;
alter table LayoutSetBranch add wapThemeId varchar(75) null;
alter table LayoutSetBranch add wapColorSchemeId varchar(75) null;
alter table LayoutSetBranch add css text null;
alter table LayoutSetBranch add settings_ text null;
alter table LayoutSetBranch add layoutSetPrototypeUuid varchar(75) null;
alter table LayoutSetBranch add layoutSetPrototypeLinkEnabled bool;

commit;

update LayoutSetBranch set logo = true;
update LayoutSetBranch set logoId = 0;
update LayoutSetBranch set layoutSetPrototypeLinkEnabled = false;

update PluginSetting set pluginType = 'layouttpl' where pluginType = 'layout-template';

alter table DLFileVersion add uuid_ varchar(75) null;

alter table DLSync add description varchar(1000) null;

alter table LayoutSetBranch add logo tinyint;
alter table LayoutSetBranch add logoId bigint;
alter table LayoutSetBranch add themeId varchar(75) null;
alter table LayoutSetBranch add colorSchemeId varchar(75) null;
alter table LayoutSetBranch add wapThemeId varchar(75) null;
alter table LayoutSetBranch add wapColorSchemeId varchar(75) null;
alter table LayoutSetBranch add css varchar(1000) null;
alter table LayoutSetBranch add settings_ varchar(1000) null;
alter table LayoutSetBranch add layoutSetPrototypeUuid varchar(75) null;
alter table LayoutSetBranch add layoutSetPrototypeLinkEnabled tinyint;

commit;\g

update LayoutSetBranch set logo = 1;
update LayoutSetBranch set logoId = 0;
update LayoutSetBranch set layoutSetPrototypeLinkEnabled = 0;

update PluginSetting set pluginType = 'layouttpl' where pluginType = 'layout-template';

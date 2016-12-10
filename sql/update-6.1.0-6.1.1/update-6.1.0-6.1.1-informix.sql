alter table DLFileVersion add uuid_ varchar(75);

alter table DLSync add description lvarchar;

alter table LayoutSetBranch add logo boolean;
alter table LayoutSetBranch add logoId int8;
alter table LayoutSetBranch add themeId varchar(75);
alter table LayoutSetBranch add colorSchemeId varchar(75);
alter table LayoutSetBranch add wapThemeId varchar(75);
alter table LayoutSetBranch add wapColorSchemeId varchar(75);
alter table LayoutSetBranch add css lvarchar;
alter table LayoutSetBranch add settings_ lvarchar;
alter table LayoutSetBranch add layoutSetPrototypeUuid varchar(75);
alter table LayoutSetBranch add layoutSetPrototypeLinkEnabled boolean;



update LayoutSetBranch set logo = 'T';
update LayoutSetBranch set logoId = 0;
update LayoutSetBranch set layoutSetPrototypeLinkEnabled = 'F';

update PluginSetting set pluginType = 'layouttpl' where pluginType = 'layout-template';

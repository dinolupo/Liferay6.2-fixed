alter table DLFileVersion add uuid_ nvarchar(75) null;

alter table DLSync add description nvarchar(2000) null;

alter table LayoutSetBranch add logo bit;
alter table LayoutSetBranch add logoId bigint;
alter table LayoutSetBranch add themeId nvarchar(75) null;
alter table LayoutSetBranch add colorSchemeId nvarchar(75) null;
alter table LayoutSetBranch add wapThemeId nvarchar(75) null;
alter table LayoutSetBranch add wapColorSchemeId nvarchar(75) null;
alter table LayoutSetBranch add css nvarchar(2000) null;
alter table LayoutSetBranch add settings_ nvarchar(2000) null;
alter table LayoutSetBranch add layoutSetPrototypeUuid nvarchar(75) null;
alter table LayoutSetBranch add layoutSetPrototypeLinkEnabled bit;

go

update LayoutSetBranch set logo = 1;
update LayoutSetBranch set logoId = 0;
update LayoutSetBranch set layoutSetPrototypeLinkEnabled = 0;

update PluginSetting set pluginType = 'layouttpl' where pluginType = 'layout-template';

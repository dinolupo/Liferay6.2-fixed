alter table DLFileVersion add uuid_ VARCHAR2(75 CHAR) null;

alter table DLSync add description varchar2(4000) null;

alter table LayoutSetBranch add logo number(1, 0);
alter table LayoutSetBranch add logoId number(30,0);
alter table LayoutSetBranch add themeId VARCHAR2(75 CHAR) null;
alter table LayoutSetBranch add colorSchemeId VARCHAR2(75 CHAR) null;
alter table LayoutSetBranch add wapThemeId VARCHAR2(75 CHAR) null;
alter table LayoutSetBranch add wapColorSchemeId VARCHAR2(75 CHAR) null;
alter table LayoutSetBranch add css varchar2(4000) null;
alter table LayoutSetBranch add settings_ varchar2(4000) null;
alter table LayoutSetBranch add layoutSetPrototypeUuid VARCHAR2(75 CHAR) null;
alter table LayoutSetBranch add layoutSetPrototypeLinkEnabled number(1, 0);

commit;

update LayoutSetBranch set logo = 1;
update LayoutSetBranch set logoId = 0;
update LayoutSetBranch set layoutSetPrototypeLinkEnabled = 0;

update PluginSetting set pluginType = 'layouttpl' where pluginType = 'layout-template';

alter table WikiPage add minorEdit boolean;

commit;

update WikiPage set minorEdit = FALSE;

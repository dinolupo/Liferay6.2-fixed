alter table WikiPage add minorEdit smallint;

commit;

update WikiPage set minorEdit = 0;

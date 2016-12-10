alter table WikiPage add minorEdit tinyint;

commit;

update WikiPage set minorEdit = 0;

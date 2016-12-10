alter table WikiPage add minorEdit number(1, 0);

commit;

update WikiPage set minorEdit = 0;

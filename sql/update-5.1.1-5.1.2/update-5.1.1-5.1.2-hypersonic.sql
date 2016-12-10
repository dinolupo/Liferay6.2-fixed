alter table WikiPage add minorEdit bit;

commit;

update WikiPage set minorEdit = false;

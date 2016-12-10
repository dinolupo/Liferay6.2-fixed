alter table WikiPage add minorEdit bool;

commit;

update WikiPage set minorEdit = false;

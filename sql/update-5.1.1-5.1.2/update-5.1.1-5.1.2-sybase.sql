alter table WikiPage add minorEdit int;

go

update WikiPage set minorEdit = 0;

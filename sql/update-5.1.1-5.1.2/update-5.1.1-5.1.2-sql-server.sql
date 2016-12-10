alter table WikiPage add minorEdit bit;

go

update WikiPage set minorEdit = 0;

alter table WikiPage add minorEdit tinyint;

commit;\g

update WikiPage set minorEdit = 0;

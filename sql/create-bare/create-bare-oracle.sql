drop user &1 cascade;
create user &1 identified by &2;
grant connect,resource to &1;
quit
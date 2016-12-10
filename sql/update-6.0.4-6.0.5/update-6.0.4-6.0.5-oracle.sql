create table ClusterGroup (
	clusterGroupId number(30,0) not null primary key,
	name VARCHAR2(75 CHAR) null,
	clusterNodeIds VARCHAR2(75 CHAR) null,
	wholeCluster number(1, 0)
);

alter table User_ add digest VARCHAR2(256 CHAR) null;

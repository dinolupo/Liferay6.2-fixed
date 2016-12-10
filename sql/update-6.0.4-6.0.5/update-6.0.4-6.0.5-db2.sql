create table ClusterGroup (
	clusterGroupId bigint not null primary key,
	name varchar(75),
	clusterNodeIds varchar(75),
	wholeCluster smallint
);

alter table User_ add digest varchar(256);

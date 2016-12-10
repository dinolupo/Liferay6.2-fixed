create table ClusterGroup (
	clusterGroupId int64 not null primary key,
	name varchar(75),
	clusterNodeIds varchar(75),
	wholeCluster smallint
);

alter table User_ add digest varchar(256);

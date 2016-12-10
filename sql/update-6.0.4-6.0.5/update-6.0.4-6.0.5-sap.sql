create table ClusterGroup (
	clusterGroupId bigint not null primary key,
	name varchar(75) null,
	clusterNodeIds varchar(75) null,
	wholeCluster boolean
);

alter table User_ add digest varchar(256) null;

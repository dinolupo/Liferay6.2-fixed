create table ClusterGroup (
	clusterGroupId bigint not null primary key,
	name nvarchar(75) null,
	clusterNodeIds nvarchar(75) null,
	wholeCluster bit
);

alter table User_ add digest nvarchar(256) null;

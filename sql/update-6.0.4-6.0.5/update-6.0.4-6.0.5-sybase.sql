create table ClusterGroup (
	clusterGroupId decimal(20,0) not null primary key,
	name varchar(75) null,
	clusterNodeIds varchar(75) null,
	wholeCluster int
)
go

alter table User_ add digest varchar(256) null;

create table ClusterGroup (
	clusterGroupId int8 not null primary key,
	name varchar(75),
	clusterNodeIds varchar(75),
	wholeCluster boolean
)
extent size 16 next size 16
lock mode row;

alter table User_ add digest varchar(256);

create table EP_BCOLONY_MASTER_DATA(
id nvarchar(30),
customerId int,
type nvarchar(10),
name nvarchar(50),
speciesId int,
description nvarchar(2000),
createdOn numeric,
modifiedOn numeric,
createdBy nvarchar(50),
modifiedBy nvarchar(50),
deleted int
);

alter table EP_BCOLONY_MASTER_DATA add constraint BCOLONY_MASTER_DATA_PK primary key(id);
create index EP_BCOLONY_MASTER_DATA_INDX1 on EP_BCOLONY_MASTER_DATA(name,type,speciesId,customerId);
create index EP_BCOLONY_MASTER_DATA_INDX2 on EP_BCOLONY_MASTER_DATA(type,speciesId,customerId);

create table EP_BCOLONY_GROUP_MSTR(
id nvarchar(30),
customerId int,
name nvarchar(50),
speciesId int,
description nvarchar(2000),
createdOn numeric,
modifiedOn numeric,
createdBy nvarchar(50),
modifiedBy nvarchar(50),
deleted int
);

alter table BCOLONY_GROUP_MSTR add constraint BCOLONY_GROUP_MSTR_PK primary key(id);
create index BCOLONY_GROUP_MSTR_INDX1 on BCOLONY_GROUP_MSTR(name,speciesId,customerId);
create index BCOLONY_GROUP_MSTR_INDX2 on BCOLONY_GROUP_MSTR(customerId);
create index BCOLONY_GROUP_MSTR_INDX3 on BCOLONY_GROUP_MSTR(speciesId,customerId);
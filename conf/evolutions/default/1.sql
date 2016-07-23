# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table item (
  id                        serial not null,
  name                      varchar(255),
  description               varchar(255),
  quantity                  integer,
  price                     float,
  minimum_price             float,
  sale_id                   integer,
  constraint pk_item primary key (id))
;

create table receipt (
  id                        serial not null,
  date                      varchar(255),
  totalprofit               float,
  user_id                   integer,
  sale_id                   integer,
  constraint pk_receipt primary key (id))
;

create table role (
  id                        serial not null,
  user_id                   integer,
  sale_id                   integer,
  role                      integer,
  constraint ck_role_role check (role in (0,1,2,3,4)),
  constraint pk_role primary key (id))
;

create table sale (
  id                        serial not null,
  name                      varchar(255),
  location                  varchar(255),
  date                      varchar(255),
  earnings                  float,
  is_open                   boolean,
  constraint pk_sale primary key (id))
;

create table transaction (
  id                        serial not null,
  quantity                  integer,
  profit                    float,
  method                    varchar(255),
  buyer                     varchar(255),
  sale_id                   integer,
  item_id                   integer,
  receipt_id                integer,
  constraint pk_transaction primary key (id))
;

create table user_table (
  id                        serial not null,
  user_name                 varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  phone_number              varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  login_attempts            integer,
  is_locked                 boolean,
  is_super_user             boolean,
  constraint uq_user_table_user_name unique (user_name),
  constraint pk_user_table primary key (id))
;

alter table item add constraint fk_item_sale_1 foreign key (sale_id) references sale (id);
create index ix_item_sale_1 on item (sale_id);
alter table receipt add constraint fk_receipt_user_2 foreign key (user_id) references user_table (id);
create index ix_receipt_user_2 on receipt (user_id);
alter table receipt add constraint fk_receipt_sale_3 foreign key (sale_id) references sale (id);
create index ix_receipt_sale_3 on receipt (sale_id);
alter table role add constraint fk_role_user_4 foreign key (user_id) references user_table (id);
create index ix_role_user_4 on role (user_id);
alter table role add constraint fk_role_sale_5 foreign key (sale_id) references sale (id);
create index ix_role_sale_5 on role (sale_id);
alter table transaction add constraint fk_transaction_sale_6 foreign key (sale_id) references sale (id);
create index ix_transaction_sale_6 on transaction (sale_id);
alter table transaction add constraint fk_transaction_item_7 foreign key (item_id) references item (id);
create index ix_transaction_item_7 on transaction (item_id);
alter table transaction add constraint fk_transaction_receipt_8 foreign key (receipt_id) references receipt (id);
create index ix_transaction_receipt_8 on transaction (receipt_id);



# --- !Downs

drop table if exists item cascade;

drop table if exists receipt cascade;

drop table if exists role cascade;

drop table if exists sale cascade;

drop table if exists transaction cascade;

drop table if exists user_table cascade;


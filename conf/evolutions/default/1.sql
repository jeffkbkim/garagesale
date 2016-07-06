# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table item (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  quantity                  integer,
  price                     double,
  sale_id                   integer,
  constraint pk_item primary key (id))
;

create table receipt (
  id                        integer not null,
  date                      varchar(255),
  totalprofit               double,
  sale_id                   integer,
  constraint pk_receipt primary key (id))
;

create table role (
  id                        integer not null,
  user_id                   integer,
  sale_id                   integer,
  role                      integer,
  constraint ck_role_role check (role in (0,1,2,3,4)),
  constraint pk_role primary key (id))
;

create table sale (
  id                        integer not null,
  name                      varchar(255),
  location                  varchar(255),
  earnings                  double,
  constraint pk_sale primary key (id))
;

create table transaction (
  id                        integer not null,
  quantity                  integer,
  profit                    double,
  buyer                     varchar(255),
  sale_id                   integer,
  item_id                   integer,
  receipt_id                integer,
  constraint pk_transaction primary key (id))
;

create table user (
  id                        integer not null,
  user_name                 varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  phone_number              varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  login_attempts            integer,
  is_locked                 boolean,
  constraint uq_user_user_name unique (user_name),
  constraint pk_user primary key (id))
;

create sequence item_seq;

create sequence receipt_seq;

create sequence role_seq;

create sequence sale_seq;

create sequence transaction_seq;

create sequence user_seq;

alter table item add constraint fk_item_sale_1 foreign key (sale_id) references sale (id) on delete restrict on update restrict;
create index ix_item_sale_1 on item (sale_id);
alter table receipt add constraint fk_receipt_sale_2 foreign key (sale_id) references sale (id) on delete restrict on update restrict;
create index ix_receipt_sale_2 on receipt (sale_id);
alter table role add constraint fk_role_user_3 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_role_user_3 on role (user_id);
alter table role add constraint fk_role_sale_4 foreign key (sale_id) references sale (id) on delete restrict on update restrict;
create index ix_role_sale_4 on role (sale_id);
alter table transaction add constraint fk_transaction_sale_5 foreign key (sale_id) references sale (id) on delete restrict on update restrict;
create index ix_transaction_sale_5 on transaction (sale_id);
alter table transaction add constraint fk_transaction_item_6 foreign key (item_id) references item (id) on delete restrict on update restrict;
create index ix_transaction_item_6 on transaction (item_id);
alter table transaction add constraint fk_transaction_receipt_7 foreign key (receipt_id) references receipt (id) on delete restrict on update restrict;
create index ix_transaction_receipt_7 on transaction (receipt_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists item;

drop table if exists receipt;

drop table if exists role;

drop table if exists sale;

drop table if exists transaction;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists item_seq;

drop sequence if exists receipt_seq;

drop sequence if exists role_seq;

drop sequence if exists sale_seq;

drop sequence if exists transaction_seq;

drop sequence if exists user_seq;


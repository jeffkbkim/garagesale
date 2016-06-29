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

create table sale (
  sale_id                   integer not null,
  name                      varchar(255),
  location                  varchar(255),
  earnings                  double,
  user_id                   integer,
  constraint pk_sale primary key (sale_id))
;

create table transaction (
  id                        integer not null,
  item_id                   integer,
  quantity                  integer,
  profit                    double,
  buyer                     varchar(255),
  sale_id                   integer,
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
  level                     integer,
  role                      integer,
  constraint pk_user primary key (id))
;

create sequence item_seq;

create sequence sale_seq;

create sequence transaction_seq;

create sequence user_seq;

alter table item add constraint fk_item_sale_1 foreign key (sale_id) references sale (sale_id) on delete restrict on update restrict;
create index ix_item_sale_1 on item (sale_id);
alter table sale add constraint fk_sale_user_2 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_sale_user_2 on sale (user_id);
alter table transaction add constraint fk_transaction_sale_3 foreign key (sale_id) references sale (sale_id) on delete restrict on update restrict;
create index ix_transaction_sale_3 on transaction (sale_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists item;

drop table if exists sale;

drop table if exists transaction;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists item_seq;

drop sequence if exists sale_seq;

drop sequence if exists transaction_seq;

drop sequence if exists user_seq;


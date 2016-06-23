# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table item (
  id                        integer not null,
  name                      varchar(255),
  quantity                  double,
  price                     double,
  description               varchar(255),
  constraint pk_item primary key (id))
;

create table sale (
  sale_id                   integer not null,
  name                      varchar(255),
  location                  varchar(255),
  earnings                  double,
  constraint pk_sale primary key (sale_id))
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
  constraint pk_user primary key (id))
;

create sequence item_seq;

create sequence sale_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists item;

drop table if exists sale;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists item_seq;

drop sequence if exists sale_seq;

drop sequence if exists user_seq;


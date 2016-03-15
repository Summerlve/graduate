# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table zh_admin (
  id                        bigint auto_increment not null,
  sfz                       varchar(255) CHARACTER SET utf8 COLLATE utf8_bin not null,
  name                      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin not null,
  telephone                 varchar(255) CHARACTER SET utf8 COLLATE utf8_bin not null,
  constraint uq_zh_admin_sfz unique (sfz),
  constraint uq_zh_admin_telephone unique (telephone),
  constraint pk_zh_admin primary key (id))
;

create table zh_area (
  id                        bigint auto_increment not null,
  name                      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin not null,
  management_company        varchar(255),
  building_num              integer,
  constraint pk_zh_area primary key (id))
;

create table zh_building (
  id                        bigint auto_increment not null,
  area_id                   bigint not null,
  building_kind_id          bigint not null,
  acreage                   integer,
  completion_date           datetime(6),
  house_num                 integer,
  selled_num                integer,
  order_num                 integer,
  constraint pk_zh_building primary key (id))
;

create table zh_building_kind (
  id                        bigint auto_increment not null,
  name                      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin not null,
  constraint uq_zh_building_kind_name unique (name),
  constraint pk_zh_building_kind primary key (id))
;

create table zh_house (
  id                        bigint auto_increment not null,
  user_id                   bigint not null,
  building_id               bigint not null,
  house_state_id            bigint not null,
  floor                     integer,
  house_no                  integer,
  space                     integer,
  buy_date                  datetime(6),
  in_date                   datetime(6),
  constraint pk_zh_house primary key (id))
;

create table zh_house_state (
  id                        bigint auto_increment not null,
  name                      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin not null,
  constraint uq_zh_house_state_name unique (name),
  constraint pk_zh_house_state primary key (id))
;

create table zh_user (
  id                        bigint auto_increment not null,
  sfz                       varchar(255) CHARACTER SET utf8 COLLATE utf8_bin not null,
  name                      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin not null,
  telephone                 varchar(255) CHARACTER SET utf8 COLLATE utf8_bin not null,
  constraint uq_zh_user_sfz unique (sfz),
  constraint uq_zh_user_telephone unique (telephone),
  constraint pk_zh_user primary key (id))
;

alter table zh_building add constraint fk_zh_building_area_1 foreign key (area_id) references zh_area (id) on delete restrict on update restrict;
create index ix_zh_building_area_1 on zh_building (area_id);
alter table zh_building add constraint fk_zh_building_buildingKind_2 foreign key (building_kind_id) references zh_building_kind (id) on delete restrict on update restrict;
create index ix_zh_building_buildingKind_2 on zh_building (building_kind_id);
alter table zh_house add constraint fk_zh_house_user_3 foreign key (user_id) references zh_user (id) on delete restrict on update restrict;
create index ix_zh_house_user_3 on zh_house (user_id);
alter table zh_house add constraint fk_zh_house_buildingId_4 foreign key (building_id) references zh_building (id) on delete restrict on update restrict;
create index ix_zh_house_buildingId_4 on zh_house (building_id);
alter table zh_house add constraint fk_zh_house_state_5 foreign key (house_state_id) references zh_house_state (id) on delete restrict on update restrict;
create index ix_zh_house_state_5 on zh_house (house_state_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table zh_admin;

drop table zh_area;

drop table zh_building;

drop table zh_building_kind;

drop table zh_house;

drop table zh_house_state;

drop table zh_user;

SET FOREIGN_KEY_CHECKS=1;


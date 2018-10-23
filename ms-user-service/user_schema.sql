create database if not exists microservice character set utf8;
use microservice;
drop table if exists user;
create table user(
  `id` int(11) auto_increment primary key,
  username varchar(99),
  password varchar(99),
  unique key(`username`)
);
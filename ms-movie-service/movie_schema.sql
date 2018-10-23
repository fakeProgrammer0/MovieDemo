create database if not exists microservice character set utf8;
use microservice;

-- drop table if exists `collected_movie`;
-- drop table if exists `shortcomment`;
-- drop table if exists `movie`;

create table if not exists `movie`(
  `id` int(11) auto_increment primary key,
  `rank` int(4) unsigned,
  `title` varchar(99) not null,
  `score` numeric(3, 1),
  `country` varchar(99),
  `year` int(4),
  `category` varchar(99),
  `votes` int(11),
  `douban_url` varchar(256),
  `pic_url` varchar(256),
  `quote` varchar(256),
  `directors` varchar(99),
  `stars` varchar(1024),
  `releaseDate` varchar(99),
  `running_time` varchar(99),
  unique key (`title`)
--   `ShortComment` varchar(4096)
);

create table if not exists `shortcomment`(
   `id` int(11) auto_increment primary key,
   `movie_id` int(11) not null,
   `content` varchar(4096),
   foreign key(`movie_id`) references `movie`(`id`) on delete cascade
);

create table if not exists `collected_movie` (
  `id` int(11) auto_increment primary key,
  `user_id` int(11) not null,
  `movie_id` int(11) not null,
  unique key (`user_id`, `movie_id`),
  foreign key(`movie_id`) references `movie`(`id`) on delete cascade,
  foreign key(`user_id`) references `user`(`id`) on delete cascade
);


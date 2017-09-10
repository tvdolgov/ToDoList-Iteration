CREATE DATABASE todolist
  WITH OWNER = root
  ENCODING = 'UTF8'
TABLESPACE = pg_default
  LC_COLLATE = 'Russian_Russia.1251'
  LC_CTYPE = 'Russian_Russia.1251'
CONNECTION LIMIT = -1;


CREATE TABLE tasks(
  id bigserial not null,
  creation_date timestamp,
  description varchar(128),
  finish_date timestamp,
  primary key (id)
);

INSERT INTO tasks (creation_date, description, finish_date) VALUES ('09.01.2017 16:18','Подготовиться к октябрю. Отремонтировать осеннюю обувь. отнести верхнюю одежду в химчистку. Пропить витамины.', '30.09.2017 00:35');
INSERT INTO tasks (creation_date, description, finish_date) VALUES ('11.08.2017 12:43','Порыбачить нужно! ALARM!', '01.09.2017 17:11');
INSERT INTO tasks (creation_date, description, finish_date) VALUES ('17.07.2017 06:33','Пожарить картошку с грибами.', '06.09.2017 12:22');
INSERT INTO tasks (creation_date, description, finish_date) VALUES ('23.08.2017 23:25','Съездить в горы срочно!', '21.09.2017 21:03');

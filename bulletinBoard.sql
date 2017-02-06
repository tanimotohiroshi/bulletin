create database bulletin_board;

use bulletin_board;

create table users
 (id int primary key auto_increment,
  login_id varchar(20) unique not null
 , password varchar(255)
 , name varchar(10) unique not null
 , is_stopped tinyint(1) default 1 not null
 , branch_id int
 , department_id int
 );


alter table users alter is_stopped set default 1;

create table branchs
 (id int primary key auto_increment
 , name varchar(255) unique not null
 );


create table departments
 (id int primary key auto_increment
 , name varchar(255) unique not null
 );


create table postings
 (
   id int primary key auto_increment
 , user_id int not null
 , title varchar(50) not null
 , message text
 , category varchar(10) not null
 , update_date timestamp not null
 );


create table comments
 (id int primary key auto_increment
 , posting_id int not null
 , user_id int not null
 , message text not null
 , update_date timestamp not null
 );

insert into branchs (name)
 values ('北海道'), ('東京'), ('名古屋'), ('大阪'), ('福岡' );

insert into departments ( name )
 values ('総務人事担当者'), ('情報管理担当者')
 , ('支店長A'), ('支店長B'), ('支店長C')
 , ('社員A'), ('社員B'), ('社員C');




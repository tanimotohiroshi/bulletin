create database bulletin_board;


create table users
 (id int primary key auto_increment,
  login_id varchar(20) unique not null
 , password varchar(255)
 , name varchar(10) unique not null
 , is_stopped tinyint(1) not null
 , branch_id int 
 , department_id int);


create table branchs
 (id int primary key auto-increment
 , name varchar(255) unique not null);
 

create table departments
 (id int priamry key auto-increment
 , name varchar(255) unique not null);



create table postings
 (
   id int primary key auto_increment
 , user_id int not null
 , title varchar(50) not null
 , message text, category varchar(10) not null
 , update_date date
 );
    

create table comments
 (id int primary key auto_increment
 , posting_id int not null
 , message text not null
 , update_date date, user_id int not null
 );
    



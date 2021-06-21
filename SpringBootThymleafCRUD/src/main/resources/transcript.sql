drop database if exists demospringthymleaf;
create database demospringthymleaf;
use  demospringthymleaf;

create table employee(
	employee_id int AUTO_INCREMENT PRIMARY KEY,
	first_name varchar(20),
	last_name varchar(20),
	email varchar(30)
);

insert into employee(employee_id,first_name,last_name,email) values (1,'Saurabh','Jha','sdj131998@gmail.com');
insert into employee(employee_id,first_name,last_name,email) values (2,'Ankit','Kumar','aKumar@yahoo.com');
insert into employee(employee_id,first_name,last_name,email) values (3,'Rishu','Pal','rishuLion@gmail.com');
insert into employee(employee_id,first_name,last_name,email) values (4,'Gouresh','Chauhan','gourseh@outlook.com');
insert into employee(employee_id,first_name,last_name,email) values (5,'Bikramjeet','Singh','biki@gmail.com');
insert into employee(employee_id,first_name,last_name,email) values (6,'Rohan','Agarwal','agarwal@yahoo.com');
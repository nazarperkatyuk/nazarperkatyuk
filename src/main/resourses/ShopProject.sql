DROP DATABASE if exists ShopProject;

CREATE DATABASE usersShopProject;

USE ShopProject;

CREATE TABLE users (
id int not null primary key auto_increment,
first_name varchar (20) not null,
last_name varchar (20) not null,
email varchar (20) not null unique,
role  varchar (20) not null
);

CREATE TABLE products (
id int not null primary key auto_increment,
name  varchar (20) not null,
description  varchar (20) not null,
price decimal (4,2) not null
);

CREATE TABLE buckets (
id int not null primary key auto_increment,
user_id int not null ,
product_id int not null ,
purchase_date date not null,
foreign key (user_id) references users(id),
foreign key (product_id) references products(id)
);

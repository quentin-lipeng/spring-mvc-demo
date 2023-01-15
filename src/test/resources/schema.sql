CREATE SCHEMA if not exists mvc_demo;

use mvc_demo;

create table user_acc_role
(
    ac_id      int auto_increment
        primary key,
    account_id varchar(32) null,
    role_id    int         null
);

create table user_account
(
    account_id varchar(32) null,
    username   varchar(32) null,
    password   varchar(32) null,
    salt       varchar(32) null
);

create table user_resource
(
    res_id        int auto_increment
        primary key,
    resource_name varchar(32) null,
    resource_info varchar(64) null
);

create table user_role
(
    role_id   int auto_increment
        primary key,
    role_name varchar(32) null,
    role_info varchar(64) null
);

create table user_role_resource
(
    rs_id   int auto_increment
        primary key,
    role_id int null,
    res_id  int null
);



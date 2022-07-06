
drop table if exists files;
drop table if exists my_users;
drop table if exists roles;
drop table if exists user_roles;
create table files (
    id varchar(255) not null,
    data longblob,
    name varchar(255),
    type varchar(255),
    primary key (id));

create table my_users (
    id bigint not null auto_increment,
    password varchar(255),
    username varchar(255),
    primary key (id));

create table roles (
    id bigint not null auto_increment,
    name varchar(255),
    primary key (id));

create table user_roles (
    user_id bigint not null,
    roles_id bigint not null,
    primary key (user_id, roles_id));

alter table my_users add constraint UK32gjj9qcsg7xdihiovixh3xcu unique (username);
alter table user_roles add constraint FKdbv8tdyltxa1qjmfnj9oboxse foreign key (roles_id) references roles (id);
alter table user_roles add constraint FK4qdaw74kg5s9r1yoii064uxwq foreign key (user_id) references my_users (id);
insert into my_users(username, password) values('user', 'password');
insert into my_users(username, password) values('admin', 'password');
insert into my_users(username, password) values('qwe', '123');

insert into roles(name) values('ROLE_USER');
insert into roles(name) values('ROLE_ADMIN');

insert into user_roles(user_id, roles_id) values('1','1');
insert into user_roles(user_id, roles_id) values('2','2');
insert into user_roles(user_id, roles_id) values('3','1');
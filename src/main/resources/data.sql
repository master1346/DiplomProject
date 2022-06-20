#insert into authentication_data(login, password, enabled) values('1','a', '1');
#insert into authentication_data(id, login, password) values('2','b', '2');
#insert into authentication_data(id, login, password) values('3','c', '3');
#insert into authentication_data(id, login, password) values('4','d', '4');

insert into users(username, password, enabled) values('user', 'pass', true);
insert into users(username, password, enabled) values('admin', 'pass', true);
insert into users(username, password, enabled) values('qwe', '123', true);

insert into authorities(authority) values( 'USER');

insert into my_user(login, position, password, authority) values('user', '1', 'password', 'USER');
insert into my_user( login, position, password, authority) values('admin', '2', 'password', 'ADMIN');

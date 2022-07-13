create table my_users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(256) NOT NULL ,
    password VARCHAR(256) NOT NULL ,
    roles SET('ROLE_ADMIN', 'ROLE_USER')
);

create table roles (
    id INT NOT NULL AUTO_INCREMENT,
    name ENUM('ROLE_ADMIN', 'ROLE_USER'),
    PRIMARY KEY (id) ,
    FOREIGN KEY (id) REFERENCES my_users (id)
);

create table user_roles(
    user_id INT NOT NULL,
    roles_id INT NOT NULL
);


create table files (
     id INT NOT NULL AUTO_INCREMENT,
     name VARCHAR(256) NOT NULL,
     type VARCHAR(256) NOT NULL,
     data LONGBLOB NOT NULL,
     PRIMARY KEY(id)
);

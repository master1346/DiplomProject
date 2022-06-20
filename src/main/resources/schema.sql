create table users(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(256) NOT NULL ,
    password VARCHAR(256) NOT NULL ,
    enabled BOOLEAN NOT NULL,
    PRIMARY KEY(id)
);

create table authorities (
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    authority VARCHAR(50) NOT NULL ,
    constraint fk_authorities_users FOREIGN KEY (id) REFERENCES users(id)
);

create table files (
     id VARCHAR(256) NOT NULL,
     name VARCHAR(256) NOT NULL,
     type VARCHAR(256) NOT NULL,
     data LONGBLOB NOT NULL,
     PRIMARY KEY(id)
);

create table my_user(
                        login VARCHAR(50) NOT NULL PRIMARY KEY ,
                        password VARCHAR(50) NOT NULL ,
                        position VARCHAR(50) NOT NULL,
                        authority VARCHAR(50) NOT NULL
);
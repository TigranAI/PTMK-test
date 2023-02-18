CREATE TABLE users (
    id varchar(36) primary key,
    username varchar(100) not null,
    birthday date not null,
    sex boolean not null
);
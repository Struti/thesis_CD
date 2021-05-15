create table user
(
    user_id  BIGINT auto_increment,
    email    char(50)              null,
    password char(100)             null,
    role     ENUM ('USER','ADMIN') null,
    expired  BOOLEAN,
    locked   BOOLEAN,
    constraint user_pk
        primary key (user_id)
);

create table users_hw9
(
    id         bigint auto_increment primary key,
    birth_date datetime(6)  null,
    name       varchar(255) null
);


create index users_hw9_birth_date_index
    on test_db.users_hw9 (birth_date);
drop table if exists products;

create table if not exists products (
    id bigint primary key,
    name varchar(25),
    price integer
);

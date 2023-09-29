drop table if exists products;

create table if not exists products (
    id integer not null,
    name varchar(25) not null,
    price integer not null
);

create table if not exists orders.`order`
(
    id   int auto_increment
        primary key,
    name varchar(50) null,
    code varchar(10) null
);

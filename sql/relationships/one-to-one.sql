create table mobile_operator(
    id serial primary key,
    name varchar(50)
);

create table frequency(
    id serial primary key,
    frequency_range varchar(50)
);

create table mobile_operator_frequency(
    id serial primary key,
    mobile_operator_id int references mobile_operator(id) unique,
    frequency_id int references frequency(id) unique
);

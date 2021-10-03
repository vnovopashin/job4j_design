create table mobile_operator(
    id serial primary key,
    name varchar(50)
);

create table abonent(
    id serial primary key,
    name varchar(255)
);

create table mobile_operator_abonent(
    id serial primary key,
    mobile_operator_id int references mobile_operator(id),
    abonent_id int references abonent(id)
);

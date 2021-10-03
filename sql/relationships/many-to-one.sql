create table mobile_operator(
    id serial primary key,
    name varchar(50)
);

create table phone_number(
    id serial primary key,
    number varchar(50),
    mobile_operator_id int references mobile_operator(id)
);

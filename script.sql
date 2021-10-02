create table book (
id serial primary key,
	title varchar(255),
	author varchar(255),
	price numeric(8, 2),
	amount int
);

insert into book(title, author, price, amount)
values('Java. Библиотека профессионала', 'Кей Хорстманн', 1500, 5);

update book
set price = 1350, amount = 8;

delete from book;
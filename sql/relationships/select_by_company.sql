/*
1. В одном запросе получить
- имена всех person, которые не состоят в компании с id = 5;
- название компании для каждого человека.
*/
select person.name, company.name from person
join company on person.company_id = company.id
where company.id <> 5;


/*
2. Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании
(нужно учесть, что таких компаний может быть несколько).
*/
select company.name, count(person.name) as Количество
from company
join person on person.company_id = company.id
group by company.name
having count(person.name) =
(select count(name) from person
group by company_id
order by company_id desc
limit 1);

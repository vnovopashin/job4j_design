package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс описывает подключение к базе данных
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public interface Store {
    List<Employee> findBy(Predicate<Employee> filter);
}

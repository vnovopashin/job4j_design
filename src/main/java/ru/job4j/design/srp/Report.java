package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Интерфейс описывает систему отчетов
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public interface Report {
    String generate(Predicate<Employee> filter);
}

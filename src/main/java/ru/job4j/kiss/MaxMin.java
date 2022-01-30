package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс реализует поиск максимального и минимального элемента
 * по критерию java.util.Comparator
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class MaxMin {

    /**
     * Метод находит максимальное значение из переданного в параметры списка и компаратор.
     *
     * @param value      список в котором нужно найти максимальное значение.
     * @param comparator компаратор, для сравнения объектов в списке.
     * @param <T>        тип объекта.
     * @return возвращает максимальный объект (значение) из списка.
     */
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findBy(value, comparator, v -> v > 0);
    }

    /**
     * Метод находит минимальное значение из переданного в параметры списка и компаратор.
     *
     * @param value      список в котором нужно найти минимальное значение.
     * @param comparator компаратор, для сравнения объектов в списке.
     * @param <T>        тип объекта.
     * @return возвращает минимальный объект (значение) из списка.
     */
    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findBy(value, comparator, v -> v < 0);
    }

    /**
     * Универсальный метод, позволяет избавиться от дублирования кода
     * и соответственно уменьшить количество потенциальных ошибок.
     *
     * @param value      список в котором нужно найти значение по какому-либо условию.
     * @param comparator компаратор, для сравнения объектов в списке.
     * @param predicate  условие для выбора объекта
     * @param <T>        тип объекта.
     * @return возвращает объект (значение) из списка, согласно условию.
     */
    private <T> T findBy(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T val = value.get(0);
        for (T t : value) {
            if (predicate.test(comparator.compare(t, val))) {
                val = t;
            }
        }
        return val;
    }
}

package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

/**
 * Класс демонстрирует работу с ListIterator
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ListUtils {

    /**
     * Метод вставляет элемент до указанного индекса
     *
     * @param list  список в который будет вставлен элемент
     * @param index индекс до которого будет вставлен элемент
     * @param value вставляемый элемент
     * @param <T>   любой не примитивный тип данных
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * Метод вставляет элемент после указанного индекса
     *
     * @param list  список в который будет вставлен элемент
     * @param index индекс после которого будет вставлен элемент
     * @param value вставляемый элемент
     * @param <T>   любой не примитивный тип данных
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.next();
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * Метод удаляет все элементы которые удовлетворяют указанному предикату
     *
     * @param list   список из которого требуется удалить элемент
     * @param filter предикат в соответствии с которым будет происходить удаление
     * @param <T>    любой не примитивный тип данных
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (filter.test(it.next())) {
                it.remove();
            }
        }
    }

    /**
     * Метод заменяет все элементы которые удовлетворяют указанному предикату
     *
     * @param list   список в котором требуется заменить элементы
     * @param filter предикат в соответствии с которым будет происходить замена
     * @param value  элемент на который требуется заменить
     * @param <T>    любой не примитивный тип данных
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (filter.test(it.next())) {
                it.set(value);
            }
        }
    }

    /**
     * Метод удаляет из списка list элементы которые есть в списке elements
     *
     * @param list     список из которого требуется удалить элементы
     * @param elements список удаляемых элементов из list
     * @param <T>      любой не примитивный тип данных
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        for (T element : elements) {
            ListIterator<T> it = list.listIterator(0);
            while (it.hasNext()) {
                if (element.equals(it.next())) {
                    it.remove();
                }
            }
        }
    }
}

package ru.job4j.iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Класс реализует итератор для обхода вложенных итераторов.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    /**
     * Метод hasNext проверяет, есть ли следующий элемент.
     *
     * @return возвращает true, если следующий элемент существует, иначе false
     */
    @Override
    public boolean hasNext() {
        while (!cursor.hasNext() && data.hasNext()) {
            cursor = data.next();
        }
        return cursor.hasNext();
    }

    /**
     * Метод next возвращает первый элемент ячейки.
     * Второй вызов метода next вернет следующий элемент и так далее.
     * Метод next сдвигает указатель итератора.
     *
     * @return возвращает элемент ячейки
     * @throws NoSuchElementException если больше нет элементов
     */

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}

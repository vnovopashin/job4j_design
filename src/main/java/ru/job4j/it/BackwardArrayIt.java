package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс реализует итератор для массива. Итератор отдает элементы в обратном порядке.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */

public class BackwardArrayIt implements Iterator<Integer> {

    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    /**
     * Метод hasNext проверяет, если ли следующий элемент.
     *
     * @return возвращает true, если следующий элемент существует, иначе false
     */
    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * Метод next возвращает последний элемент ячейки.
     * Второй вызов метода next вернет предпоследний элемент и так далее.
     * Метод next сдвигает указатель итератора.
     *
     * @return возвращает элемент ячейки
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[data.length - ++point];
    }
}

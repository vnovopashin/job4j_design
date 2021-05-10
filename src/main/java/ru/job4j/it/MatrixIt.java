package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс реализует итератор для двухмерного массива.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Метод hasNext проверяет, если ли следующий элемент.
     *
     * @return возвращает true, если следующий элемент существует, иначе false
     */
    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == column) {
            row++;
            column = 0;
        }
        return row < data.length;
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
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}

package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс реализует итератор возвращающий только четные цифры.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */

public class EvenIt implements Iterator<Integer> {

    private final int[] numbers;
    private int point = 0;

    public EvenIt(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Метод hasNext проверяет, если ли следующий четный элемент.
     *
     * @return возвращает true,
     * только если в массиве есть четные числа перед указателем, иначе false
     */
    @Override
    public boolean hasNext() {
        boolean rsl = false;
        for (int i = point; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                point = i;
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод next возвращает четный элемент ячейки.
     * Второй вызов метода next вернет следующий четный элемент и так далее.
     * Метод next сдвигает указатель итератора на следующий четный элемент массива.
     *
     * @return возвращает четный элемент ячейки
     * @throws NoSuchElementException если больше нет элементов
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[point++];
    }
}

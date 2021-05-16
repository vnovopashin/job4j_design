package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс реализует универсальную обертку над массивом
 *
 * @param <T> подставляется любой не примитивный тип
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class SimpleArray<T> implements Iterable<T> {

    private final T[] array;
    private int size;

    public SimpleArray(int capacity) {
        array = (T[]) new Object[capacity];
        size = 0;
    }

    /**
     * Метод добавляет указанный элемент (model) в первую свободную ячейку
     *
     * @param model элемент который требуется добавить в массив
     */
    public void add(T model) {
        array[size] = model;
        size++;
    }

    /**
     * Метод заменяет указанным элементом (model) элемент, находящийся по индексу index
     *
     * @param index позиция элемента в массиве
     * @param model значение на которое требуется заменить элемент
     */
    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        array[index] = model;
    }

    /**
     * Метод удаляет элемент по указанному индексу,
     * все находящиеся справа элементы при этом сдвигаются на единицу влево (в середине массива таким образом не должно быть пустых ячеек)
     *
     * @param index позиция элемента в массиве
     * @return возвращает true в случае удаления
     */
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T rsl = array[index];
        int start = index + 1;
        int length = size - index;
        System.arraycopy(array, start, array, index, size - length);
        array[size - 1] = null;
        size--;
        return rsl;
    }

    /**
     * Метод возвращает элемент, расположенный по указанному индексу
     *
     * @param index позиция элемента в массиве
     * @return возвращает элемент
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return array[index];
    }

    /**
     * Метод позволяет последовательно получить элементы набора данных
     *
     * @return объект Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    private class Itr implements Iterator<T> {
        private int elem = 0;

        /**
         * Метод hasNext проверяет, если ли следующий элемент.
         *
         * @return возвращает true, если следующий элемент существует, иначе false
         */
        @Override
        public boolean hasNext() {
            return elem < size;
        }

        /**
         * Метод возвращает первый элемент ячейки. Второй вызов метода next вернет второй элемент и так далее
         *
         * @return возвращает элемент ячейки
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[elem++];
        }
    }
}

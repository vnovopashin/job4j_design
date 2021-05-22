package ru.job4j.collection;

import java.util.*;

/**
 * Класс представляет упрощенную реализацию ArrayList.
 *
 * @param <T> подставляется любой не примитивный тип
 * @author Vasiliy Novopashin
 * @version 1.0
 */

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container;

    /**
     * Переменная defaultCapacity задает размер коллекции,
     * по умолчанию коллекция имеет размер 2
     */
    private int defaultCapacity = 2;
    private int size;

    /**
     * Счетчик для отслеживания изменения коллекции,
     * каждая операция которая структурно модифицирует коллекцию, увеличивает счетчик
     */
    private int modCount;

    public SimpleArray() {
        this.container = new Object[defaultCapacity];
    }

    /**
     * Метод возвращает элемент, расположенный по указанному индексу
     *
     * @param index позиция элемента в массиве
     * @return возвращает элемент
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    /**
     * Метод добавляет указанный элемент (model) в первую свободную ячейку
     *
     * @param model элемент который требуется добавить в массив
     */
    public void add(T model) {
        if (size == defaultCapacity) {
            grow();
        }
        container[size] = model;
        size++;
        modCount++;
    }

    /**
     * Метод расширяет коллекцию в случае заполнения, на одну ячейку.
     */
    private void grow() {
        Object[] temp = new Object[++defaultCapacity];
        System.arraycopy(container, 0, temp, 0, defaultCapacity - 1);
        container = new Object[defaultCapacity];
        container = temp;
    }

    /**
     * Метод позволяет последовательно получить элементы набора данных.
     * Итератор реализует fail-fast поведение,
     * т.е. если с момента создания итератора в коллекцию добавили новый элемент,
     * итератор кидает ConcurrentModificationException.
     *
     * @return объект Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            /**
             * Значение переменной expectedModCount задается при создании итератора,
             * для отслеживания изменения коллекции
             */
            private final int expectedModCount = modCount;
            private int element;

            /**
             * Метод hasNext проверяет, есть ли следующий элемент.
             *
             * @return возвращает true, если следующий элемент существует, иначе false
             */
            @Override
            public boolean hasNext() {
                checkModification();
                return element < size;
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
                return (T) container[element++];
            }

            /**
             * Метод проверяет счетчик изменений modCount
             * со значением expectedModCount (значение присваивается при создании итератора),
             * если после создания итератора счетчик modCount изменится,
             * т.е. коллекция структурно изменится (добавлены или удалены элементы)
             * будет брошено исключение ConcurrentModificationException
             */
            private void checkModification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    @Override
    public String toString() {
        return Arrays.toString(container);
    }
}

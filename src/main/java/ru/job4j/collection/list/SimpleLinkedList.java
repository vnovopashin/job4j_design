package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс представляет упрощенную реализацию LinkedList.
 *
 * @param <E> подставляется любой не примитивный тип
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class SimpleLinkedList<E> implements List<E> {

    private int size;

    /**
     * Счетчик для отслеживания изменения коллекции,
     * каждая операция которая структурно модифицирует список, увеличивает счетчик
     */
    private int modCount;

    /**
     * Первый узел в списке
     */
    private Node<E> first;

    /**
     * Последний узел в списке
     */
    private Node<E> last;

    /**
     * Метод добавляет элемент в конец списка
     *
     * @param value элемент который требуется добавить в список
     */
    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Метод возвращает элемент, расположенный по указанному индексу
     *
     * @param index позиция элемента в списке
     * @return возвращает элемент
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> target = first;
        for (int i = 0; i < index; i++) {
            target = target.next;
        }
        return target.item;
    }

    /**
     * Метод позволяет последовательно получить элементы набора данных.
     * Итератор реализует fail-fast поведение,
     * т.е. если с момента создания итератора в список добавили новый элемент,
     * итератор кидает ConcurrentModificationException.
     *
     * @return объект Iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> node = first;

            /**
             * Значение переменной expectedModCount задается при создании итератора,
             * для отслеживания изменения коллекции
             */
            private final int expectedModCount = modCount;

            /**
             * Метод hasNext проверяет, есть ли следующий элемент.
             *
             * @return возвращает true, если следующий элемент существует, иначе false
             */
            @Override
            public boolean hasNext() {
                checkModification();
                return node != null;
            }

            /**
             * Метод возвращает первый элемент списка. Второй вызов метода next вернет второй элемент и так далее
             *
             * @return возвращает элемент списка
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = node.item;
                node = node.next;
                return value;
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

    /**
     * Вложенный класс, представляет собой узел,
     * который содержит элемент для добавления,
     * а также ссылку на предыдущий и следующий элемент
     *
     * @param <E> подставляется любой не примитивный тип
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}

package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс представляет упрощенную реализацию односвязного списка LinkedList.
 *
 * @param <T> подставляется любой не примитивный тип
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;

    /**
     * Метод добавляет элемент в конец списка
     *
     * @param value элемент который требуется добавить в список
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Метод добавляет элемент в начало списка
     *
     * @param value элемент который требуется добавить в список
     */
    public void addFist(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        node.next = head;
        head = node;
    }

    /**
     * Метод удаляет первый элемент в списке
     *
     * @return возвращает удаленный элемент
     */
    public T deleteFirst() {
        Node<T> temp = head;
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
        return temp.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            /**
             * Метод hasNext проверяет, есть ли следующий элемент.
             *
             * @return возвращает true, если следующий элемент существует, иначе false
             */
            @Override
            public boolean hasNext() {
                return node != null;
            }

            /**
             * Метод возвращает первый элемент списка. Второй вызов метода next вернет второй элемент и так далее
             *
             * @return возвращает элемент списка
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * Вложенный класс, представляет собой узел,
     * который содержит элемент для добавления,
     * а также ссылку на следующий элемент
     *
     * @param <T> подставляется любой не примитивный тип
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}

package ru.job4j.collection;

/**
 * Класс реализует структуру данных стек на базе односвязного списка
 *
 * @param <T> подставляется любой не примитивный тип
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class SimpleStack<T> {
    /**
     * Поле linked представляет собой односвязный список
     */
    private final ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * Возвращает значение и удаляет его из стека.
     *
     * @return возвращает удаленное значение
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * Метод помещает значение в стек.
     *
     * @param value value элемент который требуется поместить в стек
     */
    public void push(T value) {
        linked.addFist(value);
    }
}

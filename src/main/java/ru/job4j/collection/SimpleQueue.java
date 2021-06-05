package ru.job4j.collection;

/**
 * Класс реализует организацию данных - очередь, с использованием двух стеков.
 *
 * @param <T> подставляется любой не примитивный тип
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class SimpleQueue<T> {

    /**
     * Для заполнения очереди используется стек in
     */
    private final SimpleStack<T> in = new SimpleStack<>();

    /**
     * Для удаления из очереди используется стек out
     */
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inCount = 0;
    private int outCount = 0;

    /**
     * Метод возвращает первое значение и удаляет его из очереди.
     * Логика метода следующая, если стек out пуст, мы перекладываем в него элементы из стека in
     * таким образом удаляемый элемент помещается на вершину стека out и удаляем элемент из стека.
     *
     * @return возвращает удаленное значение
     */
    public T poll() {
        if (outCount == 0) {
            outCount = inCount - 1;
            while (inCount > 0) {
                out.push(in.pop());
                inCount--;
            }
        }
        return out.pop();
    }

    /**
     * Метод помещает значение в конец очереди
     *
     * @param value элемент который требуется поместить в очередь
     */
    public void push(T value) {
        in.push(value);
        inCount++;
    }
}

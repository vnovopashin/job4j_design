package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

/**
 * Класс представляет упрощенную реализацию Set
 *
 * @param <T> подставляется любой не примитивный тип
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class SimpleSet<T> implements Set<T> {

    private SimpleArray<T> set = new SimpleArray<>();

    /**
     * Метод добавляет элемент в коллекцию
     *
     * @param value добавляемый элемент
     * @return возвращает true, если такого элемента еще нет в коллекции,
     * иначе элемент не добавляется и возвращается false
     */
    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            set.add(value);
            return true;
        }
        return false;
    }

    /**
     * Метод проверяет содержится элемент в коллекции
     *
     * @param value элемент наличие которого необходимо проверить
     * @return возвращает true если элемент присутствует, иначе false
     */
    @Override
    public boolean contains(T value) {
        for (T t : set) {
            if (Objects.equals(t, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

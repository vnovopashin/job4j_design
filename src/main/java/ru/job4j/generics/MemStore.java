package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует каркас универсального хранилища
 *
 * @param <T> тип элементов в хранилище
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    /**
     * Метод добавляет элемент в хранилище
     *
     * @param model добавляемый элемент
     */
    @Override
    public void add(T model) {
        mem.add(model);
    }

    /**
     * Метод заменяет элемент в хранилище
     *
     * @param id    идентификатор
     * @param model элемент на который требуется заменить
     * @return возвращает true, если элемент найден и заменен, иначе false
     */
    @Override
    public boolean replace(String id, T model) {
        for (int i = 0; i < mem.size(); i++) {
            if (id.equals(mem.get(i).getId())) {
                mem.set(i, model);
                return true;
            }
        }
        return false;
    }

    /**
     * Метод удаляет элемент из хранилища
     *
     * @param id идентификатор для удаления элемента из хранилища
     * @return возвращает true, если элемент найден и заменен, иначе false
     */
    @Override
    public boolean delete(String id) {
        return mem.removeIf(t -> id.equals(t.getId()));
    }

    /**
     * Метод ищет элемент в хранилище
     *
     * @param id идентификатор для поиска элемента в хранилище
     * @return возвращает найденный элемент, если элемент не найден возвращается null
     */
    @Override
    public T findBy(String id) {
        T rsl = null;
        for (T t : mem) {
            if (id.equals(t.getId())) {
                rsl = t;
            }
        }
        return rsl;
    }
}

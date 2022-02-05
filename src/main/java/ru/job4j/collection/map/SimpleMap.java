package ru.job4j.collection.map;

import java.util.*;

/**
 * Класс реализует упрощенную структуру данных HashMap
 *
 * @param <K> ключ по которому производятся операции вставки, получения и удаления
 * @param <V> значения которые хранятся в HashMap
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Метод помещает в массив(хэш-таблицу) новый объект с ключом K и значением V.
     *
     * @param key   ключ по которому производится вставка
     * @param value значение которое требуется хранить
     * @return если в коллекции уже есть объект с подобным ключом, то возвращается false иначе true.
     */
    @Override
    public boolean put(K key, V value) {
        MapEntry<K, V> entry = new MapEntry<>(key, value);
        int h = hash(key.hashCode());
        int index = indexFor(h);
        if (count + 1 == capacity * LOAD_FACTOR) {
            expand();
        }
        if (table[index] == null) {
            table[index] = entry;
            modCount++;
            count++;
            return true;
        }
        return false;
    }

    /**
     * Метод реализует хэш-функцию с использованием битовых операций
     *
     * @param hashCode принимает хэш-код объекта, который будет использоваться в качестве ключа
     * @return возвращает "улучшенный" хэш-код
     */
    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    /**
     * Метод определяет позицию в массиве для размещения данных
     *
     * @param hash принимает хэш-код
     * @return возвращает позицию в массиве
     */
    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    /**
     * Метод расширяет массив в случае его заполнения на 75% от capacity (размера массива)
     */
    private void expand() {
        count = 0;
        capacity = capacity * 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[(capacity)];
        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    /**
     * Метод возвращает значение, по указанному ключу
     *
     * @param key ключ по которому извлекаем значение
     * @return возвращает значение
     */
    @Override
    public V get(K key) {
        int h = hash(key.hashCode());
        int index = indexFor(h);
        if (!Objects.equals(table[index], null) && Objects.equals(table[index].key, key)) {
            return table[index].value;
        }
        return null;
    }

    /**
     * Метод удаляет объект по заданному ключу
     *
     * @param key ключ по которому необходимо удалить объект
     * @return возвращает true в случае успешного удаления, иначе false
     */
    @Override
    public boolean remove(K key) {
        int h = hash(key.hashCode());
        int index = indexFor(h);
        if (!Objects.equals(table[index], null) && Objects.equals(table[index].key, key)) {
            table[index] = null;
            modCount++;
            count--;
            return true;
        }
        return false;
    }

    /**
     * Метод возвращает количество элементов внутри массива(хэш-таблицы)
     *
     * @return возвращает количество элементов
     */
    public int getCount() {
        return count;
    }

    /**
     * Метод возвращает размер массива(хэш-таблицы)
     *
     * @return возвращает размер
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Метод позволяет последовательно получить элементы набора данных.
     * Итератор реализует fail-fast поведение,
     * т.е. если с момента создания итератора в список добавили новый элемент,
     * итератор кидает ConcurrentModificationException.
     *
     * @return возвращает объект Iterator
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            /**
             * Значение переменной expectedModCount задается при создании итератора,
             * для отслеживания изменения коллекции
             */
            private final int expectedModCount = modCount;
            private int index;

            /**
             *Метод поверяет наличие элементов, пропуская пустые(null)
             *
             * @return возвращает true, пока не дойдет до конца массива(хэш-таблицы)
             */
            @Override
            public boolean hasNext() {
                checkModification();
                for (int i = index; i < capacity; i++) {
                    if (table[index] == null) {
                        index++;
                    }
                }
                return index < capacity;
            }

            /**
             * Метод возвращает элемент массива(хэш-таблицы),
             * второй вызов метода вернет следующий элемент массива
             *
             * @return возвращает элемент массива(хэш-таблицы)
             */
            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
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
        return "SimpleMap{"
                + "capacity=" + capacity
                + ", count=" + count
                + ", modCount=" + modCount
                + ", table=" + Arrays.toString(table)
                + '}';
    }

    /**
     * Вложенный класс, для добавления в массив(хэш-таблицу)
     *
     * @param <K> любой не примитивный тип, который будет использоваться в качестве ключа
     * @param <V> любой не примитивный тип, который будет использоваться в качестве значения
     */
    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "MapEntry{"
                    + "key=" + key
                    + ", value=" + value
                    + '}';
        }
    }
}

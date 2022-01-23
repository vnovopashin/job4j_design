package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Данный класс представляет абстрактную структуру данных типа кэш.
 * По заданному ключу получаем объект кеша и если его нет в памяти,
 * задается поведение загрузки объекта в кэш.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V rsl = null;
        SoftReference<V> softReference = cache.get(key);
        if (softReference != null) {
            rsl = softReference.get();
        }
        return rsl;
    }

    protected abstract V load(K key);
}

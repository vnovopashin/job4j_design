package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс реализует структуру данных типа кэш.
 * По заданному ключу (ключом является путь до файла) получаем объект кеша и если его нет в памяти,
 * то кеш загружает себе данные из файла.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String value = get(key);
        if (value == null) {
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(String.format("%s/%s", cachingDir, key)))) {
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    stringBuilder.append(str).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            value = stringBuilder.toString();
        }
        put(key, value);
        return value;
    }
}

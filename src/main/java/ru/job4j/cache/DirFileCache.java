package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        String value = null;
        try {
            value = Files.readString(Path.of(cachingDir, key));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}

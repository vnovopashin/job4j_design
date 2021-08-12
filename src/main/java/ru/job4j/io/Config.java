package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Класс демонстрирует работу с конфигурационными файлами
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод загружает пары ключ-значение в карту values.
     */
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().filter(str -> !(str.isEmpty() || str.startsWith("#")))
                    .forEach(s -> {
                        String[] splitStr = s.split("=");
                        if (splitStr.length != 2) {
                            throw new IllegalArgumentException();
                        }
                        values.put(splitStr[0], splitStr[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод принимает ключ и возвращает значение
     *
     * @param key ключ по которому будет возвращено значение
     * @return возвращает значение
     */
    public String value(String key) {
        if (values.containsKey(key)) {
            return values.get(key);
        }
        return null;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}

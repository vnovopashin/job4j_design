package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс демонстрирует работу консольной программы с использованием ключей.
 * Например, можно указать количество памяти под программу и кодировку
 * java -Xmx=514 -encoding=UTF-8
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    /**
     * Метод принимает массив параметров и разбивает их на пары ключ, значение
     *
     * @param args аргументы которые требуется преобразовать в значения
     */
    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Enter the required parameters");
        }
        String[] split;
        for (String arg : args) {
            split = arg.split("=");
            if (split.length != 2) {
                throw new IllegalArgumentException("Invalid arguments");
            }
            values.put(split[0].substring(1), split[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

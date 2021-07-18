package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс демонстрирует работу чтения из файла, с использованием BufferedReader
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class LogFilter {
    /**
     * Метод читает из файла текст и фильтрует его по условию
     *
     * @param file принимает файл из которого происходит чтение
     * @return возвращает отфильтрованный список
     */
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            list = reader.lines().filter(s -> s.contains("404")).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}

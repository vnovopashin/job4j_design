package ru.job4j.io;

import java.io.*;
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

    /**
     * Метод записывает результат фильтрации в файл
     *
     * @param log  источник отфильтрованных данных для записи
     * @param file файл в который будет записан результат фильтрации
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String s : log) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}

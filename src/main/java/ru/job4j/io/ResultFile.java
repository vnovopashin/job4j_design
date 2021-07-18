package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Класс демонстрирует запись в файл
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ResultFile {
    /**
     * Метод строит таблицу умножения
     *
     * @param size размер таблицы умножения
     * @return возвращает двумерный массив, в виде таблицы умножения
     */
    public static int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int row = 0; row < table.length; row++) {
            for (int cell = 0; cell < table.length; cell++) {
                table[row][cell] = (row + 1) * (cell + 1);
            }
        }
        return table;
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(Arrays.deepToString(multiple(3)).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

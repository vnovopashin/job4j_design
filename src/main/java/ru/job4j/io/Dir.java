package ru.job4j.io;

import java.io.File;

/**
 * Класс демонстрирует работу с библиотечным классом File
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));

        for (File subfile : file.listFiles()) {
            if (subfile.isFile()) {
                System.out.println(subfile.getName() + " = " + subfile.length() + " byte");
            } else {
                System.out.println(subfile.getName() + " = " + folderSize(subfile) + " byte");
            }


        }
    }

    /**
     * Метод обходит рекурсивно директорию и подсчитывает размер файлов в данной директории
     *
     * @param directory директория размер которой необходимо вычислить
     * @return возвращает размер директории в байтах
     */
    public static long folderSize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                length += file.length();
            } else {
                length += folderSize(file);
            }
        }
        return length;
    }
}

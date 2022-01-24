package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Класс предоставляет пользователю следующие возможности:
 * - указать кэшируемую директорию
 * - загрузить содержимое файла в кэш
 * - получить содержимое файла из кэша
 * По умолчанию, для примера, используется директория ./src/main/java/ru/job4j/cache
 * в кэш загружаются файлы Address.txt и Names.txt
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Emulator {
    AbstractCache<String, String> cache = null;
    Scanner scanner = new Scanner(System.in);
    String dir;
    String file;

    /**
     * Метод выводит меню в консоль.
     */
    private void showMenu() {
        System.out.println("Меню");
        System.out.println("1. Выбрать директорию");
        System.out.println("2. Выбрать файл для кэширования");
        System.out.println("3. Получить содержимое файла из кэша");
        System.out.println("0. Выход");
    }

    /**
     * Метод позволяет выбрать действие, которое необходимо выполнить.
     */
    public void run() {
        boolean run = true;
        int number;
        while (run) {
            showMenu();
            System.out.println("Выберите пункт меню: ");
            number = scanner.nextInt();

            switch (number) {
                case 1:
                    pathToCacheDir();
                    break;
                case 2:
                    addToCache();
                    break;
                case 3:
                    getFromCache();
                    break;
                default:
                    run = false;
            }
        }
    }

    /**
     * Метод позволяет указать путь до директории
     */
    private void pathToCacheDir() {
        System.out.println("Укажите кэшируемую директорию: ");
        dir = scanner.next();
        cache = new DirFileCache(dir);
        System.out.println("В выбранной директории находятся следующие файлы: ");
        try (DirectoryStream<Path> files = Files.newDirectoryStream(Path.of(dir))) {
            for (Path path : files) {
                System.out.println(path.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод позволяет указать кэшируемый файл
     */
    private void addToCache() {
        System.out.println("Введите название файла: ");
        file = scanner.next();
        cache.get(file);
    }

    /**
     * Метод загружает содержимое кэша
     */
    private void getFromCache() {
        System.out.println(cache.load(file));
    }

    public static void main(String[] args) {
        new Emulator().run();
    }
}

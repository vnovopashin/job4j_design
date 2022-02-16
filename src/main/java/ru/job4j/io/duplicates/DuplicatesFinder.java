package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Класс запускает поиск дубликатов файлов,
 * для запуска необходимо выполнить команду
 * java -jar target/duplicatesFinder.jar и указать начальную директорию.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of(args[0]), duplicatesVisitor);
        duplicatesVisitor.getDuplicatesList().forEach(System.out::println);
    }
}

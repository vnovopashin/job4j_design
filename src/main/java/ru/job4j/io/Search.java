package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс осуществляет поиск по определенному предикату,
 * в качестве примера осуществляется поиск файлов имеющих окончание js,
 * а также файлов имеющих нулевой размер.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("The program must be run with parameters.\n"
                    + " The first parameter is the starting folder."
                    + " The second parameter is the file extension.\n Usage java -jar search.jar ROOT_FOLDER EXTENSION");
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        // search(start, p -> p.toFile().length() == 0).forEach(System.out::println);
    }

    /**
     * Метод осуществляет обход файловой системы с указанной директории,
     * и по заданному условию
     *
     * @param root      директория, начиная с которой будет осуществлен поиск
     * @param condition условие по которому будет осуществлен поиск
     * @return возвращает список найденных файлов
     * @throws IOException бросает исключение если возникает ошибка ввода/вывода
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}

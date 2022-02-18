package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Класс реализует поиск дубликатов файлов,
 * используя библиотечный класс SimpleFileVisitor
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, Path> files = new HashMap<>();
    private final List<Path> duplicatesList = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (files.containsKey(fileProperty)) {
            duplicatesList.add(file.toAbsolutePath());
        }
        files.put(fileProperty, file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicatesList() {
        return duplicatesList;
    }
}

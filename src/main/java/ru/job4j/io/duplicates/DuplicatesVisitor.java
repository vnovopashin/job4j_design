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
   private final List<FileProperty> duplicatesList = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getAbsolutePath());
        if (!files.containsKey(fileProperty)) {
            duplicatesList.add(fileProperty);
        }
        files.put(fileProperty, file);
        return super.visitFile(file, attrs);
    }

    public List<FileProperty> getDuplicatesList() {
        return duplicatesList;
    }
}

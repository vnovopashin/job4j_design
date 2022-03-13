package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Класс реализует утилиту для архивации папки.
 * При запуске указывается папка, которую мы хотим архивировать, например: c:\project\job4j\.
 * В качестве ключа передается расширение файлов, которые не нужно включать в архив
 * Например: java -jar pack.jar -d=c:\project\job4j\ -e=class -o=project.zip,
 * где java -jar pack.jar - Это собранный jar.
 * -d - directory - которую мы хотим архивировать
 * -e - exclude - исключить файлы *.class
 * -o - output - во что мы архивируем.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            String path = sources.get(0).getParent();
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath().replace(path + "\\", "")));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            throw new IllegalArgumentException("There must be three parameters."
                    + " For example: -d = C:\\project\\job4j\\ -e = txt -o = project.zip");
        }
        ArgsName argsName = ArgsName.of(args);
        if (!Path.of(argsName.get("d")).toFile().isDirectory()) {
            throw new Exception("Wrong directory");
        }
        List<Path> list = Search.search(Paths.get(argsName.get("d")), p -> !p.toFile().getName().endsWith(argsName.get("e")));
        List<File> fileList = new ArrayList<>();
        for (Path path : list) {
            fileList.add(path.toFile());
        }
        packFiles(fileList, new File(argsName.get("o")));
    }
}

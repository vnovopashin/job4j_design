package ru.job4j.io;

import java.io.FileInputStream;

/**
 * Класс демонстрирует чтение из файла
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int num;
            while ((num = in.read()) != -1) {
                text.append((char) num);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

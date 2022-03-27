package ru.job4j.ood.srp;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс реализует модель данных Book(Книга), при этом нарушается принцип SRP,
 * так данный класс может изменять автора и свое название,
 * выводить в консоль информацию о себе, а также сохранять себя.
 * Из данного класса необходимо вынести методы print и save в отдельные интерфейсы.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Book {
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void print() {
        System.out.println(title + "-" + author);
    }

    public void save() throws IOException {
        FileWriter fileWriter = new FileWriter(getAuthor() + " - " + getTitle() + ".txt");
        fileWriter.write(getTitle() + "-" + getAuthor());
        fileWriter.close();
    }
}

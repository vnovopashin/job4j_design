package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

/**
 * Класс Book, демонстрирует пример работы с форматом JSON,
 * который применяется для пересылки информации между браузером и сервером
 * (загрузка контента по технологии Ajax) или между веб-сайтами (различные HTTP-соединения).
 * можно использовать везде, где требуется обмен данных между программами;
 * хранение локальной информации (например, настроек);
 * за счёт лаконичности может быть выбран для сериализации сложных структур вместо XML.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Book {

    private String title;
    private boolean discount;
    private int price;
    private Author author;
    private String[] chapters;

    public Book(String title, boolean discount, int price, Author author, String... chapters) {
        this.title = title;
        this.discount = discount;
        this.price = price;
        this.author = author;
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "Book{"
                + "title='" + title + '\''
                + ", discount=" + discount
                + ", price=" + price
                + ", author=" + author
                + ", chapters=" + Arrays.toString(chapters)
                + '}';
    }

    public static void main(String[] args) {
        final Book javaBook = new Book("Java. Библиотека профессионала",
                false, 1500, new Author("Кей Хорстманн"),
                "2. Ввод и вывод", "3. XML");
        /*Преобразуем объект javaBook в json-строку*/
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(javaBook));

        /*Модифицируем json-строку*/
        final String javaBookJson = "{\"title\":\"Java. Библиотека профессионала\","
                + "\"discount\":false,"
                + "\"price\":1500,"
                + "\"author\":{\"name\":\"Кей Хорстманн\"},"
                + "\"chapters\":[\"2. Ввод и вывод\",\"3. XML\"]}";
        final Book javaBookMod = gson.fromJson(javaBookJson, Book.class);
        System.out.println(javaBookMod);
    }
}

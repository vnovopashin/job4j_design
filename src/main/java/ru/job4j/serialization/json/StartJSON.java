package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс преобразует POJO в JSONObject и json-строку.
 * JSON-Java (org.json) легковесная функциональная библиотека для работы с JSON,
 * которая дополнительно умеет преобразовывать JSON в XML, HTTP header, Cookies и др.
 * В отличие от Jackson или Gson, JSON-Java преобразует json-строку
 * не в объект пользовательского класса (способ Data bind),
 * а в объекты своей библиотеки JSONObject, JSONArray (способ Tree Model).
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class StartJSON {
    /**
     * В методе проводятся различные манипуляции с POJO Book
     * Сначала получаем JSONObject из json-строки строки,
     * затем из List`a получаем JSONArray,
     * затем получаем JSONObject напрямую методом put,
     * в заключении преобразуем объект javaBook в json-строку
     *
     * @param args массив строк
     */
    public static void main(String[] args) {

        JSONObject jsonAuthor = new JSONObject("{\"name\":\"Кей Хорстманн\"}");

        List<String> list = new ArrayList<>();
        list.add("2. Ввод и вывод");
        list.add("3. XML");
        JSONArray jsonChapters = new JSONArray(list);

        final Book javaBook = new Book("Java. Библиотека профессионала",
                false, 1500, new Author("Кей Хорстманн"),
                "2. Ввод и вывод", "3. XML");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", javaBook.getTitle());
        jsonObject.put("discount", javaBook.isDiscount());
        jsonObject.put("price", javaBook.getPrice());
        jsonObject.put("author", jsonAuthor);
        jsonObject.put("chapters", jsonChapters);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(javaBook));
    }
}

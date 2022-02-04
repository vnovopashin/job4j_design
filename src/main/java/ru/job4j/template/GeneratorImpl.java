package ru.job4j.template;

import java.util.Map;

/**
 * ККласс реализует генератор
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class GeneratorImpl implements Generator {

    /**
     * Метод получает шаблон вида I am a ${name}, Who are ${subject}?,
     * где ${name} и ${subject} - это ключи, которые нужно заменить на значения
     *
     * @param template шаблон
     * @param args     структура данных содержащая пары ключ-значение
     * @return возвращает сгенерированную строку, вида
     * I am a Petr Arsentev, Who are you?
     */
    @Override
    public String produce(String template, Map<String, String> args) {
        return null;
    }
}

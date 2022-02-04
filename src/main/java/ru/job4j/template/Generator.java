package ru.job4j.template;

import java.util.Map;

/**
 * Интерфейс описывающий генератор
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public interface Generator {
    String produce(String template, Map<String, String> args);
}

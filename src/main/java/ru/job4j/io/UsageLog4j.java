package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс демонстрирует работу с шаблоном для подстановки переменных
 * реализованном в библиотеке логирования slf4j
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 66;
        short s = 1995;
        char c = 'a';
        int i = 5;
        long l = 77L;
        float f = 23f;
        double d = 67;
        boolean bool = true;

        LOG.debug("Primitive types in Java: byte = {}, short = {}, char = {}, int = {}, long = {}, float = {}, double = {}, boolean = {}", b, s, c, i, l, f, d, bool);
    }
}

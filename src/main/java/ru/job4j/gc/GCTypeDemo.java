package ru.job4j.gc;

import java.util.Random;

/**
 * В данном классе рассматривается сборщики мусора,
 * программа запускается с различными ключами.
 * Чтобы увидеть лог сборщика мусора в консоли мы устанавливаем флаг
 * -Xlog:gc* (до JDK 9 нужно использовать -XX:-PrintGCDetails).
 * Ключи для запуска:
 * - Serial => -XX:+UseSerialGC
 * - Parallel => -XX:+UseParallelGC
 * - CMS => -XX:+UseConcMarkSweepGC (доступен до JDK 14)
 * - G1 => -XX:+UseG1GC
 * - ZGC => -XX:+UseZGC
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class GCTypeDemo {
    public static void main(String[] args) {
        Random random = new Random();
        int length = 100;
        String[] data = new String[1_000_000];
        for (int i = 0; i == (i + 1) % data.length;) {
            data[i] = String.valueOf(
                    (char) random.nextInt(255)
            ).repeat(length);
        }
    }
}

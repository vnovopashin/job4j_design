package ru.job4j.gc;

/**
 * Класс демонстрирует работу по сборке мусора.
 * Создает объекты без ссылок, т.е. потенциально те что нужно удалить.
 * В Edit Configurations... в разделе VM options задаем с помощью ключей -Xmx4m -Xms4m,
 * максимальный и начальный размеры heap, в данном случае 4 мегабайта.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class GCDemo {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    /**
     * Метод выводит некоторые характеристики памяти:
     * -freeMemory(), возвращает количество свободной памяти в байтах.
     * -totalMemory(), возвращает общее количество памяти которое может быть использовано.
     * -maxMemory(), возвращает максимальное количество памяти которое может быть использовано.
     */
    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 10000; i++) {
            new Person(i, "N" + i);
        }
        System.gc();
        info();
    }
}

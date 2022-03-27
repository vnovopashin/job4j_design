package ru.job4j.ood.srp;

import java.util.Objects;

/**
 * Класс реализует паттерн Singleton, который помимо основной задачи
 * позволяет контролировать количество созданных объектов.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Singleton {
    private static Singleton instance;
    private final String connection;

    private Singleton(String connection) {
        this.connection = connection;
    }

    public static Singleton getInstance(String connection) {
        if (instance == null) {
            instance = new Singleton(connection);
        }
        return instance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Singleton singleton = (Singleton) o;
        return Objects.equals(connection, singleton.connection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connection);
    }

    @Override
    public String toString() {
        return "Singleton{"
                + "connection='" + connection + '\''
                + '}';
    }

    public static void main(String[] args) {
        Singleton one = Singleton.getInstance("Соединение с базой данных");
        Singleton two = Singleton.getInstance("Hello world!");
        System.out.println(one);
        System.out.println(two);
        System.out.println(one == two);
    }
}

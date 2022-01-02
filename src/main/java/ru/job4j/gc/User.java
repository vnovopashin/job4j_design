package ru.job4j.gc;

/**
 * Класс на базе которого будут созданы объекты, в количестве необходимом для вызова GC.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class User {
    private String name;
    private int id;

    public User(String name, int age) {
        this.name = name;
        this.id = age;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %s %d%n", name, id);
    }
}

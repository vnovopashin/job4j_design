package ru.job4j.serialization.json;

/**
 * Класс Author
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Author {

    private String name;

    public Author(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{"
                + "name='" + name + '\''
                + '}';
    }
}

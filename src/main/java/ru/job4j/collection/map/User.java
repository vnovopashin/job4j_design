package ru.job4j.collection.map;

import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;
import java.util.Objects;

/**
 * Класс описывает модель данных пользователя.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday + '}';
    }

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();

        User userOne = new User("John Doe", 1, calendar);
        User userTwo = new User("John Doe", 1, calendar);

        Map<User, Object> users = new HashMap<>();
        users.put(userOne, new Object());
        users.put(userTwo, new Object());

        for (Map.Entry<User, Object> pair : users.entrySet()) {
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
    }
}

package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Класс демонстрирует работу с библиотечным классом PrepareStatement,
 * который предназначен для DML операций - insert, select, update, delete.
 * Здесь будет рассмотрена вставка в таблицу, т.е. insert.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Метод читает данные из файла и добавляет в список
     *
     * @return возвращает список пользователей users
     * @throws IOException в случае ошибки ввода вывода бросает исключение
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(line -> {
                String[] arr = line.split(";", 2);
                users.add(new User(arr[0], arr[1]));
            });
        }
        return users;
    }

    /**
     * Метод получает в параметры список пользователей
     * и сохраняет его в базу данных
     *
     * @param users список для сохранения в базу данных
     * @throws ClassNotFoundException в случае если класс не найден бросает исключение
     * @throws SQLException в случае ошибки при работе с базой данных бросает исключение
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "insert into users(name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    /**
     * Класс представляет собой модель данных User,
     * у юзера есть имя и электронная почта
     */
    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}

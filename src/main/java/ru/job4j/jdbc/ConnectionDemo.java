package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс реализует подключение к базе данных, для подключения используется ранее реализованный класс Config,
 * который позволят прочитать данные (url, login, password) необходимые для подключения к базе данных из файла
 * app.properties
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Config config = new Config("src/main/resources/app.properties");
        config.load();
        String reg = config.value("reg");
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        Class.forName(reg);
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}

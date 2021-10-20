package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Класс демонстрирует работу с библиотечным классом Statement,
 * который предназначен для исполнения операций типа DDL, т.е. создания, удаления, обновления таблиц/баз данных.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    /**
     * Метод инициализирует подключение к базе данных,
     * необходимые настройки для подключения метод читает из файла app.properties
     */
    private void initConnection() {
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод выполняет запросы к базе данных
     *
     * @param query принимает запрос, который необходимо выполнить
     */
    private void executeStatement(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создает пустую таблицу в базе данных
     *
     * @param tableName имя создаваемой таблицы
     */
    public void createTable(String tableName) {
        String sql = String.format(
                "create table if not exists %s (%s, %s);",
                tableName,
                "id serial primary key",
                "name text"
        );
        executeStatement(sql);
    }

    /**
     * Метод удаляет таблицу в базе данных
     *
     * @param tableName имя удаляемой таблицы
     */
    public void dropTable(String tableName) {
        String sql = String.format(
                "drop table %s;",
                tableName
        );
        executeStatement(sql);
    }

    /**
     * Метод добавляет столбец в таблицу
     *
     * @param tableName  имя таблицы
     * @param columnName имя добавляемого столбца
     * @param type       тип добавляемого столбца
     */
    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "alter table %s add %s %s;",
                tableName,
                columnName,
                type
        );
        executeStatement(sql);
    }

    /**
     * Метод удаляет столбец из таблицы
     *
     * @param tableName  имя таблицы
     * @param columnName имя удаляемого столбца
     */
    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "alter table %s drop column %s;",
                tableName,
                columnName
        );
        executeStatement(sql);
    }

    /**
     * Метод переименовывает столбец в таблице
     *
     * @param tableName     имя таблицы
     * @param columnName    имя столбца, который необходимо переименовать
     * @param newColumnName новое имя столбца
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "alter table %s rename column %s to %s;",
                tableName,
                columnName,
                newColumnName
        );
        executeStatement(sql);
    }

    /**
     * Метод позволяет проверить создание, удаление или изменение таблицы
     *
     * @param connection принимает соединение к базе данных
     * @param tableName  название таблицы
     * @return возвращает строковый результат в консоль
     * @throws Exception в случае ошибки выбрасывает исключение
     */
    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    /**
     * Метод закрывает соединение с базой данных
     *
     * @throws Exception в случае ошибки выбрасывает исключение
     */
    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}

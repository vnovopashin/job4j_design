package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Класс реализует интерфейс Report, и генерирует версию отчета для отдела программистов.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ProgrammingReport implements Report {
    private Store store;

    public ProgrammingReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE HTML>")
                .append(System.lineSeparator())
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">")
                .append(System.lineSeparator())
                .append("<title>Таблица</title>")
                .append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("<table>").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("<th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th>")
                .append(System.lineSeparator())
                .append("</tr>").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr>").append(System.lineSeparator())
                    .append("<td>").append(employee.getName()).append("</td>")
                    .append("<td>").append(employee.getHired()).append("</td>")
                    .append("<td>").append(employee.getFired()).append("</td>")
                    .append("<td>").append(employee.getSalary()).append("</td>")
                    .append("</tr>").append(System.lineSeparator());
        }
        text.append("</table>").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        return text.toString();
    }
}

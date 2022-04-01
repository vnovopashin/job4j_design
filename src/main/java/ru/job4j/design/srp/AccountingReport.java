package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Класс реализует интерфейс Report, и генерирует версию отчета для бухгалтерии.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class AccountingReport implements Report {
    private Store store;
    public static final double RATIO = 1.1;

    public AccountingReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() * RATIO).append("₽").append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

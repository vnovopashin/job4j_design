package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс кинотеатр
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public interface Cinema {
    List<Session> find(Predicate<Session> filter);

    Ticket buy(Account account, int row, int column, Calendar date);

    void add(Session session);
}

package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты на класс Cinema
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class CinemaTest {
    @Ignore
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    /**
     * Метод описывает случай, когда один аккаунт покупает один и тот же билет дважды
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyOneTicketTwiceThenException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        cinema.buy(account, 1, 1, date);
        cinema.buy(account, 1, 1, date);
    }

    /**
     * Метод описывает случай, когда покупается билет на несуществующее место
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyTicketNonExistSeatThenException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        cinema.buy(account, 0, 0, date);

    }

    /**
     * Метод описывает случай покупки разными аккаунтами билета на одно и то же место
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyOneTicketDifferentAccountsThenException() {
        Account account = new AccountCinema();
        Account account2 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        cinema.buy(account, 0, 0, date);
        cinema.buy(account2, 0, 0, date);

    }

    @Ignore
    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    /**
     * Метод описывает случай, когда сеанс не найден
     */
    @Ignore
    @Test
    public void whenFindThenNotFound() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertNull(sessions);
    }
}

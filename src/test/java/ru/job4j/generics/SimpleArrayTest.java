package ru.job4j.generics;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddThenGet() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(1);
        simpleArray.add(5);
        int rsl = simpleArray.get(0);
        assertThat(rsl, is(5));
    }

    @Test
    public void whenSetZeroThenZero() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(5);
        simpleArray.add(7);
        simpleArray.add(9);
        simpleArray.set(1, 0);
        int rsl = simpleArray.get(1);
        assertThat(rsl, is(0));
    }

    @Test
    public void whenRemove() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(5);
        simpleArray.add(7);
        simpleArray.add(9);
        int rsl = simpleArray.remove(1);
        assertThat(rsl, is(7));
        assertThat(simpleArray.toString(), is("[5, 9, null]"));
    }

    @Test
    public void whenMultiCallHasNextThenTrue() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(5);
        simpleArray.add(7);
        simpleArray.add(9);
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));

    }

    @Test
    public void whenReadSequence() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(5);
        simpleArray.add(7);
        simpleArray.add(9);
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(9));
    }

    @Test(expected = NoSuchElementException.class)
    public void returnNumbersSequentially() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(5);
        simpleArray.add(7);
        simpleArray.add(9);
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.hasNext(), Is.is(true));
        assertThat(it.next(), Is.is(5));
        assertThat(it.hasNext(), Is.is(true));
        assertThat(it.next(), Is.is(7));
        assertThat(it.hasNext(), Is.is(true));
        assertThat(it.next(), Is.is(9));
        assertThat(it.hasNext(), Is.is(false));
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        Iterator<Integer> it = simpleArray.iterator();
        it.next();
    }
}
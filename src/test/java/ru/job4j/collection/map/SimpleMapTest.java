package ru.job4j.collection.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutThenGet() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("0", "zero");
        map.put("1", "one");
        map.put("2", "two");
        assertThat(map.get("0"), is("zero"));
        assertThat(map.get("1"), is("one"));
        assertThat(map.get("2"), is("two"));
    }

    @Test
    public void whenTwoDiffPutThenCountTwo() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("0", "zero");
        map.put("1", "one");
        assertThat(map.getCount(), is(2));
    }

    @Test
    public void whenTwoEqualsPutThenCountOne() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("0", "zero");
        map.put("0", "one");
        assertThat(map.getCount(), is(1));
    }

    @Test
    public void whenMapEmptyThenNull() {
        SimpleMap<String, String> map = new SimpleMap<>();
        assertNull(map.get("1"));
    }

    @Test
    public void whenResize() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("0", "zero");
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        map.put("4", "four");
        map.put("5", "five");
        assertThat(map.getCapacity(), is(16));
    }

    @Test
    public void whenRemove() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("0", "zero");
        map.put("1", "one");
        assertTrue(map.remove("0"));
        assertFalse(map.remove("2"));
        assertFalse(map.remove("5"));
    }

    @Test
    public void whenPutThenIt() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("0", "zero");
        map.put("1", "one");
        map.put("2", "two");
        Iterator<String> it = map.iterator();
        assertThat(it.next(), is("0"));
        assertThat(it.next(), is("1"));
        assertThat(it.next(), is("2"));
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("0", "zero");
        map.put("1", "one");

        Iterator<String> first = map.iterator();
        assertThat(first.hasNext(), is(true));
        assertThat(first.next(), is("0"));
        assertThat(first.hasNext(), is(true));
        assertThat(first.next(), is("1"));
        assertThat(first.hasNext(), is(false));

        Iterator<String> second = map.iterator();
        assertThat(second.hasNext(), is(true));
        assertThat(second.next(), is("0"));
        assertThat(second.hasNext(), is(true));
        assertThat(second.next(), is("1"));
        assertThat(second.hasNext(), is(false));
    }

    @Test
    public void whenHasNext() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("0", "zero");
        map.put("1", "one");
        Iterator<String> it = map.iterator();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleMap<String, String> map = new SimpleMap<>();
        Iterator<String> it = map.iterator();
        it.next();
    }


    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("0", "zero");
        Iterator<String> it = map.iterator();
        map.put("1", "one");
        it.next();
    }


}
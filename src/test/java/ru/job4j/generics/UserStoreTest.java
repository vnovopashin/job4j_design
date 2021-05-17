package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    private Store<User> store;
    User user1 = new User("1");
    User user2 = new User("2");
    User user3 = new User("3");
    User user4 = new User("4");

    @Before
    public void setUp() {
        store = new MemStore<>();
        store.add(user1);
        store.add(user2);
        store.add(user3);
    }

    @Test
    public void whenAdd() {
        store.add(user4);
        assertThat(store.findBy("4"), is(user4));
    }

    @Test
    public void whenReplace() {
        assertTrue(store.replace("2", new User("5")));
    }

    @Test
    public void whenNotReplace() {
        assertFalse(store.replace("5", new User("7")));
    }

    @Test
    public void whenDelete() {
        assertTrue(store.delete("1"));
    }

    @Test
    public void whenNotDelete() {
        assertFalse(store.delete("0"));
    }

    @Test
    public void whenFindBy() {
        assertThat(store.findBy("2"), is(user2));
    }

    @Test
    public void whenNotFindBy() {
        assertNull(store.findBy("5"));
    }
}

package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithBreakingThePattern() {
        String path = "./data/pair_with_breaking_the_pattern.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
    }
}

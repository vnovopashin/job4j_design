package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * В классе тестируются метод produce
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class GeneratorImplTest {

    @Ignore
    @Test
    public void whenProducerOk() {
        Generator generator = new GeneratorImpl();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        String res = generator.produce("I am a ${name}, Who are ${subject}?", map);
        assertThat(res, is("I am a Petr Arsentev, Who are you?"));
    }

    /**
     * Тест описывает случай когда в шаблоне есть ключи,
     * которых нет в карте и бросает исключение.
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenTemplateHasKeysAndProducerNot() {
        Generator generator = new GeneratorImpl();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        generator.produce("I am a ${name}, I am ${age} years old"
                + " Who are ${subject}?", map);
    }

    /**
     * Тест описывает случай когда в карте есть лишние ключи,
     * которых нет в шаблоне и бросает исключение.
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenExtraKeysInMap() {
        Generator generator = new GeneratorImpl();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        map.put("age", "25");
        generator.produce("I am a ${name}, Who are ${subject}?", map);
    }
}

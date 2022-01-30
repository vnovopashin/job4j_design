package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Класс тестирует методы класса MaxMin
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class MaxMinTest {

    @Test
    public void whenMax() {
        List<Integer> list = List.of(0, 1);
        MaxMin maxMin = new MaxMin();
        int res = maxMin.max(list, Integer::compare);
        Assert.assertEquals(res, 1);
    }

    @Test
    public void whenMin() {
        List<Integer> list = List.of(0, 1);
        MaxMin maxMin = new MaxMin();
        int res = maxMin.min(list, Integer::compare);
        Assert.assertEquals(res, 0);
    }
}

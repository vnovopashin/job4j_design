package ru.job4j.ood.ocp;

/**
 * Данный класс нарушает принцип OCP, т.к. при установке другой операционной системы,
 * например Linux, нам придется изменять данный класс, чтобы написать метод для установки Linux.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Computer {
    public void installWindows() {
        System.out.println("Windows установлена.");
    }
}

package ru.job4j.ood.srp;

/**
 * Класс Calculator нарушает принцип SRP, так как он выводит в консоль информацию о себе,
 * умеет производить операцию сложения двух чисел. Следовательно, нужно разделить данный класс на два
 * интерфейса один для расчетов, другой для требуемого вывода.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Calculator {
    private final int x;
    private final int y;

    public Calculator(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double calculate() {
        return x + y;
    }

    public void print() {
        System.out.println(calculate());
    }

    public static void main(String[] args) {
        Calculator c = new Calculator(6, 7);
        c.calculate();
        c.print();
    }
}

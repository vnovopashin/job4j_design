package ru.job4j.serialization.json;

/**
 * Класс демонстрирует пример, когда при преобразовании объектов в json-строку возможно рекурсивное зацикливание,
 * в данном случае объект А содержи ссылку на объект В, а он в свою очередь ссылается на первоначальный объект А.
 * При выполнении будут осуществляться переходы по ссылке и сериализация до возникновения исключения StackOverflowError.
 * Чтобы избежать исключения, необходимо разорвать цепочку, как правило,
 * это делается в момент перехода по ссылке на объект,
 * который уже сериализован. В библиотеке JSON-Java (org.json) для этого существует аннотация @JSONPropertyIgnore
 * Если установить аннотацию @JSONPropertyIgnore над методом getB(), то исключение StackOverflowError не возникнет.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class A {
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }
}

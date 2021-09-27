package ru.job4j.serialization.json;

import org.json.JSONObject;

/**
 * Класс демонстрирует пример, когда при преобразовании объектов в json-строку возможно рекурсивное зацикливание,
 * в данном случае объект А содержи ссылку на объект В, а он в свою очередь ссылается на первоначальный объект А.
 * При выполнении будут осуществляться переходы по ссылке и сериализация до возникновения исключения StackOverflowError.
 * Чтобы избежать исключения, необходимо разорвать цепочку, как правило,
 * это делается в момент перехода по ссылке на объект,
 * который уже сериализован. В библиотеке JSON-Java (org.json) для этого существует аннотация @JSONPropertyIgnore
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class B {
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);

        System.out.println(new JSONObject(b));
    }
}

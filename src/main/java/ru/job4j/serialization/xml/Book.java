package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Класс демонстрирует пример сериализации/десериализации объекта Book в XML и обратно в объект
 *
 * @author Vasiliy Novopashin
 * * @version 1.0
 */

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    @XmlAttribute
    private String title;

    @XmlAttribute
    private boolean discount;

    @XmlAttribute
    private int price;

    private ru.job4j.serialization.xml.Author author;

    @XmlElementWrapper
    @XmlElement(name = "chapter")
    private String[] chapters;

    public Book() {
    }

    public Book(String title, boolean discount, int price, ru.job4j.serialization.xml.Author author, String... chapters) {
        this.title = title;
        this.discount = discount;
        this.price = price;
        this.author = author;
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "Book{"
                + "title='" + title + '\''
                + ", discount=" + discount
                + ", price=" + price
                + ", author=" + author
                + ", chapters=" + Arrays.toString(chapters)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final ru.job4j.serialization.xml.Book javaBook = new ru.job4j.serialization.xml.Book("Java. Библиотека профессионала",
                false, 1500, new ru.job4j.serialization.xml.Author("Кей Хорстманн"),
                "2. Ввод и вывод", "3. XML");

        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(ru.job4j.serialization.xml.Book.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(javaBook, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // Десериализуем
            Book result = (Book) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}

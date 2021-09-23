package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс Author
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */

@XmlRootElement(name = "author")
public class Author {

    @XmlAttribute
    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{"
                + "name='" + name + '\''
                + '}';
    }
}

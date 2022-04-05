package ru.job4j.ood.ocp;

import java.util.ArrayList;

/**
 * Класс нарушает принцип OCP, т.к.
 * если в последующем нам потребуется хранить записи записной книжки не в ArrayList,
 * а в другой структуре хранения данных, нам потребуется изменить класс.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Notebook {
    private ArrayList<String> entries;

    public void addEntry(String entry) {
        entries.add(entry);
    }
}

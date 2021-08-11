package ru.job4j.io;

import java.io.*;

/**
 * Класс реализует преобразование одного файла в другой, на примере файла регистрации событий сервера.
 * Файл имеет следующий формат Status (возможны например следующие статусы 200, 300, 400, 500)
 * и Date (т.е это время проверки, например 10:56:01)
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Analizy {

    /**
     * Метод находит и записывает в файл диапазоны когда сервер не работал,
     * т.е. записывает время когда статус на сервере был 400 или 500
     *
     * @param source путь к файлу лога
     * @param target путь к файлу с результатом анализа
     * @throws IOException метод бросает исключение в случае ошибок вода/вывода
     */
    public void unavailable(String source, String target) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
        PrintWriter out = new PrintWriter(new FileOutputStream(target));
        try {
            String str;
            String[] arr;
            String curr = null;
            while ((str = reader.readLine()) != null) {
                arr = str.split(" ");
                if ((arr[0].equals("400") || arr[0].equals("500")) && curr == null) {
                    curr = arr[1];
                    out.print(curr + ";");
                } else if (!(arr[0].equals("400") || arr[0].equals("500")) && curr != null) {
                    curr = arr[1];
                    out.print(curr + ";");
                    curr = null;
                    out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Analizy analizy = new Analizy();
        analizy.unavailable("source.txt", "target.txt");
        analizy.unavailable("source2.txt", "target2.txt");
    }
}

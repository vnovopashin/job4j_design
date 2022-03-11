package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует программу "Консольный чат".
 * - пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * - программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
 * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
 * - при вводе слова «закончить» программа прекращает работу.
 * - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Метод содержит логику чата.
     */
    public void run() {
        List<String> log = new ArrayList<>();
        List<String> phrases = readPhrases();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();
            while (!str.equals(OUT)) {
                if (str.equals(STOP)) {
                    while (!str.equals(CONTINUE)) {
                        log.add(str);
                        str = br.readLine();
                    }
                }
                log.add(str);
                int index = (int) (Math.random() * phrases.size());
                String answer = phrases.get(index);
                System.out.println(answer);
                log.add(answer);
                str = br.readLine();
                if (str.equals(OUT)) {
                    log.add(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveLog(log);
    }

    /**
     * Метод читает фразы из файла (ответы бота)
     *
     * @return возвращает список фраз.
     */
    private List<String> readPhrases() {
        try {
            return Files.readAllLines(Path.of(botAnswers));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Метод сохраняет лог чата в файл.
     *
     * @param log принимает список строк, представляющий собой диалог между ботом и пользователем.
     */
    private void saveLog(List<String> log) {
        try (PrintWriter printWriter = new PrintWriter(path)) {
            for (String s : log) {
                printWriter.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("logChat.txt", "answers.txt");
        cc.run();
    }
}

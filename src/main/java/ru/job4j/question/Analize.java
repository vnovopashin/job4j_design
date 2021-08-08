package ru.job4j.question;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Класс реализует статистику по коллекции,
 * разницу между начальным состоянием множества и измененным состоянием множества.
 * Множество может быть изменено следующим образом,
 * добавление, изменение и удаление элементов
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class Analize {

    /**
     * Метод находит разницу между состояниями множества,
     * где элементами являются пользователи User, у которых есть идентификатор и имя
     *
     * @param previous начальное множество
     * @param current  измененное множество
     * @return возвращает объект info,
     * который показывает разницу между previous начальным множеством и current измененным множеством
     */
    public static Info diff(Set<User> previous, Set<User> current) {

        Info info = new Info(0, 0, 0);
        Map<Integer, String> map = new HashMap<>();

        for (User user : previous) {
            map.put(user.getId(), user.getName());
        }

        for (User user : current) {
            if (map.containsKey(user.getId())) {
                if (!map.containsValue(user.getName())) {
                    info.setChanged(info.getChanged() + 1);
                }
            } else {
                info.setAdded(info.getAdded() + 1);
            }
        }
        info.setDeleted(previous.size() + info.getAdded() - current.size());
        return info;
    }
}

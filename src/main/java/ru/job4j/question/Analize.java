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

        Map<Integer, String> previousMap = new HashMap<>();
        Map<Integer, String> currentMap = new HashMap<>();
        Info info = new Info(0, 0, 0);

        for (User user : previous) {
            previousMap.put(user.getId(), user.getName());
        }
        for (User user : current) {
            currentMap.put(user.getId(), user.getName());
        }
        for (Integer key : currentMap.keySet()) {
            if (!previousMap.containsKey(key)) {
                info.setAdded(info.getAdded() + 1);
            }
        }
        for (Map.Entry<Integer, String> entry : previousMap.entrySet()) {
            if (currentMap.containsKey(entry.getKey()) && !currentMap.containsValue(entry.getValue())) {
                info.setChanged(info.getChanged() + 1);
            }
            if (!currentMap.containsKey(entry.getKey())) {
                info.setDeleted(info.getDeleted() + 1);
            }
        }
        return info;
    }
}

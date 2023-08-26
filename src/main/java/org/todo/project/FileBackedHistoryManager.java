package org.todo.project;

import java.util.ArrayList;
import java.util.List;

public class FileBackedHistoryManager extends InMemoryHistoryManager {

    public static List<Integer> fromStringToIds(String value) {
        List<Integer> taskIds = new ArrayList<>();

        String[] strings = value.split(",");
        for (String s : strings) {
            taskIds.add(Integer.parseInt(s));
        }

        return taskIds;
    }

    public static String toString(HistoryManager historyManager) {
        List<Task> tasksHistory = historyManager.getHistory();
        StringBuilder historyIds = new StringBuilder();

        if (!tasksHistory.isEmpty()) {
            for (Task task : tasksHistory) {
                historyIds.append(task.getId()).append(",");
            }
            historyIds.deleteCharAt(historyIds.length() - 1);
            return historyIds.toString();
        }
        return "";
    }

}

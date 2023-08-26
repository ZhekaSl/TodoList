package org.todo.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private static int id;
    protected Map<Integer, Task> tasks;
    protected HistoryManager historyManager;

    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        historyManager = Managers.getDefaultHistory();
    }

    @Override
    public void clear() {
        tasks.clear();
        id = 0;
    }

    @Override
    public Task getTask(int id) {
        Task task = tasks.get(id);
        historyManager.add(task);

        return tasks.get(id);
    }

    @Override
    public void update(Task task) {
        tasks.put(task.getId(), task);

        if (task instanceof Subtask subtask) {
            updateEpicStatus(subtask);
        }
    }

    @Override
    public int size() {
        return tasks.size();
    }

    public void printTasks() {
        for (Task task : tasks.values()) {
            if (!(task instanceof Subtask)) {

                System.out.println(task);
                System.out.println("---------------");
            }
        }

    }

    @Override
    public void deleteTask(int id) {
        if (id <= 0 || id > tasks.size()) {
            throw new IndexOutOfBoundsException("Введен неправильный индекс!");
        }

        Task task = tasks.get(id);
        if (task instanceof Subtask subtask) {
            subtask.getEpic().removeSubtask(subtask);
            updateEpicStatus(subtask);
        }
        tasks.remove(id);
        InMemoryTaskManager.id--;
        reindexTasksAfterDeleting(id);
    }

    public void printSubtasksByEpic(Epic epic) {
        for (Subtask subtask : epic.getSubtasks()) {
            System.out.println(subtask);
        }
    }

    @Override
    public void addTask(Task task) {
        task.setId(++id);
        tasks.put(task.getId(), task);

        if (task instanceof Subtask subtask) {
            subtask.getEpic().addSubtask(subtask);
            updateEpicStatus(subtask);
        }
    }

    public List<Task> history() {
        return historyManager.getHistory();
    }



    private void updateEpicStatus(Subtask subtask) {
        subtask.getEpic().update();
    }

    private void reindexTasksAfterDeleting(int deletedTaskId) {
        Map<Integer, Task> newMap = new HashMap<>();
        int index = 1;
        for (Task task : tasks.values()) {
            newMap.put(index, task);
            task.setId(index);
            index++;
        }

        tasks = newMap;
    }


}

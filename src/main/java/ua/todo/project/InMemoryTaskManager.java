package ua.todo.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private int id;
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
            subtask.getEpic().updateStatus();
        }
    }

    public List<Task> getTasks() {
        if (tasks != null)
            return tasks.values().stream().toList();
        else return new ArrayList<>();
    }

    @Override
    public List<Task> getPrioritizedTasks() {
        return null;
        //TODO
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
        }
        historyManager.remove(id);
        tasks.remove(id);
        this.id--;
        reindexTasksAfterDeleting();
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
        }
    }

    public List<Task> history() {
        return historyManager.getHistory();
    }


    private void reindexTasksAfterDeleting() {
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

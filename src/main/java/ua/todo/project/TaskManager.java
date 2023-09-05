package ua.todo.project;

import java.util.List;

public interface TaskManager {
    void addTask(Task task);

    void deleteTask(int id);

    Task getTask(int id);

    List<Task> getTasks();

    List<Task> getPrioritizedTasks();

    void update(Task task);

    int size();

    void clear();

    List<Task> history();
}

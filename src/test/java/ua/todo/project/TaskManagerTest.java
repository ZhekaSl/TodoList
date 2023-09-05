package ua.todo.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ua.todo.project.enumeration.TaskType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
    TaskManager manager;

    @BeforeEach
    public void init() {
        manager = new InMemoryTaskManager();
    }

    @Test
    public void addTask() {

        Task task = new Task("task", "task1");

        manager.addTask(task);
        int taskId = task.getId();

        Task savedTask = manager.getTask(taskId);
        assertNotNull(savedTask, "Задача не найдена");
        assertEquals(task, savedTask, "Задачи не совпадают!");
    }

    @Test
    public void deleteTask() {
        Task task = new Task("task", "task1");
        manager.addTask(task);
        int id = task.getId();

        manager.deleteTask(id);
        List<Task> tasks = manager.getTasks();

        assertEquals(0, tasks.size());
    }

    @Test
    public void getTasks() {
        Task task = new Task("task", "task1");
        Task task1 = new Task("task", "task1");
        Task task2 = new Task("task", "task1");
        Task task3 = new Task("task", "task1");
        manager.addTask(task);
        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(task3);
        List<Task> tasks = manager.getTasks();
        assertEquals(4, tasks.size(), "Добавлены не все задачи в менеджер");
        assertEquals(task3, tasks.get(3), "Задачи в списке расположены в неправильном порядке");
    }

    @Test
    public void getTask() {
        Task task = new Task("task", "task1");
        Task task1 = new Task("task", "task1");
        Task task2 = new Task("task", "task1");
        Task task3 = new Task("task", "task1");
        manager.addTask(task);
        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(task3);
        Task task4 = manager.getTask(4);
        assertEquals(task3, task4);
        assertNull(task4);
        manager.clear();
        task4 = manager.getTask(0);
        assertNull(task4);
    }

    @Test
    public void update() {

    }

    @Test
    public void size() {
        Task task = new Task("task", "task1");
        Task task1 = new Task("task", "task1");
        Task task2 = new Task("task", "task1");
        Task task3 = new Task("task", "task1");
        manager.addTask(task);
        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(task3);
        List<Task> tasks = manager.getTasks();
        assertEquals(4, tasks.size());
        manager.deleteTask(3);
        tasks = manager.getTasks();
        assertEquals(3, tasks.size());
    }

    @Test
    public void clear() {
        Task task = new Task("task", "task1");
        Task task1 = new Task("task", "task1");
        Task task2 = new Task("task", "task1");
        Task task3 = new Task("task", "task1");
        manager.addTask(task);
        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(task3);
        manager.clear();
        assertEquals(0, manager.getTasks().size());
    }
}
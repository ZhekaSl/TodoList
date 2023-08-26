package org.todo.project;

import java.nio.file.Paths;

public class ToDoList {
    public static void main(String[] args) {
        /*Task task = new Task("Топ", "аолво");
        Task task1 = new Task("То2п1", "аолво");
        Task task2 = new Task("То2п2", "аолво");
        Task task3 = new Task("То2п3", "аолво");
        Task task4 = new Task("То2п4", "аолво");
        Task task5 = new Task("То2п5", "аолво");
        Task task6 = new Task("То2п6", "аолво");
        Task task7 = new Task("То2п7", "аолво");
        Task task8 = new Task("То2п8", "аолво");
        Task task9 = new Task("То2п9", "аолво");
        Task task10 = new Task("То2п10", "аолво");
        Epic epic1 = new Epic("epic1", "Хз");
        Subtask subtask = new Subtask("В коридоре1", "полить цветы в ванной", epic1);
        Subtask subtask3 = new Subtask("В кухне3", "полить цветы в кухне", epic1);
        Subtask subtask4 = new Subtask("В кухне4", "полить цветы в кухне", epic1);
        Subtask subtask5 = new Subtask("В кухне5", "полить цветы в кухне", epic1);

        TaskManager manager = new FileBackedTaskManager(Paths.get("C:\\Users\\zheny\\Desktop\\tasks.csv.txt"));


        manager.addTask(task1);
        manager.addTask(epic1);
        manager.addTask(task);
        manager.addTask(task2);
        manager.addTask(task3);
        manager.addTask(task4);
        manager.addTask(task5);
        manager.addTask(task6);
        manager.addTask(task7);
        manager.addTask(task8);
        manager.addTask(task9);
        manager.addTask(task10);
        manager.addTask(subtask);
        manager.addTask(subtask4);


        manager.getTask(3);
        manager.getTask(2);
        manager.getTask(6);
        manager.getTask(7);
        manager.getTask(2);
        manager.getTask(2);
        manager.getTask(1);
        manager.getTask(4);
        manager.getTask(8);
        manager.getTask(3);
        manager.getTask(13);
        manager.getTask(12);
        manager.getTask(11);
        manager.getTask(10);
        manager.getTask(9);
        manager.getTask(2);
        manager.getTask(2);
        System.out.println("История просмотренных задач:");


        int i = 0;
        for (Task t : manager.history()) {
            System.out.println((++i) + " ------------");
            System.out.println(t);
        }*/

        FileBackedTaskManager fileBackedTaskManager = FileBackedTaskManager.loadFromFile(Paths.get("C:\\Users\\zheny\\Desktop\\fjkdsjf.csv"));
        fileBackedTaskManager.printTasks();
        fileBackedTaskManager.deleteTask(1);
        fileBackedTaskManager.printTasks();
        fileBackedTaskManager.addTask(new Task("popa", "fdf"));
        fileBackedTaskManager.printTasks();
        fileBackedTaskManager.addTask(new Task("fdfffff", "zhopa"));
        fileBackedTaskManager.printTasks();
        fileBackedTaskManager.addTask(new Task("fdffff333f", "zhop3a"));
        System.out.println();
        fileBackedTaskManager.printTasks();





    }
}
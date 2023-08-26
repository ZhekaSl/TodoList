package org.todo.project;


import org.todo.project.enumeration.TaskStatus;

public class Task {
    protected String name;
    protected String description;
    protected int id;
    protected TaskStatus taskStatus;

    public Task(String name, String descr) {
        this.name = name;
        this.description = descr;
        this.taskStatus = TaskStatus.NEW;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    @Override
    public String toString() {


        return "Name: " + name + "\n" +
                "Description: " + description + "\n" +
                "ID: " + id + "\n" +
                "Status: " + taskStatus.getDescription();
    }
}

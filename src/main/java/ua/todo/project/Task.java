package ua.todo.project;


import ua.todo.project.enumeration.TaskStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Task {
    protected String name;
    protected String description;
    protected int id;
    protected TaskStatus taskStatus;
    protected Duration duration;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

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

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    private void setEndTime() {
        if (startTime != null && duration != null)
            endTime = startTime.plus(duration);
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Description: " + description + "\n" +
                "ID: " + id + "\n" +
                "Status: " + taskStatus.getDescription();
    }
}

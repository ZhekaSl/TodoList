package ua.todo.project;


import ua.todo.project.enumeration.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Subtask> subtasks;


    public Epic(String name, String descr) {
        super(name, descr);
        subtasks = new ArrayList<>();
        taskStatus = TaskStatus.NEW;
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
        updateStatus();
    }

    public void removeSubtask(Subtask subtask) {
        subtasks.remove(subtask);
        updateStatus();
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void updateStatus() {
        if (subtasks.isEmpty()) {
            taskStatus = TaskStatus.NEW;
        }
        boolean areAllSubtasksDone = true;

        for (Subtask subtask : subtasks) {
            if (subtask.getTaskStatus() != TaskStatus.DONE) {
                areAllSubtasksDone = false;
                break;
            }
        }

        if (areAllSubtasksDone) {
            taskStatus = TaskStatus.DONE;
        } else {
            taskStatus = TaskStatus.IN_PROGRESS;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        int index = 0;
        if (!subtasks.isEmpty()) {
            stringBuilder.append("\n  Подзадачи:");
            for (Subtask subtask : subtasks) {
                stringBuilder.append("\n");
                stringBuilder.append("\tName: ").append(subtask.name).append("\n");
                stringBuilder.append("\tDescription: ").append(subtask.description).append("\n");
                stringBuilder.append("\tID: ").append(subtask.id).append("\n");
                stringBuilder.append("\tStatus: ").append(subtask.taskStatus.getDescription());
                index++;
                if (index != subtasks.size())
                    stringBuilder.append("\n---");
            }
        }

        return stringBuilder.toString();
    }


}

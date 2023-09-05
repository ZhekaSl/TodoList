package ua.todo.project;

import org.junit.jupiter.api.Test;
import ua.todo.project.enumeration.TaskStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EpicTest {
    @Test
    public void emptyListOfSubtasks() {
        Epic epic = new Epic("epic", "epic");
        String taskStatus = epic.getTaskStatus().name();

        assertEquals("NEW", taskStatus);
    }

    @Test
    public void shouldBeDoneIfAllSubtasksStatusDone() {
        Epic epic = new Epic("fdf", "fdf");
        Subtask subtask1 = new Subtask("subtask1", "subtask1", epic);
        Subtask subtask2 = new Subtask("subtask2", "subtask2", epic);
        Subtask subtask3 = new Subtask("subtask3", "subtask3", epic);
        subtask1.taskStatus = TaskStatus.DONE;
        subtask2.taskStatus = TaskStatus.DONE;
        subtask3.taskStatus = TaskStatus.DONE;
        epic.addSubtask(subtask1);
        epic.addSubtask(subtask2);
        epic.addSubtask(subtask3);
        TaskStatus taskStatus = epic.getTaskStatus();

        assertEquals(TaskStatus.DONE, taskStatus);
    }

    @Test
    public void shouldBeInProgressIfAllSubtasksStatusInProgress() {
        Epic epic = new Epic("fdf", "fdf");
        Subtask subtask1 = new Subtask("subtask1", "subtask1", epic);
        Subtask subtask2 = new Subtask("subtask2", "subtask2", epic);
        Subtask subtask3 = new Subtask("subtask3", "subtask3", epic);
        subtask1.taskStatus = TaskStatus.IN_PROGRESS;
        subtask2.taskStatus = TaskStatus.IN_PROGRESS;
        subtask3.taskStatus = TaskStatus.IN_PROGRESS;
        epic.addSubtask(subtask1);
        epic.addSubtask(subtask2);
        epic.addSubtask(subtask3);
        TaskStatus taskStatus = epic.getTaskStatus();

        assertEquals(TaskStatus.IN_PROGRESS, taskStatus);
    }

    @Test
    public void shouldBeInProgressIfAllSubtasksStatusNew() {
        Epic epic = new Epic("fdf", "fdf");
        Subtask subtask1 = new Subtask("subtask1", "subtask1", epic);
        Subtask subtask2 = new Subtask("subtask2", "subtask2", epic);
        Subtask subtask3 = new Subtask("subtask3", "subtask3", epic);
        epic.addSubtask(subtask1);
        epic.addSubtask(subtask2);
        epic.addSubtask(subtask3);
        TaskStatus taskStatus = epic.getTaskStatus();

        assertEquals(TaskStatus.IN_PROGRESS, taskStatus);
    }

    @Test
    public void shouldBeInProgressIfSomeSubtasksStatusNewAndInProgress() {
        Epic epic = new Epic("fdf", "fdf");
        Subtask subtask1 = new Subtask("subtask1", "subtask1", epic);
        Subtask subtask2 = new Subtask("subtask2", "subtask2", epic);
        Subtask subtask3 = new Subtask("subtask3", "subtask3", epic);
        subtask2.taskStatus = TaskStatus.IN_PROGRESS;
        subtask3.taskStatus = TaskStatus.IN_PROGRESS;
        epic.addSubtask(subtask1);
        epic.addSubtask(subtask2);
        epic.addSubtask(subtask3);
        TaskStatus taskStatus = epic.getTaskStatus();

        assertEquals(TaskStatus.IN_PROGRESS, taskStatus);
    }

    @Test
    public void addSubtask() {
        Epic epic = new Epic("fdf", "fdf");
        Subtask subtask1 = new Subtask("subtask1", "subtask1", epic);
        Subtask subtask2 = new Subtask("subtask2", "subtask2", epic);
        epic.addSubtask(subtask1);
        epic.addSubtask(subtask2);
        List<Subtask> subtaskList = epic.getSubtasks();
        int size = subtaskList.size();
        Task subtask = subtaskList.get(1);
        assertEquals(2, size);
        assertEquals(subtask2, subtask);
    }

    @Test
    public void removeSubtask() {
        Epic epic = new Epic("fdf", "fdf");
        Subtask subtask1 = new Subtask("subtask1", "subtask1", epic);
        Subtask subtask2 = new Subtask("subtask2", "subtask2", epic);
        epic.addSubtask(subtask1);
        epic.addSubtask(subtask2);
        epic.removeSubtask(subtask1);
        List<Subtask> subtaskList = epic.getSubtasks();
        int size = subtaskList.size();
        Task subtask = subtaskList.get(0);
        assertEquals(1, size);
        assertEquals(subtask2,subtask);
    }

}

package org.todo.project;



import org.todo.project.enumeration.TaskStatus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class FileBackedTaskManager extends InMemoryTaskManager {
    private Path saveFile;

    public FileBackedTaskManager(Path saveFile) {
        this.saveFile = saveFile;
        initializeFile();
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
        save();
    }

    @Override
    public void deleteTask(int id) {
        super.deleteTask(id);
        save();
    }

    @Override
    public Task getTask(int id) {
        save();
        return super.getTask(id);
    }

    @Override
    public void update(Task task) {
        super.update(task);
        save();
    }

    @Override
    public void clear() {
        super.clear();
        save();
    }


    public void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(saveFile)) {
            writer.write("id,type,name,status,description,epic");

            writer.newLine();
            for (Task task : tasks.values()) {
                writer.write(toString(task));
                writer.newLine();
            }
            writer.newLine();
            writer.write(FileBackedHistoryManager.toString(historyManager));
        } catch (IOException e) {
            throw new RuntimeException("Что-то пошло не так при сохранении данных в файл!", e);
        }
    }

    public static FileBackedTaskManager loadFromFile(Path path) {
        FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(path);
        FileBackedHistoryManager historyManager = new FileBackedHistoryManager();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.readLine();
            String str;
            while (reader.ready() && !Objects.equals(str = reader.readLine(), "")) {
                Task task = fileBackedTaskManager.fromStringToTask(str);
                fileBackedTaskManager.tasks.put(task.getId(), task);
            }
            str = reader.readLine();
            if (str != null) {
                List<Integer> historyTasksId = FileBackedHistoryManager.fromStringToIds(str);
                for (Integer id : historyTasksId) {
                    historyManager.add(fileBackedTaskManager.getTask(id));
                }
                fileBackedTaskManager.historyManager = historyManager;
            }

        } catch (IOException e) {
            System.out.println("Хэ");
        }
        return fileBackedTaskManager;
    }

    public String toString(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(task.getId()).append(",")
                .append(task.getClass().getSimpleName().toUpperCase()).append(",")
                .append(task.getName()).append(",")
                .append(task.getTaskStatus().toString()).append(",")
                .append(task.getDescription()).append(",");
        if (task instanceof Subtask) stringBuilder.append(((Subtask) task).getEpic().getId());
        return stringBuilder.toString();
    }

    private Task fromStringToTask(String value) {
        String[] values = value.split(",");
        int id = Integer.parseInt(values[0]);
        String taskType = values[1];
        String name = values[2];
        TaskStatus taskStatus = TaskStatus.valueOf(values[3]);
        String description = values[4];

        Task task;
        if (taskType.equals("TASK")) task = new Task(name, description);
        else if (taskType.equals("EPIC")) task = new Epic(name, description);
        else {
            int epicId = Integer.parseInt(values[5]);
            task = new Subtask(name, description, (Epic) getTask(epicId));
            Subtask subtask = (Subtask) task;

            subtask.getEpic().addSubtask(subtask);
        }
        task.setId(id);
        task.setTaskStatus(taskStatus);
        return task;

    }

    private void initializeFile() {
        try {
            if (Files.notExists(saveFile)) {
                Files.createFile(saveFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Неверный путь к файлу!");
        }
    }


}

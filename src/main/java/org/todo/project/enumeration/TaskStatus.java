package org.todo.project.enumeration;

public enum TaskStatus {
    NEW("Новая!"),
    IN_PROGRESS("В процессе!"),
    DONE("Завершена!");
    private String description;

    TaskStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

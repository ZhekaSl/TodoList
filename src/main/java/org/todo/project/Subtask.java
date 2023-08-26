package org.todo.project;

public class Subtask extends Task {
    private Epic epic;


    public Subtask(String name, String descr, Epic epic) {
        super(name, descr);
        this.epic = epic;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public String toString() {
        return "Main task: " + epic.getName() + "\n" +
                "Name: " + name + "\n" +
                "Description: " + description + "\n" +
                "ID: " + id + "\n" +
                "Status: " + taskStatus.getDescription();

    }


}

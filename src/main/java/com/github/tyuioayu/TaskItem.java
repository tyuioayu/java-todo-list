package com.github.tyuioayu;

public class TaskItem {
    private String task;
    private String dueDate;
    private boolean isCompleted;

    public TaskItem(String task, String dueDate) {
        this.task = task;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public void toggleCompleted() {
        isCompleted = !isCompleted;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return (isCompleted ? "✓ " : "✗ ") + task + " (" + dueDate + ")";
    }
}

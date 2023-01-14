package com.example.todolist.Beans;

import java.util.Date;

public class TaskModel {
    private int id;
    private String name;
    private Date deadline;
    private String sharedWith = "";
    private boolean done;
    public TaskModel(int id, String name, Date deadline, String sharedWith,boolean done) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.sharedWith = sharedWith;
        this.done=done;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(String sharedWith) {
        this.sharedWith = sharedWith;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deadline=" + deadline +
                ", sharedWith='" + sharedWith + '\'' +
                ", done=" + done +
                '}';
    }
}

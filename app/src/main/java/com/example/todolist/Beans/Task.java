package com.example.todolist.Beans;

import java.util.Date;

public class Task {
    private int taskId;
    private String name;
    private String deadline;
    private Date deadlinee;
    private String done;
    private String lastUpdateDesc;
    private String lastUpdateDate;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Date getDeadlinee() {
        return deadlinee;
    }

    public void setDeadlinee(Date deadlinee) {
        this.deadlinee = deadlinee;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", name='" + name + '\'' +
                ", deadline='" + deadline + '\'' +
                ", deadlinee=" + deadlinee +
                ", done='" + done + '\'' +
                ", lastUpdateDesc='" + lastUpdateDesc + '\'' +
                ", lastUpdateDate='" + lastUpdateDate + '\'' +
                '}';
    }

    public Task(int taskId, String name, String deadline, Date deadlinee, String done, String lastUpdateDesc, String lastUpdateDate) {
        this.taskId = taskId;
        this.name = name;
        this.deadline = deadline;
        this.deadlinee = deadlinee;
        this.done = done;
        this.lastUpdateDesc = lastUpdateDesc;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Task(String name, Date deadlinee) {
        this.name = name;
        this.deadlinee = deadlinee;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getLastUpdateDesc() {
        return lastUpdateDesc;
    }

    public void setLastUpdateDesc(String lastUpdateDesc) {
        this.lastUpdateDesc = lastUpdateDesc;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}

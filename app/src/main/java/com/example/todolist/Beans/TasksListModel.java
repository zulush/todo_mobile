package com.example.todolist.Beans;

import java.util.List;

public class TasksListModel {
    private List<TaskModel> personalTasks;
    private List<TaskModel> sharedTasks;
    private List<TaskModel> groupTasks;

    public TasksListModel(List<TaskModel> personalTasks, List<TaskModel> sharedTasks, List<TaskModel> groupTasks) {
        this.personalTasks = personalTasks;
        this.sharedTasks = sharedTasks;
        this.groupTasks = groupTasks;
    }

    public List<TaskModel> getPersonalTasks() {
        return personalTasks;
    }

    public void setPersonalTasks(List<TaskModel> personalTasks) {
        this.personalTasks = personalTasks;
    }

    public List<TaskModel> getSharedTasks() {
        return sharedTasks;
    }

    public void setSharedTasks(List<TaskModel> sharedTasks) {
        this.sharedTasks = sharedTasks;
    }

    public List<TaskModel> getGroupTasks() {
        return groupTasks;
    }

    public void setGroupTasks(List<TaskModel> groupTasks) {
        this.groupTasks = groupTasks;
    }

    @Override
    public String toString() {
        return "TasksListModel{" +
                "personalTasks=" + personalTasks +
                ", sharedTasks=" + sharedTasks +
                ", groupTasks=" + groupTasks +
                '}';
    }
}

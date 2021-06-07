package com.example.slsl1_2.model;

import java.io.Serializable;

public class TaskModel implements Serializable {
    private String title;

    public String getTitle() {
        return title;
    }
    public TaskModel(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

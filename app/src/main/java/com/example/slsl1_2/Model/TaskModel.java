package com.example.slsl1_2.Model;

import java.io.Serializable;

public class TaskModel implements Serializable {
    private String title;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    private String description;

    public TaskModel(String title, String description) {
        this.title = title;
        this.description = description;
    }
}

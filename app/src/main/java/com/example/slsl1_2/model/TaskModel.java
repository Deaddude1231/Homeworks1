package com.example.slsl1_2.model;

import android.graphics.Color;

import java.io.Serializable;

public class TaskModel implements Serializable {
    private String title;
    private String background;

    public String getTitle() {
        return title;
    }

    public TaskModel(String title, String background) {
        this.background = background;
        this.title = title;
    }

    public TaskModel(){}
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}

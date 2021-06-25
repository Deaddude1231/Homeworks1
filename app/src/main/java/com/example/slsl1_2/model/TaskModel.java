package com.example.slsl1_2.model;

import android.graphics.Color;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class TaskModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String background;
    private String date;

    public String getTitle() {
        return title;
    }

    public TaskModel(String title, String background, String date) {
        this.date = date;
        this.background = background;
        this.title = title;
    }

    public TaskModel() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
}

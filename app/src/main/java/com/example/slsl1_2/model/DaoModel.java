package com.example.slsl1_2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DaoModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    String input;

    public DaoModel(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}

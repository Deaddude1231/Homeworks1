package com.example.slsl1_2.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.slsl1_2.model.TaskModel;

@Database(entities = {TaskModel.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskDao getTaskDao();
}

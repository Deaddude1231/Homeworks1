package com.example.slsl1_2.Room;

import android.app.Application;
import androidx.room.Room;

public class App extends Application {
    public static AppDataBase instance = null;
    public static AppDataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room.databaseBuilder(this,AppDataBase.class,"database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public static AppDataBase getInstance() {
        return instance;
    }

    public static AppDataBase getDataBase() {
        return dataBase;
    }
}

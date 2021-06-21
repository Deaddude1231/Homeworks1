package com.example.slsl1_2.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.slsl1_2.model.DaoModel;
import com.example.slsl1_2.model.TaskModel;

import java.util.List;

@Dao
public interface TaskDao {
   @Query("SELECT * FROM taskmodel")
   LiveData<List<TaskModel>>getAll();
    @Insert
    void insert(TaskModel taskModel);
    @Update
    void  update(TaskModel taskModel);
    @Delete
    void  delete(TaskModel taskModel);
}

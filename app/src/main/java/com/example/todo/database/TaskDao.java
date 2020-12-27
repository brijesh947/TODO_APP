package com.example.todo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insert(TaskDetail taskDetail);

    @Query("DELETE FROM TASK_DETAIL")
    void deleteAll();

    @Query("SELECT * FROM TASK_DETAIL WHERE TaskProgress = 0 ")
    LiveData<List<TaskDetail>> getDoTask();

    @Query("SELECT * FROM TASK_DETAIL WHERE TaskProgress = 1 ")
    LiveData<List<TaskDetail>> getDoneTask();

    @Delete
    void deleteRow(TaskDetail taskDetail);

    @Update
    void updateRow(TaskDetail taskDetail);
}

package com.example.todo.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_detail")
public class TaskDetail {
    @NonNull
    @ColumnInfo(name = "TaskName")
    private String task_name;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    @ColumnInfo(name = "AchieveDate")
    private String task_endDate;
    @NonNull
    @ColumnInfo(name = "TaskProgress")
    private int task_status;

    public TaskDetail(@NonNull String task_name, @NonNull String task_endDate, @NonNull int task_status) {
        this.task_name = task_name;
        this.task_endDate = task_endDate;
        this.task_status = task_status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getTask_name() {
        return task_name;
    }

    @NonNull
    public String getTask_endDate() {
        return task_endDate;
    }

    @NonNull
    public int getTask_status() {
        return task_status;
    }
}

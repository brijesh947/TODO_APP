package com.example.todo.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class TaskViewModel extends AndroidViewModel {
   private TaskDetailRepository taskDetailRepository;
   private LiveData<List<TaskDetail>> mDowork;
    private LiveData<List<TaskDetail>> mDonework;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskDetailRepository = new TaskDetailRepository(application);
        mDowork = taskDetailRepository.getmDowork();
        mDonework = taskDetailRepository.getmDoneWork();
    }

    public LiveData<List<TaskDetail>> getmDowork() {
        return mDowork;
    }

    public LiveData<List<TaskDetail>> getmDonework() {
        return mDonework;
    }

    public void insert(TaskDetail taskDetail){
        taskDetailRepository.insert(taskDetail);
    }
    public void delete(TaskDetail taskDetail){
        taskDetailRepository.delete(taskDetail);
    }
    public void update(TaskDetail taskDetail){
        taskDetailRepository.update(taskDetail);
    }
    public void deleteAll(){
        taskDetailRepository.deleteAll();
    }
}

package com.example.todo.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskDetailRepository {
    private TaskDao taskDao;
    private LiveData<List<TaskDetail>> mDowork;
    private LiveData<List<TaskDetail>> mDoneWork;

    public TaskDetailRepository(Application application) {
        TaskDataBase dataBase = TaskDataBase.getINSTANCE(application);
        taskDao = dataBase.taskDao();
        mDowork = taskDao.getDoTask();
        mDoneWork = taskDao.getDoneTask();
    }

    public LiveData<List<TaskDetail>> getmDowork() {
        return mDowork;
    }

    public LiveData<List<TaskDetail>> getmDoneWork() {
        return mDoneWork;
    }

    public void insert(TaskDetail taskDetail) {
        new insertAsyncTask(taskDao).execute(taskDetail);
    }

    private class insertAsyncTask extends AsyncTask<TaskDetail, Void, Void> {
        private TaskDao mAsyncTaskDao;

        public insertAsyncTask(TaskDao taskDao) {
            mAsyncTaskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskDetail... taskDetails) {
            mAsyncTaskDao.insert(taskDetails[0]);
            return null;
        }
    }

    public void delete(TaskDetail taskDetail) {
        new deleteRowAsyncTask(taskDao).execute(taskDetail);
    }

    private class deleteRowAsyncTask extends AsyncTask<TaskDetail, Void, Void> {
        private TaskDao mAsyncTaskDao;

        public deleteRowAsyncTask(TaskDao taskDao) {
            mAsyncTaskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskDetail... taskDetails) {
            mAsyncTaskDao.deleteRow(taskDetails[0]);
            return null;
        }
    }

    public void update(TaskDetail taskDetail) {
        new updateAsyncTask(taskDao).execute(taskDetail);
    }

    private class updateAsyncTask extends AsyncTask<TaskDetail, Void, Void> {
        private TaskDao mAsyncTaskDao;

        public updateAsyncTask(TaskDao taskDao) {
            mAsyncTaskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskDetail... taskDetails) {
            mAsyncTaskDao.updateRow(taskDetails[0]);
            return null;
        }
    }

    public void deleteAll() {
        new deleteAsyncTask(taskDao).execute();
    }

    private class deleteAsyncTask extends android.os.AsyncTask<Void, Void, Void> {
        private TaskDao mAsyncTaskDao;

        public deleteAsyncTask(TaskDao taskDao) {
            mAsyncTaskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}

package com.example.todo.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TaskDetail.class}, version = 1, exportSchema = false)
public abstract class TaskDataBase extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static TaskDataBase INSTANCE;

    static TaskDataBase getINSTANCE(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDataBase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TaskDataBase.class, "task_database")
                               .fallbackToDestructiveMigration()
                               .build();
            }
        }
        return INSTANCE;
    }
}
//    private static RoomDatabase.Callback sRoomCallBack = new RoomDatabase.Callback() {
//
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//            new SetDefultAsyncTask(INSTANCE).execute();
//        }
//    };
//
//    private static class SetDefultAsyncTask extends AsyncTask<Void, Void, Void> {
//        private final TaskDao taskDao;
//
//        public SetDefultAsyncTask(TaskDataBase instance) {
//            taskDao = instance.taskDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            taskDao.deleteAll();
//            TaskDetail taskDetail = new TaskDetail("Default Task", "Today", 0);
//            taskDao.insert(taskDetail);
//            return null;
//        }
//    }
//
//}

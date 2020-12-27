package com.example.todo.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.Adapter.DisplayDataAdapter;
import com.example.todo.R;
import com.example.todo.activity.AddTaskActivity;
import com.example.todo.database.TaskDetail;
import com.example.todo.database.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DoWork extends Fragment {
    private FloatingActionButton floatButton;
    private TaskViewModel taskViewModel;

    public DoWork() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dolayout, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.do_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        final DisplayDataAdapter displayDataAdapter = new DisplayDataAdapter();
        recyclerView.setAdapter(displayDataAdapter);
        floatButton = view.findViewById(R.id.fabs);
        taskViewModel = ViewModelProviders.of(getActivity()).get(TaskViewModel.class);
        taskViewModel.getmDowork().observe(getActivity(), new Observer<List<TaskDetail>>() {
            @Override
            public void onChanged(List<TaskDetail> taskDetails) {
                displayDataAdapter.setTaskDetails(taskDetails);

            }
        });

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            String task_name = data.getStringExtra(AddTaskActivity.EXTRA_TITLE);
            String task_date = data.getStringExtra(AddTaskActivity.EXTRA_DATE);
            TaskDetail taskDetail = new TaskDetail(task_name,task_date,0);
            taskViewModel.insert(taskDetail);
            Toast.makeText(getContext(),"Task Saved",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getContext(),"Task Not saved ",Toast.LENGTH_LONG).show();
        }
    }
}

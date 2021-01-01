package com.example.todo.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.database.TaskDetail;
import com.example.todo.database.TaskViewModel;


import java.util.ArrayList;
import java.util.List;

public class DisplayDataAdapter extends RecyclerView.Adapter<DisplayDataAdapter.TaskDetailHolder> {
    private List<TaskDetail> taskDetails = new ArrayList<>();
    Activity activity;

    public DisplayDataAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public TaskDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_cardview, parent, false);

        return new TaskDetailHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskDetailHolder holder, int position) {
        TaskDetail currentTask = taskDetails.get(position);
        holder.task_name.setText(currentTask.getTask_name());
        holder.task_endDate.setText(currentTask.getTask_endDate());
        holder.task_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.linearLayout.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskDetails.size();
    }

    public void setTaskDetails(List<TaskDetail> taskDetails) {
        this.taskDetails = taskDetails;
        notifyDataSetChanged();
    }

    public TaskDetail getTaskDetailAt(int position) {
        return taskDetails.get(position);
    }

    class TaskDetailHolder extends RecyclerView.ViewHolder {
        private TaskViewModel taskViewModel;
        private TextView task_name;
        private TextView task_endDate;
        private LinearLayout linearLayout;
        Button deleteButton, cancelButton, doneButton, editButton;

        public TaskDetailHolder(@NonNull View itemView) {
            super(itemView);
            task_name = itemView.findViewById(R.id.task_name);
            task_endDate = itemView.findViewById(R.id.task_date);
            linearLayout = itemView.findViewById(R.id.show_action_onclick);
            deleteButton = itemView.findViewById(R.id.delete_button);
            cancelButton = itemView.findViewById(R.id.cancel_button);
            doneButton = itemView.findViewById(R.id.done_button);
            editButton = itemView.findViewById(R.id.edit_button);
            taskViewModel = ViewModelProviders.of((FragmentActivity) activity).get(TaskViewModel.class);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearLayout.setVisibility(View.VISIBLE);
                }
            });
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearLayout.setVisibility(View.GONE);
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    taskViewModel.deleteAll();
                    Toast.makeText(linearLayout.getContext(), "All Task Deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}

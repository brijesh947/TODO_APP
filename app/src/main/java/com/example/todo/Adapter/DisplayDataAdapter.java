package com.example.todo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.database.TaskDetail;


import java.util.ArrayList;
import java.util.List;

public class DisplayDataAdapter extends RecyclerView.Adapter<DisplayDataAdapter.TaskDetailHolder> {
    private List<TaskDetail> taskDetails = new ArrayList<>();

    @NonNull
    @Override
    public TaskDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.display_cardview,parent,false);

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

    class TaskDetailHolder extends RecyclerView.ViewHolder {
        private TextView task_name;
        private TextView task_endDate;
        private LinearLayout linearLayout;

        public TaskDetailHolder(@NonNull View itemView) {
            super(itemView);
            task_name = itemView.findViewById(R.id.task_name);
            task_endDate = itemView.findViewById(R.id.task_date);
            linearLayout = itemView.findViewById(R.id.show_action_onclick);
        }
    }
}

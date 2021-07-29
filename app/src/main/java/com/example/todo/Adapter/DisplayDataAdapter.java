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

import com.example.todo.Fragments.DoWork;
import com.example.todo.Fragments.DoneWork;
import com.example.todo.R;
import com.example.todo.activity.EditActivity;
import com.example.todo.database.TaskDetail;
import com.example.todo.database.TaskViewModel;


import java.util.ArrayList;
import java.util.List;

public class DisplayDataAdapter extends RecyclerView.Adapter<DisplayDataAdapter.TaskDetailHolder> {
    private List<TaskDetail> taskDetails = new ArrayList<>();
    private List<TaskDetail> copyTask = new ArrayList<>();

    Activity activity;
    DoWork doWork;
    DoneWork doneWork;

    public DisplayDataAdapter(Activity activity, DoWork doWork) {
        this.activity = activity;
        this.doWork = doWork;
    }

    public DisplayDataAdapter(Activity activity, DoneWork doneWork) {
        this.activity = activity;
        this.doneWork = doneWork;
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
        this.taskDetails= taskDetails;
        this.copyTask = taskDetails;
        notifyDataSetChanged();
    }
    public void updateTaskDetail(List<TaskDetail>taskDetails){
        this.taskDetails= taskDetails;
        notifyDataSetChanged();
    }
    public void filter(String str){
        //Log.d("TAG", "filter: string is " + str);
        List<TaskDetail> temp = new ArrayList<>();
         temp.clear();
         if(str.isEmpty()){
             //Log.d("TAG", "filter:  string is empty"  );
             temp.addAll(copyTask);
             updateTaskDetail(temp);
             return;
         }
         for(TaskDetail detail:copyTask){
              Log.d("TAG", "filter: string is " + detail.getTask_name());

             if(detail.getTask_name().toLowerCase().contains(str.toLowerCase().trim())){

                 temp.add(detail);
             }
         }
         updateTaskDetail(temp);
    }
    public TaskDetail getTaskDetailAt(int position) {
        return taskDetails.get(position);
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

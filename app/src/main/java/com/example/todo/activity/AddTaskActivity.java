package com.example.todo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.todo.R;

public class AddTaskActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.example.todo.activity.EXTRA_TITLE";
    public static final String EXTRA_DATE = "com.example.todo.activity.EXTRA_DATE";
    EditText taskName, taskDetail;
    TextView endDate;
    CalendarView calendarView;
    private LinearLayout calendarLayout;
    Button add_button;
    ImageView calender_image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Intent intent = getIntent();
        taskName = findViewById(R.id.taskNameDetail);
        endDate = findViewById(R.id.endDate);
        taskDetail = findViewById(R.id.TaskDescription);
        add_button = findViewById(R.id.AddTask);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTaskDetail();
            }
        });
        calender_image2 = findViewById(R.id.image_calender2);
        calendarLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.calender_layout,null,false);
        // calendarView.getParent().removeView(calendarView);
        calendarView = (CalendarView) calendarLayout.getChildAt(0);

        calender_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(calendarView.getParent()!=null){
                    ((ViewGroup)calendarView.getParent()).removeView(calendarView);
                }
                dealWithDate1();
                new AlertDialog.Builder(AddTaskActivity.this)
                        .setTitle("Select the Date")
                        .setView(calendarView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dealWithDate1();
                                dialog.dismiss();

                            }
                        })
                        .setCancelable(true)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();


            }
        });
    }

    private void saveTaskDetail() {
        String task_name = taskName.getText().toString();
        String task_endDate = endDate.getText().toString();

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, task_name);
        data.putExtra(EXTRA_DATE, task_endDate);
        setResult(RESULT_OK, data);
        finish();
    }

    private void dealWithDate1() {

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            String Date;

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date = dayOfMonth + "/" + (month + 1) ;
                endDate.setText(Date);


            }

        });

    }


}
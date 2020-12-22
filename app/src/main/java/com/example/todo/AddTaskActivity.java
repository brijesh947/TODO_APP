package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;

public class AddTaskActivity extends AppCompatActivity {
    EditText taskName, startDate, endDate, taskDetail;
    CalendarView calendarView;
    Button add_button;
    ImageView calender_image1, calender_image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Intent intent = getIntent();
        taskName = findViewById(R.id.taskNameDetail);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        taskDetail = findViewById(R.id.TaskDescription);
        calendarView = findViewById(R.id.Calender_view);
        add_button = findViewById(R.id.AddTask);
        calender_image1 = findViewById(R.id.image_calender1);
        calender_image2 = findViewById(R.id.image_calender2);
        calendarView.setVisibility(View.GONE);
        calender_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.VISIBLE);
                dealWithDate();

            }
        });
        calender_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.VISIBLE);
                dealWithDate1();


            }
        });
    }

    private void dealWithDate1() {

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            String Date;

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date = dayOfMonth + "-" + (month + 1) + "-" + year;
                endDate.setText(Date);
                calendarView.setVisibility(View.GONE);

            }

        });

    }

    private void dealWithDate() {

        calendarView.setVisibility(View.VISIBLE);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            String Date;

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date = dayOfMonth + "-" + (month + 1) + "-" + year;
                startDate.setText(Date);
                calendarView.setVisibility(View.GONE);
            }

        });

    }
}
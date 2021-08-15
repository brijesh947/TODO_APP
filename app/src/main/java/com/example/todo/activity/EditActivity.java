package com.example.todo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo.Fragments.DoWork;
import com.example.todo.R;

public class EditActivity extends AppCompatActivity {
    private EditText taskDetail;
    private TextView taskDate;
    private Button addButton;
    private ImageView calenderImage;
    private LinearLayout calendarLayout;
    private CalendarView calendarView;
    public static final String EXTRA_TITLE = "com.example.todo.activity.EXTRA_TITLE";
    public static final String EXTRA_DATE = "com.example.todo.activity.EXTAR_DATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        taskDetail = findViewById(R.id.EditTaskNameDetail);
        taskDate = findViewById(R.id.EditendDate);
        addButton = findViewById(R.id.EditAddTask);
        calendarLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.calender_layout,null,false);
       // calendarView.getParent().removeView(calendarView);
        calendarView = (CalendarView) calendarLayout.getChildAt(0);
        if(intent.getStringExtra(DoWork.EXTRA_TITLE)!=null && intent.getStringExtra(DoWork.EXTRA_DATE) !=null){
            taskDetail.setText(intent.getStringExtra(DoWork.EXTRA_TITLE));
            taskDate.setText(intent.getStringExtra(DoWork.EXTRA_DATE));
        }else{
            taskDetail.setText(intent.getStringExtra("EXTRA_TITLE"));
            taskDate.setText(intent.getStringExtra("EXTRA_DATE"));
        }
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTaskDetail();
            }
        });

        calenderImage = findViewById(R.id.EditImage_calender2);
        calenderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(calendarView.getParent()!=null){
                    ((ViewGroup)calendarView.getParent()).removeView(calendarView);
                }
                dealWithDate1();
                new AlertDialog.Builder(EditActivity.this)
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
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, taskDetail.getText().toString());
        intent.putExtra(EXTRA_DATE, taskDate.getText().toString());
        setResult(RESULT_OK, intent);
        finish();

    }

    private void dealWithDate1() {

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            String Date;

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date = dayOfMonth + "-" + (month + 1) + "-" + year;
                taskDate.setText(Date);
            }

        });

    }
}
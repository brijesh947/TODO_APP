package com.example.todo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todo.R
import android.content.Intent
import com.example.todo.Fragments.DoWork
import android.view.ViewGroup
import android.content.DialogInterface
import com.example.todo.activity.EditActivity
import android.app.Activity
import android.view.View
import android.widget.*
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AlertDialog

class EditActivity : AppCompatActivity() {
    private lateinit var taskDetail: EditText
    private lateinit var taskDate: TextView
    private lateinit var addButton: Button
    private lateinit var calenderImage: ImageView
    private var calendarLayout: LinearLayout? = null
    private var calendarView: CalendarView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val intent = intent
        taskDetail = findViewById(R.id.EditTaskNameDetail)
        taskDate = findViewById(R.id.EditendDate)
        addButton = findViewById(R.id.EditAddTask)
        calendarLayout = layoutInflater.inflate(R.layout.calender_layout, null, false) as LinearLayout
        // calendarView.getParent().removeView(calendarView);
        calendarView = calendarLayout!!.getChildAt(0) as CalendarView
        if (intent.getStringExtra(DoWork.EXTRA_TITLE) != null && intent.getStringExtra(DoWork.EXTRA_DATE) != null) {
            taskDetail.setText(intent.getStringExtra(DoWork.EXTRA_TITLE))


            taskDate.setText(intent.getStringExtra(DoWork.EXTRA_DATE))
        } else {
            taskDetail.setText(intent.getStringExtra("EXTRA_TITLE"))
            taskDate.setText(intent.getStringExtra("EXTRA_DATE"))
        }
        addButton.setOnClickListener(View.OnClickListener { saveTaskDetail() })
        calenderImage = findViewById(R.id.EditImage_calender2)
        calenderImage.setOnClickListener(View.OnClickListener {
            if (calendarView!!.parent != null) {
                (calendarView!!.parent as ViewGroup).removeView(calendarView)
            }
            dealWithDate1()
            AlertDialog.Builder(this@EditActivity)
                    .setTitle("Select the Date")
                    .setView(calendarView)
                    .setPositiveButton("OK") { dialog, which ->
                        dealWithDate1()
                        dialog.dismiss()
                    }
                    .setCancelable(true)
                    .setNegativeButton("Cancel") { dialog, which -> dialog.dismiss() }.show()
        })
    }

    private fun saveTaskDetail() {
        val intent = Intent()
        intent.putExtra(EXTRA_TITLE, taskDetail!!.text.toString())
        intent.putExtra(EXTRA_DATE, taskDate!!.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun dealWithDate1() {
        calendarView!!.setOnDateChangeListener(object : OnDateChangeListener {
            var Date: String? = null
            override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
                Date = dayOfMonth.toString() + "/" + (month + 1)
                taskDate!!.text = Date
            }
        })
    }

    companion object {
        const val EXTRA_TITLE = "com.example.todo.activity.EXTRA_TITLE"
        const val EXTRA_DATE = "com.example.todo.activity.EXTAR_DATE"
    }
}
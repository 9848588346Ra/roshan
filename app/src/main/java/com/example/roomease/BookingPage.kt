package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.TintableCompoundButton
import java.util.Calendar

class DateActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_date)
        button = findViewById(R.id.calendar)
        textView = findViewById(R.id.textView12)

        button.setOnClickListener {
            loadCalendar()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadCalendar() {
        var c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)

        var dateDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener
            { datePicker, year, month, dayOfMonth ->
                textView.text ="$year/$month/$dayOfMonth" },

            year,month,day)
        dateDialog.show()

    }
}
package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectAgeBtn : Button = findViewById(R.id.selectDateBtn)

        selectAgeBtn.setOnClickListener {
            clickDatePicker()

        }
    }

    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = String.format("%02d", myCalendar.get(Calendar.YEAR))
        val month = String.format("%02d", myCalendar.get(Calendar.MONTH))
        val day = String.format("%02d", myCalendar.get(Calendar.DAY_OF_MONTH))


        val dpd = DatePickerDialog(this, {
                _, selectedYear, selectedMonth, selectedDayOfMonth ->

//            Toast.makeText(this,"The chosen year is $year, month is $month and day is $selectedDayOfMonth", Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            Toast.makeText(this,"The chosen date is $selectedDate", Toast.LENGTH_LONG).show()


            val tvSelectedDate : TextView = findViewById(R.id.displayDate)

            val tvAge : TextView = findViewById(R.id.displayAge)

            var today = Date()

            var dob: String = tvAge.text.toString()

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(dob)

            var days: Long = (today.time - theDate.time)/86400000

//            var hours: Long = (today.time - theDate.time) % 86400000 / 3600000
//
//            var minutes: Long = (today.time - theDate.time) % 86400000 % 3600000 / 60000
//
//            var seconds: Long = (today.time - theDate.time) %  86400000 % 3600000 / 60000 / 1000

            //val selectedDateInMinutes = theDate!!.time /604800000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            //val currentDateInMinutes = currentDate!!.time /604800000

            //val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            tvSelectedDate.text = selectedDate

            tvAge.text = days.toString()



        }
        ,year.toInt() ,month.toInt() ,day.toInt())

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}
package com.example.aviaseils_like

import com.example.aviaseils_like.R
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var chooseFrom: Spinner
    private lateinit var chooseTo: Spinner
    private lateinit var editDateFrom: TextInputEditText
    private lateinit var editDateTo: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chooseFrom = findViewById<Spinner>(R.id.choose_from)
        val chooseTo = findViewById<Spinner>(R.id.choose_to)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.cities_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        chooseFrom.adapter = adapter
        chooseTo.adapter = adapter

        val editDateFrom = findViewById<TextInputEditText>(R.id.edit_date_from)
        val editDateTo = findViewById<TextInputEditText>(R.id.edit_date_to)

        editDateFrom.setOnClickListener {
            showDatePickerDialog(editDateFrom)
        }

        editDateTo.setOnClickListener {
            showDatePickerDialog(editDateTo)
        }
    }

    private fun showDatePickerDialog(editText: TextInputEditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                editText.setText(selectedDate)
            },
            year, month, day
        )

        datePickerDialog.show()
    }
}

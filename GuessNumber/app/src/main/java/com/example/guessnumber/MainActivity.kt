package com.example.guessnumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onGuessClick(view: View) {
        val beginEditText = findViewById<EditText>(R.id.begin)
        val endEditText = findViewById<EditText>(R.id.end)

        if (beginEditText.text.isNotEmpty() && endEditText.text.isNotEmpty()) {
            val begin = beginEditText.text.toString().toInt()
            val end = endEditText.text.toString().toInt()

            if (begin < end) {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("begin", begin)
                intent.putExtra("end", end)
                startActivity(intent)
            } else {
                beginEditText.error = "Begin should be less than end"
            }
        } else {
            beginEditText.error = "input MIN"
            endEditText.error = "input MAX"
        }
    }
}

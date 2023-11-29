package com.example.guessnumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.TextView
import android.widget.Toast

class GameActivity : AppCompatActivity() {
    private var begin: Int = 0
    private var end: Int = 100
    var guessNumber = 0
    var guess = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        begin = intent.getIntExtra("begin", 0)
        end = intent.getIntExtra("end", 100)
        Log.d("mytag", "begin = $begin")
        Log.d("mytag", "end = $end")
        question()
    }

    private fun question() {
        val tvQuestion = findViewById<TextView>(R.id.question)
        if (end != begin) {
            guessNumber = (begin + end) / 2
            tvQuestion.text = "Ваше число больше $guessNumber?"
        } else {
            tvQuestion.text = "Ваше число: $begin"
            guess = true
        }
    }

    fun onYesNoClick(view: View) {
        when (view.id) {
            R.id.yes -> {
                begin = guessNumber + 1
            }

            R.id.no -> {
                end = guessNumber
            }
        }

        if (!guess) {
            question()
        }

    }

}
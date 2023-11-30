package com.example.randomfilm

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var filmTextView: TextView
    private lateinit var resetButton: Button
    private lateinit var findButton: Button

    private val moviesArray by lazy {
        resources.getStringArray(R.array.movies).toList()
    }

    private val watchedMovies: MutableSet<String> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filmTextView = findViewById(R.id.film)
        resetButton = findViewById(R.id.reset)
        findButton = findViewById(R.id.find)

        findButton.setOnClickListener { show() }
        resetButton.setOnClickListener { reset() }
    }

    private fun show() {
        if (watchedMovies.size == moviesArray.size) {
            filmTextView.text = getString(R.string.no_films)
            return
        }

        var randomMovie: String
        do {
            randomMovie = moviesArray.random()
        } while (watchedMovies.contains(randomMovie))

        watchedMovies.add(randomMovie)
        filmTextView.text = randomMovie
    }

    private fun reset() {
        watchedMovies.clear()
        filmTextView.text = ""
    }
}

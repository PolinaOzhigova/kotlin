package com.example.randomfilm

import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var movies: List<Movie>
    private lateinit var displayTextView: TextView
    private lateinit var showMovieButton: Button
    private lateinit var resetButton: Button
    private var watchedMovies: MutableSet<Movie> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayTextView = findViewById(R.id.film)
        showMovieButton = findViewById(R.id.find)
        resetButton = findViewById(R.id.reset)

        loadMovies()

        showMovieButton.setOnClickListener { show() }
        resetButton.setOnClickListener { reset() }
    }

    private fun loadMovies() {
        resources.openRawResource(R.raw.movies).use { moviesStream ->
            try {
                val gson = Gson()
                val type: Type = object : TypeToken<Movies>() {}.type
                val moviesData: Movies = gson.fromJson(InputStreamReader(moviesStream), type)
                movies = moviesData.movies.toList()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun show() {
        if (movies.isEmpty()) {
            displayTextView.text = resources.getString(R.string.no_films)
        } else {
            val randomMovie = movies.random()
            movies -= randomMovie
            watchedMovies.add(randomMovie)
            displayTextView.text = formatMovieInfo(randomMovie)
        }
    }

    private fun formatMovieInfo(movie: Movie): CharSequence = with(movie) {
        """
            <b>Название:</b> $name<br>
            <b>Год:</b> $year<br>
            <b>Рейтинг:</b> $rating<br>
            <b>Жанры:</b> ${genre.joinToString(", ")}<br>
            <b>Время:</b> $time
        """.trimIndent().let { Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY) }
    }

    private fun reset() {
        movies += watchedMovies
        watchedMovies.clear()
        show()
    }
}

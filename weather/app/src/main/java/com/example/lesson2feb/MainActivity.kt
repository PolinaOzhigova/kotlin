package com.example.lesson2feb

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_lang, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val config: Configuration = this.resources.configuration
        return when (item.itemId) {
            R.id.menu_english -> {
                setLocale("en")
                true
            }
            R.id.menu_russian -> {
                setLocale("ru")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setLocale(localeCode: String) {
        val locale = Locale(localeCode)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.setLocale(locale)
        baseContext.resources.updateConfiguration(
            configuration,
            baseContext.resources.displayMetrics
        )
        recreate()
    }

}
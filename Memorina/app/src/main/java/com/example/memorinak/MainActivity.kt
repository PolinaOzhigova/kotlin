package com.example.memorinak

import android.app.ActionBar
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Random
import kotlinx.coroutines.*
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val numberOfPairs = 8
    private lateinit var cardViews: ArrayList<ImageView>
    private var randomArray: Array<String> = generateRandomArray()
    private var firstCard: ImageView? = null
    private var secondCard: ImageView? = null
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(applicationContext)
        layout.orientation = LinearLayout.VERTICAL

//        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
//        params.weight = 1.toFloat()
        val params = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.weight = 1.toFloat()
        params.setMargins(4, 0, 4, 0)

        cardViews = ArrayList()
        for (i in 0 until numberOfPairs * 2) {
            val imageView = ImageView(applicationContext).apply {
                setImageResource(R.drawable.cows)
                layoutParams = params

                tag = randomArray[i]
                setOnClickListener { openCard(this) }
            }
            cardViews.add(imageView)
        }

        val rows = Array(4) { LinearLayout(applicationContext) }

        var count = 0
        for (view in cardViews) {
            val row: Int = count / 4
            rows[row].addView(view)
            count++
        }
        for (row in rows) {
            layout.addView(row)
        }

        val resetButton = Button(applicationContext).apply {
            text = "RESET"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setOnClickListener { resetGame() }
        }
        layout.addView(resetButton)

        setContentView(layout)
    }

    private fun resetGame() {
        for (card in cardViews) {
            card.visibility = View.VISIBLE
            card.isClickable = true
            card.setImageResource(R.drawable.cows)
        }

        firstCard = null
        secondCard = null
        count = 0

        randomArray = generateRandomArray()
        for (i in 0 until numberOfPairs * 2) {
            cardViews[i].tag = randomArray[i]
        }
    }

    suspend fun guessedPair(firstCard: ImageView, secondCard: ImageView) {
        delay(300)
        count += 1
        withContext(Dispatchers.Main) {
            firstCard.visibility = View.INVISIBLE
            firstCard.isClickable = false
            secondCard.visibility = View.INVISIBLE
            secondCard.isClickable = false
        }
        if(count == 8){
            delay(100)
            showToast("WIN!!!")
        }
    }

    private suspend fun showToast(message: String) {
        withContext(Dispatchers.Main) {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun showPair(firstCard: ImageView, secondCard: ImageView) {
        val tag1 = resources.getIdentifier("img${firstCard.tag}", "drawable", packageName)
        val tag2 = resources.getIdentifier("img${secondCard.tag}", "drawable", packageName)
        if(tag1 == tag2){
            guessedPair(firstCard, secondCard)
        }
        else {
            delay(1000)
            withContext(Dispatchers.Main) {
                firstCard.setImageResource(R.drawable.cows)
                secondCard.setImageResource(R.drawable.cows)
                firstCard.isClickable = true
                secondCard.isClickable = true
            }
        }
    }

    fun generateRandomArray(): Array<String> {
        val values = listOf("0", "1", "2", "3", "4", "5", "6", "7")
        val allValues = values + values
        val shuffledValues = allValues.shuffled()

        return shuffledValues.toTypedArray()
    }

    fun openCard(card: ImageView) {
        GlobalScope.launch {
            delay(300)
            if (firstCard == null || secondCard == null) {
                val resourceId = resources.getIdentifier("img${card.tag}", "drawable", packageName)
                card.setImageResource(resourceId)

                if (firstCard == null) {
                    firstCard = card
                    card.isClickable = false
                } else {
                    secondCard = card
                    card.isClickable = false
                    showPair(firstCard!!, secondCard!!)
                    firstCard = null
                    secondCard = null
                    }
            }
        }
    }

}
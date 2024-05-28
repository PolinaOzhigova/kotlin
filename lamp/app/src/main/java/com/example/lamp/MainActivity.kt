package com.example.lamp

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private lateinit var scoreTextView: TextView
    private val initialGridSize = 4
    private var gridSize = initialGridSize
    private lateinit var buttons: Array<Array<Button>>
    private var moves = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridLayout = findViewById(R.id.gridLayout)
        scoreTextView = findViewById(R.id.scoreTextView)
        initGame()
    }

    private fun initGame() {
        moves = 0
        scoreTextView.text = "Moves: $moves"

        gridLayout.removeAllViews()
        gridLayout.columnCount = gridSize
        gridLayout.rowCount = gridSize

        buttons = Array(gridSize) { row ->
            Array(gridSize) { col ->
                Button(this).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = 0
                        height = 0
                        rowSpec = GridLayout.spec(row, 1f)
                        columnSpec = GridLayout.spec(col, 1f)
                        setMargins(8, 8, 8, 8)
                    }
                    post {
                        val size = minOf(width, height)
                        layoutParams.width = size
                        layoutParams.height = size
                        requestLayout()
                    }
                    background = createCircleDrawable(if (Random.nextBoolean()) Color.RED else Color.YELLOW)
                    setOnClickListener { onButtonClick(row, col) }
                    gridLayout.addView(this)
                }
            }
        }
    }

    private fun createCircleDrawable(color: Int): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(color)
            setStroke(3, Color.GRAY)
        }
    }

    private fun onButtonClick(row: Int, col: Int) {
        moves++
        scoreTextView.text = "Moves: $moves"
        toggleButton(row, col)
        for (i in 0 until gridSize) {
            if (i != row) toggleButton(i, col)
            if (i != col) toggleButton(row, i)
        }
        checkGameEnd()
    }

    private fun toggleButton(row: Int, col: Int) {
        buttons[row][col].apply {
            val currentColor = (background as GradientDrawable).color?.defaultColor ?: Color.YELLOW
            val newColor = if (currentColor == Color.YELLOW) Color.RED else Color.YELLOW
            background = createCircleDrawable(newColor)
        }
    }

    private fun checkGameEnd() {
        val firstTag = (buttons[0][0].background as GradientDrawable).color?.defaultColor ?: return
        var allSame = true
        for (row in buttons) {
            for (button in row) {
                val buttonColor = (button.background as GradientDrawable).color?.defaultColor ?: return
                if (buttonColor != firstTag) {
                    allSame = false
                    break
                }
            }
        }
        if (allSame) {
            gridSize++
            initGame()
        }
    }
}

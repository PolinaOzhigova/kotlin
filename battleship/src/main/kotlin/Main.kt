import kotlin.random.Random

const val SIZE = 10
val grid = Array(SIZE) { IntArray(SIZE) { 0 } }

fun main() {
    placeShips()
    printGrid()
}

fun placeShips() {
    placeShip(4, 1)
    placeShip(3, 2)
    placeShip(2, 3)
    placeShip(1, 4)
}

fun placeShip(size: Int, count: Int) {
    repeat(count) {
        var placed = false
        while (!placed) {
            val orientation = Random.nextBoolean() // true for horizontal, false for vertical
            val row = Random.nextInt(SIZE)
            val col = Random.nextInt(SIZE)

            if (canPlaceShip(row, col, size, orientation)) {
                placeShipAt(row, col, size, orientation)
                placed = true
            }
        }
    }
}

fun canPlaceShip(row: Int, col: Int, size: Int, horizontal: Boolean): Boolean {
    for (i in 0 until size) {
        val r = if (horizontal) row else row + i
        val c = if (horizontal) col + i else col
        if (r >= SIZE || c >= SIZE || !isCellFree(r, c)) {
            return false
        }
    }
    return true
}

fun isCellFree(row: Int, col: Int): Boolean {
    if (grid[row][col] == 1) return false

    // Check surrounding cells
    for (i in -1..1) {
        for (j in -1..1) {
            val r = row + i
            val c = col + j
            if (r in 0 until SIZE && c in 0 until SIZE && grid[r][c] == 1) {
                return false
            }
        }
    }
    return true
}

fun placeShipAt(row: Int, col: Int, size: Int, horizontal: Boolean) {
    for (i in 0 until size) {
        val r = if (horizontal) row else row + i
        val c = if (horizontal) col + i else col
        grid[r][c] = 1
    }
}

fun printGrid() {
    for (row in grid) {
        println(row.joinToString(" ") { if (it == 1) "1" else "0" })
    }
}

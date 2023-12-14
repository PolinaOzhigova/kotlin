import java.io.FileInputStream
import java.util.*

fun main() {
    val scRoles = Scanner(FileInputStream("./src/main/resources/roles.txt"))
    val scText = Scanner(FileInputStream("./src/main/resources/textLines.txt"))

    val roles = ArrayList<String>()
    while (scRoles.hasNextLine()) {
        roles.add(scRoles.nextLine())
    }

    val roleLines = mutableMapOf<String, MutableList<String>>()

    while (scText.hasNextLine()) {
        val line = scText.nextLine()
        val role = line.substringBefore(":")
        val text = line.substringAfter(":").trim()

        roleLines.computeIfAbsent(role) { mutableListOf() }.add(text)
    }

    for (role in roles) {
        val roleName = role.substringBefore(":")
        val roleTexts = roleLines[roleName] ?: emptyList<String>()

        println("$roleName:")
        for ((index, text) in roleTexts.withIndex()) {
            println("${index + 1}) $text")
        }
        println()
    }
}

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.GsonBuilder
import com.opencsv.CSVReaderBuilder
import java.io.FileReader
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

data class AppInfo(
    val name: String,
    val category: String,
    val rating: String,
    val reviews: String,
    val size: String,
    val installs: String,
    val type: String,
    val price: String,
    val contentRating: String,
    val genres: List<String>,
    val lastUpdated: String,
    val currentVer: String,
    val androidVer: Int
)

val translation = mapOf(
    "ART_AND_DESIGN" to "ИСКУССТВО И ДИЗАЙН",
    "AUTO_AND_VEHICLES" to "АВТОМОБИЛИ И ТРАНСПОРТ",
    "BEAUTY" to "КРАСОТА",
    "BOOKS_AND_REFERENCE" to "КНИГИ",
    "BUSINESS" to "БИЗНЕС",
    "COMICS" to "КОМИКСЫ",
    "COMMUNICATION" to "ОБЩЕНИЕ",
    "DATING" to "ЗНАКОМСТВА",
    "EDUCATION" to "ОБРАЗОВАНИЕ",
    "ENTERTAINMENT" to "РАЗВЛЕЧЕНИЯ",
    "EVENTS" to "СОБЫТИЯ",
    "FINANCE" to "ФИНАНСЫ",
    "FOOD_AND_DRINK" to "ЕДА И НАПИТКИ",
    "HEALTH_AND_FITNESS" to "ЗДОРОВЬЕ И ФИТНЕС",
    "HOUSE_AND_HOME" to "ДОМ",
    "LIBRARIES_AND_DEMO" to "БИБЛИОТЕКИ",
    "LIFESTYLE" to "ОБРАЗ ЖИЗНИ",
    "GAME" to "ИГРЫ",
    "FAMILY" to "СЕМЬЯ",
    "MEDICAL" to "МЕДИЦИНА",
    "SOCIAL" to "СОЦИАЛЬНЫЕ",
    "SHOPPING" to "ШОППИНГ",
    "PHOTOGRAPHY" to "ФОТОГРАФИЯ",
    "SPORTS" to "СПОРТ",
    "TRAVEL_AND_LOCAL" to "ПУТЕШЕСТВИЯ",
    "TOOLS" to "ИНСТРУМЕНТЫ",
    "PERSONALIZATION" to "ПЕРСОНАЛИЗАЦИЯ",
    "PRODUCTIVITY" to "ПРОДУКТИВНОСТЬ",
    "PARENTING" to "ВОСПИТАНИЕ ДЕТЕЙ",
    "WEATHER" to "ПОГОДА",
    "VIDEO_PLAYERS" to "ВИДЕОПЛЕЕРЫ",
    "NEWS_AND_MAGAZINES" to "НОВОСТИ И ЖУРНАЛЫ",
    "MAPS_AND_NAVIGATION" to "КАРТЫ И НАВИГАЦИЯ"
)

fun parseCsvLine(line: Array<String>): AppInfo {
    val translatedCategory = translation[line[1]] ?: line[1]

    return AppInfo(
        line[0],
        translatedCategory,
        line[2],
        line[3],
        line[4],
        line[5],
        line[6],
        line[7],
        line[8],
        line[9].split(";"),
        formatDate(line[10]),
        line[11],
        mapAndroidVersion(line[12])
    )
}

fun formatDate(date: String): String {
    val inputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.US)
    val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    val parsedDate = inputFormat.parse(date)
    return outputFormat.format(parsedDate)
}

fun androidVersionToApi(version: Double): Int {
    return when (version) {
        in 1.0..1.5 -> 1
        in 1.5..1.6 -> 3
        in 1.6..2.0 -> 4
        in 2.0..2.1 -> 5
        in 2.1..2.2 -> 7
        in 2.2..2.3 -> 8
        in 2.3..3.0 -> 9
        in 3.0..3.1 -> 11
        in 3.1..3.2 -> 12
        in 3.2..4.0 -> 13
        in 4.0..4.1 -> 14
        in 4.1..4.2 -> 16
        in 4.2..4.3 -> 17
        in 4.3..4.4 -> 18
        in 4.4..5.0 -> 19
        in 5.0..5.1 -> 21
        in 5.1..6.0 -> 22
        in 6.0..7.0 -> 23
        in 7.0..7.1 -> 24
        in 7.1..8.0 -> 25
        in 8.0..8.1 -> 26
        in 8.1..9.0 -> 27
        in 9.0..10.0 -> 28
        in 10.0..11.0 -> 29
        in 11.0..12.0 -> 30
        else -> -1
    }
}

fun mapAndroidVersion(androidVer: String): Int {
    val versionRegex = "(\\d+(\\.\\d+)?)".toRegex()
    val matchResult = versionRegex.find(androidVer)
    val version = matchResult?.groupValues?.get(1)?.toDoubleOrNull() ?: 0.0
    return androidVersionToApi(version)
}

fun main() {
    val csvFile = "./src/main/resources/googleplaystore.csv"
    val apps = mutableListOf<AppInfo>()

    CSVReaderBuilder(FileReader(csvFile)).withSkipLines(1).build().use { csvReader ->
        csvReader.forEach { line ->
            try {
                apps.add(parseCsvLine(line))
            } catch (e: Exception) {
                println(" ")
            }
        }
    }

    val jsonResult = JsonArray()
    apps.groupBy { it.category }.forEach { (category, appsInCategory) ->
        val jsonCategory = JsonObject().apply {
            addProperty("category", category)
            add("apps", GsonBuilder().setPrettyPrinting().create().toJsonTree(appsInCategory))
        }
        jsonResult.add(jsonCategory)
    }

    val outputFile = "./src/main/resources/result.json"
    FileWriter(outputFile).use { fileWriter ->
        GsonBuilder().setPrettyPrinting().create().toJson(jsonResult, fileWriter)
    }
}
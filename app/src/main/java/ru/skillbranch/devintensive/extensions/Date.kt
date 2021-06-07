package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUtils {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.format(pattern: String = "HH:mm:ss dd.mm.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUtils = TimeUtils.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUtils.SECOND -> value * SECOND
        TimeUtils.MINUTE -> value * MINUTE
        TimeUtils.HOUR -> value * HOUR
        TimeUtils.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    if (date.format("DD").toInt() - this.format("DD").toInt() == 1) {
        return "Вчера"
    }
    if (date.format("DD").toInt() - this.format("DD").toInt() == 2) {
        return "Позавчера"
    }
    val secondsDuration: Long = (date.getTime() - this.getTime()) / 1000
    var decName: String = ""
    return when (this) {
        in Date().add(-5, TimeUtils.SECOND)..Date() -> "Только что"
        in Date().add(-59, TimeUtils.SECOND)..Date().add(-5,TimeUtils.SECOND) -> {
            decName = "секунд"
            if (secondsDuration % 10L == 1L && secondsDuration != 11L) decName = "секунду"
            if (secondsDuration % 10L == 4L && secondsDuration != 14L) decName = "секунды"
            "$secondsDuration $decName назад"
        }
        in Date().add(-59, TimeUtils.MINUTE)..Date().add(-1,TimeUtils.MINUTE) -> {
            val minuteDuration = secondsDuration / 60
            decName = "минут"
            if (minuteDuration % 10L == 1L && minuteDuration != 11L) decName = "минуту"
            if (minuteDuration % 10L == 2L && minuteDuration != 12L ||
                minuteDuration % 10L == 3L && minuteDuration != 13L ||
                minuteDuration % 10L == 4L && minuteDuration != 14L) decName = "минуты"
            "$minuteDuration $decName назад"
        }
        in Date().add(-23, TimeUtils.HOUR)..Date().add(-1,TimeUtils.HOUR) -> {
            val hourDuration = secondsDuration / 60 / 60
            decName = "часов"
            if (hourDuration % 10L == 1L && hourDuration != 11L) decName = "час"
            if (hourDuration % 10L == 2L && hourDuration != 12L ||
                hourDuration % 10L == 3L && hourDuration != 13L ||
                hourDuration % 10L == 4L && hourDuration != 14L) decName = "часа"
            "$hourDuration $decName назад"
        }
        in Date().add(-31, TimeUtils.DAY)..Date().add(-1,TimeUtils.DAY) -> {
            val dayDuration = secondsDuration / 60 / 60 / 24
            decName = "дней"
            if (dayDuration % 10L == 1L && dayDuration != 11L) decName = "день"
            if (dayDuration % 10L == 2L && dayDuration != 12L ||
                dayDuration % 10L == 3L && dayDuration != 13L ||
                dayDuration % 10L == 4L && dayDuration != 14L) decName = "дня"
            "$dayDuration $decName назад"
        }
        else -> {
            val dayDuration = secondsDuration / 60 / 60 / 24
            if (dayDuration < 364) {
                when (val monthDuration = date.format("MM").toInt() - this.format("MM").toInt()) {
                    1 -> "Месяц назад"
                    in 2..4 -> "$monthDuration месяца назад"
                    in 5..11 -> "$monthDuration месяцев назад"
                    else -> "В прошлом году"
                }
            } else "Больше года назад"
        }
    }
}
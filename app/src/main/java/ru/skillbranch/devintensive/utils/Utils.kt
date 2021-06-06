package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = if (parts?.getOrNull(0).isNullOrEmpty()) "Undefined" else parts?.get(0)
        val lastName = if (parts?.getOrNull(1).isNullOrEmpty()) "Undefined" else parts?.get(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val (firstName, lastName) = parseFullName(payload)
        return Translate.toTranslate(firstName!!) + divider + Translate.toTranslate(lastName!!)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        //TODO
        // вернуть инициалы
        return "toInitials not implemented"
    }
}

class Translate {
    companion object Translator {
        private var letters: MutableMap<String, String> = mutableMapOf(
            "А" to "A",
            "Б" to "B",
            "В" to "V",
            "Г" to "G",
            "Д" to "D",
            "Е" to "E",
            "Ё" to "E",
            "Ж" to "Zh",
            "З" to "Z",
            "И" to "I",
            "Й" to "I",
            "К" to "K",
            "Л" to "L",
            "М" to "M",
            "Н" to "N",
            "О" to "O",
            "П" to "P",
            "Р" to "R",
            "С" to "S",
            "Т" to "T",
            "У" to "U",
            "Ф" to "F",
            "Х" to "Kh",
            "Ц" to "C",
            "Ч" to "Ch",
            "Ш" to "Sh",
            "Щ" to "Sch",
            "Ъ" to "'",
            "Ы" to "Y",
            "Ъ" to "'",
            "Э" to "E",
            "Ю" to "Yu",
            "Я" to "Ya",
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sch",
            "ъ" to "'",
            "ы" to "y",
            "ъ" to "'",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
        )

        fun toTranslate(text: String): String {
            val sb = StringBuilder(text.length)
            for (i in text.indices) {
                val l = text.substring(i, i + 1)
                if (letters.containsKey(l)) {
                    sb.append(letters[l])
                } else {
                    sb.append(l)
                }
            }
            return sb.toString()
        }
    }
}
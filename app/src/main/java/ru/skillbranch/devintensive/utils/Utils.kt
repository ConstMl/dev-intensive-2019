package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        // TODO FIX ME
        val parts: List<String>? = fullName?.split(" ")
        val firstName = if (parts?.getOrNull(0).isNullOrEmpty()) "Undefined" else parts?.get(0)
        val lastName = if (parts?.getOrNull(1).isNullOrEmpty()) "Undefined" else parts?.get(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        //TODO
        // вывести в качестве ника
        // имя и фамилию литиницой
        return "transliteration not implemented"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        //TODO
        // вернуть инициалы
        return "toInitials not implemented"
    }
}
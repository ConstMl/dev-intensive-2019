package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        // TODO FIX ME
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
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
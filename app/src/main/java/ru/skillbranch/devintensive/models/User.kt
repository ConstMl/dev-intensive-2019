package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false

) {

    constructor(
        id: String, firstName: String?, lastName: String?
    ) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, firstName = "John", lastName = "Doe")

    init {
        println(
            "It's Alive! \n" +
                    "${if (lastName === "Doe") "FName: $firstName, LName: $lastName" else "Else: FName: $firstName, LName: $lastName"}\n"
        )
    }

    // Починить фкнцию
    // плохо проходит тест
    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(
                id = "$lastId", firstName = firstName, lastName = lastName
            )
        }
    }


//    private fun getIntro(): String = """
//        qwe qweqweqwe qweqweqw
//        qweqw qweqweqw eqweqwe !!!!
//
//        qewe
//
//        qweqwrqwrqwe qweqwe qqweqw weq
//        ${"\n\n\n"}
//        $firstName $lastName
//    """.trimIndent()

//    fun printMe() = println(
//        """
//        id: $id
//        firstName: $firstName
//        lastName: $lastName
//        avatar: $avatar
//        rating: $rating
//        respect: $respect
//        lastVisit: $lastVisit
//        """.trimIndent()
//    )


}
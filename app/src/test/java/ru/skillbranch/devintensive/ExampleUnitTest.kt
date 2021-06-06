package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User("1")
        val user2 = User("2", "John", "Cena")
    }

    @Test
    fun test_factory_makeUser() {
        val user1 = User.makeUser("Ivan Ivanov")
        val user11 = User.makeUser("Ivan")
        val user2 = User.makeUser("")
        val user3 = User.makeUser(" ")
        val user4 = User.makeUser(null)
    }

    @Test
    fun test_factory_makeUser_data() {
        val user1 = User.makeUser("Ivan Ivanov")
        val user2 = user1.copy(
            id = "2",
            lastName = "Petrov",
            lastVisit = Date()
        )
        println(user1);
        println(user2);
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("Ivan Ivanov")

        fun getUserInfo() = user

        val (id, firstName, lastName) = getUserInfo()

        println("$id, $firstName, $lastName")
        println("${user.component1()}, ${user.component2()}, ${user.component3()}")
    }

    @Test
    fun test_data_mapping() {
        val user = User.makeUser("Иван Иванов")
        println(user)
        val userView = user.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory_message() {
        val user = User.makeUser("Иван Иванов")
        val chat = Chat("0")
        val txtMessage = BaseMessage.makeMessage(user, chat, payload = "any text message", type = MessageType.TEXT)
        val imgMessage = BaseMessage.makeMessage(user, chat, payload = "any image url", type = MessageType.IMAGE)

        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())
    }

    @Test
    fun test_humanizeDiff() {
        // and change TimeUtils
        for (i in 0..1000 step 30) {
            println("$i")
            val dat = Date().add(-i, TimeUtils.DAY);
            println("$dat - ${dat.humanizeDiff()}");
        }
    }
}
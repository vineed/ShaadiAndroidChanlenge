package com.shaadi.shaadiandroidchallenge

import com.shaadi.shaadiandroidchallenge.partner_match.model.UserMatch
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*
import org.hamcrest.CoreMatchers.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class UserMatcherTest {
    @Test
    fun `does return correct displayName given firstName and lastName`() {
        val uuid = UUID.randomUUID().toString()
        val userMatch = UserMatch(
            uuid, "MR", "Vijay", "Singh", 27, "",
            "", "", "", "", "", "", null
        )

        assertThat(userMatch.displayName, `is`(equalTo("Vijay S")))
    }

    @Test
    fun `does return correct displayName given firstName and lastName in small letters`() {
        val uuid = UUID.randomUUID().toString()
        val userMatch = UserMatch(
            uuid, "MR", "Vijay", "singh", 27, "",
            "", "", "", "", "", "", null
        )

        assertThat(userMatch.displayName, `is`(equalTo("Vijay S")))
    }

    @Test
    fun `does return correct displayName given blank or empty firstName and lastName`() {
        val uuid = UUID.randomUUID().toString()
        val userMatch = UserMatch(
            uuid, "MR", " ", "Singh", 27, "",
            "", "", "", "", "", "", null
        )

        assertThat(userMatch.displayName, `is`(equalTo("Singh")))
    }

    @Test
    fun `does return correct shortDesc given age, street, city, state and country`() {
        val uuid = UUID.randomUUID().toString()
        val userMatch = UserMatch(
            uuid, "MR", "Vijay", "Singh", 27, "MyStreet",
            "MyCity", "MyState", "MyCountry", "", "", "", null
        )

        assertThat(userMatch.shortDesc, `is`(equalTo("27, MyStreet, MyCity, MyState, MyCountry")))
    }

    @Test
    fun `does return correct shortDesc given age, state and country`() {
        val uuid = UUID.randomUUID().toString()
        val userMatch = UserMatch(
            uuid, "MR", "Vijay", "Singh", 27, "",
            "", "MyState", "MyCountry", "", "", "", null
        )

        assertThat(userMatch.shortDesc, `is`(equalTo("27, MyState, MyCountry")))
    }

    @Test
    fun `does return correct shortDesc given state and country`() {
        val uuid = UUID.randomUUID().toString()
        val userMatch = UserMatch(
            uuid, "MR", "Vijay", "Singh", -1, "",
            "", "MyState", "MyCountry", "", "", "", null
        )

        assertThat(userMatch.shortDesc, `is`(equalTo("MyState, MyCountry")))
    }
}
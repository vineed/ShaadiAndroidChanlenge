package com.shaadi.shaadiandroidchallenge

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.shaadi.shaadiandroidchallenge.partner_match.model.UserMatch
import com.shaadi.shaadiandroidchallenge.repository.db.dao.UserMatchDao
import com.shaadi.shaadiandroidchallenge.repository.db.entities.UserMatchEntity
import com.shaadi.shaadiandroidchallenge.repository.db.impl.ShaadiDatabase
import com.shaadi.shaadiandroidchallenge.repository.db.stub.IShaadiDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Before
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UserMatchRepoTest {

    private lateinit var userMatchDao: UserMatchDao
    private lateinit var shaadiDatabase: ShaadiDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        shaadiDatabase = Room.inMemoryDatabaseBuilder(context, ShaadiDatabase::class.java).build()
        userMatchDao = shaadiDatabase.getUserMatchDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        shaadiDatabase.close()
    }

    @Test
    fun does_inserting_values_save_data() = runBlocking {
        val uuid = UUID.randomUUID().toString()

        userMatchDao.addAllMatchUser(
            mutableListOf<UserMatchEntity>(
                UserMatchEntity(
                    uuid,
                    "MR",
                    "Vijay",
                    "Singh",
                    27, "", "", "", "", "", "", ""
                )
            )
        )

        assertThat(userMatchDao.getAllMatchUser().size, `is`(greaterThan(0)))
    }

}
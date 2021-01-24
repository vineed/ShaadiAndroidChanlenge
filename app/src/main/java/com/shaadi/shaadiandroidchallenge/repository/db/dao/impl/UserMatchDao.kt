package com.shaadi.shaadiandroidchallenge.repository.db.dao.impl

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaadi.shaadiandroidchallenge.repository.db.entities.UserMatchEntity
import com.shaadi.shaadiandroidchallenge.repository.db.stub.core.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface UserMatchDao {

    @Query("SELECT * FROM ${Constants.TABLE_USER}")
    suspend fun getAllMatchUser(): Flow<List<UserMatchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchUser(matchEntity: UserMatchEntity)
}
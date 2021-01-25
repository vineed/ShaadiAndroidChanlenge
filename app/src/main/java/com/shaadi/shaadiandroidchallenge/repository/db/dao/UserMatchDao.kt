package com.shaadi.shaadiandroidchallenge.repository.db.dao

import androidx.room.*
import com.shaadi.shaadiandroidchallenge.repository.db.entities.UserMatchEntity
import com.shaadi.shaadiandroidchallenge.repository.db.stub.core.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface UserMatchDao {

    /*@Query("SELECT * FROM ${Constants.TABLE_USER}")
    fun getAllMatchUser(): Flow<List<UserMatchEntity>>*/

    @Query("SELECT * FROM ${Constants.TABLE_USER}")
    suspend fun getAllMatchUser(): List<UserMatchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchUser(matchEntity: UserMatchEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMatchUser(matchEntity: UserMatchEntity): Int

    @Query("DELETE FROM ${Constants.TABLE_USER}")
    suspend fun deleteAllMatchUser()

    @Transaction
    suspend fun addAllMatchUser(matchEntityList: List<UserMatchEntity>) {
        deleteAllMatchUser()
        for (matchEntity in matchEntityList) insertMatchUser(matchEntity)
    }
}
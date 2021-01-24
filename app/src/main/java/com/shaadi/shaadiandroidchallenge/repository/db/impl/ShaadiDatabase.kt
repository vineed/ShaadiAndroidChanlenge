package com.shaadi.shaadiandroidchallenge.repository.db.impl

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shaadi.shaadiandroidchallenge.repository.db.entities.UserEntity
import com.shaadi.shaadiandroidchallenge.repository.db.stub.IShaadiDatabase
import com.shaadi.shaadiandroidchallenge.repository.db.stub.core.Constants

@Database(entities = [UserEntity::class], version = Constants.DB_VERSION, exportSchema = true)
abstract class ShaadiDatabase : RoomDatabase(), IShaadiDatabase {
    companion object {
        private var shaadiDatabase: IShaadiDatabase? = null

        fun getDatabase(context: Context): IShaadiDatabase {
            if (shaadiDatabase == null) {
                shaadiDatabase =
                    Room.databaseBuilder(context, ShaadiDatabase::class.java, Constants.DB_NAME)
                        .build()
            }

            return shaadiDatabase as ShaadiDatabase
        }

        fun destroyInstance() {
            if (shaadiDatabase != null) shaadiDatabase = null
        }
    }
}
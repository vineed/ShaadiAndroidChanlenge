package com.shaadi.shaadiandroidchallenge.repository.db.entitiesimport androidx.room.Entityimport androidx.room.PrimaryKeyimport com.shaadi.shaadiandroidchallenge.repository.db.stub.core.Constants@Entity(tableName = Constants.TABLE_USER)class UserEntity(    @PrimaryKey(autoGenerate = true) var id: Int = 0,    var name: String,    var city: String,)
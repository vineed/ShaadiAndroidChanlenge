package com.shaadi.shaadiandroidchallenge.repository.db.entitiesimport androidx.room.Entityimport androidx.room.PrimaryKeyimport com.shaadi.shaadiandroidchallenge.repository.db.stub.core.Constants@Entity(tableName = Constants.TABLE_USER)class UserMatchEntity(    @PrimaryKey(autoGenerate = false)    var uuid: String,    var first_name: String,    var middle_name: String,    var last_name: String,    var age: Int,    var street: String,    var city: String,    var state: String,    var country: String,)
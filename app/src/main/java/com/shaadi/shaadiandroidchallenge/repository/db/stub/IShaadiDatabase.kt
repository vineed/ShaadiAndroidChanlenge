package com.shaadi.shaadiandroidchallenge.repository.db.stub

import com.shaadi.shaadiandroidchallenge.repository.db.dao.UserMatchDao

interface IShaadiDatabase {
    fun getUserMatchDao(): UserMatchDao
}
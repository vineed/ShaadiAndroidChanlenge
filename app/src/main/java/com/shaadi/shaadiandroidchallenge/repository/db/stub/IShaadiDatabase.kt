package com.shaadi.shaadiandroidchallenge.repository.db.stub

import com.shaadi.shaadiandroidchallenge.repository.db.dao.impl.UserMatchDao

interface IShaadiDatabase {
    fun getUserMatchDao(): UserMatchDao
}
package com.shaadi.shaadiandroidchallenge.repository.api.stub

import com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match.UserMatchDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException

interface IShaadiApi {
    @Throws(IOException::class)
    @GET(".")
    suspend fun getAllUserMatch(
        @Query("results") resultCount: String,
        @Query("page") pageNo: String
    ): Response<UserMatchDTO>
}
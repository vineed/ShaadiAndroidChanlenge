package com.shaadi.shaadiandroidchallenge.repository.api.stub

import com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match.UserMatchDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.io.IOException

interface IShaadiApi {
    @Throws(IOException::class)
    @POST("")
    suspend fun getLogin(
        @Query("result") result: String,
        @Query("page") page: String
    ): Response<UserMatchDTO>
}
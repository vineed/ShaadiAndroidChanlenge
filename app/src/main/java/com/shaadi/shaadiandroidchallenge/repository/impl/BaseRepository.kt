package com.shaadi.shaadiandroidchallenge.repository.impl

import com.shaadi.shaadiandroidchallenge.repository.impl.core.Constants
import com.shaadi.shaadiandroidchallenge.repository.model.ApiOutput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    protected suspend fun <T> parseApiCall(apiCall: suspend () -> Response<T>): ApiOutput<T> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = apiCall()

                if (response.isSuccessful) {
                    ApiOutput.Network.Success(response.message(), response.code(), response.body())
                } else {
                    val errorBody = response.errorBody()?.string()
                    ApiOutput.Network.Failure(
                        errorBody ?: Constants.ERROR_OCCURRED,
                        response.message(),
                        response.code()
                    )
                }
            } catch (ioEx: IOException) {
                ApiOutput.Error(ioEx)
            }
        }
}
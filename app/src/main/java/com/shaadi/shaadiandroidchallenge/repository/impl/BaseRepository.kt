package com.shaadi.shaadiandroidchallenge.repository.impl

import com.shaadi.shaadiandroidchallenge.repository.impl.core.Constants
import com.shaadi.shaadiandroidchallenge.repository.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    /*protected suspend fun <T> parseApiCall(apiCall: suspend () -> Response<T>): Result<T> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = apiCall()

                if (response.isSuccessful) {
                    Result.Network.Success(response.message(), response.code(), response.body())
                } else {
                    val errorBody = response.errorBody()?.string()
                    Result.Network.Failure(
                        errorBody ?: Constants.ERROR_OCCURRED,
                        response.message(),
                        response.code()
                    )
                }
            } catch (ioEx: IOException) {
                Result.Error(ioEx)
            }
        }*/

    protected inline fun <reified T> flowApiCall(crossinline apiCall: suspend () -> Response<T>) =
        flow {
            //try {
            val response = apiCall()

            if (response.isSuccessful) {
                val responseBody = response.body()

                if (responseBody is T) {
                    emit(responseBody)
                }
            } else
                throw IOException("Invalid")
            /*} catch (ioEx: IOException){
                thr
            }*/
        }
}
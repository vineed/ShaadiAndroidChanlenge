package com.shaadi.shaadiandroidchallenge.repository.model

sealed class ApiOutput<out T> {

    sealed class Network<T>(
        var msg: String = "Success",
        val stCode: Int
    ) : ApiOutput<T>() {
        data class Success<T>(
            var successMsg: String = "Success",
            val successSTCode: Int,
            val body: T,
        ) : Network<T>(successMsg, successSTCode)

        data class Failure(
            val failureBody: String,
            var failureMsg: String = "Failure",
            var failureSTCode: Int
        ) : Network<Nothing>(failureMsg, failureSTCode)
    }

    data class Error(val exception: Exception) : ApiOutput<Nothing>()
}
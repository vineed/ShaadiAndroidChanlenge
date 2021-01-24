package com.shaadi.shaadiandroidchallenge.repository.model

sealed class Result<out T> {

    sealed class Success<T>(
        var successMsg: String,
        val body: T?,
    ) : Result<T>() {
        class DBSource<T>(msg: String = "Success", body: T?) : Success<T>(msg, body)
        class APISource<T>(msg: String = "Success", body: T?) : Success<T>(msg, body)
    }

    data class Failure(
        var failureMsg: String = "Failure",
        //var failureSTCode: Int
    ) : Result<Nothing>()
}
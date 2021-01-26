package com.shaadi.shaadiandroidchallenge.repository.model

sealed class Result<out T> {

    open class Success<T>(
        var successMsg: String = "Success",
        val body: T? = null,
    ) : Result<T>() {
        class DBSource<T>(msg: String = "Success", body: T? = null) : Success<T>(msg, body)
        class APISource<T>(msg: String = "Success", body: T? = null) : Success<T>(msg, body)
    }

    //data class Success<T>(val msg: String = "Success", val body: T? = null) : Result<T>()

    data class Failure(
        var failureMsg: String = "Failure"
    ) : Result<Nothing>()
}
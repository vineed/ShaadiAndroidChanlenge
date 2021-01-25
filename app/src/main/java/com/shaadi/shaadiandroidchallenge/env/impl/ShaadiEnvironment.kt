package com.shaadi.shaadiandroidchallenge.env.impl

import com.shaadi.shaadiandroidchallenge.BuildConfig
import com.shaadi.shaadiandroidchallenge.env.stub.IShaadiEnvironment
import java.util.*

sealed class ShaadiEnvironment(override val BASE_URL: String = BuildConfig.BASE_URL) :
    IShaadiEnvironment {

    object ShaadiLocalEnvironment /*private constructor()*/ :
        ShaadiEnvironment()

    object ShaadiUatEnvironment :
        ShaadiEnvironment()

    object ShaadiLiveEnvironment :
        ShaadiEnvironment()

    companion object {
        val shaadiEnvironment: IShaadiEnvironment by lazy {
            when (BuildConfig.FLAVOR.toLowerCase(Locale.US)) {
                "uat" -> ShaadiUatEnvironment
                "live" -> ShaadiLiveEnvironment
                else -> ShaadiLocalEnvironment
            }
        }
    }
}
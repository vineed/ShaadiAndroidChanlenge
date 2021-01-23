package com.shaadi.shaadiandroidchallenge.env.stub

import com.shaadi.shaadiandroidchallenge.env.impl.IShaadiEnvironment
import com.shaadi.shaadiandroidchallenge.BuildConfig;

sealed class ShaadiEnvironment(override val BASE_URL: String = BuildConfig.BASE_URL) :
    IShaadiEnvironment {

    object ShaadiLocalEnvironment /*private constructor()*/:
        ShaadiEnvironment()

    object ShaadiUatEnvironment :
        ShaadiEnvironment()

    object ShaadiLiveEnvironment :
        ShaadiEnvironment()

    companion object {
        val shaadiEnvironment: IShaadiEnvironment by lazy {
            when (BuildConfig.FLAVOR.toLowerCase()) {
                "uat" -> ShaadiUatEnvironment
                "live" -> ShaadiLiveEnvironment
                else -> ShaadiLocalEnvironment
            }
        }
    }
}
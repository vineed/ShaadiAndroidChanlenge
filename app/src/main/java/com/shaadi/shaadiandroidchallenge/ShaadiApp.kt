package com.shaadi.shaadiandroidchallenge

import android.app.Application
import com.shaadi.shaadiandroidchallenge.di.shaadiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class ShaadiApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShaadiApp)
            modules(shaadiModule)
        }
        Timber.plant(Timber.DebugTree())
    }

}
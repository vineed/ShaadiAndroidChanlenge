package com.shaadi.shaadiandroidchallenge.di

import com.shaadi.shaadiandroidchallenge.BuildConfig
import com.shaadi.shaadiandroidchallenge.env.impl.IShaadiEnvironment
import com.shaadi.shaadiandroidchallenge.env.stub.ShaadiEnvironment
import com.shaadi.shaadiandroidchallenge.repository.api.stub.IShaadiApi
import com.shaadi.shaadiandroidchallenge.repository.db.impl.ShaadiDatabase
import com.shaadi.shaadiandroidchallenge.repository.db.stub.IShaadiDatabase
import com.shaadi.shaadiandroidchallenge.repository.impl.UserMatchRepository
import com.shaadi.shaadiandroidchallenge.repository.stub.IUserMatchRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val shaadiModule = module(override = true) {
    single { ShaadiEnvironment.shaadiEnvironment }
    single { ShaadiDatabase.getDatabase(get()) }
    single<IUserMatchRepository> { UserMatchRepository() }
    single<IShaadiApi> {
        val environment: IShaadiEnvironment = get()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(environment.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            retrofitBuilder.client(client)
        }

        retrofitBuilder.build().create(IShaadiApi::class.java)
    }

}
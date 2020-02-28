package com.example.weatherapp.di

import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class OkHttpClientModule {

    //private val CACHE_FILE_NAME = "http_cache"
    private val CACHE_SIZE: Long = 10 * 1000 * 1000
    private val CONNECT_TIMEOUT: Long = 30
    private val CONNECT_TIME_UNIT = TimeUnit.SECONDS
    private val READ_TIMEOUT: Long = 30
    private val READ_TIME_UNIT = TimeUnit.SECONDS

    /*
     * The method returns the Okhttp object
     * */

    @Provides
    @Singleton
    fun okHttpClient(/*cache: Cache, */httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val okHttpClientBuilder : OkHttpClient.Builder = OkHttpClient()
            .newBuilder()
            .dispatcher(dispatcher)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, CONNECT_TIME_UNIT)
            .readTimeout(READ_TIMEOUT, READ_TIME_UNIT)

        return okHttpClientBuilder.build()

    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}
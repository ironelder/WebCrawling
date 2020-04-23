package com.ironelder.withinno.data.remote

import com.ironelder.withinno.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object NetworkApiService {

    val imageApi: NetworkService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        return@lazy retrofit.create(NetworkService::class.java)
    }


    private val mOkHttpClient: OkHttpClient by lazy {
        val httpClient = OkHttpClient.Builder().apply {
            connectTimeout(30L, TimeUnit.SECONDS)
            readTimeout(30L, TimeUnit.SECONDS)
            addNetworkInterceptor(getLogInterceptor())
            addInterceptor(getSearchInterceptor())
        }
        return@lazy httpClient.build()
    }

    private fun getLogInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    private fun getSearchInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }
}



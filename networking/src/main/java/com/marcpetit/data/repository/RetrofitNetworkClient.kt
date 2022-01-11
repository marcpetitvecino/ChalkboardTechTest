package com.marcpetit.data.repository

import com.marcpetit.data.network.NetworkClient
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitNetworkClient() : NetworkClient<Retrofit> {

    private var okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.MINUTES)
                .build()

            response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val client = Retrofit.Builder()
        .baseUrl("https://code-challenge-e9f47.web.app")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    override fun client(): Retrofit = client

}
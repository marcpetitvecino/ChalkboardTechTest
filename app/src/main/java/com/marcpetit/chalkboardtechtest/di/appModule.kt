package com.marcpetit.chalkboardtechtest.di

import com.marcpetit.data.network.NetworkClient
import com.marcpetit.data.repository.RetrofitNetworkClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single<NetworkClient<Retrofit>>(named(NetworkClient.NAME)) {
        RetrofitNetworkClient()
    }
}
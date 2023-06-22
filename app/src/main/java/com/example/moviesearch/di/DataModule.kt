package com.example.moviesearch.di

import android.content.Context
import com.example.moviesearch.data.LocalStorage
import com.example.moviesearch.data.NetworkClient
import com.example.moviesearch.data.network.IMDbApiService
import com.example.moviesearch.data.network.RetrofitNetworkClient
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<IMDbApiService> {
        Retrofit.Builder()
            .baseUrl("https://imdb-api.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IMDbApiService::class.java)
    }

    single {
        androidContext()
            .getSharedPreferences("local_storage", Context.MODE_PRIVATE)
    }

    factory { Gson() }

    single {
        LocalStorage(get())
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get(), androidContext())
    }
}
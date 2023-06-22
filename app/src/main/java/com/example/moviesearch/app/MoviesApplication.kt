package com.example.moviesearch.app

import android.app.Application
import com.example.moviesearch.di.dataModule
import com.example.moviesearch.di.interactorModule
import com.example.moviesearch.di.repositoryModule
import com.example.moviesearch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApplication)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule)
        }
    }
}
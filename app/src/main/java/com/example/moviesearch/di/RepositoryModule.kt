package com.example.moviesearch.di

import com.example.moviesearch.data.MoviesRepositoryImpl
import com.example.moviesearch.domain.api.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get())
    }

}
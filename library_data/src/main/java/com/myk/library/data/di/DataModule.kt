package com.myk.library.data.di

import androidx.room.Room
import com.myk.library.data.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "default-database"
        ).build()
    }

    factory { get<AppDatabase>().pokemonDao() }
}

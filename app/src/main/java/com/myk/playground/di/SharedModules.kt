package com.myk.playground.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val sharedModules = module {

    /**
     * Singleton instance of Retrofit. This allows us to make calls to the REST Api for pokeapi.co
     *
     * Note: Using Moshi Converter Factory to handle parsing
     */
    single(override = true) {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

package com.myk.feature.search.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.myk.feature.search.data.service.PokeApiService
import com.myk.feature.search.domain.SearchRepository
import retrofit2.Retrofit

class SearchRepositoryImpl(
    retrofit: Retrofit
) : SearchRepository {
    private val service = retrofit.create(PokeApiService::class.java)

    override fun getPokemon() = liveData {
        try {
            emit(service.getPokemon().results)
        } catch (e: Exception) {
            Log.e("tag", "hype $e")
        }
    }
}

package com.myk.feature.search.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.myk.feature.search.data.model.PokemonRemoteDataModel
import com.myk.feature.search.data.model.toDomainModel
import com.myk.feature.search.data.service.PokeApiService
import com.myk.feature.search.domain.repository.PokemonRepository
import retrofit2.Retrofit

class PokemonRepositoryImpl(
    retrofit: Retrofit
) : PokemonRepository {
    private val service = retrofit.create(PokeApiService::class.java)

    override fun getPokemon() = liveData {
        try {
            emit(service.getPokemon().results.map(PokemonRemoteDataModel::toDomainModel))
        } catch (e: Exception) {
            Log.e("tag", "$e")
        }
    }
}

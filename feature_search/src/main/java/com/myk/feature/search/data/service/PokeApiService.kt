package com.myk.feature.search.data.service

import com.myk.feature.search.data.response.PokemonResponse
import retrofit2.http.GET

interface PokeApiService {
    @GET("pokemon/")
    suspend fun getPokemon(): PokemonResponse
}

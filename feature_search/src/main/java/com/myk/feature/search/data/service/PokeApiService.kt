package com.myk.feature.search.data.service

import com.myk.feature.search.data.response.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET

interface PokeApiService {
    @GET("pokemon/")
    fun getPokemon(): Call<PokemonResponse>
}

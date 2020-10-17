package com.myk.feature.search.data.service

import com.myk.feature.search.data.response.ItemResponse
import com.myk.feature.search.data.response.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon/")
    suspend fun getPokemon(): PokemonResponse

    @GET("pokemon")
    suspend fun getPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonResponse

    @GET("item/")
    suspend fun getItems(): ItemResponse

    @GET("item")
    suspend fun getItems(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ItemResponse
}

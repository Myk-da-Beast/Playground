package com.myk.feature.search.data.service

import com.myk.feature.search.data.model.PokemonRemoteDataModel
import com.myk.feature.search.data.response.ItemResponse
import com.myk.feature.search.data.response.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokeApiService {
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): PokemonRemoteDataModel

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

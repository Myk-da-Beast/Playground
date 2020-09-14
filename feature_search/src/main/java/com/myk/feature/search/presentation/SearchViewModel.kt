package com.myk.feature.search.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.myk.feature.search.data.response.PokemonResponse
import com.myk.feature.search.data.service.PokeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SearchViewModel(
    retrofit: Retrofit
) : ViewModel() {
    init {
        val service = retrofit.create(PokeApiService::class.java)
        service.getPokemon().enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                Log.d("tag", "Response Success: ${response.body()?.results}")
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Log.e("tag", "Error getting pokemon: $t")
            }
        })
    }
}

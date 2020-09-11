package com.myk.feature.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myk.feature.search.data.response.PokemonResponse
import com.myk.feature.search.data.service.PokeApiService
import com.myk.feature.search.databinding.SearchFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val service = retrofit.create(PokeApiService::class.java)
        val binding = SearchFragmentBinding.inflate(inflater, container, false)

        // binding.textView.setOnClickListener {
        service.getPokemon().enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                println("here comes something")
                println(response.body()?.results)
                println(response.body()?.results?.get(0)?.name)
                println("it gone")
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                println(t)
            }
        })
        // }

        return binding.root
    }
}

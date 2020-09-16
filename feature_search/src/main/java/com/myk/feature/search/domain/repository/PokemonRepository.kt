package com.myk.feature.search.domain.repository

import androidx.lifecycle.LiveData
import com.myk.feature.search.domain.model.Pokemon

interface PokemonRepository {
    fun getPokemon(): LiveData<List<Pokemon>>
}

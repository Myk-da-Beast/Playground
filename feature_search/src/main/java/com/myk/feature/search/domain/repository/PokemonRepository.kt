package com.myk.feature.search.domain.repository

import com.myk.feature.search.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemon(): Flow<List<Pokemon>>
}

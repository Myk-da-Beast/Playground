package com.myk.feature.search.domain.repository

import com.myk.feature.search.domain.model.ItemDomainModel
import com.myk.feature.search.domain.model.PokemonDomainModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemon(): Flow<List<PokemonDomainModel>>
    fun getItems(): Flow<List<ItemDomainModel>>
}

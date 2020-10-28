package com.myk.feature.search.domain.repository

import androidx.paging.PagingData
import com.myk.feature.search.domain.model.ItemDomainModel
import com.myk.feature.search.domain.model.PokemonDomainModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonPage(): Flow<PagingData<PokemonDomainModel>>
    fun getPokemon(id: Int): Flow<PokemonDomainModel?>
    fun getItems(): Flow<PagingData<ItemDomainModel>>
}

package com.myk.feature.search.domain.repository

import androidx.paging.PagingData
import com.myk.feature.search.domain.model.ItemDomainModel
import com.myk.feature.search.domain.model.PokemonDomainModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemon(): Flow<PagingData<PokemonDomainModel>>
    fun getItems(): Flow<PagingData<ItemDomainModel>>
}

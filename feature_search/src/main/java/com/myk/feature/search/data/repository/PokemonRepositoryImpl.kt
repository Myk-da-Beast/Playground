package com.myk.feature.search.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.myk.feature.search.data.model.toDomainModel
import com.myk.feature.search.data.service.PokeApiService
import com.myk.feature.search.domain.model.ItemDomainModel
import com.myk.feature.search.domain.model.PokemonDomainModel
import com.myk.feature.search.domain.repository.PokemonRepository
import com.myk.library.data.dao.ItemDao
import com.myk.library.data.dao.PokemonDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PAGE_SIZE = 20

class PokemonRepositoryImpl(
    private val service: PokeApiService,
    private val pokemonDao: PokemonDao,
    private val itemDao: ItemDao,
) : PokemonRepository {

    @ExperimentalCoroutinesApi
    override fun getPokemon(): Flow<PagingData<PokemonDomainModel>> = Pager(
        config = PagingConfig(PAGE_SIZE),
        remoteMediator = PokemonRemoteMediator(pokemonDao, service)
    ) {
        pokemonDao.pagingSource()
    }.flow.map { pagingData ->
        pagingData.map { it.toDomainModel }
    }

    @ExperimentalCoroutinesApi
    override fun getItems(): Flow<PagingData<ItemDomainModel>> = Pager(
        config = PagingConfig(PAGE_SIZE),
        remoteMediator = ItemRemoteMediator(itemDao, service)
    ) {
        itemDao.getPage()
    }.flow.map { pagingData ->
        pagingData.map { it.toDomainModel }
    }
}

package com.myk.feature.search.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.myk.feature.search.data.model.ItemRemoteDataModel
import com.myk.feature.search.data.model.toDomainModel
import com.myk.feature.search.data.model.toLocalDataModel
import com.myk.feature.search.data.service.PokeApiService
import com.myk.feature.search.domain.model.PokemonDomainModel
import com.myk.feature.search.domain.repository.PokemonRepository
import com.myk.library.data.dao.ItemDao
import com.myk.library.data.dao.PokemonDao
import com.myk.library.data.model.ItemLocalDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

private const val POKEMON_PAGE_SIZE = 20

class PokemonRepositoryImpl(
    private val service: PokeApiService,
    private val pokemonDao: PokemonDao,
    private val itemDao: ItemDao,
) : PokemonRepository {

    @ExperimentalCoroutinesApi
    override fun getPokemon(): Flow<PagingData<PokemonDomainModel>> = Pager(
        config = PagingConfig(POKEMON_PAGE_SIZE),
        remoteMediator = PokemonRemoteMediator(pokemonDao, service)
    ) {
        pokemonDao.pagingSource()
    }.flow.map { pagingData ->
        pagingData.map { it.toDomainModel }
    }

    @ExperimentalCoroutinesApi
    override fun getItems() = flow {
        val results = try {
            service.getItems().results
        } catch (e: Exception) {
            throw e
        }

        itemDao.insertAll(results.map(ItemRemoteDataModel::toLocalDataModel))
        val items = itemDao.getAll().mapLatest {
            it.map(ItemLocalDataModel::toDomainModel)
        }

        emitAll(items)
    }
}

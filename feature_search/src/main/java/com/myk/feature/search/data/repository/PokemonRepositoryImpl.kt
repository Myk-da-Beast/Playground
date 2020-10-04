package com.myk.feature.search.data.repository

import com.myk.feature.search.data.model.ItemRemoteDataModel
import com.myk.feature.search.data.model.PokemonRemoteDataModel
import com.myk.feature.search.data.model.toDomainModel
import com.myk.feature.search.data.model.toLocalDataModel
import com.myk.feature.search.data.service.PokeApiService
import com.myk.feature.search.domain.repository.PokemonRepository
import com.myk.library.data.dao.ItemDao
import com.myk.library.data.dao.PokemonDao
import com.myk.library.data.model.ItemLocalDataModel
import com.myk.library.data.model.PokemonLocalDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

class PokemonRepositoryImpl(
    private val service: PokeApiService,
    private val pokemonDao: PokemonDao,
    private val itemDao: ItemDao,
) : PokemonRepository {

    @ExperimentalCoroutinesApi
    override fun getPokemon() = flow {
        val results = try {
            service.getPokemon().results
        } catch (e: Exception) {
            throw e
        }

        pokemonDao.insertAll(results.map(PokemonRemoteDataModel::toLocalDataModel))
        val pokemon = pokemonDao.getAll().mapLatest {
            it.map(PokemonLocalDataModel::toDomainModel)
        }

        emitAll(pokemon)
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

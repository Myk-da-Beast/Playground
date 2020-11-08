package com.myk.feature.search.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.myk.feature.search.data.model.toDomainModel
import com.myk.feature.search.data.model.toLocalDataModel
import com.myk.feature.search.data.service.PokeApiService
import com.myk.feature.search.domain.model.ItemDomainModel
import com.myk.feature.search.domain.model.PokemonDomainModel
import com.myk.feature.search.domain.repository.PokemonRepository
import com.myk.library.data.dao.ItemDao
import com.myk.library.data.dao.PokemonDao
import com.myk.library.data.model.PokemonLocalDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

private const val PAGE_SIZE = 20

internal class PokemonRepositoryImpl(
    private val service: PokeApiService,
    private val pokemonDao: PokemonDao,
    private val itemDao: ItemDao,
) : PokemonRepository {

    @ExperimentalCoroutinesApi
    override fun getPokemonPage(): Flow<PagingData<PokemonDomainModel>> = Pager(
        config = PagingConfig(PAGE_SIZE),
        remoteMediator = PokemonRemoteMediator(pokemonDao, service)
    ) {
        pokemonDao.pagingSource()
    }.flow.map { pagingData ->
        pagingData.map {
            Timber.d("glop $it")
            it.toDomainModel
        }
    }

    override fun getPokemon(id: Int) = flow {
        // we have to use a coroutine scope in order to use `async`
        coroutineScope {
            // Query a pokemon from api, then cache that pokemon in local database.
            // This function needs to be asynchronous so that this flow can immediately emit cached
            // data without waiting for the backend
            async {
                val response = service.getPokemon(id)
                pokemonDao.update(response.toLocalDataModel)
            }

            // Get cached pokemon from local data store as flow, convert it to flow of domain model
            // and then forward the emissions to flow builder.
            // NOTE: This code is written out in a more verbose style intentionally to make the flow
            // of data more apparent.
            val localDataModel: Flow<PokemonLocalDataModel> =
                pokemonDao.getPokemon(id).filterNotNull()
            val domainModel: Flow<PokemonDomainModel> = localDataModel.map {
                it.toDomainModel
            }
            emitAll(domainModel)
        }
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

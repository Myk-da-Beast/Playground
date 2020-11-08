package com.myk.feature.search.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.myk.feature.search.data.model.PokemonRemoteDataModel
import com.myk.feature.search.data.model.toLocalDataModel
import com.myk.feature.search.data.service.PokeApiService
import com.myk.library.data.dao.PokemonDao
import com.myk.library.data.model.PokemonLocalDataModel
import timber.log.Timber

@OptIn(ExperimentalPagingApi::class)
internal class PokemonRemoteMediator(
    private val pokemonDao: PokemonDao,
    private val networkService: PokeApiService
) : RemoteMediator<Int, PokemonLocalDataModel>() {

    @Suppress("ReturnCount")
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonLocalDataModel>
    ): MediatorResult {
        return try {
            // We are ignoring prepend since we don't need to page backward
            if (loadType == LoadType.PREPEND) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            // You must explicitly check if the last item is null when
            // appending, since passing null to networkService is only
            // valid for initial load. If lastItem is null it means no
            // items were loaded after the initial REFRESH and there are
            // no more items to load.
            if (loadType == LoadType.APPEND && state.lastItemOrNull() == null) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            // pokemon ids are conveniently numbered starting from 1, so we can determine which
            // page we are on by the id of the last loaded pokemon.
            val offset = if (loadType == LoadType.REFRESH) 0 else state.lastItemOrNull()!!.id

            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            val response = networkService.getPokemon(offset = offset, limit = 20).results

            val localDataModels = response.map(PokemonRemoteDataModel::toLocalDataModel)

            // Note: These functions have to be transactions for whatever reason. Seems like you can
            // only have one transaction in this function total as well.
            if (loadType == LoadType.REFRESH) pokemonDao.insertAll(localDataModels)
            else pokemonDao.insertAll(localDataModels)

            MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: Exception) {
            Timber.e(e)
            MediatorResult.Error(e)
        }
    }
}

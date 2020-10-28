package com.myk.feature.search.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.myk.feature.search.data.model.ItemRemoteDataModel
import com.myk.feature.search.data.model.toLocalDataModel
import com.myk.feature.search.data.service.PokeApiService
import com.myk.library.data.dao.ItemDao
import com.myk.library.data.model.ItemLocalDataModel
import timber.log.Timber

@OptIn(ExperimentalPagingApi::class)
internal class ItemRemoteMediator(
    private val itemDao: ItemDao,
    private val networkService: PokeApiService
) : RemoteMediator<Int, ItemLocalDataModel>() {

    @Suppress("ReturnCount")
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ItemLocalDataModel>
    ): MediatorResult {
        return try {
            if (loadType == LoadType.PREPEND) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            if (loadType == LoadType.APPEND && state.lastItemOrNull() == null) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            val offset = if (loadType == LoadType.REFRESH) 0 else state.lastItemOrNull()!!.id

            val response = networkService.getItems(offset = offset, limit = 20).results

            val localDataModels = response.map(ItemRemoteDataModel::toLocalDataModel)
            // Note: These functions have to be transactions for whatever reason. Seems like you can
            // only have one transaction in this function total as well.
            if (loadType == LoadType.REFRESH) itemDao.clearDatabaseAndInsertNew(localDataModels)
            else itemDao.insertAll(localDataModels)

            MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: Exception) {
            Timber.e(e)
            MediatorResult.Error(e)
        }
    }
}

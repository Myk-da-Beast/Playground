package com.myk.feature.search.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.myk.feature.search.data.DataFixtures
import com.myk.feature.search.data.model.ItemRemoteDataModel
import com.myk.feature.search.data.model.toLocalDataModel
import com.myk.feature.search.data.service.PokeApiService
import com.myk.library.data.dao.ItemDao
import com.myk.library.data.model.ItemLocalDataModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ItemRemoteMediatorTest {

    @MockK
    private lateinit var mockService: PokeApiService

    @MockK
    private lateinit var mockItemDao: ItemDao

    private lateinit var cut: ItemRemoteMediator

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = ItemRemoteMediator(mockItemDao, mockService)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `when load type is prepend we return success and back out`() {
        // given
        val loadType = LoadType.PREPEND

        // when
        val result = runBlocking {
            cut.load(loadType, mockk())
        }

        // then
        coVerify(exactly = 0) {
            mockService.getPokemon(any(), any())
            mockItemDao.clearDatabaseAndInsertNew(any())
            mockItemDao.insertAll(any())
        }

        result shouldBeInstanceOf RemoteMediator.MediatorResult.Success::class
        (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached shouldBeEqualTo true
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `when load type is append and reached end of page we return success and back out`() {
        // given
        val loadType = LoadType.APPEND
        val state = mockk<PagingState<Int, ItemLocalDataModel>>()
        every { state.lastItemOrNull() } returns null

        // when
        val result = runBlocking {
            cut.load(loadType, state)
        }

        // then
        coVerify(exactly = 0) {
            mockService.getPokemon(any(), any())
            mockItemDao.clearDatabaseAndInsertNew(any())
            mockItemDao.insertAll(any())
        }

        result shouldBeInstanceOf RemoteMediator.MediatorResult.Success::class
        (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached shouldBeEqualTo true
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `when load type is append and not end of page we perform query with offset and fetch`() {
        // given
        val loadType = LoadType.APPEND
        val state = mockk<PagingState<Int, ItemLocalDataModel>>()
        val offsetParam1 = 113
        val itemParam = listOf(DataFixtures.getItemRemoteDataModel())

        coEvery {
            mockService.getItems(any(), any()).results
        } returns itemParam
        coEvery {
            mockItemDao.insertAll(any())
        } just runs

        // when
        every { state.lastItemOrNull()?.id } returns offsetParam1
        val result = runBlocking {
            cut.load(loadType, state)
        }

        // then
        coVerify {
            mockService.getItems(offsetParam1, any())
            mockItemDao.insertAll(itemParam.map(ItemRemoteDataModel::toLocalDataModel))
        }
        coVerify(exactly = 0) {
            mockItemDao.clearDatabaseAndInsertNew(any())
        }

        result shouldBeInstanceOf RemoteMediator.MediatorResult.Success::class
        (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached shouldBeEqualTo false
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `when load type is refresh we query first page and refresh database`() {
        // given
        val loadType = LoadType.REFRESH
        val state = mockk<PagingState<Int, ItemLocalDataModel>>(relaxed = true)
        val itemParam = listOf(DataFixtures.getItemRemoteDataModel())

        coEvery {
            mockService.getItems(any(), any()).results
        } returns itemParam
        coEvery {
            mockItemDao.clearDatabaseAndInsertNew(any())
        } just runs

        // when
        val result = runBlocking {
            cut.load(loadType, state)
        }

        // then
        coVerify {
            mockService.getItems(0, any())
            mockItemDao.clearDatabaseAndInsertNew(itemParam.map(ItemRemoteDataModel::toLocalDataModel))
        }
        coVerify(exactly = 0) {
            mockItemDao.insertAll(any())
        }

        result shouldBeInstanceOf RemoteMediator.MediatorResult.Success::class
        (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached shouldBeEqualTo false
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `when service throws exception return MediatorResult Error`() {
        // given
        val loadType = LoadType.REFRESH
        val state = mockk<PagingState<Int, ItemLocalDataModel>>(relaxed = true)

        coEvery {
            mockService.getItems(any(), any())
        } throws Exception()

        // when
        val result = runBlocking {
            cut.load(loadType, state)
        }

        // then
        result shouldBeInstanceOf RemoteMediator.MediatorResult.Error::class
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `when dao throws exception return MediatorResult Error`() {
        // given
        val loadType = LoadType.REFRESH
        val state = mockk<PagingState<Int, ItemLocalDataModel>>(relaxed = true)

        coEvery {
            mockService.getItems(any(), any())
        } returns mockk()
        coEvery {
            mockItemDao.clearDatabaseAndInsertNew(any())
        } throws Exception()

        // when
        val result = runBlocking {
            cut.load(loadType, state)
        }

        // then
        result shouldBeInstanceOf RemoteMediator.MediatorResult.Error::class
    }
}

package com.myk.feature.search.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.myk.feature.search.data.DataFixtures
import com.myk.feature.search.data.model.PokemonRemoteDataModel
import com.myk.feature.search.data.model.toLocalDataModel
import com.myk.feature.search.data.service.PokeApiService
import com.myk.library.data.dao.PokemonDao
import com.myk.library.data.model.PokemonLocalDataModel
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
class PokemonRemoteMediatorTest {

    @MockK
    private lateinit var mockService: PokeApiService

    @MockK
    private lateinit var mockPokemonDao: PokemonDao

    private lateinit var cut: PokemonRemoteMediator

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = PokemonRemoteMediator(mockPokemonDao, mockService)
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
            mockPokemonDao.clearDatabaseAndInsertNew(any())
            mockPokemonDao.insertAll(any())
        }

        result shouldBeInstanceOf RemoteMediator.MediatorResult.Success::class
        (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached shouldBeEqualTo true
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `when load type is append and reached end of page we return success and back out`() {
        // given
        val loadType = LoadType.APPEND
        val state = mockk<PagingState<Int, PokemonLocalDataModel>>()
        every { state.lastItemOrNull() } returns null

        // when
        val result = runBlocking {
            cut.load(loadType, state)
        }

        // then
        coVerify(exactly = 0) {
            mockService.getPokemon(any(), any())
            mockPokemonDao.clearDatabaseAndInsertNew(any())
            mockPokemonDao.insertAll(any())
        }

        result shouldBeInstanceOf RemoteMediator.MediatorResult.Success::class
        (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached shouldBeEqualTo true
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `when load type is append and not end of page we perform query with offset and fetch`() {
        // given
        val loadType = LoadType.APPEND
        val state = mockk<PagingState<Int, PokemonLocalDataModel>>()
        val offsetParam1 = 113
        val pokemonParam = listOf(DataFixtures.getPokemonRemoteDataModel())

        coEvery {
            mockService.getPokemon(any(), any()).results
        } returns pokemonParam
        coEvery {
            mockPokemonDao.insertAll(any())
        } just runs

        // when
        every { state.lastItemOrNull()?.id } returns offsetParam1
        val result = runBlocking {
            cut.load(loadType, state)
        }

        // then
        coVerify {
            mockService.getPokemon(offsetParam1, any())
            mockPokemonDao.insertAll(pokemonParam.map(PokemonRemoteDataModel::toLocalDataModel))
        }
        coVerify(exactly = 0) {
            mockPokemonDao.clearDatabaseAndInsertNew(any())
        }

        result shouldBeInstanceOf RemoteMediator.MediatorResult.Success::class
        (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached shouldBeEqualTo false
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `when load type is refresh we query first page and refresh database`() {
        // given
        val loadType = LoadType.REFRESH
        val state = mockk<PagingState<Int, PokemonLocalDataModel>>(relaxed = true)
        val pokemonParam = listOf(DataFixtures.getPokemonRemoteDataModel())

        coEvery {
            mockService.getPokemon(any(), any()).results
        } returns pokemonParam
        coEvery {
            mockPokemonDao.insertAll(any())
        } just runs

        // when
        val result = runBlocking {
            cut.load(loadType, state)
        }

        // then
        coVerify {
            mockService.getPokemon(0, any())
            mockPokemonDao.insertAll(pokemonParam.map(PokemonRemoteDataModel::toLocalDataModel))
        }

        result shouldBeInstanceOf RemoteMediator.MediatorResult.Success::class
        (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached shouldBeEqualTo false
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `when service throws exception return MediatorResult Error`() {
        // given
        val loadType = LoadType.REFRESH
        val state = mockk<PagingState<Int, PokemonLocalDataModel>>(relaxed = true)

        coEvery {
            mockService.getPokemon(any(), any())
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
        val state = mockk<PagingState<Int, PokemonLocalDataModel>>(relaxed = true)

        coEvery {
            mockService.getPokemon(any(), any())
        } returns mockk()
        coEvery {
            mockPokemonDao.clearDatabaseAndInsertNew(any())
        } throws Exception()

        // when
        val result = runBlocking {
            cut.load(loadType, state)
        }

        // then
        result shouldBeInstanceOf RemoteMediator.MediatorResult.Error::class
    }
}

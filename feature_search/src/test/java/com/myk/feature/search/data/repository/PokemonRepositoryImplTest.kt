package com.myk.feature.search.data.repository

import com.myk.feature.search.data.DataFixtures
import com.myk.feature.search.data.model.PokemonRemoteDataModel
import com.myk.feature.search.data.model.toDomainModel
import com.myk.feature.search.data.model.toLocalDataModel
import com.myk.feature.search.data.response.PokemonResponse
import com.myk.feature.search.data.service.PokeApiService
import com.myk.library.data.dao.PokemonDao
import com.myk.library.data.model.PokemonLocalDataModel
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonRepositoryImplTest {

    @MockK
    private lateinit var mockService: PokeApiService

    @MockK
    private lateinit var mockDao: PokemonDao

    private lateinit var cut: PokemonRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = PokemonRepositoryImpl(mockService, mockDao)
    }

    @Test
    fun `getPokemon() interfaces with service and data store and returns domain model`() =
        runBlockingTest {
            // given
            val remoteDataModels = listOf(DataFixtures.getPokemonRemoteDataModel().copy())
            val convertedDataModels = remoteDataModels.map(PokemonRemoteDataModel::toLocalDataModel)
            val convertedDomainModels =
                convertedDataModels.map(PokemonLocalDataModel::toDomainModel)

            coEvery { mockService.getPokemon() } returns PokemonResponse(remoteDataModels)
            coEvery { mockDao.insertAll(any()) } just Runs
            coEvery { mockDao.getAll() } returns flowOf(convertedDataModels)

            // when
            val result = cut.getPokemon().take(1).first()

            // then
            coVerifySequence {
                mockService.getPokemon()
                mockDao.insertAll(convertedDataModels)
                mockDao.getAll()
            }
            result shouldBeEqualTo convertedDomainModels
        }
}

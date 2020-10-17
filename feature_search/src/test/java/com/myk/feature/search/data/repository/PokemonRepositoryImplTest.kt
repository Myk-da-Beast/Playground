package com.myk.feature.search.data.repository

import androidx.paging.PagingData
import com.myk.feature.search.data.service.PokeApiService
import com.myk.library.data.dao.ItemDao
import com.myk.library.data.dao.PokemonDao
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonRepositoryImplTest {

    @MockK
    private lateinit var mockService: PokeApiService

    @MockK
    private lateinit var mockPokemonDao: PokemonDao

    @MockK
    private lateinit var mockItemDao: ItemDao

    private lateinit var cut: PokemonRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = PokemonRepositoryImpl(mockService, mockPokemonDao, mockItemDao)
    }

    @Test
    fun `getPokemon() interfaces with service and data store and returns domain model`() =
        runBlockingTest {
            // given
            coEvery { mockPokemonDao.pagingSource() } returns mockk(relaxed = true)

            // when
            val result = cut.getPokemon().take(1).first()

            // then
            coVerify {
                mockPokemonDao.pagingSource()
            }

            // For now just making sure we are returning Paging Data.
            // todo find out how to compare contents of PagingData
            result shouldBeInstanceOf (PagingData::class.java)
        }

    @Test
    fun `getItems() interfaces with service and data store and returns domain model`() =
        runBlockingTest {
            // given
            coEvery { mockItemDao.getPage() } returns mockk(relaxed = true)

            // when
            val result = cut.getItems().take(1).first()

            // then
            coVerify {
                mockItemDao.getPage()
            }

            // For now just making sure we are returning Paging Data.
            // todo find out how to compare contents of PagingData
            result shouldBeInstanceOf (PagingData::class.java)
        }
}

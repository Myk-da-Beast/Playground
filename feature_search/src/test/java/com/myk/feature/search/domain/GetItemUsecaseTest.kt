package com.myk.feature.search.domain

import androidx.paging.PagingData
import com.myk.feature.search.data.repository.PokemonRepositoryImpl
import com.myk.feature.search.domain.usecase.GetItemsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetItemUsecaseTest {

    @MockK
    internal lateinit var mockPokemonRepository: PokemonRepositoryImpl

    private lateinit var cut: GetItemsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = GetItemsUseCase(mockPokemonRepository)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `return Paging Data list of items`() {
        // given
        val items = listOf(DomainFixtures.getItemDomainModel())
        coEvery { mockPokemonRepository.getItems() } returns flowOf(PagingData.from(items))

        // when
        val result = runBlocking { cut.invoke().first() }

        // then
        // For now just making sure we are returning Paging Data.
        // todo find out how to compare contents of PagingData
        result shouldBeInstanceOf (PagingData::class.java)
    }
}

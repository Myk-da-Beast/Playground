package com.myk.feature.search.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.myk.feature.search.domain.DomainFixtures
import com.myk.feature.search.domain.usecase.GetPokemonPageUseCase
import com.myk.feature.search.presentation.search.SearchViewModel
import com.myk.library.testUtils.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    internal lateinit var mockGetPokemonUseCase: GetPokemonPageUseCase

    private lateinit var cut: SearchViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = SearchViewModel(mockGetPokemonUseCase)
    }

    @Test
    fun `getPokemonPages() invokes UseCase and returns page of pokemon domain models`() {
        // given
        val pokemon = listOf(DomainFixtures.getPokemonDomainModel())
        coEvery {
            mockGetPokemonUseCase.invoke()
        } returns flowOf(PagingData.from(pokemon))

        // when
        val result = runBlocking {
            cut.getPokemonPages().first()
        }

        // then
        coVerify { mockGetPokemonUseCase.invoke() }
        result shouldBeInstanceOf (PagingData::class.java)
    }
}

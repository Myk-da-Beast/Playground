package com.myk.feature.search.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.myk.feature.search.domain.usecase.GetPokemonUsecase
import com.myk.library.testUtils.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    internal lateinit var mockGetPokemonUsecase: GetPokemonUsecase

    private lateinit var cut: SearchViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = SearchViewModel(mockGetPokemonUsecase)
    }

    @Test
    fun `execute getPokemonUseCase`() {
        // given

        // when

        // then
        coVerify { mockGetPokemonUsecase.invoke() }
    }
}

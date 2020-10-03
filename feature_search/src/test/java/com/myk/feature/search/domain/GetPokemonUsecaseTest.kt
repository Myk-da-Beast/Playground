package com.myk.feature.search.domain

import com.myk.feature.search.data.repository.PokemonRepositoryImpl
import com.myk.feature.search.domain.usecase.GetPokemonUsecase
import com.myk.feature.search.domain.usecase.GetPokemonUsecaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetPokemonUsecaseTest {

    @MockK
    internal lateinit var mockPokemonRepository: PokemonRepositoryImpl

    private lateinit var cut: GetPokemonUsecase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = GetPokemonUsecaseImpl(mockPokemonRepository)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `return list of pokemon`() {
        // given
        val pokemon = listOf(DomainFixtures.getPokemonDomainModel())
        coEvery { mockPokemonRepository.getPokemon() } returns flowOf(pokemon)

        // when
        val result = runBlocking { cut.invoke().take(1).first() }

        // then
        result shouldBeEqualTo pokemon
    }
}

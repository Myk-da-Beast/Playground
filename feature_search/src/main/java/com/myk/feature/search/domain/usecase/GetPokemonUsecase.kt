package com.myk.feature.search.domain.usecase

import com.myk.feature.search.domain.model.Pokemon
import com.myk.feature.search.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

interface GetPokemonUsecase {
    operator fun invoke(): Flow<List<Pokemon>>
}

class GetPokemonUsecaseImpl(
    private val repository: PokemonRepository
) : GetPokemonUsecase {
    override fun invoke() = repository.getPokemon()
}

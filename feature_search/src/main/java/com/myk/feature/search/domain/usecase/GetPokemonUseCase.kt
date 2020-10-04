package com.myk.feature.search.domain.usecase

import com.myk.feature.search.domain.repository.PokemonRepository

class GetPokemonUseCase(
    private val repository: PokemonRepository
) {
    operator fun invoke() = repository.getPokemon()
}

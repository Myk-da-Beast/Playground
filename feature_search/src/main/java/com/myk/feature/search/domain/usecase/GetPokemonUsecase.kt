package com.myk.feature.search.domain.usecase

import androidx.lifecycle.LiveData
import com.myk.feature.search.domain.model.Pokemon
import com.myk.feature.search.domain.repository.PokemonRepository

interface GetPokemonUsecase {
    operator fun invoke(): LiveData<List<Pokemon>>
}

class GetPokemonUsecaseImpl(
    private val repository: PokemonRepository
) : GetPokemonUsecase {
    override fun invoke() = repository.getPokemon()
}

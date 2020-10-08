package com.myk.feature.search.domain.usecase

import androidx.paging.PagingData
import com.myk.feature.search.domain.model.PokemonDomainModel
import com.myk.feature.search.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonUseCase(
    private val repository: PokemonRepository
) {
    /**
     * Assumes page count starts at 0
     */
    operator fun invoke(): Flow<PagingData<PokemonDomainModel>> = repository.getPokemon()
}

package com.myk.feature.search.domain.usecase

import com.myk.feature.search.domain.repository.PokemonRepository

class GetItemsUseCase(
    private val repository: PokemonRepository
) {
    operator fun invoke() = repository.getItems()
}

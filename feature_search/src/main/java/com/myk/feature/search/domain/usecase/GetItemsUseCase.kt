package com.myk.feature.search.domain.usecase

import androidx.paging.PagingData
import com.myk.feature.search.domain.model.ItemDomainModel
import com.myk.feature.search.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetItemsUseCase(
    private val repository: PokemonRepository
) {
    /**
     * Assumes page count starts at 0
     */
    operator fun invoke(): Flow<PagingData<ItemDomainModel>> = repository.getItems()
}

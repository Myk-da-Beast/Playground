package com.myk.feature.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.myk.feature.search.domain.usecase.GetPokemonUseCase

class SearchViewModel(
    private val getPokemon: GetPokemonUseCase
) : ViewModel() {
    fun searchRepo() = getPokemon().cachedIn(viewModelScope)
}

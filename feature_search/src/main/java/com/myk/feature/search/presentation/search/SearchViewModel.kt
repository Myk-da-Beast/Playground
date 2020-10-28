package com.myk.feature.search.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.myk.feature.search.domain.usecase.GetPokemonPageUseCase

class SearchViewModel(
    private val getPokemon: GetPokemonPageUseCase
) : ViewModel() {
    fun getPokemonPages() = getPokemon().cachedIn(viewModelScope)
}

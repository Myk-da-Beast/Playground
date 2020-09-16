package com.myk.feature.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myk.feature.search.domain.model.Pokemon
import com.myk.feature.search.domain.usecase.GetPokemonUsecase

class SearchViewModel(
    getPokemon: GetPokemonUsecase
) : ViewModel() {
    val pokemon: LiveData<List<Pokemon>> = getPokemon()
}

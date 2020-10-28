package com.myk.feature.search.presentation.pokemonDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.myk.feature.search.domain.usecase.GetPokemonUseCase

class PokemonViewModel(
    id: Int,
    getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {
    val pokemon = getPokemonUseCase(id).asLiveData()
}

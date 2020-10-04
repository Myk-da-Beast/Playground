package com.myk.feature.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.myk.feature.search.domain.model.PokemonDomainModel
import com.myk.feature.search.domain.usecase.GetPokemonUseCase

class SearchViewModel(
    getPokemon: GetPokemonUseCase
) : ViewModel() {
    val pokemon: LiveData<List<PokemonDomainModel>> = getPokemon().asLiveData()
}

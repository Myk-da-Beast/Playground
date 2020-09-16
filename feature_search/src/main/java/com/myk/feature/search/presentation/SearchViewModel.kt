package com.myk.feature.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myk.feature.search.data.model.Pokemon
import com.myk.feature.search.domain.SearchRepository

class SearchViewModel(
    repository: SearchRepository
) : ViewModel() {
    val pokemon: LiveData<List<Pokemon>> = repository.getPokemon()
}

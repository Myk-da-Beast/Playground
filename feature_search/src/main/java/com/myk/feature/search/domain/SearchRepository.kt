package com.myk.feature.search.domain

import androidx.lifecycle.LiveData
import com.myk.feature.search.data.model.Pokemon

interface SearchRepository {
    fun getPokemon(): LiveData<List<Pokemon>>
}

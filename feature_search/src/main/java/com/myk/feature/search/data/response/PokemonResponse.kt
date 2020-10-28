package com.myk.feature.search.data.response

import com.myk.feature.search.data.model.PokemonRemoteDataModel

internal data class PokemonResponse(
    val results: List<PokemonRemoteDataModel>
)

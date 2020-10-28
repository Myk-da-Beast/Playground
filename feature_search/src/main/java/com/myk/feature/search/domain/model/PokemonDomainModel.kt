package com.myk.feature.search.domain.model

data class PokemonDomainModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String> = listOf()
)

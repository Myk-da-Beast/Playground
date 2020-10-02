package com.myk.feature.search.domain

import com.myk.feature.search.domain.model.Pokemon

object DomainFixtures {
    internal fun getPokemonDomainModel(
        name: String = "bulbasaur",
        imageUrl: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +
            "versions/generation-vii/ultra-sun-ultra-moon/1.png",
    ): Pokemon = Pokemon(name, imageUrl)
}

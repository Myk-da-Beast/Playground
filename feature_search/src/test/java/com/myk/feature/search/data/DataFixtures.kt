package com.myk.feature.search.data

import com.myk.feature.search.data.model.PokemonRemoteDataModel
import com.myk.library.data.model.PokemonLocalDataModel

object DataFixtures {
    internal fun getPokemonRemoteDataModel(
        name: String? = "bulbasaur",
        url: String? = "https://pokeapi.co/api/v2/pokemon/1/",
    ): PokemonRemoteDataModel = PokemonRemoteDataModel(name, url)

    internal fun getPokemonLocalDataModel(
        name: String = "bulbasaur",
        url: String? = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +
            "versions/generation-vii/ultra-sun-ultra-moon/1.png"
    ): PokemonLocalDataModel = PokemonLocalDataModel(name, url)
}

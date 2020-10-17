package com.myk.feature.search.data

import com.myk.feature.search.data.model.ItemRemoteDataModel
import com.myk.feature.search.data.model.PokemonRemoteDataModel
import com.myk.library.data.model.ItemLocalDataModel
import com.myk.library.data.model.PokemonLocalDataModel

object DataFixtures {
    internal fun getPokemonRemoteDataModel(
        name: String? = "bulbasaur",
        url: String? = "https://pokeapi.co/api/v2/pokemon/1/",
    ): PokemonRemoteDataModel = PokemonRemoteDataModel(name, url)

    internal fun getPokemonLocalDataModel(
        id: Int = 1,
        name: String = "bulbasaur",
        url: String? = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +
            "versions/generation-vii/ultra-sun-ultra-moon/1.png"
    ): PokemonLocalDataModel = PokemonLocalDataModel(id, name, url)

    internal fun getItemRemoteDataModel(
        name: String = "master-ball",
        imageUrl: String = "https://pokeapi.co/api/v2/item/1/",
    ): ItemRemoteDataModel = ItemRemoteDataModel(name, imageUrl)

    internal fun getItemLocalDataModel(
        id: Int = 1,
        name: String = "master-ball",
        imageUrl: String = "https://pokeapi.co/api/v2/item/1/",
    ): ItemLocalDataModel = ItemLocalDataModel(id, name, imageUrl)
}

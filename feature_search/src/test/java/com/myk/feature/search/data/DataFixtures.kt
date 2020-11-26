package com.myk.feature.search.data

import com.myk.feature.search.data.model.ItemRemoteDataModel
import com.myk.feature.search.data.model.PokemonRemoteDataModel
import com.myk.library.data.model.ItemLocalDataModel
import com.myk.library.data.model.PokemonLocalDataModel

object DataFixtures {
    internal fun getPokemonRemoteDataModel(
        id: Int? = 1,
        name: String? = "bulbasaur",
        url: String? = "https://pokeapi.co/api/v2/pokemon/1/",
        types: List<PokemonRemoteDataModel.PokemonTypeList>? = listOf(
            PokemonRemoteDataModel.PokemonTypeList(
                PokemonRemoteDataModel.PokemonType("grass", "https://pokeapi.co/api/v2/type/12/")
            ),
            PokemonRemoteDataModel.PokemonTypeList(
                PokemonRemoteDataModel.PokemonType("poison", "https://pokeapi.co/api/v2/type/4/")
            )
        )
    ): PokemonRemoteDataModel = PokemonRemoteDataModel(id, name, url, types)

    internal fun getPokemonLocalDataModel(
        id: Int = 1,
        name: String = "bulbasaur",
        url: String? = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +
            "versions/generation-vii/ultra-sun-ultra-moon/1.png",
        types: List<String>? = listOf("grass", "poison")
    ): PokemonLocalDataModel = PokemonLocalDataModel(id, name, url, types)

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

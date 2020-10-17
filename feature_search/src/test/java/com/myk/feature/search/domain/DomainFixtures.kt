package com.myk.feature.search.domain

import com.myk.feature.search.domain.model.ItemDomainModel
import com.myk.feature.search.domain.model.PokemonDomainModel

object DomainFixtures {
    internal fun getPokemonDomainModel(
        name: String = "bulbasaur",
        imageUrl: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +
            "versions/generation-vii/ultra-sun-ultra-moon/1.png",
    ): PokemonDomainModel = PokemonDomainModel(name, imageUrl)

    internal fun getItemDomainModel(
        name: String = "master-ball",
        imageUrl: String = "https://pokeapi.co/api/v2/item/1/",
    ): ItemDomainModel = ItemDomainModel(name, imageUrl)
}

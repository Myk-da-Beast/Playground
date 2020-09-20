package com.myk.feature.search.data.model

import com.myk.feature.search.domain.model.Pokemon
import com.myk.library.data.model.PokemonLocalDataModel

/**
 * Maps the Pokemon DTO to the domain model.
 */
fun PokemonRemoteDataModel.toLocalDataModel(): PokemonLocalDataModel {
    /**
     * Very specific function that will try to parse an Id out of a string. Always assuming that the
     * string is a url of the form: "https://pokeapi.co/api/v2/pokemon/1/" where id is in the last
     * part of the url.
     */
    fun extractId(url: String): String {
        // remove the last "/"
        val preppedUrl = url.removeSuffix("/")

        // gets the substring after the last (remaining) "/" which is presumably the id
        return preppedUrl.substringAfterLast("/")
    }

    val id = extractId(url)

    return PokemonLocalDataModel(
        name,
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/$id.png"
    )
}

val PokemonLocalDataModel.toDomainModel: Pokemon
    get() = Pokemon(
        name,
        imageUrl ?: "N/A"
    )

val Pokemon.toLocalDataModel: PokemonLocalDataModel
    get() = PokemonLocalDataModel(
        name,
        imageUrl
    )

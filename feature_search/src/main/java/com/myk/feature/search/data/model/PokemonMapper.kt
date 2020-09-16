package com.myk.feature.search.data.model

import com.myk.feature.search.domain.model.Pokemon

/**
 * Maps the Pokemon DTO to the domain model.
 */
fun PokemonRemoteDataModel.toDomainModel(): Pokemon {
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

    return Pokemon(
        name,
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/$id.png"
    )
}

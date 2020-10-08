package com.myk.feature.search.data.model

import com.myk.feature.search.domain.model.PokemonDomainModel
import com.myk.library.data.model.PokemonLocalDataModel

/**
 * Maps the Pokemon DTO to the domain model.
 */
val PokemonRemoteDataModel.toLocalDataModel: PokemonLocalDataModel
    get() {
        requireNotNull(name)
        /**
         * Very specific function that will try to parse an Id out of a string. Always assuming that the
         * string is a url of the form: "https://pokeapi.co/api/v2/pokemon/1/" where id is in the last
         * part of the url.
         */
        fun extractId(url: String?): String {
            // remove the last "/"
            val preppedUrl = url?.removeSuffix("/") ?: "1/"

            // gets the substring after the last (remaining) "/" which is presumably the id
            return preppedUrl.substringAfterLast("/")
        }

        return PokemonLocalDataModel(
            extractId(url).toInt(),
            name,
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/" +
                "generation-vii/ultra-sun-ultra-moon/${extractId(url)}.png"
        )
    }

val PokemonLocalDataModel.toDomainModel: PokemonDomainModel
    get() = PokemonDomainModel(
        name,
        imageUrl ?: "N/A"
    )

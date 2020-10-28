package com.myk.feature.search.data.model

import com.myk.feature.search.domain.model.PokemonDomainModel
import com.myk.library.data.model.PokemonLocalDataModel

/**
 * Maps the Pokemon DTO to the domain model.
 */
internal val PokemonRemoteDataModel.toLocalDataModel: PokemonLocalDataModel
    get() {
        requireNotNull(name) {
            "Name is null."
        }

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
            id = id ?: extractId(url).toInt(),
            name = name,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/" +
                "official-artwork/${id ?: extractId(url).toInt()}.png",
            types = types?.map {
                it.type.name
            }
        )
    }

internal val PokemonLocalDataModel.toDomainModel: PokemonDomainModel
    get() = PokemonDomainModel(
        id = id,
        name = name,
        imageUrl = imageUrl ?: "N/A",
        types = types ?: listOf()
    )

package com.myk.feature.search.data.model

import com.myk.feature.search.domain.model.ItemDomainModel
import com.myk.library.data.model.ItemLocalDataModel

/**
 * Maps the Item DTO to the domain model.
 */
val ItemRemoteDataModel.toLocalDataModel: ItemLocalDataModel
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
        return ItemLocalDataModel(
            extractId(url).toInt(),
            name,
            if (url == null) null
            else "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/$name.png"
        )
    }

val ItemLocalDataModel.toDomainModel: ItemDomainModel
    get() = ItemDomainModel(
        name,
        imageUrl ?: "N/A"
    )

package com.myk.feature.search.data.model

import com.myk.feature.search.domain.model.ItemDomainModel
import com.myk.library.data.model.ItemLocalDataModel

/**
 * Maps the Item DTO to the domain model.
 */
val ItemRemoteDataModel.toLocalDataModel: ItemLocalDataModel
    get() {
        requireNotNull(name)

        return ItemLocalDataModel(
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

package com.myk.feature.search.domain.model

import androidx.annotation.ColorRes
import com.myk.feature.search.R

data class PokemonDomainModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String> = listOf()
) {
    @ColorRes
    fun getColorForType(type: String?) = if (typeColorMap.containsKey(type)) {
        typeColorMap[type] ?: R.color.normal_type_color
    } else {
        R.color.normal_type_color
    }
}

/**
 * Maps string names for pokemon types to a color resource
 */
private val typeColorMap = mapOf(
    "fire" to R.color.fire_type_color,
    "water" to R.color.water_type_color,
    "grass" to R.color.grass_type_color,
    "electric" to R.color.electric_type_color,
    "normal" to R.color.normal_type_color,
    "fighting" to R.color.fighting_type_color,
    "flying" to R.color.flying_type_color,
    "poison" to R.color.poison_type_color,
    "rock" to R.color.rock_type_color,
    "ground" to R.color.ground_type_color,
    "bug" to R.color.bug_type_color,
    "ghost" to R.color.ghost_type_color,
    "steel" to R.color.steel_type_color,
    "psychic" to R.color.psychic_type_color,
    "ice" to R.color.ice_type_color,
    "dragon" to R.color.dragon_type_color,
    "dark" to R.color.dark_type_color,
    "fairy" to R.color.fairy_type_color,
)

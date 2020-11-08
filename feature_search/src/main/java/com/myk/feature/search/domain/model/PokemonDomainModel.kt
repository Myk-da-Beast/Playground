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
    fun getColorForType(type: String?) = when (type) {
        "fire" -> R.color.fire_type_color
        "water" -> R.color.water_type_color
        "grass" -> R.color.grass_type_color
        "electric" -> R.color.electric_type_color
        "normal" -> R.color.normal_type_color
        "fighting" -> R.color.fighting_type_color
        "flying" -> R.color.flying_type_color
        "poison" -> R.color.poison_type_color
        "rock" -> R.color.rock_type_color
        "ground" -> R.color.ground_type_color
        "bug" -> R.color.bug_type_color
        "ghost" -> R.color.ghost_type_color
        "steel" -> R.color.steel_type_color
        "psychic" -> R.color.psychic_type_color
        "ice" -> R.color.ice_type_color
        "dragon" -> R.color.dragon_type_color
        "dark" -> R.color.dark_type_color
        "fairy" -> R.color.fairy_type_color
        else -> R.color.normal_type_color
    }
}

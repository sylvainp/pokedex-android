package com.sylvainp.pokedex.utils

import androidx.compose.ui.graphics.Color

fun PKColorFromType(type: String): Color {
    return when (type) {
        "normal" -> Color(0xFFAAA67F)
        "fire" -> Color(0xFFF57D31)
        "water" -> Color(0xFF6493EB)
        "electric" -> Color(0xFFF9CF30)
        "grass" -> Color(0xFF74CB48)
        "ice" -> Color(0xFF9AD6DF)
        "fighting" -> Color(0xFFC12239)
        "poison" -> Color(0xFFA43E9E)
        "ground" -> Color(0xFFDEC16B)
        "flying" -> Color(0xFFA891EC)
        "psychic" -> Color(0xFFFB5584)
        "bug" -> Color(0xFFA7B723)
        "rock" -> Color(0xFFB69E31)
        "ghost" -> Color(0xFF70559B)
        "dragon" -> Color(0xFF7037FF)
        "dark" -> Color(0xFF75574C)
        "steel" -> Color(0xFFB7B9D0)
        "fairy" -> Color(0xFFE69EAC)
        else -> Color(0xFFAAA67F)
    }
}
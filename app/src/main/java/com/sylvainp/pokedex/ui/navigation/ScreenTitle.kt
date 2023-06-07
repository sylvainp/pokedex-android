package com.sylvainp.pokedex.ui.navigation

import android.content.Context
import com.sylvainp.pokedex.R

fun ScreenTitle(context: Context, route: String?): String {
    return when (route) {
        BottomNavItem.FindPokemon.screen_route -> context.getString(R.string.screen_title_find_pokemon)
        else -> context.getString(R.string.screen_title_my_pokemon)
    }
}
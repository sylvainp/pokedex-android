package com.sylvainp.pokedex.ui.navigation

import com.sylvainp.pokedex.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object FindPokemon : BottomNavItem("Find pokemon", R.drawable.pokeball, "find_pokemon")
    object Pokedex : BottomNavItem("Pokedex", R.drawable.baseline_menu_book_24, "pokedex")


}

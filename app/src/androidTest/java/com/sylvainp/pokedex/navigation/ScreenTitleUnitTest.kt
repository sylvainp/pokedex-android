package com.sylvainp.pokedex.navigation

import androidx.test.platform.app.InstrumentationRegistry
import com.sylvainp.pokedex.R
import com.sylvainp.pokedex.ui.navigation.BottomNavItem
import com.sylvainp.pokedex.ui.navigation.ScreenTitle
import org.junit.Assert.*
import org.junit.Test

class ScreenTitleUnitTest {

    @Test
    fun  ScreenTitleForRouteFindPokemonMustBeOk(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val expectedTitle = appContext.getString(R.string.screen_title_find_pokemon);
        val title = ScreenTitle(appContext, BottomNavItem.FindPokemon.screen_route)
        assertEquals(expectedTitle, title);
    }

    @Test
    fun  ScreenTitleForRouteMyPokemonMustBeOk(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val expectedTitle = appContext.getString(R.string.screen_title_my_pokemon);
        val title = ScreenTitle(appContext, BottomNavItem.Pokedex.screen_route)
        assertEquals(expectedTitle, title);
    }

    @Test
    fun ScreenTitleForNullRouteMustBeOk(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val expectedTitle = appContext.getString(R.string.screen_title_my_pokemon);
        val title = ScreenTitle(appContext, null)
        assertEquals(expectedTitle, title);
    }
}
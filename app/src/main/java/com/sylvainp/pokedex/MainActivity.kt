package com.sylvainp.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.ui.pokedexScreen.PokedexScreen
import com.sylvainp.pokedex.ui.pokedexScreen.PokedexViewmodel
import com.sylvainp.pokedex.ui.pokemonDetailScreen.PokemonDetailScreen
import com.sylvainp.pokedex.ui.pokemonDetailScreen.PokemonDetailViewmodel
import com.sylvainp.pokedex.ui.theme.primary
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                color = Color.Yellow,
                contentColor = primary
            ) {
                MainScreenView();
            }
        }
    }
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    NavigationView(navController)
}

@Composable
fun NavigationView(navController: NavHostController) {
    NavHost(navController, startDestination = "pokedex") {
        composable("pokedex") {
            val viewModel = hiltViewModel<PokedexViewmodel>()
            PokedexScreen(navController, viewModel)
        }
        composable(
            "pokedex/{pokemonType}/{pokemonId}/{pokemonName}/{pokemonImage}", arguments = listOf(
                navArgument("pokemonType") { type = NavType.StringType },
                navArgument("pokemonId") { type = NavType.StringType },
                navArgument("pokemonName") { type = NavType.StringType },
                navArgument("pokemonImage") { type = NavType.StringType },
            )
        ) {
            val viewModel = hiltViewModel<PokemonDetailViewmodel>()
            val pokemonType = it.arguments?.getString("pokemonType")
            val pokemonId = it.arguments?.getString("pokemonId")
            val pokemonName = it.arguments?.getString("pokemonName")
            val pokemonImage = URLDecoder.decode(it.arguments?.getString("pokemonImage"), "UTF-8");
            PokemonDetailScreen(
                navController,
                viewModel,
                PokemonPreviewEntity(
                    id = pokemonId!!,
                    name = pokemonName!!,
                    image = pokemonImage,
                    mainType = pokemonType
                )
            )
        }

    }
}
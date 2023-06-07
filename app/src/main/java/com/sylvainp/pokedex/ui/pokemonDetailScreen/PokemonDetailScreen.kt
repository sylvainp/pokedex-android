package com.sylvainp.pokedex.ui.pokemonDetailScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sylvainp.pokedex.domain.entities.PokemonEntity
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.ui.components.PKBaseStat
import com.sylvainp.pokedex.ui.components.PKPokemonHeight
import com.sylvainp.pokedex.ui.components.PKPokemonType
import com.sylvainp.pokedex.ui.components.PKPokemonWeight
import com.sylvainp.pokedex.ui.components.PKTopbarDetailPokemon
import com.sylvainp.pokedex.ui.theme.PKTypography
import com.sylvainp.pokedex.ui.theme.light
import com.sylvainp.pokedex.ui.theme.medium
import com.sylvainp.pokedex.ui.theme.topbbarBackground
import com.sylvainp.pokedex.ui.theme.white
import com.sylvainp.pokedex.utils.PKColorFromType


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    navHostController: NavHostController,
    viewmodel: PokemonDetailViewmodel,
    pokemonPreview: PokemonPreviewEntity
) {
    val mainColor = if (pokemonPreview.mainType !== null) {
        PKColorFromType(pokemonPreview.mainType)
    } else {
        topbbarBackground
    }
    LaunchedEffect(
        key1 = pokemonPreview,
        block = { viewmodel.loadPokemon(pokemonId = pokemonPreview.id) })
    Scaffold(
        containerColor = mainColor,
        topBar = {
            PKTopbarDetailPokemon(pokemon = pokemonPreview)
            { navHostController.navigateUp() }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp, it.calculateTopPadding(), 4.dp, 4.dp)
                .background(
                    white,
                    shape = RoundedCornerShape(0.dp, 0.dp, 4.dp, 4.dp)
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (viewmodel.uiState.pokemon !== null) {
                    val pokemon = viewmodel.uiState.pokemon!!;
                    PokemonType(pokemon = pokemon)
                    Subtitle(text = "About", textColor = mainColor)
                    PokemonMeasurement(pokemon = pokemon)
                    Subtitle(text = "Base Stats", textColor = mainColor)
                    PokemonStats(pokemon = pokemon, mainColor = mainColor)
                }
            }
        }

    }
}

@Composable
fun Subtitle(text: String, textColor: Color) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = text,
        color = textColor,
        style = PKTypography.titleSmall,
        textAlign = TextAlign.Center
    )
}

@Composable
fun PokemonType(pokemon: PokemonEntity) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        pokemon.type.map { PKPokemonType(type = it) }
    }
}

@Composable
fun PokemonMeasurement(pokemon: PokemonEntity) {
    Row(modifier = Modifier.height(60.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        PKPokemonWeight(pokemonEntity = pokemon)
        Divider(
            thickness = 1.dp, color = light, modifier = Modifier
                .fillMaxHeight()  //fill the max height
                .width(1.dp)
                .padding(0.dp, 16.dp)
        )
        PKPokemonHeight(pokemonEntity = pokemon)
    }
}

@Composable
fun PokemonStats(pokemon: PokemonEntity, mainColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp)
    ) {
        pokemon.basesStats.map { PKBaseStat(stat = it, mainColor) }

    }
}
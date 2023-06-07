package com.sylvainp.pokedex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sylvainp.pokedex.R
import com.sylvainp.pokedex.domain.entities.PokemonEntity
import com.sylvainp.pokedex.ui.theme.PKTypography
import com.sylvainp.pokedex.ui.theme.dark
import com.sylvainp.pokedex.ui.theme.light
import com.sylvainp.pokedex.ui.theme.medium

@Composable
fun PKPokemonWeight(pokemonEntity: PokemonEntity) {
    PKPokemonMeasurement(pokemonEntity, PokemonMeasurement.WEIGHT)
}

@Composable
fun PKPokemonHeight(pokemonEntity: PokemonEntity) {
    PKPokemonMeasurement(pokemonEntity, PokemonMeasurement.HEIGHT)
}

private enum class PokemonMeasurement(val measurement: Int) {
    HEIGHT(1), WEIGHT(2)
}

@Composable
private fun PKPokemonMeasurement(pokemonEntity: PokemonEntity, measurement: PokemonMeasurement) {

    val icon = when (measurement) {
        PokemonMeasurement.HEIGHT ->
            Pair(painterResource(id = R.drawable.ic_straighten), "height")

        else ->
            Pair(painterResource(id = R.drawable.ic_weight), "weight")

    }
    val label = when (measurement) {
        PokemonMeasurement.HEIGHT -> "${pokemonEntity.height} m"
        else -> "${pokemonEntity.weight} kg"
    }
    val title = when (measurement) {
        PokemonMeasurement.HEIGHT -> "Height"
        else -> "Weight"
    }

    Column(modifier = Modifier.padding(8.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            Icon(
                painter = icon.first,
                contentDescription = icon.second,
                tint = dark
            )
            Text(text = label, color = dark, style = PKTypography.labelLarge)
        }
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = title,
            color = medium,
            style = PKTypography.labelSmall
        )
    }
}
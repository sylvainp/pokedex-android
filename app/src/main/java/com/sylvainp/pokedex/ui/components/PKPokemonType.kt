package com.sylvainp.pokedex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sylvainp.pokedex.ui.theme.PKTypography
import com.sylvainp.pokedex.ui.theme.white
import com.sylvainp.pokedex.utils.PKColorFromType

@Composable
fun PKPokemonType(type: String) {
    Text(
        modifier = Modifier
            .background(
                PKColorFromType(type),
                shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp)
            )
            .padding(12.dp, 4.dp),
        text = type,
        color = white,
        style = PKTypography.labelLarge,

        )
}
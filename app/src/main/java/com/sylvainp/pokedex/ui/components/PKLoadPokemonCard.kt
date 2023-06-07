package com.sylvainp.pokedex.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sylvainp.pokedex.R

@Composable
fun PKLoadPokemonCard(onButtonClicked: () -> Unit, modifier: Modifier = Modifier) {
    val mainButtonColor = ButtonDefaults.buttonColors(
        containerColor = Color(156, 177, 144, 255),
    )
    Button(
        onClick = { onButtonClicked() }, modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(16.dp),
        colors = mainButtonColor
    ) {
        Image(

                    painter = painterResource (id = R.drawable.gameiconsrollingdices),
            contentDescription = "load pokemon"
        )
    }
}
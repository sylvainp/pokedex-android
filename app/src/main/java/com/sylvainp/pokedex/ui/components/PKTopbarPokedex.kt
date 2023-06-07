package com.sylvainp.pokedex.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.sylvainp.pokedex.R
import com.sylvainp.pokedex.ui.theme.primary
import com.sylvainp.pokedex.ui.theme.white

@Composable
fun PKTopbarPokedex(title: String) {
    Row(
        modifier = Modifier
            .background(primary)
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.pokeball),
            contentDescription = null,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = white
        )
    }
}
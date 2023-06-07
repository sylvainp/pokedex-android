package com.sylvainp.pokedex.ui.components

import android.widget.ProgressBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sylvainp.pokedex.domain.entities.PokemonStatEntity
import com.sylvainp.pokedex.ui.theme.PKTypography
import com.sylvainp.pokedex.ui.theme.dark
import com.sylvainp.pokedex.ui.theme.light
import com.sylvainp.pokedex.ui.theme.medium

@Composable
fun PKBaseStat(stat: PokemonStatEntity, tintColor: Color = medium) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(4.dp, 0.dp, 0.dp, 0.dp)
                .weight(2f),
            text = stat.name,
            color = tintColor,
            style = PKTypography.labelLarge,
            textAlign = TextAlign.Right
        )
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = light
            )
        }

        Text(
            modifier = Modifier
                .weight(1f),
            text = "${stat.value}",
            color = dark,
            style = PKTypography.labelMedium,
            textAlign = TextAlign.Center
        )
        LinearProgressIndicator(
            color = tintColor,
            trackColor = light,
            progress = (stat.value / 100f).coerceAtMost(1f),
            modifier = Modifier.weight(8f)
        )
    }
}
package com.sylvainp.pokedex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sylvainp.pokedex.R
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.ui.theme.PKTypography
import com.sylvainp.pokedex.ui.theme.topbbarBackground
import com.sylvainp.pokedex.ui.theme.white
import com.sylvainp.pokedex.utils.PKColorFromType

@Composable
fun PKTopbarDetailPokemon(pokemon: PokemonPreviewEntity, onBackButtonClicked: () -> Unit) {
    val mainColor = if (pokemon.mainType !== null) {
        PKColorFromType(pokemon.mainType)
    } else {
        topbbarBackground
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .background(mainColor)
    .padding(4.dp, 0.dp)
    ) {

        val (topbar, bottomShape, pokemonImage) = createRefs()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .padding(16.dp)
                .paint(
                    painterResource(id = R.drawable.pokeball_large),
                    contentScale = ContentScale.FillHeight, alignment = Alignment.CenterEnd
                )
                .constrainAs(topbar) { top.linkTo(parent.top) },

            verticalAlignment = Alignment.Top
        ) {
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = onBackButtonClicked
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = white
                )
            }
            Text(
                text = pokemon.name,
                modifier = Modifier.weight(6f),
                style = PKTypography.titleLarge,
                color = white
            )
            Text(
                text = "#${pokemon.id}",
                modifier = Modifier.weight(2f),
                textAlign = TextAlign.Right,
                style = PKTypography.titleMedium,
                color = white
            )
        }
        Box(
            modifier = Modifier
                .background(white, shape = RoundedCornerShape(4.dp, 4.dp, 0.dp, 0.dp))
                .fillMaxWidth()
                .padding(4.dp, 0.dp)
                .fillMaxHeight(0.3f)
                .constrainAs(bottomShape) {
                    bottom.linkTo(parent.bottom);
                    centerHorizontallyTo(parent)
                }


        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(pokemon.image)
                .crossfade(true).build(),
            placeholder = painterResource(R.drawable.pokemon_placeholder),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .constrainAs(pokemonImage) {
                    centerHorizontallyTo(parent)
                    bottom.linkTo(bottomShape.bottom)
                }
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f)
        )
    }
}
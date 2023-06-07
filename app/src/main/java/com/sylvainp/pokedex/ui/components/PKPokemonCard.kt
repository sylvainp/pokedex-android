package com.sylvainp.pokedex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sylvainp.pokedex.R
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.ui.theme.PKTypography
import com.sylvainp.pokedex.ui.theme.dark
import com.sylvainp.pokedex.ui.theme.light
import com.sylvainp.pokedex.ui.theme.medium
import com.sylvainp.pokedex.ui.theme.white

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PKPokemonCard(
    pokemon: PokemonPreviewEntity, onButtonClicked: () -> Unit, modifier: Modifier = Modifier
) {

    val mainButtonColor = ButtonDefaults.buttonColors(
        containerColor = white
    )

    Button(
        onClick = { onButtonClicked() },
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(16.dp),
        colors = mainButtonColor,
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 5.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 3.dp,
            focusedElevation = 3.dp
        )


    ) {
        // val columnModifier: Modifier = Modifier.background(PKColorFromType(pokemon.types.first()));
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (pokemonName, pokemonId, pokemonImage) = createRefs();
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth()
                    .background(color = light, shape = RoundedCornerShape(8.dp))
                    .constrainAs(pokemonName) { bottom.linkTo(parent.bottom, margin = 0.dp) },
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth().padding(4.dp),
                    text = pokemon.name,
                    style = PKTypography.labelMedium,
                    color = dark,
                    textAlign = TextAlign.Center
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(white)
                    .padding(8.dp)
                    .constrainAs(pokemonId) {
                        absoluteRight.linkTo(
                            parent.absoluteRight,
                            margin = 0.dp
                        )
                    },
                text = "#${pokemon.id}",
                style = PKTypography.labelSmall,
                color = medium,
                textAlign = TextAlign.End
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(pokemon.image)
                    .crossfade(true).build(),
                placeholder = painterResource(R.drawable.pokemon_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize(0.6f)
                    .constrainAs(pokemonImage) {
                        centerHorizontallyTo(parent)
                        centerVerticallyTo(parent)
                    }
            )

        }
    }
}

@Preview(device = "spec:width=1080px,height=2340px,dpi=440", showBackground = false)
@Composable
fun PokemonCardPreview() {
    val pokemon = PokemonPreviewEntity(
        "1",
        "Pikachu",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/25.svg",
    )
    PKPokemonCard(
        pokemon = pokemon, onButtonClicked = { println("clicked") }, Modifier.padding(16.dp)
    )
}
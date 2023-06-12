package com.sylvainp.pokedex.ui.pokedexScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.sylvainp.pokedex.ui.components.PKPokemonCard
import com.sylvainp.pokedex.ui.components.PKTopbarPokedex
import com.sylvainp.pokedex.ui.theme.medium
import com.sylvainp.pokedex.ui.theme.primary
import com.sylvainp.pokedex.ui.theme.white
import kotlinx.coroutines.Dispatchers
import java.net.URLEncoder

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PokedexScreen(
    navHostController: NavHostController,
    viewmodel: PokedexViewmodel
) {

    val pokemons = viewmodel.loadPokemons().collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            PKTopbarPokedex(title = "Pokedex")
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) { paddingsValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingsValues)
                .background(primary)
                .padding(4.dp, 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Column(
                modifier = Modifier
                    .background(white, shape = RoundedCornerShape(8.dp))
                    .fillMaxSize(),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                when (pokemons.loadState.refresh) {
                    is LoadState.Loading -> CircularProgressIndicator(
                        color = medium,
                        modifier = Modifier
                            .padding(16.dp)
                            .width(24.dp)
                            .height(24.dp)
                    )

                    is LoadState.NotLoading -> {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            content = {
                                items(pokemons.itemCount) { index ->
                                    val pokemonPreviewEntity = pokemons[index];
                                    PKPokemonCard(
                                        pokemon = pokemonPreviewEntity!!,
                                        onButtonClicked = {
                                            navHostController.navigate(
                                                "pokedex/${pokemonPreviewEntity.mainType}/${pokemonPreviewEntity.id}/${pokemonPreviewEntity.name}/${
                                                    URLEncoder.encode(
                                                        pokemonPreviewEntity.image,
                                                        "UTF-8"
                                                    )
                                                }"
                                            )
                                        },
                                        modifier = Modifier.padding(4.dp)
                                    )
                                }
                            },
                            modifier = Modifier
                                .background(white, shape = RoundedCornerShape(8.dp))
                                .padding(8.dp, 8.dp)
                                .weight(1f)

                        )
                        if (pokemons.loadState.append == LoadState.Loading) {
                            CircularProgressIndicator(
                                color = medium,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .width(24.dp)
                                    .height(24.dp)
                            )
                        }
                    }

                    else -> {
                        println("Aïe !")
                    }
                }
            }
        }
    }

}
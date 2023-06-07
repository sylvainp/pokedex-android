package com.sylvainp.pokedex.ui.pokedexScreen

import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity

data class PokedexUiState(
    val loading: Boolean = false,
    val error: String?,
    val data: MutableList<PokemonPreviewEntity>?,
    val loadingNext: Boolean
);

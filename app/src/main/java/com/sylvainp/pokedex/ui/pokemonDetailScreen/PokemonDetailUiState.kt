package com.sylvainp.pokedex.ui.pokemonDetailScreen

import com.sylvainp.pokedex.domain.entities.PokemonEntity
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity

data class PokemonDetailUiState(
    val loading: Boolean,
    val pokemon: PokemonEntity?,
    val error: String?
)

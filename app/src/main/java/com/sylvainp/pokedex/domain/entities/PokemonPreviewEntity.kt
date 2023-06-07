package com.sylvainp.pokedex.domain.entities

data class PokemonPreviewEntity(
    val id: String,
    val name: String,
    val mainType: String? = null,
    val image: String? = null,
    ) {
}

package com.sylvainp.pokedex.domain.entities

data class PokemonEntity(
    val id: String,
    val name: String,
    val image: String,
    val weight: String,
    val height: String,
    val type: List<String>,
    val basesStats: List<PokemonStatEntity>
)

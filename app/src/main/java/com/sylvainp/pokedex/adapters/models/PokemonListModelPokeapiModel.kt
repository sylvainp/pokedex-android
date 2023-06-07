package com.sylvainp.pokedex.adapters.models

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListModelPokeapiModel(val results: List<PokemonListResourcePokeapiModel>);

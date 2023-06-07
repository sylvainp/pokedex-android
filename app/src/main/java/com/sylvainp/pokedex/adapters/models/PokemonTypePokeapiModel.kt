package com.sylvainp.pokedex.adapters.models

import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypePokeapiModel(val slot:Int, val type:TypePokeapiModel)

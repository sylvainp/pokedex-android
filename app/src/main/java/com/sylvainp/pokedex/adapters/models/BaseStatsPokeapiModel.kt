package com.sylvainp.pokedex.adapters.models

import kotlinx.serialization.Serializable

@Serializable
data class BaseStatsPokeapiModel(val name: String, val url: String? = null)

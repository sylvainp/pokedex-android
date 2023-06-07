package com.sylvainp.pokedex.adapters.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpritesPokeapiModel(
    @SerialName("other") val otherSprites: OtherSpritesPokeapiModel,
    @SerialName("front_default") val defaultImage: String
)

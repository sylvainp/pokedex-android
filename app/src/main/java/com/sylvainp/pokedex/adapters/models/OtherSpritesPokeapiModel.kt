package com.sylvainp.pokedex.adapters.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class OtherSpritesPokeapiModel(
    @SerialName("dream_world") val dreamWorld: DreamWordPokeapiModel? = null,
    @SerialName("official-artwork") val officialArtwork: OfficialArtWorkPokeapiModel? = null
) {
}
package com.sylvainp.pokedex.adapters.models

import com.sylvainp.pokedex.domain.entities.PokemonEntity
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import kotlinx.serialization.Serializable

@Serializable
data class PokemonPokeapiModel(
    val id: Int,
    val name: String,
    val sprites: SpritesPokeapiModel,
    val types: List<PokemonTypePokeapiModel>? = null,
    val weight: Int,
    val height: Int,
    val stats: List<ListBaseStatsPokeapiModel>
) {

    fun toPokemonPreviewEntityDomain(): PokemonPreviewEntity {
        return PokemonPreviewEntity(
            id = "$id",
            name = name,
            image = getImageToUse(),
            mainType = types?.first()?.type?.name
        )
    }

    private fun getImageToUse(): String {
        return if (sprites.otherSprites.dreamWorld?.front_default !== null) {
            sprites.otherSprites.dreamWorld.front_default
        } else if (sprites.otherSprites.officialArtwork?.front_default !== null) {
            sprites.otherSprites.officialArtwork.front_default
        } else {
            sprites.defaultImage
        }
    }

    fun toPokemonEntityDomain(): PokemonEntity {
        return PokemonEntity(
            id = "$id",
            name = name,
            image = getImageToUse(),
            weight = "${weight / 10f}",
            height = "${height / 10f}",
            type = types!!.map { it.type.name },
            basesStats = stats.map { it.toPokemonStatEntity() })
    }
}
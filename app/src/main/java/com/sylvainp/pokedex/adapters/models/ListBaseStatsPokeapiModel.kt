package com.sylvainp.pokedex.adapters.models

import com.sylvainp.pokedex.domain.entities.PokemonStatEntity
import kotlinx.serialization.Serializable

@Serializable
data class ListBaseStatsPokeapiModel(
    val base_stat: Int,
    val effort: Int,
    val stat: BaseStatsPokeapiModel
) {

    fun toPokemonStatEntity(): PokemonStatEntity {
        return PokemonStatEntity(shortName(), base_stat)
    }

    private fun shortName(): String {
        return when (stat.name) {
            "hp" -> "HP"
            "attack" -> "ATK"
            "defense" -> "DEF"
            "special-attack" -> "SATK"
            "special-defense" -> "SDEF"
            "speed" -> "SPD"
            else -> "UNK"
        }
    }
}
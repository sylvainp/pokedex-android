package com.sylvainp.pokedex.domain.entities

import org.junit.Assert.*
import org.junit.Test

class PokemonEntityUnitTest {
    @Test
    fun `pokemonEntity is ok`(){
        val pokemon = PokemonPreviewEntity("1", "Pikachu");
        assertEquals(pokemon.id,"1");
        assertEquals(pokemon.name, "Pikachu")
    }
}
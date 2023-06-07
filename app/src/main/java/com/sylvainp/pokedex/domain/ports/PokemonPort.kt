package com.sylvainp.pokedex.domain.ports

import com.sylvainp.pokedex.domain.entities.PokemonEntity
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import kotlin.jvm.Throws

interface PokemonPort {
    @Throws(Exception::class)
    suspend fun getPokemonFromId(id:String): PokemonEntity?;

    @Throws(Exception::class)
    suspend fun getPokemonList(pageIndex: Int):List<PokemonPreviewEntity>;
}
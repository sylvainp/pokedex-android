package com.sylvainp.pokedex.domain.ports

import androidx.paging.PagingData
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import kotlinx.coroutines.flow.Flow

interface PokemonPreviewPort {
    fun getAllPokemonPreview(): Flow<PagingData<PokemonPreviewEntity>>
}
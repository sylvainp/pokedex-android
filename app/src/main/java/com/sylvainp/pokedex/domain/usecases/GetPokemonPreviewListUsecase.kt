package com.sylvainp.pokedex.domain.usecases

import androidx.paging.PagingData
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.domain.ports.PokemonPreviewPort
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonPreviewListUsecase @Inject constructor(private val pokemonPreviewPort: PokemonPreviewPort) {

    fun execute(): Flow<PagingData<PokemonPreviewEntity>> {
        return pokemonPreviewPort.getAllPokemonPreview()
    }
}
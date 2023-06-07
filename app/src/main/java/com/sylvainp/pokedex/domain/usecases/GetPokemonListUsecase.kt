package com.sylvainp.pokedex.domain.usecases

import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.domain.ports.PokemonPort
import java.lang.Exception
import javax.inject.Inject

class GetPokemonListUsecase @Inject constructor(private val pokemonPort: PokemonPort) :
    Usecase<Int, List<PokemonPreviewEntity>?>() {
    override suspend fun execute(params: Int?): UsecaseResponse<List<PokemonPreviewEntity>?> {
        if (params == null) {
            return UsecaseResponse(data = null, error = "No page index provided")
        }
        return try {
            val response = pokemonPort.getPokemonList(params);
            UsecaseResponse(data = response, error = null)

        } catch (error: Exception) {
            error.printStackTrace();
            UsecaseResponse(error = "Unable to get pokemon list", data = null)
        }
    }
}
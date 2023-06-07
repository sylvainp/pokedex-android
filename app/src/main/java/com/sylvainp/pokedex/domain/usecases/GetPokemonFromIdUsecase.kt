package com.sylvainp.pokedex.domain.usecases

import com.sylvainp.pokedex.domain.entities.PokemonEntity
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.domain.ports.PokemonPort
import java.lang.Exception
import javax.inject.Inject

class GetPokemonFromIdUsecase @Inject constructor(private val pokemonAdapter: PokemonPort) :
    Usecase<String, PokemonEntity?>() {
    override suspend fun execute(params: String?): UsecaseResponse<PokemonEntity?> {
        if (params == null) {
            return UsecaseResponse(error = "No pokemon id provided", data = null);
        }
        return try {
            val response: PokemonEntity? = pokemonAdapter.getPokemonFromId(params)
            UsecaseResponse(data = response, error = null);
        } catch (error: Exception) {
            error.printStackTrace()
            UsecaseResponse(error = "Cannot get pokemon with id $params", data = null);
        }

    }
}
package com.sylvainp.pokedex.adapters

import com.sylvainp.pokedex.adapters.models.PokemonListModelPokeapiModel
import com.sylvainp.pokedex.adapters.models.PokemonPokeapiModel
import com.sylvainp.pokedex.domain.entities.PokemonEntity
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.domain.ports.PokemonPort
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class PokeapiPokemonAdapter @Inject constructor() : PokemonPort {
    private val baseURL = "https://pokeapi.co/api/v2/"
    private val clientHttp: OkHttpClient = OkHttpClient();
    private val format = Json { ignoreUnknownKeys = true }

    private val recordPerPage = 20;

    override suspend fun getPokemonFromId(id: String): PokemonEntity? {
        val url = "${baseURL}/pokemon/${id}"

        val request = Request.Builder().url(url).build()
        val response = clientHttp.newCall(request).execute()
        if (response.code != 200) {
            throw Exception("Une erreur est survenue lors de la récupération des données")
        }
        val responseString = response.body?.string()
        return if (responseString !== null) {
            format.decodeFromString<PokemonPokeapiModel>(responseString).toPokemonEntityDomain();
        } else null
    }

    override suspend fun getPokemonList(pageIndex: Int): List<PokemonPreviewEntity> {
        val url = "${baseURL}/pokemon?limit=${recordPerPage}&offset=${pageIndex * recordPerPage}"
        val getAllPokemonRequest = Request.Builder().url(url).build();
        val getAllPokemonResponse = clientHttp.newCall(getAllPokemonRequest).execute()
        if (getAllPokemonResponse.code != 200) {
            throw Exception("Une erreur est survenue lors de la récupération des données")
        }
        val getAllPokemonResponseString = getAllPokemonResponse.body?.string()
        if (getAllPokemonResponseString != null) {
            val allPokemonResource: PokemonListModelPokeapiModel =
                format.decodeFromString<PokemonListModelPokeapiModel>(getAllPokemonResponseString);
            return allPokemonResource.results.mapNotNull {
                val pokemonRequest = Request.Builder().url(it.url).build();
                val pokemonResponse = clientHttp.newCall(pokemonRequest).execute();
                if (pokemonResponse.code == 200) {
                    val pokemonResponseString = pokemonResponse.body?.string()
                    if (pokemonResponseString !== null) {
                        format.decodeFromString<PokemonPokeapiModel>(pokemonResponseString)
                            .toPokemonPreviewEntityDomain()
                    } else {
                        null
                    }
                } else {
                    null
                }

            }

        }
        return emptyList()
    }
}
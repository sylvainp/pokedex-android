package com.sylvainp.pokedex.adapters

import com.sylvainp.pokedex.adapters.models.PokemonListModelPokeapiModel
import com.sylvainp.pokedex.adapters.models.PokemonPokeapiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class PokeapiClient {
    private val format = Json { ignoreUnknownKeys = true }

    private val RECORD_PER_PAGE = 20
    private val baseURL = "https://pokeapi.co/api/v2/"
    private val clientHttp: OkHttpClient = OkHttpClient();

    fun getPokemonFromId(id: String): Response {
        val url = "${baseURL}/pokemon/${id}"

        val request = Request.Builder().url(url).build()
        return clientHttp.newCall(request).execute()
    }

    fun getPokemonFromUrl(url: String): Response {
        val pokemonRequest = Request.Builder().url(url).build();
        return clientHttp.newCall(pokemonRequest).execute();
    }

    fun getPokemonsList(pageIndex: Int) {

        val url =
            "${baseURL}/pokemon?limit=${RECORD_PER_PAGE}&offset=${pageIndex * RECORD_PER_PAGE}"
        val getAllPokemonRequest = Request.Builder().url(url).build();
        val getAllPokemonResponse = clientHttp.newCall(getAllPokemonRequest).execute()
        if (getAllPokemonResponse.code != 200) {
            throw Exception("Une erreur est survenue lors de la récupération des données")
        }
        val getAllPokemonResponseString = getAllPokemonResponse.body?.string()
        if (getAllPokemonResponseString != null) {
            val allPokemonResource: PokemonListModelPokeapiModel =
                format.decodeFromString<PokemonListModelPokeapiModel>(
                    getAllPokemonResponseString
                );
            allPokemonResource.results.mapNotNull {
                val pokemonResponse = getPokemonFromUrl(it.url);
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
        } else {
            emptyList()
        }

    }
}
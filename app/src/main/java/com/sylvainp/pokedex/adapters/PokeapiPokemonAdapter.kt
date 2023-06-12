package com.sylvainp.pokedex.adapters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sylvainp.pokedex.adapters.models.PokemonListModelPokeapiModel
import com.sylvainp.pokedex.adapters.models.PokemonPokeapiModel
import com.sylvainp.pokedex.domain.entities.PokemonEntity
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.domain.ports.PokemonPort
import com.sylvainp.pokedex.domain.ports.PokemonPreviewPort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class PokeapiPokemonAdapter @Inject constructor() : PokemonPort, PokemonPreviewPort,
    PagingSource<Int, PokemonPreviewEntity>() {
    private val baseURL = "https://pokeapi.co/api/v2/"
    private val clientHttp: OkHttpClient = OkHttpClient();
    private val format = Json { ignoreUnknownKeys = true }

    private val RECORD_PER_PAGE = 20

    private val pager =
        Pager(
            initialKey = 0,
            config = PagingConfig(pageSize = RECORD_PER_PAGE, prefetchDistance = 5),
            pagingSourceFactory = { PokeapiPokemonAdapter() })

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

    private fun getAllPokemon(pageIndex: Int): List<PokemonPreviewEntity> {
        val url =
            "${baseURL}/pokemon?limit=${RECORD_PER_PAGE}&offset=${pageIndex * RECORD_PER_PAGE}"
        val getAllPokemonRequest = Request.Builder().url(url).build();
        val getAllPokemonResponse = clientHttp.newCall(getAllPokemonRequest).execute()
        if (getAllPokemonResponse.code != 200) {
            throw Exception("Une erreur est survenue lors de la récupération des données")
        }
        val getAllPokemonResponseString = getAllPokemonResponse.body?.string()
        return if (getAllPokemonResponseString != null) {
            val allPokemonResource: PokemonListModelPokeapiModel =
                format.decodeFromString<PokemonListModelPokeapiModel>(
                    getAllPokemonResponseString
                )
            allPokemonResource.results.mapNotNull {
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
        } else {
            emptyList()
        }
    }

    override fun getAllPokemonPreview(): Flow<PagingData<PokemonPreviewEntity>> {
        return pager.flow;
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonPreviewEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonPreviewEntity> {
        return try {
            val page = params.key ?: 0

            val response = withContext(Dispatchers.IO) {
                getAllPokemon(page)
            }

            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}
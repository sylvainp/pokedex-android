package com.sylvainp.pokedex.ui.pokemonDetailScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvainp.pokedex.domain.entities.PokemonEntity
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.domain.usecases.GetPokemonFromIdUsecase
import com.sylvainp.pokedex.domain.usecases.UsecaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewmodel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(
        PokemonDetailUiState(
            loading = false,
            pokemon = null,
            error = null
        )
    )
        private set

    @Inject
    lateinit var getPokemonFromIdUsecase: GetPokemonFromIdUsecase;

    var pokemonJob: Job? = null;

    fun loadPokemon(pokemonId: String) {
        uiState = uiState.copy(loading = true)
        pokemonJob?.cancel()
        pokemonJob = viewModelScope.launch(Dispatchers.IO) {
            val result: UsecaseResponse<PokemonEntity?> = getPokemonFromIdUsecase.execute(pokemonId)
            uiState = uiState.copy(loading = false, error = result.error, pokemon = result.data)
        }
    }
}
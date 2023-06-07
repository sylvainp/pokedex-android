package com.sylvainp.pokedex.ui.pokedexScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.domain.usecases.GetPokemonListUsecase
import com.sylvainp.pokedex.domain.usecases.UsecaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewmodel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var getPokemonListUsecase: GetPokemonListUsecase;
    var uiState by mutableStateOf(
        PokedexUiState(false, null, data = null, loadingNext = false)
    )
        private set

    private var pageIndex: Int = 0;
    private var loadPokemonJob: Job? = null;

    fun loadPokemons() {
        // TODO:STUFF
        uiState = uiState.copy(
            loading = pageIndex == 0,
            error = null,
            loadingNext = pageIndex > 0
        )
        loadPokemonJob?.cancel();
        loadPokemonJob = viewModelScope.launch(Dispatchers.IO) {
            val result: UsecaseResponse<List<PokemonPreviewEntity>?> =
                getPokemonListUsecase.execute(pageIndex)
            uiState = if (result.data != null) {
                val newPokemonList: MutableList<PokemonPreviewEntity> = if (uiState.data !== null) {
                    uiState.data!!
                } else {
                    mutableListOf()
                };
                newPokemonList.addAll(result.data);
                uiState.copy(
                    loading = false,
                    error = null,
                    data = newPokemonList,
                    loadingNext = false
                )
            } else {
                uiState.copy(
                    loading = false,
                    error = result.error,
                    data = null,
                    loadingNext = false
                )
            }
            pageIndex++
        }

    }
}
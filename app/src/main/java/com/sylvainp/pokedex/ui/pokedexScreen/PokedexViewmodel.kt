package com.sylvainp.pokedex.ui.pokedexScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.domain.ports.PokemonPort
import com.sylvainp.pokedex.domain.usecases.GetPokemonPreviewListUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokedexViewmodel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var getPokemonListUsecase: GetPokemonPreviewListUsecase;


    fun loadPokemons(): Flow<PagingData<PokemonPreviewEntity>> = getPokemonListUsecase.execute();

}
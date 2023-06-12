package com.sylvainp.pokedex.di

import com.sylvainp.pokedex.adapters.PokeapiPokemonAdapter
import com.sylvainp.pokedex.domain.ports.PokemonPort
import com.sylvainp.pokedex.domain.ports.PokemonPreviewPort
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonModule {

    @Singleton
    @Binds
    abstract fun bindPokemonPort(pokemonAdapter: PokeapiPokemonAdapter): PokemonPort;

    @Singleton
    @Binds
    abstract fun bindPokemonPreviewPort(pokemonAdapter: PokeapiPokemonAdapter): PokemonPreviewPort;
}
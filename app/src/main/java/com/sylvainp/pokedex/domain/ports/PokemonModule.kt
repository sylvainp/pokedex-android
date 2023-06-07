package com.sylvainp.pokedex.domain.ports

import com.sylvainp.pokedex.adapters.PokeapiPokemonAdapter
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
}
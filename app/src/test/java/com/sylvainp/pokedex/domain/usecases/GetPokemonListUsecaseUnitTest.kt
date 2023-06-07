package com.sylvainp.pokedex.domain.usecases

import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.domain.ports.PokemonPort
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class GetPokemonListUsecaseUnitTest {

    @Test
    fun `execute usecase must call port getPokemonList function`() {
        val pokemonAdapterMock = mock<PokemonPort> {
            onBlocking { getPokemonList(Mockito.anyInt()) } doReturn emptyList()
        }
        val usecase: GetPokemonListUsecase = GetPokemonListUsecase(pokemonAdapterMock);
        runTest {
            usecase.execute(0);
            verify(pokemonAdapterMock, times(1)).getPokemonList(0)
        }
    }

    @Test
    fun `execute usecase must return a usecaseResponse with error if no params provided`() {
        val pokemonAdapterMock = mock<PokemonPort> {
            onBlocking { getPokemonList(Mockito.anyInt()) } doReturn emptyList()
        }
        val usecase: GetPokemonListUsecase = GetPokemonListUsecase(pokemonAdapterMock);
        runTest {
            val result = usecase.execute(null);
            verify(pokemonAdapterMock, times(0)).getPokemonList(Mockito.anyInt())
            assertNull(result.data);
            assertEquals(result.error, "No page index provided")
        }
    }

    @Test
    fun `execute usecase must return pokemon list returned by pokemonPort`() {
        val pokemon = PokemonPreviewEntity(
            "1",
            "Pikachu",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/25.svg",
        )
        val pokemonAdapterMock = mock<PokemonPort> {
            onBlocking { getPokemonList(Mockito.anyInt()) } doReturn listOf(pokemon)
        }

        val usecase: GetPokemonListUsecase = GetPokemonListUsecase(pokemonAdapterMock);
        runTest {
            val result = usecase.execute(0);
            assertNull(result.error);
            assertEquals(result.data, listOf(pokemon))
        }
    }

    @Test
    fun `execute usecase must return usecase with error if pokemon port throw an error`() {
        val pokemonAdapterMock = mock<PokemonPort> {
            onBlocking { getPokemonList(Mockito.anyInt()) } doThrow (Exception("An error occured get pokemon list"))
        }
        val usecase: GetPokemonListUsecase = GetPokemonListUsecase(pokemonAdapterMock);
        runTest {
            val result = usecase.execute(0);
            assertNull(result.data)
            assertEquals(result.error, "Unable to get pokemon list");
        }
    }
}
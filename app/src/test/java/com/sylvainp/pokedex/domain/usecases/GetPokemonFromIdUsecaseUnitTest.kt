package com.sylvainp.pokedex.domain.usecases

import com.sylvainp.pokedex.domain.entities.PokemonEntity
import com.sylvainp.pokedex.domain.entities.PokemonPreviewEntity
import com.sylvainp.pokedex.domain.ports.PokemonPort
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.times
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
class GetPokemonFromIdUsecaseUnitTest {

    @Test
    fun `execute usecase must call port getPokemonFromId function`() {
        val pokemonAdapterMock = mock<PokemonPort> {
            onBlocking { getPokemonFromId("1") } doReturn null
        }
        val usecase: GetPokemonFromIdUsecase = GetPokemonFromIdUsecase(pokemonAdapterMock);
        runTest {
            usecase.execute("1")
            verify(pokemonAdapterMock, times(1)).getPokemonFromId("1")
        }
    }

    @Test
    fun `execute usecase must return a usecase response if no params provided without calling adapter`() {
        val pokemonAdapterMock = mock<PokemonPort> {
            onBlocking { getPokemonFromId("1") } doReturn null
        }
        val usecase = GetPokemonFromIdUsecase(pokemonAdapterMock);
        runTest {
            val result: UsecaseResponse<PokemonEntity?> = usecase.execute(null);
            verify(pokemonAdapterMock, times(0)).getPokemonFromId(anyString())
            assertNull(result.data);
            assertEquals(result.error, "No pokemon id provided");
        }
    }

    @Test
    fun `execute usecase must return an UsecaseResponse with pokemon returned by adapter`() {
        val expectedPokemon = PokemonEntity(
            id = "1",
            name = "Pikachu",
            image = "image",
            weight = "4",
            height = "5",
            type = emptyList(),
            basesStats = emptyList()
        );
        val pokemonAdapter = mock<PokemonPort> {
            onBlocking { getPokemonFromId(anyString()) } doReturn expectedPokemon
        }
        val usecase = GetPokemonFromIdUsecase(pokemonAdapter);
        runTest {
            val response: UsecaseResponse<PokemonEntity?> = usecase.execute("1");
            assertNull(response.error);
            assertEquals(response.data, expectedPokemon)

        }
    }

    @Test
    fun `execute usecase must return a usecase response with error if adapter throw an error`() {
        val pokemonAdapter = mock<PokemonPort> {
            onBlocking { getPokemonFromId(anyString()) } doThrow Exception("An error occured")
        }
        val usecase = GetPokemonFromIdUsecase(pokemonAdapter);
        runTest {
            val response: UsecaseResponse<PokemonEntity?> = usecase.execute("1");
            assertNull(response.data);
            assertEquals(response.error, "Cannot get pokemon with id 1")
        }
    }
}
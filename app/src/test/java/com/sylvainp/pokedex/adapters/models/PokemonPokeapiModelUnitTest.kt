package com.sylvainp.pokedex.adapters.models

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.Assert.*

class PokemonPokeapiModelUnitTest {

    @Test
    fun `toDomain must return a PokemonPreviewEntity with dream_word front default image`() {
        val pokemonJson: String = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"bulbasaur\",\n" +
                "  \"sprites\": {\n" +
                "    \"front_default\": \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png\",\n" +
                "    \"other\": {\n" +
                "      \"dream_world\": {\n" +
                "        \"front_default\": \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg\"\n" +
                "      },\n" +
                "      \"official-artwork\": {\n" +
                "        \"front_default\": \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}"
        val format = Json { ignoreUnknownKeys = true }
        val pokemonModel = format.decodeFromString<PokemonPokeapiModel>(pokemonJson);
        val pokemonEntity = pokemonModel.toPokemonPreviewEntityDomain();
        assertEquals(pokemonEntity.id, "1");
        assertEquals(pokemonEntity.name, "bulbasaur");
        assertEquals(pokemonEntity.image, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg");
    }

    @Test
    fun `toDomain must return a PokemonPreviewEntity with official artwork front default image if no dream world found`() {
        val pokemonJson: String = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"bulbasaur\",\n" +
                "  \"sprites\": {\n" +
                "    \"front_default\": \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png\",\n" +
                "    \"other\": {\n" +
                "      \"official-artwork\": {\n" +
                "        \"front_default\": \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}"
        val format = Json { ignoreUnknownKeys = true }
        val pokemonModel = format.decodeFromString<PokemonPokeapiModel>(pokemonJson);
        val pokemonEntity = pokemonModel.toPokemonPreviewEntityDomain();
        assertEquals(pokemonEntity.id, "1");
        assertEquals(pokemonEntity.name, "bulbasaur");
        assertEquals(pokemonEntity.image, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png");
    }

    @Test
    fun `toDomain must return a PokemonPreviewEntity with front default image if no official artwork found`() {
        val pokemonJson: String = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"bulbasaur\",\n" +
                "  \"sprites\": {\n" +
                "    \"front_default\": \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png\",\n" +
                "    \"other\": {\n" +
                "    }\n" +
                "  }\n" +
                "}"
        val format = Json { ignoreUnknownKeys = true }
        val pokemonModel = format.decodeFromString<PokemonPokeapiModel>(pokemonJson);
        val pokemonEntity = pokemonModel.toPokemonPreviewEntityDomain();
        assertEquals(pokemonEntity.id, "1");
        assertEquals(pokemonEntity.name, "bulbasaur");
        assertEquals(pokemonEntity.image, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png");
    }
}
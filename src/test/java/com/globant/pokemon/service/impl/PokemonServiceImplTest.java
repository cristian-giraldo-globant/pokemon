package com.globant.pokemon.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.globant.pokemon.builder.PokemonBuilder;
import com.globant.pokemon.exception.EntityInvalidException;
import com.globant.pokemon.model.Pokemon;
import com.globant.pokemon.repository.IPokemonRepository;

@SpringBootTest
public class PokemonServiceImplTest {

	@Mock
	private IPokemonRepository pokemonRepository;

	@InjectMocks
	private PokemonServiceImpl pokemonService;

	@Test
	public void testFindByIdAndEntityExist() {

		Integer id = 1;
		Optional<Pokemon> optionalPokemon = Optional.of(PokemonBuilder.init().build());
		when(pokemonRepository.findById(id)).thenReturn(optionalPokemon);

		Pokemon pokemon = pokemonService.findById(id);

		assertNotNull(pokemon);
	}

	@Test
	public void testFindByIdAndEntityDoesntExist() {

		Integer id = 1;
		String messageError = String.format(PokemonServiceImpl.MESSAGE_NOT_FOUND, id);
		Optional<Pokemon> optionalPokemon = Optional.empty();
		when(pokemonRepository.findById(id)).thenReturn(optionalPokemon);

		assertThrows(EntityNotFoundException.class, () -> pokemonService.findById(id), messageError);
	}

	@Test
	public void testCreateWithAllFieldsOk() {

		Pokemon pokemon = PokemonBuilder.init().id(null).build();
		when(pokemonRepository.save(pokemon)).thenReturn(pokemon);

		pokemonService.create(pokemon);

		verify(pokemonRepository, times(1)).save(pokemon);
	}

	@Test
	public void testCreateWhenHasID() {

		Pokemon pokemon = PokemonBuilder.init().build();
		when(pokemonRepository.save(pokemon)).thenReturn(pokemon);

		assertThrows(EntityInvalidException.class, () -> pokemonService.create(pokemon),
				PokemonServiceImpl.MESSAGE_ID_MUST_BE_NULL);
	}

	@Test
	public void testCreateWhenTwoPokemonHaveTheSameName() {

		Pokemon pokemon = PokemonBuilder.init().id(null).build();
		Pokemon pokemonRepeated = PokemonBuilder.init().build();
		Optional<Pokemon> optionalPokemon = Optional.of(pokemonRepeated);
		when(pokemonRepository.findByName(pokemon.getName())).thenReturn(optionalPokemon);

		assertThrows(EntityInvalidException.class, () -> pokemonService.create(pokemon),
				PokemonServiceImpl.MESSAGE_POKEMON_EXIST);
	}
}

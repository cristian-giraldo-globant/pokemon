package com.globant.pokemon.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.pokemon.exception.EntityInvalidException;
import com.globant.pokemon.model.Pokemon;
import com.globant.pokemon.repository.IPokemonRepository;
import com.globant.pokemon.service.IPokemonService;

@Service
public class PokemonServiceImpl implements IPokemonService {

	public static final String MESSAGE_NOT_FOUND = "Pokemon with ID = %d doesn't exist";
	public static final String MESSAGE_POKEMON_EXIST = "Pokemon already exists";
	public static final String MESSAGE_ID_MUST_BE_NULL = "Pokemon cannot have ID";
	public static final String MESSAGE_ID_CANNOT_BE_NULL = "Pokemon ID cannot be null";

	@Autowired
	private IPokemonRepository pokemonRepository;

	@Override
	public List<Pokemon> findAll() {

		return pokemonRepository.findAll();
	}

	@Override
	public Pokemon findById(Integer id) {

		return pokemonRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(MESSAGE_NOT_FOUND, id)));
	}

	@Override
	public Pokemon create(Pokemon pokemon) {

		validateEntityToCreate(pokemon);

		return pokemonRepository.save(pokemon);
	}

	private void validateEntityToCreate(Pokemon pokemon) {

		validateIdIsNull(pokemon);
		validateNewNameDoestExist(pokemon);
	}

	private void validateIdIsNull(Pokemon pokemon) {
		if (pokemon.getId() != null) {
			throw new EntityInvalidException(MESSAGE_ID_MUST_BE_NULL);
		}
	}

	private void validateNewNameDoestExist(Pokemon pokemon) {
		pokemonRepository.findByName(pokemon.getName()).ifPresent(i -> {
			throw new EntityInvalidException(MESSAGE_POKEMON_EXIST);
		});
	}

	@Override
	public Pokemon update(Pokemon pokemon) {

		validateEntityToUpdate(pokemon);

		return pokemonRepository.save(pokemon);
	}

	private void validateEntityToUpdate(Pokemon pokemon) {
		validateIdIsNotNull(pokemon);
		findById(pokemon.getId());
		validateUpdateNameDoestExist(pokemon);
	}

	private void validateIdIsNotNull(Pokemon pokemon) {
		if (pokemon.getId() == null) {
			throw new EntityInvalidException(MESSAGE_ID_CANNOT_BE_NULL);
		}
	}

	private void validateUpdateNameDoestExist(Pokemon pokemon) {
		pokemonRepository.findByName(pokemon.getName()).ifPresent(pokemonInDB -> {

			if (!pokemonInDB.getId().equals(pokemon.getId())) {
				throw new EntityInvalidException(MESSAGE_POKEMON_EXIST);
			}
		});
	}

	@Override
	public void deleteById(Integer id) {

		findById(id);

		pokemonRepository.deleteById(id);
	}

}

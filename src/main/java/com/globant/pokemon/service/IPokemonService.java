package com.globant.pokemon.service;

import java.util.List;

import com.globant.pokemon.model.Pokemon;

public interface IPokemonService {

	List<Pokemon> findAll();

	Pokemon findById(Integer id);

	Pokemon create(Pokemon pokemon);

	Pokemon update(Pokemon pokemon);

	void deleteById(Integer id);
}

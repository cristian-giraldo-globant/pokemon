package com.globant.pokemon.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.pokemon.model.Pokemon;
import com.globant.pokemon.service.IPokemonService;

@RestController
@RequestMapping("api/v1/pokemon")
public class PokemonController {

	@Autowired
	private IPokemonService pokemonService;

	@GetMapping
	public List<Pokemon> findAll() {

		return pokemonService.findAll();
	}

	@GetMapping("/{id}")
	public Pokemon findById(@PathVariable Integer id) {

		return pokemonService.findById(id);
	}

	@PostMapping
	public Pokemon create(@Valid @RequestBody Pokemon pokemon) {

		return pokemonService.create(pokemon);
	}

	@PutMapping
	public Pokemon update(@Valid @RequestBody Pokemon pokemon) {

		return pokemonService.update(pokemon);
	}

	@DeleteMapping("/{id}")
	public Map<String, String> delete(@PathVariable Integer id) {

		pokemonService.deleteById(id);

		return Collections.singletonMap("message", "Pokemon deleted successfuly");
	}
}

package com.globant.pokemon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globant.pokemon.model.Pokemon;

public interface IPokemonRepository extends JpaRepository<Pokemon, Integer> {

	Optional<Pokemon> findByName(String name);
}

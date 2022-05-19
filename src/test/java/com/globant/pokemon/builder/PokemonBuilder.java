package com.globant.pokemon.builder;

import com.globant.pokemon.model.Pokemon;

public class PokemonBuilder {

	private Integer id = 1;
	private String name = "Pikachu";
	private Integer age = 17;
	private String description = "Pikachu is a yellow, mouse-like creature with electrical abilities";
	private Double height = 40.64;
	private Double weight = 6.0;
	private Integer HP = 30;
	private String ability = "Electric";
	private Integer resistance = 15;
	private Integer power = 30;
	private Boolean tamed = true;

	public PokemonBuilder id(Integer id) {
		this.id = id;
		return this;
	}

	public PokemonBuilder name(String name) {
		this.name = name;
		return this;
	}

	public PokemonBuilder age(Integer age) {
		this.age = age;
		return this;
	}

	public PokemonBuilder description(String description) {
		this.description = description;
		return this;
	}

	public PokemonBuilder height(Double height) {
		this.height = height;
		return this;
	}

	public PokemonBuilder weight(Double weight) {
		this.weight = weight;
		return this;
	}

	public PokemonBuilder HP(Integer HP) {
		this.HP = HP;
		return this;
	}

	public PokemonBuilder ability(String ability) {
		this.ability = ability;
		return this;
	}

	public PokemonBuilder resistence(Integer resistance) {
		this.resistance = resistance;
		return this;
	}

	public PokemonBuilder power(Integer power) {
		this.power = power;
		return this;
	}

	public PokemonBuilder tamed(Boolean tamed) {
		this.tamed = tamed;
		return this;
	}

	public Pokemon build() {

		Pokemon pokemon = new Pokemon();
		pokemon.setId(id);
		pokemon.setName(name);
		pokemon.setAge(age);
		pokemon.setDescription(description);
		pokemon.setHeight(height);
		pokemon.setWeight(weight);
		pokemon.setHP(HP);
		pokemon.setAbility(ability);
		pokemon.setResistance(resistance);
		pokemon.setPower(power);
		pokemon.setTamed(tamed);

		return pokemon;
	}

	public static PokemonBuilder init() {
		return new PokemonBuilder();
	}
}

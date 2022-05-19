package com.globant.pokemon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "pokemon")
public class Pokemon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "The fild name cannot be null")
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@NotNull(message = "The fild age cannot be null")
	@Column(name = "age", nullable = false)
	private Integer age;

	@NotNull(message = "The fild description cannot be null")
	@Column(name = "description", nullable = false)
	private String description;

	@NotNull(message = "The fild height cannot be null")
	@Column(name = "height", nullable = false)
	private Double height;

	@NotNull(message = "The fild weight cannot be null")
	@Column(name = "weight", nullable = false)
	private Double weight;

	@NotNull(message = "The fild HP cannot be null")
	@Column(name = "hp", nullable = false)
	private Integer HP;

	@NotNull(message = "The fild ability cannot be null")
	@Column(name = "ability", nullable = false)
	private String ability;

	@NotNull(message = "The fild resistance cannot be null")
	@Column(name = "resistance", nullable = false)
	private Integer resistance;

	@NotNull(message = "The fild power cannot be null")
	@Column(name = "power", nullable = false)
	private Integer power;

	@NotNull(message = "The fild tamed cannot be null")
	@Column(name = "tamed", nullable = false)
	private Boolean tamed;
	
}

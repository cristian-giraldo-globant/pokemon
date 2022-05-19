package com.globant.pokemon.exception;

public class EntityInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityInvalidException(String message) {
		super(message);
	}

}

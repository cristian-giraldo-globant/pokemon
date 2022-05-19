package com.globant.pokemon.controller;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.globant.pokemon.exception.EntityInvalidException;

@ControllerAdvice
public class AdvisorController extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		Map<String, Object> body = buildBodyErrorMessage(errors, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityInvalidException.class)
	public ResponseEntity<Object> handleEntityInvalidException(EntityInvalidException ex, WebRequest request) {

		return buildResponseError(ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {

		return buildResponseError(ex, HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<Object> buildResponseError(Exception ex, HttpStatus status) {

		Map<String, Object> body = buildBodyErrorMessage(ex.getMessage(), status);

		return new ResponseEntity<>(body, status);
	}

	private Map<String, Object> buildBodyErrorMessage(String error, HttpStatus status) {

		List<String> errors = List.of(error);

		return buildBodyErrorMessage(errors, status);
	}

	private Map<String, Object> buildBodyErrorMessage(List<String> errors, HttpStatus status) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());
		body.put("errors", errors);

		return body;
	}
}

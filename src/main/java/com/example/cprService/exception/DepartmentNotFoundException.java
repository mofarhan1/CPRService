package com.example.cprService.exception;

public class DepartmentNotFoundException extends RuntimeException {

	public DepartmentNotFoundException(String message) {
		super(message);
	}

}
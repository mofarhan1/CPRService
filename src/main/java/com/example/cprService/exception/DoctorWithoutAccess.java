package com.example.cprService.exception;

public class DoctorWithoutAccess extends RuntimeException {

	public DoctorWithoutAccess(String message) {
		super(message);
	}

}
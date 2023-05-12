package com.petkpetk.admin.exception;

public class EmailDuplicateException extends RuntimeException {

	public EmailDuplicateException() {
		super();
	}

	public EmailDuplicateException(String email) {
		super(email);
	}

}

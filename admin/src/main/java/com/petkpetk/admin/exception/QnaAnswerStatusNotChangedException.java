package com.petkpetk.admin.exception;

public class QnaAnswerStatusNotChangedException extends RuntimeException {
	
	private static final String message = "Failed to update answer status";

	public QnaAnswerStatusNotChangedException() {}

	public QnaAnswerStatusNotChangedException(String message) {
		super(message);
	}
}

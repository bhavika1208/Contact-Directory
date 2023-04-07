package com.bhavika.ContactDirectory.responses;

public class ResponseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResponseException(String message) {
		super(message);
	}

	public ResponseException(Throwable cause) {
		super(cause);
	}

	public ResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResponseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
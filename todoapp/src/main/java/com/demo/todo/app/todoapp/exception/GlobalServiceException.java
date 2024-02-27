package com.demo.todo.app.todoapp.exception;

public class GlobalServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	 public GlobalServiceException() {
		super();
	}

	public GlobalServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public GlobalServiceException(String message) {
		super(message);
	}

	public GlobalServiceException(Throwable cause) {
		super(cause);
	}


}

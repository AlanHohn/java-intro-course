package org.anvard.introtojava.jdbc;

public class DataAccessException extends RuntimeException {

	private static final long serialVersionUID = -4056262020632206194L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}

}

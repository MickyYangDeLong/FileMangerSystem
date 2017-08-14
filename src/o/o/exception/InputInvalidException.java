package o.o.exception;

public class InputInvalidException extends Exception {
	/**
	 * InputInvalidException
	 */
	private static final long serialVersionUID = 1L;

	public InputInvalidException() {
		super();
	}

	public InputInvalidException(Exception e) {
		super(e);
	}

	public InputInvalidException(String e) {
		super(e);
	}

	public InputInvalidException(String e, Throwable throwable) {
		super(e, throwable);
	}

}

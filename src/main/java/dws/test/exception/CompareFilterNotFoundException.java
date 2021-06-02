package dws.test.exception;

public class CompareFilterNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompareFilterNotFoundException() {
		super("Please inform NAME or POPULARITY");
	}

	public CompareFilterNotFoundException(Exception ex) {
		super(ex);
	}

	public CompareFilterNotFoundException(String message) {
		super(message);
	}
}

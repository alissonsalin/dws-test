package dws.test.exception;

public class BandsNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public BandsNotFoundException() {
		
	}
	
	public BandsNotFoundException(Exception ex) {
		super(ex);
	}
	
	public BandsNotFoundException(String message) {
		super(message);
	}
}

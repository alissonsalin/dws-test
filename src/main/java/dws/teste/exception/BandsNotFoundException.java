package dws.teste.exception;

public class BandsNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public BandsNotFoundException() {
		
	}
	
	public BandsNotFoundException(Exception ex) {
		super(ex);
	}
}

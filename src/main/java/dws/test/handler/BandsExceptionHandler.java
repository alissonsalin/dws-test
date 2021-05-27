package dws.test.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dws.teste.exception.BandNotFoundException;
import dws.teste.exception.BandsNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class BandsExceptionHandler extends ResponseEntityExceptionHandler {
	  @Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		  log.error(ex.getMessage());
		  return new ResponseEntity<Object>(ex.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
	  }
	  
	  @ExceptionHandler(value = { BandsNotFoundException.class })
	  public ResponseEntity<Object> handleBandsNotFoundException(BandsNotFoundException ex) {
		  log.error("Bands not found");
		  return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
	  }
	  
	  @ExceptionHandler(value = { BandNotFoundException.class })
	  public ResponseEntity<Object> handleBandNotFoundException(BandsNotFoundException ex) {
		  return new ResponseEntity<Object>("Band(s) not found", HttpStatus.NOT_FOUND);
	  }
	  
	  @ExceptionHandler(value = { Exception.class })
	  public ResponseEntity<Object> handleException(Exception ex) {
		  return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}

package dws.test.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dws.test.exception.BandsNotFoundException;

@ControllerAdvice
public class BandsExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(
				messageSource.getMessage("bad.request", null, LocaleContextHolder.getLocale()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { BandsNotFoundException.class })
	public ResponseEntity<Object> handleBandsNotFoundException(BandsNotFoundException ex) {
		return new ResponseEntity<Object>(
				messageSource.getMessage("bands.not.found", null, LocaleContextHolder.getLocale()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleException(Exception ex) {
		return new ResponseEntity<Object>(
				messageSource.getMessage("internal.server.error", null, LocaleContextHolder.getLocale()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

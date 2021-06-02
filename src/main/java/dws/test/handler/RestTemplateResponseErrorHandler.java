package dws.test.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			throw new RuntimeException(
					messageSource.getMessage("server.error", null, LocaleContextHolder.getLocale()));
		} else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
			if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new RuntimeException(
						messageSource.getMessage("isobarfm.url.not.found", null, LocaleContextHolder.getLocale()));
			} else if (httpResponse.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
				throw new RuntimeException(
						messageSource.getMessage("internal.server.error", null, LocaleContextHolder.getLocale()));
			}
		}
	}
}

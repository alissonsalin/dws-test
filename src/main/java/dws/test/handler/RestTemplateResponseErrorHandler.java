package dws.test.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
	@Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (
          httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR 
          || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR
        );
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
        	 throw new RuntimeException("Server error");
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RuntimeException("Infor.bar url not found");
            } else if(httpResponse.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            	throw new RuntimeException("Isobar.fm internal server error");
            }
        }
    }
}

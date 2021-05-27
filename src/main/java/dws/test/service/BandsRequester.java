package dws.test.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dws.test.handler.RestTemplateResponseErrorHandler;
import dws.test.model.Band;

@Service
public class BandsRequester {
	private RestTemplate restTemplate;
	
	@Value("${dws.bands.url}")
	private String bandsUrl;

    @Autowired
    public BandsRequester(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
          .errorHandler(new RestTemplateResponseErrorHandler())
          .build();
    }

    public List<Band> findAll() {
    	List<Band> bands = null;
    	try {
    		//TODO: create a wrapper
    		Band[] bandsResponse = restTemplate.getForObject(bandsUrl, Band[].class);
			bands = Arrays.asList(bandsResponse);
		} catch (Exception e) {
			throw e;
		}
        return bands;
    }
}

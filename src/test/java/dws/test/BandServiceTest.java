package dws.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dws.test.exception.BandsNotFoundException;
import dws.test.model.Band;
import dws.test.service.BandService;

@SpringBootTest
public class BandServiceTest {

	@Autowired
	private BandService bandService;
	
	@Test
	public void testBandService() throws BandsNotFoundException, Exception {
		Assertions.assertNotNull(bandService.findAll());
		List<Band> bands = bandService.findBandsByName("The");
		Assertions.assertNotNull(bands);
	}
}

package dws.test.handler.filters;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import dws.test.model.Band;

@Component("POPULARITY")
public class BandsComparePopularity implements BandFilter{

	@Override
	public Comparator<Band> getFilter() {
		return Comparator.comparing(Band::getNumPlays);
	}

}

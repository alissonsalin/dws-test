package dws.test.handler.filters;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import dws.test.model.Band;

@Component("NAME")
public class BandsCompareName implements BandFilter {

	@Override
	public Comparator<Band> getFilter() {
		return Comparator.comparing(Band::getName);
	}

	
}

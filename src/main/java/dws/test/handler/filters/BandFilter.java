package dws.test.handler.filters;

import java.util.Comparator;

import dws.test.model.Band;

public interface BandFilter {
	public Comparator<Band> getFilter();
}

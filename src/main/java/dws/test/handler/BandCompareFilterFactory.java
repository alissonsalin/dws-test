package dws.test.handler;

import dws.test.handler.filters.BandFilter;

public interface BandCompareFilterFactory {
	public BandFilter getCompareFilter(String bandFilterType);
}

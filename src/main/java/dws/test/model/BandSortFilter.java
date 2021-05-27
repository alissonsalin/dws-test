package dws.test.model;

public enum BandSortFilter {
	NAME("NAME"), POPULARITY("POPULARITY");
	
	final String filterName;
	
	BandSortFilter(String filterName) {
		this.filterName = filterName;
	}
	
	public String getFilterName() {
		return filterName;
	}
}

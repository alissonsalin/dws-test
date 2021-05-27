package dws.test.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dws.test.handler.BandCompareFilterFactory;
import dws.test.model.Band;
import dws.test.model.BandSortFilter;
import dws.teste.exception.BandsNotFoundException;

@Service
public class BandService {
	
	@Autowired
	private BandsRequester bandsRequester;
	
	@Autowired
	private BandCompareFilterFactory bandCompareFilterFactory;
	
	@Cacheable("bands")
	public List<Band> findAll() throws BandsNotFoundException, Exception {
		return Optional.ofNullable(bandsRequester.findAll()).orElseThrow(BandsNotFoundException::new);
	}
	
	
	@Cacheable("bandsByName")
	public List<Band> findBandsByName(String name) throws BandsNotFoundException, Exception {
		List<Band> bands = findAll().stream().filter(band -> band.getName().startsWith(name)).collect(Collectors.toList());
		return bands;
	}
	
	@Cacheable("bandsByNameSorted")
	public List<Band> findBandsByNameSorted(String name, BandSortFilter sortedFilter) throws BandsNotFoundException, Exception {
		List<Band> bands = findAll().stream()
				.filter(band -> band.getName().toUpperCase().startsWith(name.toUpperCase()))
				.collect(Collectors.toList());
		
        bands = bands.stream()
        		.sorted(bandCompareFilterFactory.getCompareFilter(sortedFilter.getFilterName()).getFilter())
        		.collect(Collectors.toList());
		
		return bands;
	}
}

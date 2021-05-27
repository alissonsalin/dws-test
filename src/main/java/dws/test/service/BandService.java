package dws.test.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dws.test.exception.BandsNotFoundException;
import dws.test.handler.BandCompareFilterFactory;
import dws.test.model.Band;
import dws.test.model.BandSortFilter;

@Service
public class BandService {
	
	@Autowired
	private BandsRequester bandsRequester;
	
	@Autowired
	private BandCompareFilterFactory bandCompareFilterFactory;
	
	@Cacheable("bands")
	public List<Band> findAll() {
		return Optional.ofNullable(bandsRequester.findAll()).orElseThrow(BandsNotFoundException::new);
	}
	
	
	@Cacheable("bandsByName")
	public List<Band> findBandsByName(String name) {
		List<Band> bands = findAll()
				.stream()
				.filter(band -> band.getName().startsWith(name))
				.collect(
						Collectors.collectingAndThen(Collectors.toList(), result -> {
							if (result.isEmpty()) throw new BandsNotFoundException("Bands not found");
							return result;
						}
				));
		return bands;
	}
	
	@Cacheable("bandsByNameSorted")
	public List<Band> findBandsByNameSorted(String name, BandSortFilter sortedFilter) {
		List<Band> bands = findAll()
				.stream()
				.filter(band -> band.getName().toUpperCase().startsWith(name.toUpperCase()))
				.sorted(
					bandCompareFilterFactory
						.getCompareFilter(sortedFilter.getFilterName()).getFilter()
				)
				.collect(
						Collectors.collectingAndThen(Collectors.toList(), result -> {
							if (result.isEmpty()) throw new BandsNotFoundException("Bands not found");
							return result;
						}
				));
		
		return bands;
	}
}

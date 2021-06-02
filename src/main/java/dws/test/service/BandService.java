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
						Collectors.collectingAndThen(Collectors.toList(), bandsList -> {
							if (bandsList.isEmpty()) throw new BandsNotFoundException();
							return bandsList;
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
						Collectors.collectingAndThen(Collectors.toList(), bandsList -> {
							if (bandsList.isEmpty()) throw new BandsNotFoundException();
							return bandsList;
						}
				));
		
		return bands;
	}
}

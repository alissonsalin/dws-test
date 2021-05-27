package dws.test.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dws.test.exception.BandsNotFoundException;
import dws.test.model.Band;
import dws.test.model.BandSortFilter;
import dws.test.service.BandService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/isobarfm/v1")
public class BandsController {

	@Autowired
	private BandService bandService;
	
	@ApiOperation(value = "Find all the bands")
	@GetMapping("/bands")
	public ResponseEntity<List<Band>> findAll() {
		return new ResponseEntity<List<Band>>(bandService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find a band list by starts with name (case insensitive) and sorted")
	@GetMapping("/band/{name}/sort/{sortedFilter}")
	public ResponseEntity<List<Band>> findBandByName(
			@PathVariable @NotBlank @Size(min=3, message = "Please inform 3 characters") String name, 
			@PathVariable @NotBlank BandSortFilter sortedFilter)  {
		return new ResponseEntity<List<Band>>(bandService.findBandsByNameSorted(name, sortedFilter), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find a band list by starts with name and sort by name")
	@GetMapping("/band/{name}")
	public ResponseEntity<List<Band>> findBandByName(
			@PathVariable @NotBlank @Size(min=3, message = "Please informe 3 characters") String name) {
		return new ResponseEntity<List<Band>>(bandService.findBandsByName(name), HttpStatus.OK);
	}
}

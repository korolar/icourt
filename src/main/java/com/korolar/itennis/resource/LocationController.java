package com.korolar.itennis.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korolar.itennis.dto.location.LocationDto;
import com.korolar.itennis.service.dto.location.ILocationDtoService;

@RestController
@CrossOrigin(origins = "*")
public class LocationController {

	@Autowired
	private ILocationDtoService locationDtoService;

	@GetMapping(value = "/location")
	public List<LocationDto> getAllLocations() {
		return locationDtoService.getAllLocations();
	}

}

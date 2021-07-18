package com.korolar.itennis.resource;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korolar.itennis.dto.location.LocationDto;
import com.korolar.itennis.service.dto.location.ILocationDtoService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LocationController {

	private final ILocationDtoService locationDtoService;

	@GetMapping(value = "/location")
	public List<LocationDto> getAllLocations() {
		return locationDtoService.getAllLocations();
	}

}

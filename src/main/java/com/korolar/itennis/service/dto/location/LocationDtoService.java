package com.korolar.itennis.service.dto.location;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korolar.itennis.dto.location.LocationDto;
import com.korolar.itennis.entity.Location;
import com.korolar.itennis.service.dao.location.ILocationDaoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationDtoService implements ILocationDtoService {

	private final ILocationDaoService locationService;

	@Override
	public List<LocationDto> getAllLocations() {
		return locationService.getAllLocations().stream().map(x -> fromEntity(x)).collect(Collectors.toList());
	}

	private LocationDto fromEntity(Location location) {
		LocationDto locationDto = new LocationDto();
		locationDto.setId(location.getId());
		locationDto.setName(location.getName());
		return locationDto;
	}
}

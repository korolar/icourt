package com.korolar.itennis.service.dao.location;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.korolar.itennis.entity.Location;
import com.korolar.itennis.repositories.LocationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationDaoService implements ILocationDaoService {

	private final LocationRepository locationRepository;

	@Override
	public List<Location> getAllLocations() {
		return locationRepository.findAll();
	}

	@Override
	public Location getById(Long id) {
		return locationRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Location with id " + id + " not found!"));
	}

	@Override public Location saveLocation(Location location) {
		return locationRepository.save(location);
	}
}

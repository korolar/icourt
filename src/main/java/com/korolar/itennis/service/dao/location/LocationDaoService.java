package com.korolar.itennis.service.dao.location;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import com.korolar.itennis.entity.Location;
import com.korolar.itennis.repositories.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationDaoService implements ILocationDaoService {

	@Autowired
	private LocationRepository locationRepository;

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

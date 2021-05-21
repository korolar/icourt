package com.korolar.itennis.service.dao.location;

import java.util.List;

import com.korolar.itennis.entity.Location;

public interface ILocationDaoService {

	List<Location> getAllLocations();

	Location getById(Long id);

	Location saveLocation(Location location);
}

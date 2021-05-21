package com.korolar.itennis.repositories;

import com.korolar.itennis.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import com.korolar.itennis.entity.Club;

public interface LocationRepository extends JpaRepository<Location, Long> {

}

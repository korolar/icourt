package com.korolar.itennis.repositories;

import com.korolar.itennis.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long> {

}
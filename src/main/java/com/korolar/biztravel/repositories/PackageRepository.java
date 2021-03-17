package com.korolar.biztravel.repositories;

import com.korolar.biztravel.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long> {

}
package com.korolar.itennis.repositories;

import com.korolar.itennis.entity.SubscriptionPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import com.korolar.itennis.entity.Schedule;

public interface SubscriptionPackageRepository extends JpaRepository<SubscriptionPackage, Long> {

}

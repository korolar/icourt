package com.korolar.itennis.repositories;

import com.korolar.itennis.entity.BusinessRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRoleRepository extends JpaRepository<BusinessRole, Long> {

	BusinessRole findByName(String name);
}
package com.korolar.biztravel.repositories;

import com.korolar.biztravel.entity.BusinessRole;
import com.korolar.biztravel.entity.SecurityRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRoleRepository extends JpaRepository<BusinessRole, Long> {

	BusinessRole findByName(String name);
}
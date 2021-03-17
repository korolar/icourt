package com.korolar.biztravel.repositories;

import com.korolar.biztravel.entity.SecurityRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRoleRepository extends JpaRepository<SecurityRole, Long> {

	SecurityRole findByName(String name);
}
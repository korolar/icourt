package com.korolar.itennis.repositories;

import com.korolar.itennis.entity.SecurityRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRoleRepository extends JpaRepository<SecurityRole, Long> {

	SecurityRole findByName(String name);
}
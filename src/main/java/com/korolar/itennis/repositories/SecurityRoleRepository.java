package com.korolar.itennis.repositories;

import com.korolar.itennis.entity.SecurityRole;
import com.korolar.itennis.enums.ESecurityRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRoleRepository extends JpaRepository<SecurityRole, Long> {

	SecurityRole findByName(ESecurityRole name);
}

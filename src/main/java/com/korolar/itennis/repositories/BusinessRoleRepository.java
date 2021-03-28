package com.korolar.itennis.repositories;

import com.korolar.itennis.entity.BusinessRole;
import com.korolar.itennis.enums.EBusinessRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRoleRepository extends JpaRepository<BusinessRole, Long> {

	BusinessRole findByName(EBusinessRole name);

}

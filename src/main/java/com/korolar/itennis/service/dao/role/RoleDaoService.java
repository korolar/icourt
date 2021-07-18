package com.korolar.itennis.service.dao.role;

import org.springframework.stereotype.Service;

import com.korolar.itennis.entity.BusinessRole;
import com.korolar.itennis.entity.SecurityRole;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.enums.ESecurityRole;
import com.korolar.itennis.repositories.BusinessRoleRepository;
import com.korolar.itennis.repositories.SecurityRoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleDaoService implements IRoleDaoService {

	private final BusinessRoleRepository businessRoleRepository;
	private final SecurityRoleRepository securityRoleRepository;

	@Override
	public BusinessRole getByName(EBusinessRole eBusinessRole) {
		return businessRoleRepository.findByName(eBusinessRole);
	}

	@Override
	public SecurityRole getByName(ESecurityRole eSecurityRole) {
		return securityRoleRepository.findByName(eSecurityRole);
	}
}

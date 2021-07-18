package com.korolar.itennis.service.businessdao.user;

import org.springframework.stereotype.Service;

import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.service.dao.user.IUserDaoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OwnerService implements IOwnerService {

	private final IUserDaoService userDaoService;

	@Override public User getOwnerById(Long id) {
		return userDaoService.getUserWithBusinessRole(id, EBusinessRole.ROLE_OWNER);
	}
}

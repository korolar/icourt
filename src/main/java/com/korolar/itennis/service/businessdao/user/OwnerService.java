package com.korolar.itennis.service.businessdao.user;

import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.service.dao.user.IUserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService implements IOwnerService {

	@Autowired
	private IUserDaoService userDaoService;

	@Override public User getOwnerById(Long id) {
		return userDaoService.getUserWithBusinessRole(id, EBusinessRole.ROLE_OWNER);
	}
}

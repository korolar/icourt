package com.korolar.itennis.service.utils;

import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;

public class BusinessRuleUtils {

	public static Boolean checkIfUserHasBusinessRole(User user, EBusinessRole role) {
		return user.getBusinessRoles().stream().map(businessRole -> businessRole.getName()).filter(roleEnum -> role.equals(roleEnum)).count() > 0;
	}
}

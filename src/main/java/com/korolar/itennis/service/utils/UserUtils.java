package com.korolar.itennis.service.utils;

import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.entity.User;

public class UserUtils {
	public static void mapFromUserToDto(User user, UserDto userDto) {
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
	}
}

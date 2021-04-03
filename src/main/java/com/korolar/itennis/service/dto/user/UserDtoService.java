package com.korolar.itennis.service.dto.user;

import com.korolar.itennis.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.entity.User;

@Service
public class UserDtoService implements IUserDtoService {

	@Autowired
	private IUserService userService;

	@Override
	public UserDto getEntityAsDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		return userDto;
	}

	@Override
	public UserDto getById(Long id) {
		return getEntityAsDto(userService.getUser(id));
	}

}

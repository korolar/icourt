package com.korolar.itennis.service.dto.user;

import org.springframework.stereotype.Service;

import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.service.dao.user.IUserDaoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDtoService implements IUserDtoService {

	private final IUserDaoService userService;

	@Override
	public UserDto getEntityAsDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		return userDto;
	}

	@Override
	public User getDtoAsEntity(UserDto entity) {
		User userDto = new User();
		userDto.setId(entity.getId());
		userDto.setFirstName(entity.getFirstName());
		userDto.setLastName(entity.getLastName());
		return userDto;
	}

	@Override
	public UserDto getById(Long id) {
		return getEntityAsDto(userService.getUser(id));
	}

}

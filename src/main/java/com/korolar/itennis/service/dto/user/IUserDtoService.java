package com.korolar.itennis.service.dto.user;

import java.util.List;
import java.util.stream.Collectors;

import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.User;

public interface IUserDtoService {

	UserDto getEntityAsDto(User entity);

	User getDtoAsEntity(UserDto entity);

	default List<UserDto> fromEntities(List<User> users) {
		return users.stream().map(this::getEntityAsDto).collect(Collectors.toList());
	}

	default List<User> fromDtos(List<UserDto> users) {
		return users.stream().map(this::getDtoAsEntity).collect(Collectors.toList());
	}

	UserDto getById(Long id);
}

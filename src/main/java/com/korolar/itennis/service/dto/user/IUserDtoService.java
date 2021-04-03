package com.korolar.itennis.service.dto.user;

import java.util.List;
import java.util.stream.Collectors;

import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.User;

public interface IUserDtoService<T, R> {

	T getEntityAsDto(R entity);

	default List<T> fromEntities(List<R> users) {
		return users.stream().map(this::getEntityAsDto).collect(Collectors.toList());
	}

	UserDto getById(Long id);
}

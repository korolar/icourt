package com.korolar.itennis.service.user;

import java.util.List;
import java.util.stream.Collectors;

public interface IUserService<T, R> {

	T getEntityAsDto(Long id);

	T fromEntity(R dto);

	default List<T> fromEntities(List<R> dtos) {
		return dtos.stream().map(this::fromEntity).collect(Collectors.toList());
	}

}

package com.korolar.itennis.service.user;

import com.korolar.itennis.service.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.repositories.UserRepository;

@Service(value = "user_service")
public class UserService implements IUserService<UserDto, User> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto getEntityAsDto(Long id) {
		return fromEntity(getUserFromRepository(id));
	}

	@Override
	public UserDto fromEntity(User user) {
		UserDto userDto = new UserDto();
		UserUtils.mapFromUserToDto(user, userDto);
		return userDto;
	}

	/**
	 * Get by user Id from repository
	 * 
	 * @param id
	 * @return if found return User, throw exception otherwise
	 */
	private User getUserFromRepository(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceAccessException("User with id " + id + " not found"));
	}

}

package com.korolar.itennis.resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.service.dto.user.IUserDtoService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

	private final IUserDtoService userService;

	@GetMapping(value = "/user/{id}")
	public UserDto getUserById(@PathVariable("id") Long id) {
		return userService.getById(id);
	}

}

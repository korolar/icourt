package com.korolar.itennis.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.service.dto.user.IUserDtoService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private IUserDtoService userService;

	@GetMapping(value = "/user/{id}")
	public UserDto getUserById(@PathVariable("id") Long id) {
		return userService.getById(id);
	}

}

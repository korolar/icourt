package com.korolar.itennis.resource;

import com.korolar.itennis.dto.user.OwnerDto;
import com.korolar.itennis.service.user.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class OwnerController {

	@Autowired
	private OwnerService userService;

	@GetMapping(value = "/owner/{id}")
	public OwnerDto getUserById(@PathVariable("id") Long id) {
		return userService.getEntityAsDto(id);
	}

}
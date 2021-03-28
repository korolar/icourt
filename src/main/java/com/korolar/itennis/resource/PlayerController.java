package com.korolar.itennis.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.korolar.itennis.dto.user.PlayerDto;
import com.korolar.itennis.service.user.PlayerService;

@RestController
@CrossOrigin(origins = "*")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@GetMapping(value = "/player/{id}")
	public PlayerDto getUserById(@PathVariable("id") Long id) {
		return playerService.getEntityAsDto(id);
	}

}

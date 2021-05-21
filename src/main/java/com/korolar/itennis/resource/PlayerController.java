package com.korolar.itennis.resource;

import com.korolar.itennis.dto.user.PlayerDto;
import com.korolar.itennis.service.dto.player.IPlayerDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class PlayerController {

	@Autowired
	private IPlayerDtoService iPlayerDtoService;

	@GetMapping(value = "/player/{id}")
	public PlayerDto getScheduleForPlayer(@PathVariable("id") Long id) {
		return iPlayerDtoService.getPlayerStatus(id);
	}

}

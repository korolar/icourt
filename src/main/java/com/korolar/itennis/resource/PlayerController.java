package com.korolar.itennis.resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.korolar.itennis.dto.user.PlayerDto;
import com.korolar.itennis.service.dto.player.IPlayerDtoService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PlayerController {

	private final IPlayerDtoService iPlayerDtoService;

	@GetMapping(value = "/player/{id}")
	public PlayerDto getScheduleForPlayer(@PathVariable("id") Long id) {
		return iPlayerDtoService.getPlayerStatus(id);
	}

}

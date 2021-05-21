package com.korolar.itennis.service.dto.player;

import com.korolar.itennis.dto.user.PlayerDto;
import com.korolar.itennis.dto.user.UserDto;

import java.util.List;

public interface IPlayerDtoService {

	List<UserDto> createPlayersForOwner(Long id, List<UserDto> userDto);

	UserDto createPlayerForOwner(Long id, UserDto playerDto);

	List<UserDto> getAllPlayersForOwner(Long id);

	List<PlayerDto> getPlayersStatusForOwner(Long id);

	PlayerDto getPlayerStatus(Long id);
}

package com.korolar.itennis.service.dto.player;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korolar.itennis.dto.schedule.ScheduleDto;
import com.korolar.itennis.dto.subpackage.SubPackageDto;
import com.korolar.itennis.dto.user.PlayerDto;
import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.service.businessdao.user.IOwnerService;
import com.korolar.itennis.service.businessdao.user.IPlayerService;
import com.korolar.itennis.service.dto.schedule.ISchedulerDtoService;
import com.korolar.itennis.service.dto.subpackage.ISubPackageDtoService;
import com.korolar.itennis.service.dto.user.IUserDtoService;

@Service
public class PlayerDtoService implements IPlayerDtoService {

	@Autowired
	private IPlayerService playerService;

	@Autowired
	private IOwnerService ownerService;

	@Autowired
	private IUserDtoService userDtoService;

	@Autowired
	private ISchedulerDtoService schedulerService;

	@Autowired
	private ISubPackageDtoService packageDtoService;

	@Override
	public List<UserDto> createPlayersForOwner(Long id, List<UserDto> userDto) {
		return userDto.stream().map(x -> createPlayerForOwner(id, x)).collect(Collectors.toList());
	}

	@Override
	public UserDto createPlayerForOwner(Long id, UserDto userDto) {
		//map basic fields
		User userToBeSaved = userDtoService.getDtoAsEntity(userDto);

		User savedUser = playerService.createPlayerForOwner(userToBeSaved, id);

		return userDtoService.getEntityAsDto(savedUser);
	}

	@Override
	public List<UserDto> getAllPlayersForOwner(Long id) {
		User user = ownerService.getOwnerById(id);
		List<User> playersForClub = playerService.getPlayersForClub(user.getClub());
		return userDtoService.fromEntities(playersForClub);
	}

	@Override
	public List<PlayerDto> getPlayersStatusForOwner(Long id) {
		User user = ownerService.getOwnerById(id);
		List<User> playersForClub = playerService.getPlayersForClub(user.getClub());
		return playersForClub.stream().map(this::getBasicPlayerInfoForUser).collect(Collectors.toList());
	}

	@Override
	public PlayerDto getPlayerStatus(Long id) {
		User user = playerService.getPlayerById(id);
		return getPlayerInfoDtoForUser(user);
	}

	private PlayerDto getPlayerInfoDtoForUser(User user) {
		List<ScheduleDto> scheduleForPlayer = schedulerService.getScheduleForPlayer(user.getId());
		List<SubPackageDto> allPackagesForUser = packageDtoService.getAllPackagesForUser(user.getId());
		PlayerDto basicPlayerInfoForUser = getBasicPlayerInfoForUser(user);
		basicPlayerInfoForUser.setSubPackageDtos(allPackagesForUser);
		basicPlayerInfoForUser.setScheduleList(scheduleForPlayer);
		return basicPlayerInfoForUser;
	}

	private PlayerDto getBasicPlayerInfoForUser(User user) {
		PlayerDto playerDto = new PlayerDto();
		playerDto.setId(user.getId());
		playerDto.setFirstName(user.getFirstName());
		playerDto.setLastName(user.getLastName());
		playerDto.setActivePackageAmount(user.getLeftAmount());
		return playerDto;
	}

}

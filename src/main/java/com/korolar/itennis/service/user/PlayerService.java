package com.korolar.itennis.service.user;

import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.service.schedule.ScheduleService;
import com.korolar.itennis.service.utils.BusinessRuleUtils;
import com.korolar.itennis.service.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.korolar.itennis.dto.user.PlayerDto;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.repositories.UserRepository;

import java.util.NoSuchElementException;

@Service(value = "player_service")
public class PlayerService implements IUserService<PlayerDto, User> {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public PlayerDto getEntityAsDto(Long id) {
		return fromEntity(getUserFromRepository(id, EBusinessRole.ROLE_PLAYER));
	}

	@Override
	public PlayerDto fromEntity(User user) {
		PlayerDto playerDto = new PlayerDto();
		UserUtils.mapFromUserToDto(user, playerDto);
		playerDto.setScheduleList(scheduleService.fromEntities(user.getScheduleList()));
		playerDto.setActivePackageAmount(user.getLeftAmount());
		return playerDto;
	}

	/**
	 * Get by user Id from repository
	 * 
	 * @param id
	 * @return if found return User, throw exception otherwise
	 */
	private User getUserFromRepository(Long id, EBusinessRole eBusinessRole) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceAccessException("User with id " + id + " not found"));

		if (BusinessRuleUtils.checkIfUserHasBusinessRole(user, eBusinessRole)) {
			return user;
		} else {
			throw new NoSuchElementException("User with id " + id + " is not " + eBusinessRole);
		}
	}

}

package com.korolar.itennis.service.user;

import com.korolar.itennis.dto.user.TrainerDto;
import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.service.schedule.ScheduleService;
import com.korolar.itennis.service.utils.BusinessRuleUtils;
import com.korolar.itennis.service.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.korolar.itennis.entity.User;
import com.korolar.itennis.repositories.UserRepository;

import java.util.NoSuchElementException;

@Service(value = "trainer_service")
public class TrainerService implements IUserService<TrainerDto, User> {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public TrainerDto getEntityAsDto(Long id) {
		return fromEntity(getUserFromRepository(id, EBusinessRole.ROLE_TRAINER));
	}

	@Override
	public TrainerDto fromEntity(User user) {
		TrainerDto trainerDto = new TrainerDto();
		UserUtils.mapFromUserToDto(user, trainerDto);
		trainerDto.setScheduleList(scheduleService.fromEntities(user.getScheduleList()));
		return trainerDto;
	}

	public void mapFromUserToDto(User user, UserDto userDto) {
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
	}

	/**
	 * Get Trainer by UserId from repository
	 * 
	 * @param id
	 * @return if found return Trainer, throw exception otherwise
	 */
	private User getUserFromRepository(Long id, EBusinessRole businessRole) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceAccessException("Trainer with id " + id + " not found"));

		if (BusinessRuleUtils.checkIfUserHasBusinessRole(user, businessRole)) {
			return user;
		} else {
			throw new NoSuchElementException("User with id " + user.getId() + " is not " + businessRole);
		}
	}

}

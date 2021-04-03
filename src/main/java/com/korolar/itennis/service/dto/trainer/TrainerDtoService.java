package com.korolar.itennis.service.dto.trainer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korolar.itennis.dto.user.TrainerDto;
import com.korolar.itennis.entity.Club;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.service.schedule.ISchedulerService;
import com.korolar.itennis.service.user.IUserService;

@Service
public class TrainerDtoService implements ITrainerDtoService {

	@Autowired
	private ISchedulerService scheduleService;

	@Autowired
	private IUserService userService;

	@Override
	public List<TrainerDto> getTrainersWithScheduleForClub(Club club) {
		List<User> listUsers = userService.getTrainersForClub(club);

		if (CollectionUtils.isEmpty(listUsers)) {
			//throw exception - no trainers assigned to this club
			return new ArrayList<>();
		}

		ArrayList<TrainerDto> trainerDtos = new ArrayList<>();

		for (User user : listUsers) {
			//maps
			TrainerDto trainerDto = new TrainerDto();
			trainerDto.setId(user.getId());
			trainerDto.setFirstName(user.getFirstName());
			trainerDto.setLastName(user.getLastName());
			trainerDto.setScheduleList(scheduleService.getScheduleForTrainer(user.getId()));
			//add
			trainerDtos.add(trainerDto);
		}

		return trainerDtos;
	}

	@Override
	public List<TrainerDto> getTrainersWithScheduleForOwner(Long id) {
		User user = userService.getUserWithBusinessRole(id, EBusinessRole.ROLE_OWNER);
		return getTrainersWithScheduleForClub(user.getClub());
	}
}

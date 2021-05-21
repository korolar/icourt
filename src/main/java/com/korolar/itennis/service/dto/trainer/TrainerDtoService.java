package com.korolar.itennis.service.dto.trainer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korolar.itennis.dto.user.TrainerDto;
import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.entity.Club;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.service.businessdao.user.IOwnerService;
import com.korolar.itennis.service.businessdao.user.ITrainerService;
import com.korolar.itennis.service.dto.schedule.ISchedulerDtoService;
import com.korolar.itennis.service.dto.user.IUserDtoService;

@Service
public class TrainerDtoService implements ITrainerDtoService {

	@Autowired
	private ISchedulerDtoService scheduleService;

	@Autowired
	private IOwnerService ownerService;

	@Autowired
	private ITrainerService trainerService;

	@Autowired
	private IUserDtoService userDtoService;

	@Override
	public List<TrainerDto> getTrainersWithScheduleForOwner(Long id) {
		User user = ownerService.getOwnerById(id);
		return getTrainersWithScheduleForClub(user.getClub());
	}

	@Override
	public List<UserDto> getAllTrainersForOwner(Long id) {
		User user = ownerService.getOwnerById(id);
		return userDtoService.fromEntities(trainerService.getTrainersForClub(user.getClub()));
	}

	private List<TrainerDto> getTrainersWithScheduleForClub(Club club) {
		List<User> listUsers = trainerService.getTrainersForClub(club);

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
}

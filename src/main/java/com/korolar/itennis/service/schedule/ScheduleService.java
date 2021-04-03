package com.korolar.itennis.service.schedule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korolar.itennis.dto.user.ScheduleDto;
import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.service.dto.user.IUserDtoService;
import com.korolar.itennis.service.user.IUserService;

@Service
public class ScheduleService implements ISchedulerService {

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserDtoService userDtoService;

	@Override
	public List<ScheduleDto> getScheduleForPlayer(Long id) {
		User user = userService.getUserWithBusinessRole(id, EBusinessRole.ROLE_PLAYER);

		List<ScheduleDto> scheduleDtoList = new ArrayList<>();
		for (Schedule schedule : user.getScheduleList()) {
			ScheduleDto scheduleDto = fromEntity(schedule);
			scheduleDto.setTrainerDto(userDtoService.getEntityAsDto(userService.getTrainerForSchedule(schedule)));
			scheduleDtoList.add(scheduleDto);
		}

		return scheduleDtoList;
	}

	@Override
	public List<ScheduleDto> getScheduleForTrainer(Long id) {
		User user = userService.getUserWithBusinessRole(id, EBusinessRole.ROLE_TRAINER);

		List<ScheduleDto> scheduleDtoList = new ArrayList<>();
		for (Schedule schedule : user.getScheduleList()) {
			ScheduleDto scheduleDto = fromEntity(schedule);
			scheduleDto.setPlayers(userDtoService.fromEntities(userService.getPlayersForSchedule(schedule)));
			scheduleDtoList.add(scheduleDto);
		}

		return scheduleDtoList;
	}

	private ScheduleDto fromEntity(Schedule schedule) {
		ScheduleDto scheduleDto = new ScheduleDto();
		scheduleDto.setBeginning(schedule.getBeginning());
		scheduleDto.setEnd(schedule.getEnd());
		scheduleDto.setClub(schedule.getClub().getName());
		scheduleDto.setValue(schedule.getValue());
		scheduleDto.setPlayed(schedule.getPlayed());
		return scheduleDto;
	}

}

package com.korolar.itennis.service.dto.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.korolar.itennis.service.businessdao.schedule.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korolar.itennis.dto.location.LocationDto;
import com.korolar.itennis.dto.schedule.ScheduleDto;
import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.service.businessdao.user.IPlayerService;
import com.korolar.itennis.service.businessdao.user.ITrainerService;
import com.korolar.itennis.service.dao.location.ILocationDaoService;
import com.korolar.itennis.service.dao.schedule.IScheduleDaoService;
import com.korolar.itennis.service.dto.user.IUserDtoService;

@Service
public class ScheduleDtoService implements ISchedulerDtoService {

	@Autowired
	private IPlayerService playerService;

	@Autowired
	private ITrainerService trainerService;

	@Autowired
	private IUserDtoService userDtoService;

	@Autowired
	private IScheduleService scheduleService;

	@Override
	public List<ScheduleDto> createSchedule(List<ScheduleDto> createScheduleDto) {
		return createScheduleDto.stream().map(s -> createSchedule(s)).collect(Collectors.toList());
	}

	private ScheduleDto createSchedule(ScheduleDto createScheduleDto) {
		Schedule schedule = new Schedule();

		schedule.setBeginning(createScheduleDto.getBeginning());
		schedule.setEnd(createScheduleDto.getEnd());
		schedule.setValue(createScheduleDto.getValue());

		List<Long> playersId = createScheduleDto.getPlayers().stream().map(UserDto::getId).collect(Collectors.toList());
		Long locationId = createScheduleDto.getLocation().getId();
		Long trainerId = createScheduleDto.getTrainer().getId();

		Schedule scheduleSaved = scheduleService.createSchedule(schedule, trainerId, playersId, locationId);

		return fromEntity(scheduleSaved);

	}

	@Override
	public List<ScheduleDto> getScheduleForPlayer(Long id) {
		User user = playerService.getPlayerById(id);

		List<ScheduleDto> scheduleDtoList = new ArrayList<>();
		for (Schedule schedule : user.getScheduleList()) {
			ScheduleDto scheduleDto = fromEntity(schedule);
			scheduleDto.setTrainer(userDtoService.getEntityAsDto(trainerService.getTrainerForSchedule(schedule)));
			scheduleDtoList.add(scheduleDto);
		}

		return scheduleDtoList;
	}

	@Override
	public List<ScheduleDto> getScheduleForTrainer(Long id) {
		User user = trainerService.getTrainerById(id);

		List<ScheduleDto> scheduleDtoList = new ArrayList<>();
		for (Schedule schedule : user.getScheduleList()) {
			ScheduleDto scheduleDto = fromEntity(schedule);
			scheduleDto.setPlayers(userDtoService.fromEntities(playerService.getPlayersForSchedule(schedule)));
			scheduleDtoList.add(scheduleDto);
		}

		return scheduleDtoList;
	}

	private ScheduleDto fromEntity(Schedule schedule) {
		ScheduleDto scheduleDto = new ScheduleDto();
		scheduleDto.setBeginning(schedule.getBeginning());
		scheduleDto.setEnd(schedule.getEnd());
		LocationDto locationDto = new LocationDto();
		locationDto.setName(schedule.getLocation().getName());
		scheduleDto.setLocation(locationDto);
		scheduleDto.setValue(schedule.getValue());
		scheduleDto.setPlayed(schedule.getPlayed());
		return scheduleDto;
	}

}

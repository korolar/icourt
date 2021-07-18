package com.korolar.itennis.service.businessdao.schedule;

import java.util.List;

import org.springframework.stereotype.Service;

import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.service.businessdao.user.IPlayerService;
import com.korolar.itennis.service.businessdao.user.ITrainerService;
import com.korolar.itennis.service.dao.location.ILocationDaoService;
import com.korolar.itennis.service.dao.schedule.IScheduleDaoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService {

	private final IScheduleDaoService scheduleDaoService;
	private final IPlayerService playerService;
	private final ITrainerService trainerService;
	private final ILocationDaoService locationDaoService;

	@Override
	public Schedule createSchedule(Schedule schedule, Long trainerId, List<Long> playersId, Long locationId) {

		schedule.setLocation(locationDaoService.getById(locationId));

		Schedule scheduleSaved = scheduleDaoService.saveSchedule(schedule);

		//todo: add check that trainer is provided
		trainerService.addScheduleToTrainer(trainerId, scheduleSaved);

		//todo: add check that at least one player is provided
		playerService.addScheduleToPlayers(playersId, scheduleSaved);

		return scheduleSaved;
	}
}

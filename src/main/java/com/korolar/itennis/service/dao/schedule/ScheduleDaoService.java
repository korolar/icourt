package com.korolar.itennis.service.dao.schedule;

import org.springframework.stereotype.Service;

import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.repositories.ScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleDaoService implements IScheduleDaoService {

	private final ScheduleRepository scheduleRepository;

	@Override
	public Schedule saveSchedule(Schedule schedule) {
		return scheduleRepository.save(schedule);
	}
}

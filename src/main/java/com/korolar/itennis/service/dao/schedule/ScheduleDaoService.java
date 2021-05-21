package com.korolar.itennis.service.dao.schedule;

import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleDaoService implements IScheduleDaoService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override public Schedule saveSchedule(Schedule schedule) {
		return scheduleRepository.save(schedule);
	}
}

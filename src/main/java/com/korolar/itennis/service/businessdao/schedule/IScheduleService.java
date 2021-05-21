package com.korolar.itennis.service.businessdao.schedule;

import com.korolar.itennis.entity.Schedule;

import java.util.List;

public interface IScheduleService {

	Schedule createSchedule(Schedule schedule, Long trainerId, List<Long> playersId, Long locationId);
}

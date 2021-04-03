package com.korolar.itennis.service.schedule;

import com.korolar.itennis.dto.user.ScheduleDto;
import com.korolar.itennis.entity.Schedule;

import java.util.List;
import java.util.stream.Collectors;

public interface ISchedulerService {

	List<ScheduleDto> getScheduleForPlayer(Long id);

	List<ScheduleDto> getScheduleForTrainer(Long id);

}

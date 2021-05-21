package com.korolar.itennis.service.dto.schedule;

import java.util.List;

import com.korolar.itennis.dto.schedule.ScheduleDto;

public interface ISchedulerDtoService {

	List<ScheduleDto> createSchedule(List<ScheduleDto> createScheduleDto);

	List<ScheduleDto> getScheduleForPlayer(Long id);

	List<ScheduleDto> getScheduleForTrainer(Long id);

}

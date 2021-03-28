package com.korolar.itennis.service.schedule;

import com.korolar.itennis.dto.user.ScheduleDto;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service(value = "schedule_service")
public class ScheduleService implements ISchedulerService<ScheduleDto, Schedule> {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public ScheduleDto getEntityAsDto(Long id) {
		return fromEntity(getScheduleFromRepository(id));
	}

	@Override
	public ScheduleDto fromEntity(Schedule schedule) {
		ScheduleDto scheduleDto = new ScheduleDto();
		scheduleDto.setBeginning(schedule.getBeginning());
		scheduleDto.setEnd(schedule.getEnd());
		scheduleDto.setClub(schedule.getClub().getName());
		scheduleDto.setValue(schedule.getValue());
		scheduleDto.setPlayed(schedule.getPlayed());
		return scheduleDto;
	}

	/**
	 * Get by user Id from repository
	 * 
	 * @param id
	 * @return if found return User, throw exception otherwise
	 */
	private Schedule getScheduleFromRepository(Long id) {
		return scheduleRepository.findById(id).orElseThrow(() -> new ResourceAccessException("User with id " + id + " not found"));
	}

}

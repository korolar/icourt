package com.korolar.itennis.resource;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.korolar.itennis.dto.schedule.ScheduleDto;
import com.korolar.itennis.service.dto.schedule.ISchedulerDtoService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TrainerController {

	private final ISchedulerDtoService schedulerService;

	@GetMapping(value = "/trainer/{id}")
	public List<ScheduleDto> getScheduleForTrainer(@PathVariable("id") Long id) {
		return schedulerService.getScheduleForTrainer(id);
	}

}

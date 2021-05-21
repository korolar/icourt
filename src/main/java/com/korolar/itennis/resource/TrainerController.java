package com.korolar.itennis.resource;

import com.korolar.itennis.dto.schedule.ScheduleDto;
import com.korolar.itennis.service.dto.schedule.ISchedulerDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TrainerController {

	@Autowired
	private ISchedulerDtoService schedulerService;

	@GetMapping(value = "/trainer/{id}")
	public List<ScheduleDto> getScheduleForTrainer(@PathVariable("id") Long id) {
		return schedulerService.getScheduleForTrainer(id);
	}

}

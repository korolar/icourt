package com.korolar.itennis.resource;

import com.korolar.itennis.dto.user.ScheduleDto;
import com.korolar.itennis.service.schedule.ISchedulerService;
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
	private ISchedulerService schedulerService;

	@GetMapping(value = "/trainer/{id}")
	public List<ScheduleDto> getUserById(@PathVariable("id") Long id) {
		return schedulerService.getScheduleForTrainer(id);
	}

}

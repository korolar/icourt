package com.korolar.itennis.resource;

import java.util.List;

import com.korolar.itennis.service.schedule.ISchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.korolar.itennis.dto.user.ScheduleDto;

@RestController
@CrossOrigin(origins = "*")
public class PlayerController {

	@Autowired
	private ISchedulerService schedulerService;

	@GetMapping(value = "/player/{id}")
	public List<ScheduleDto> getScheduleForPlayer(@PathVariable("id") Long id) {
		return schedulerService.getScheduleForPlayer(id);
	}

}

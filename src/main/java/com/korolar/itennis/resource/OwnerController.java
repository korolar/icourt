package com.korolar.itennis.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.korolar.itennis.dto.user.TrainerDto;
import com.korolar.itennis.service.dto.trainer.ITrainerDtoService;

@RestController
@CrossOrigin(origins = "*")
public class OwnerController {

	@Autowired
	private ITrainerDtoService iTrainerService;

	@GetMapping(value = "/owner/{id}")
	public List<TrainerDto> getTrainersForOwner(@PathVariable("id") Long id) {
		return iTrainerService.getTrainersWithScheduleForOwner(id);
	}

}

package com.korolar.itennis.resource;

import com.korolar.itennis.dto.user.TrainerDto;
import com.korolar.itennis.service.user.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class TrainerController {

	@Autowired
	private TrainerService trainerService;

	@GetMapping(value = "/trainer/{id}")
	public TrainerDto getUserById(@PathVariable("id") Long id) {
		return trainerService.getEntityAsDto(id);
	}

}

package com.korolar.itennis.service.dto.trainer;

import java.util.List;

import com.korolar.itennis.dto.user.TrainerDto;
import com.korolar.itennis.dto.user.UserDto;

public interface ITrainerDtoService {

	List<TrainerDto> getTrainersWithScheduleForOwner(Long id);

	List<UserDto> getAllTrainersForOwner(Long id);
}

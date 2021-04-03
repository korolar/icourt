package com.korolar.itennis.service.dto.trainer;

import java.util.List;

import com.korolar.itennis.dto.user.TrainerDto;
import com.korolar.itennis.entity.Club;

public interface ITrainerDtoService {

	List<TrainerDto> getTrainersWithScheduleForClub(Club club);

	List<TrainerDto> getTrainersWithScheduleForOwner(Long id);
}

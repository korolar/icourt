package com.korolar.itennis.dto.user;

import java.io.Serializable;
import java.util.List;

public class OwnerDto extends TrainerDto implements Serializable {

	private List<TrainerDto> trainerList;

	public List<TrainerDto> getTrainerList() {
		return trainerList;
	}

	public void setTrainerList(List<TrainerDto> trainerList) {
		this.trainerList = trainerList;
	}
}

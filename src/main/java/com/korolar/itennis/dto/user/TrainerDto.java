package com.korolar.itennis.dto.user;

import java.io.Serializable;
import java.util.List;

import com.korolar.itennis.dto.schedule.ScheduleDto;

import lombok.Data;

@Data
public class TrainerDto extends UserDto implements Serializable {

	private List<ScheduleDto> scheduleList;

}

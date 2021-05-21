package com.korolar.itennis.dto.user;

import com.korolar.itennis.dto.schedule.ScheduleDto;

import java.io.Serializable;
import java.util.List;



public class TrainerDto extends UserDto implements Serializable {

	private List<ScheduleDto> scheduleList;

	public List<ScheduleDto> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<ScheduleDto> scheduleList) {
		this.scheduleList = scheduleList;
	}
}

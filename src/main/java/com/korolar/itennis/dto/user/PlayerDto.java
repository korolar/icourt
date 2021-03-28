package com.korolar.itennis.dto.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PlayerDto extends UserDto implements Serializable {

	private BigDecimal activePackageAmount;
	private List<ScheduleDto> scheduleList;

	public BigDecimal getActivePackageAmount() {
		return activePackageAmount;
	}

	public void setActivePackageAmount(BigDecimal activePackageAmount) {
		this.activePackageAmount = activePackageAmount;
	}

	public List<ScheduleDto> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<ScheduleDto> scheduleList) {
		this.scheduleList = scheduleList;
	}
}

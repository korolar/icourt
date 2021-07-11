package com.korolar.itennis.dto.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.korolar.itennis.dto.schedule.ScheduleDto;
import com.korolar.itennis.dto.subpackage.SubPackageDto;

import lombok.Data;

@Data
public class PlayerDto extends UserDto implements Serializable {

	private BigDecimal activePackageAmount;
	private List<ScheduleDto> scheduleList;
	private List<SubPackageDto> subPackageDtos;

}

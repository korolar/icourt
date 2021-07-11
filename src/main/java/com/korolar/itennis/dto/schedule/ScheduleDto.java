package com.korolar.itennis.dto.schedule;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.korolar.itennis.dto.location.LocationDto;
import com.korolar.itennis.dto.user.UserDto;

import lombok.Data;

@Data
public class ScheduleDto implements Serializable {

	private Long id;
	private BigDecimal value;
	private Boolean played;
	private OffsetDateTime beginning;
	private OffsetDateTime end;
	private LocationDto location;
	private UserDto trainer;
	private List<UserDto> players;
}

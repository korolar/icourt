package com.korolar.itennis.dto.schedule;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.korolar.itennis.dto.location.LocationDto;
import com.korolar.itennis.dto.user.UserDto;

public class ScheduleDto implements Serializable {

	private Long id;
	private BigDecimal value;
	private Boolean played;
	private OffsetDateTime beginning;
	private OffsetDateTime end;
	private LocationDto location;
	private UserDto trainer;
	private List<UserDto> players;

	public List<UserDto> getPlayers() {
		return players;
	}

	public void setPlayers(List<UserDto> players) {
		this.players = players;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Boolean getPlayed() {
		return played;
	}

	public void setPlayed(Boolean played) {
		this.played = played;
	}

	public OffsetDateTime getBeginning() {
		return beginning;
	}

	public void setBeginning(OffsetDateTime beginning) {
		this.beginning = beginning;
	}

	public OffsetDateTime getEnd() {
		return end;
	}

	public void setEnd(OffsetDateTime end) {
		this.end = end;
	}

	public LocationDto getLocation() {
		return location;
	}

	public void setLocation(LocationDto location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDto getTrainer() {
		return trainer;
	}

	public void setTrainer(UserDto trainer) {
		this.trainer = trainer;
	}
}

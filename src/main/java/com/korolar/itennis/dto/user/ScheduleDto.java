package com.korolar.itennis.dto.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleDto implements Serializable {

	private Long id;
	private BigDecimal value;
	private Boolean played;
	private LocalDateTime beginning;
	private LocalDateTime end;
	private String club;
	private UserDto trainerDto;
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

	public LocalDateTime getBeginning() {
		return beginning;
	}

	public void setBeginning(LocalDateTime beginning) {
		this.beginning = beginning;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDto getTrainerDto() {
		return trainerDto;
	}

	public void setTrainerDto(UserDto trainerDto) {
		this.trainerDto = trainerDto;
	}
}

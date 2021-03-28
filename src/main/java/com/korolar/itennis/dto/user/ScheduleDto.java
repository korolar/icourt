package com.korolar.itennis.dto.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ScheduleDto implements Serializable {

	private Long id;
	private BigDecimal value;
	private Boolean played;
	private LocalDateTime beginning;
	private LocalDateTime end;
	private String club;
	private TrainerDto trainerDto;

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

	public TrainerDto getTrainerDto() {
		return trainerDto;
	}

	public void setTrainerDto(TrainerDto trainerDto) {
		this.trainerDto = trainerDto;
	}
}

package com.korolar.itennis.dto.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TrainerScheduleDto extends ScheduleDto implements Serializable {

	private List<PlayerDto> players;

	public List<PlayerDto> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerDto> players) {
		this.players = players;
	}
}

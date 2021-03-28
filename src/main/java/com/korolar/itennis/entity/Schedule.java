package com.korolar.itennis.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Schedule {

	@Id
	@Column(name = "schedule_id")
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	private BigDecimal value;

	@NotNull
	private Boolean played;

	@NotNull
	private LocalDateTime beginning;

	@NotNull
	private LocalDateTime end;

	@ManyToOne
	@JoinTable(
			name = "schedule_club",
			joinColumns = @JoinColumn(name = "schedule_id"),
			inverseJoinColumns = @JoinColumn(name = "club_id")
	)
	private Club club;

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

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
}

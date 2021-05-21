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
import java.time.OffsetDateTime;
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
	private OffsetDateTime beginning;

	@NotNull
	private OffsetDateTime end;

	@ManyToOne
	@JoinTable(
			name = "schedule_location",
			joinColumns = @JoinColumn(name = "schedule_id"),
			inverseJoinColumns = @JoinColumn(name = "location_id")
	)
	private Location club;

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

	public Location getLocation() {
		return club;
	}

	public void setLocation(Location club) {
		this.club = club;
	}
}

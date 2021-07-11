package com.korolar.itennis.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;
import lombok.Data;

@Entity
@Data
public class Schedule {

	@Id
	@Column(name = "schedule_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
	@JoinTable(name = "schedule_location", joinColumns = @JoinColumn(name = "schedule_id"), inverseJoinColumns = @JoinColumn(name = "location_id"))
	private Location location;
}

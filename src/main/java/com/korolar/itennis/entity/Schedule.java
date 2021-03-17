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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private BigDecimal value;

	@NotNull
	private Boolean played;

	@NotNull
	private LocalDateTime date;

	@ManyToOne
	@JoinTable(
			name = "schedule_club",
			joinColumns = @JoinColumn(name = "schedule_id"),
			inverseJoinColumns = @JoinColumn(name = "club_id")
	)
	private Club club;

	@ManyToMany(mappedBy = "scheduleList")
	private List<User> users;
}

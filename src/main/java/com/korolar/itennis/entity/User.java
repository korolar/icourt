package com.korolar.itennis.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String username;

	@NotNull
	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_sec_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "security_role_id"))
	private Set<SecurityRole> securityRoles = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_bus_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "business_role_id"))
	private Set<BusinessRole> businessRoles = new HashSet<>();

	@ManyToOne
	@JoinTable(name = "users_club", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "club_id"))
	private Club club;

	@NotNull
	private BigDecimal leftAmount;

	@ManyToMany
	@JoinTable(name = "users_schedule", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "schedule_id"))
	private List<Schedule> scheduleList;

	@OneToMany
	@JoinTable(name = "users_package", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "package_id"))
	private List<SubscriptionPackage> packageList;
}

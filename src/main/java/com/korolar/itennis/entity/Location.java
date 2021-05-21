package com.korolar.itennis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Location {

	@Id
	@Column(name = "location_id")
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false, unique = true)
	@NotNull
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

}

package com.korolar.itennis.entity;

import com.korolar.itennis.enums.EBusinessRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class BusinessRole {

	@Id
	@Column(name = "business_role_id")
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;

	@Enumerated(EnumType.STRING)
	private EBusinessRole name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EBusinessRole getName() {
		return name;
	}

	public void setName(EBusinessRole name) {
		this.name = name;
	}
}

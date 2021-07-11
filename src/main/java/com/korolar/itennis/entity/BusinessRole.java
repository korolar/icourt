package com.korolar.itennis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.korolar.itennis.enums.EBusinessRole;

import lombok.Data;

@Entity
@Data
public class BusinessRole {

	@Id
	@Column(name = "business_role_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Enumerated(EnumType.STRING)
	private EBusinessRole name;

}

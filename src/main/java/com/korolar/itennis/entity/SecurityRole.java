package com.korolar.itennis.entity;

import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.enums.ESecurityRole;

import javax.persistence.*;
import java.util.List;

@Entity
public class SecurityRole {

	@Id
	@Column(name = "security_role_id")
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;

	@Enumerated(EnumType.STRING)
	private ESecurityRole name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ESecurityRole getName() {
		return name;
	}

	public void setName(ESecurityRole name) {
		this.name = name;
	}
}

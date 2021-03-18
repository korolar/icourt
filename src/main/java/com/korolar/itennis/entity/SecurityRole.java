package com.korolar.itennis.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class SecurityRole {

	@Id
	@Column(name = "security_role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
package com.korolar.itennis.dto.auth;

import java.util.List;

public class AuthorizationToken {
	private Long id;
	private String accessToken;
	private String username;
	private List<String> roles;

	public AuthorizationToken() {

	}

	public AuthorizationToken(String token, String username) {
		this.accessToken = token;
		this.username = username;
	}

	public AuthorizationToken(String token) {
		this.accessToken = token;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

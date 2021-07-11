package com.korolar.itennis.dto.auth;

import java.util.List;

import lombok.Data;

@Data
public class AuthorizationToken {
	private Long id;
	private String accessToken;
	private String username;
	private List<String> roles;

	public AuthorizationToken(String token, String username) {
		this.accessToken = token;
		this.username = username;
	}

}

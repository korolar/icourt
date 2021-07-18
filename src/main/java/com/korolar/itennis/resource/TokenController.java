package com.korolar.itennis.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.korolar.itennis.dto.auth.AuthorizationToken;
import com.korolar.itennis.dto.auth.LoginUser;
import com.korolar.itennis.service.token.ITokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/token")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TokenController {

	private final ITokenService tokenService;

	@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginUser authenticationRequest) {
		AuthorizationToken authorizationToken = tokenService.getAuthorizationToken(authenticationRequest);
		return ResponseEntity.ok(authorizationToken);
	}
}

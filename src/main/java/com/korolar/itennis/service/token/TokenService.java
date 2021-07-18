package com.korolar.itennis.service.token;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.korolar.itennis.dto.auth.AuthorizationToken;
import com.korolar.itennis.dto.auth.LoginUser;
import com.korolar.itennis.security.jwt.JwtTokenUtil;
import com.korolar.itennis.security.userdetails.CustomUserDetails;
import com.korolar.itennis.security.userdetails.MyUserDetailsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService implements ITokenService {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;
	private final MyUserDetailsService userDetailsService;

	@Override
	public AuthorizationToken getAuthorizationToken(LoginUser authenticationRequest) {
		//authenticate
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		//generate token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);

		CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
		List<String> roles = principal.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

		//build authorization token
		AuthorizationToken authToken = new AuthorizationToken(token, authenticationRequest.getUsername());
		authToken.setRoles(roles);
		authToken.setId(principal.getId());

		return authToken;
	}
}

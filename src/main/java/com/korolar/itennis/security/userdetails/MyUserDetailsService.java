package com.korolar.itennis.security.userdetails;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.korolar.itennis.entity.User;
import com.korolar.itennis.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User byUsername = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No user with username " + username));

		return buildCustomerUserDetails(byUsername);
	}

	private static CustomUserDetails buildCustomerUserDetails(User user) {
		List<GrantedAuthority> authorities = user.getSecurityRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		List<GrantedAuthority> authoritiesBusiness = user.getBusinessRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		authorities.addAll(authoritiesBusiness);

		return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), authorities);
	}
}

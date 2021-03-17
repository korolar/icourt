package com.korolar.biztravel;

import com.korolar.biztravel.entity.BusinessRole;
import com.korolar.biztravel.entity.SecurityRole;
import com.korolar.biztravel.entity.User;
import com.korolar.biztravel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//todo - check what to do if user doesnt exists
		User byUsername = userRepository.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException("No user with username " + username));

		return new org.springframework.security.core.userdetails.User(byUsername.getUsername(), byUsername.getPassword(), getAuthorities(byUsername));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
		//extract business and security roles
		Set<SecurityRole> securityRoles = user.getSecurityRoles();
		Set<BusinessRole> businessRoles = user.getBusinessRoles();

		//map to Authoritiess
		String[] userRoles = securityRoles.stream().map((role) -> role.getName()).toArray(String[]::new);
		String[] userBusinessRoles = businessRoles.stream().map((role) -> role.getName()).toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		Collection<GrantedAuthority> authoritiesBusiness = AuthorityUtils.createAuthorityList(userBusinessRoles);

		authorities.addAll(authoritiesBusiness);

		return authorities;
	}
}

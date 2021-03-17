package com.korolar.biztravel.startup;

import com.korolar.biztravel.entity.BusinessRole;
import com.korolar.biztravel.entity.SecurityRole;
import com.korolar.biztravel.entity.User;
import com.korolar.biztravel.enums.EBusinessRole;
import com.korolar.biztravel.enums.ESecurityRole;
import com.korolar.biztravel.repositories.BusinessRoleRepository;
import com.korolar.biztravel.repositories.SecurityRoleRepository;
import com.korolar.biztravel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityRoleRepository roleRepository;

	@Autowired
	private BusinessRoleRepository businessRoleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;
		BusinessRole player = createBusinessRuleIfNotFound(EBusinessRole.PLAYER.name());
		BusinessRole owner = createBusinessRuleIfNotFound(EBusinessRole.OWNER.name());

		SecurityRole admin = createSecurityRuleIfNotFound(ESecurityRole.ADMIN.name());

//		SecurityRole byName = roleRepository.findByName(ESecurityRole.ADMIN.name());
//		BusinessRole byNameBusinessRole = businessRoleRepository.findByName(EBusinessRole.OWNER.name());

		User user = getUser("Josipa", player, admin);
		User user2 = getUser("Zoran", owner, admin);
		User user3 = getUser("Andrea", owner, admin);

		userRepository.save(user);
		userRepository.save(user2);
		userRepository.save(user3);
		alreadySetup = true;
	}

	private User getUser(String name, BusinessRole player, SecurityRole admin) {
		User user = new User();
		user.setFirstName(name);
		user.setLastName(name);
		user.setUsername(name);
		user.setPassword(passwordEncoder.encode("test"));

		user.setBusinessRoles(Set.copyOf(Arrays.asList(player)));
		user.setSecurityRoles(Set.copyOf(Arrays.asList(admin)));
		return user;
	}

	@Transactional
	BusinessRole createBusinessRuleIfNotFound(String name) {

		BusinessRole businessRole = businessRoleRepository.findByName(name);
		if (businessRole == null) {
			businessRole = new BusinessRole();
			businessRole.setName(name);
			return businessRoleRepository.save(businessRole);
		}
		return businessRole;
	}

	@Transactional
	SecurityRole createSecurityRuleIfNotFound(String name) {

		SecurityRole businessRole = roleRepository.findByName(name);
		if (businessRole == null) {
			businessRole = new SecurityRole();
			businessRole.setName(name);
			return roleRepository.save(businessRole);
		}
		return businessRole;
	}
}
package com.korolar.itennis.startup;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

import javax.transaction.Transactional;

import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.korolar.itennis.entity.BusinessRole;
import com.korolar.itennis.entity.Club;
import com.korolar.itennis.entity.SecurityRole;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.enums.ESecurityRole;
import com.korolar.itennis.repositories.BusinessRoleRepository;
import com.korolar.itennis.repositories.ClubRepository;
import com.korolar.itennis.repositories.SecurityRoleRepository;
import com.korolar.itennis.repositories.UserRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClubRepository clubRepository;

	@Autowired
	private SecurityRoleRepository roleRepository;

	@Autowired
	private BusinessRoleRepository businessRoleRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;

		BusinessRole player = createBusinessRuleIfNotFound(EBusinessRole.ROLE_PLAYER);
		BusinessRole owner = createBusinessRuleIfNotFound(EBusinessRole.ROLE_OWNER);
		BusinessRole trainer = createBusinessRuleIfNotFound(EBusinessRole.ROLE_TRAINER);
		SecurityRole admin = createSecurityRuleIfNotFound(ESecurityRole.ROLE_ADMIN);
		SecurityRole read = createSecurityRuleIfNotFound(ESecurityRole.ROLE_READ);

		Club club = new Club();
		club.setName("Srki club");

		Club club1 = new Club();
		club1.setName("Precko");

		clubRepository.save(club);
		clubRepository.save(club1);

		Schedule schedule = new Schedule();
		schedule.setBeginning(LocalDateTime.now());
		schedule.setEnd(LocalDateTime.now().minusHours(2));
		schedule.setValue(BigDecimal.TEN);
		schedule.setClub(club1);
		schedule.setPlayed(Boolean.FALSE);

		Schedule schedule1 = new Schedule();
		schedule1.setBeginning(LocalDateTime.now());
		schedule1.setEnd(LocalDateTime.now().minusHours(2));
		schedule1.setValue(BigDecimal.TEN);
		schedule1.setClub(club1);
		schedule1.setPlayed(Boolean.TRUE);

		scheduleRepository.save(schedule);
		scheduleRepository.save(schedule1);

		User user = getUser("Goran", "Gogi", Set.copyOf(Arrays.asList(trainer)), admin, club);

		User user2 = getUser("Srdjan", "Rizvan", Set.copyOf(Arrays.asList(trainer, owner)), admin, club);

		User user3 = getUser("Tin", "Prpic", Set.copyOf(Arrays.asList(trainer)), read, club);
		user3.setScheduleList(Arrays.asList(schedule, schedule1));

		User user4 = getUser("Zoran", "Brajkovic", Set.copyOf(Arrays.asList(player)), admin, club);
		user4.setLeftAmount(BigDecimal.valueOf(100L));
		user4.setScheduleList(Arrays.asList(schedule, schedule1));

		User user5 = getUser("Boris", "Dika", Set.copyOf(Arrays.asList(player)), admin, club);
		user5.setLeftAmount(BigDecimal.valueOf(100L));
		user5.setScheduleList(Arrays.asList(schedule, schedule1));

		userRepository.save(user);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		userRepository.save(user5);
		alreadySetup = true;
	}

	private User getUser(String name, String lastName, Set<BusinessRole> businessRoles, SecurityRole admin, Club club) {

		User user = new User();
		user.setFirstName(name);
		user.setLastName(lastName);
		user.setUsername(name);
		user.setClub(club);
		user.setPassword(passwordEncoder.encode("testing"));
		user.setBusinessRoles(businessRoles);
		user.setSecurityRoles(Set.copyOf(Arrays.asList(admin)));
		return user;
	}

	@Transactional
	BusinessRole createBusinessRuleIfNotFound(EBusinessRole name) {

		BusinessRole businessRole = businessRoleRepository.findByName(name);
		if (businessRole == null) {
			businessRole = new BusinessRole();
			businessRole.setName(name);
			return businessRoleRepository.save(businessRole);
		}
		return businessRole;
	}

	@Transactional
	SecurityRole createSecurityRuleIfNotFound(ESecurityRole name) {

		SecurityRole securityRole = roleRepository.findByName(name);
		if (securityRole == null) {
			securityRole = new SecurityRole();
			securityRole.setName(name);
			return roleRepository.save(securityRole);
		}
		return securityRole;
	}
}

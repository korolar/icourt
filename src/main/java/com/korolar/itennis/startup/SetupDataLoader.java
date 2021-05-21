package com.korolar.itennis.startup;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Set;

import javax.transaction.Transactional;

import com.korolar.itennis.service.dao.location.ILocationDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.korolar.itennis.entity.BusinessRole;
import com.korolar.itennis.entity.Club;
import com.korolar.itennis.entity.Location;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.SecurityRole;
import com.korolar.itennis.entity.SubscriptionPackage;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.enums.ESecurityRole;
import com.korolar.itennis.repositories.BusinessRoleRepository;
import com.korolar.itennis.repositories.ClubRepository;
import com.korolar.itennis.repositories.SubscriptionPackageRepository;
import com.korolar.itennis.repositories.ScheduleRepository;
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
	private ILocationDaoService locationDaoService;

	@Autowired
	private SecurityRoleRepository roleRepository;

	@Autowired
	private BusinessRoleRepository businessRoleRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private SubscriptionPackageRepository packageRepository;

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

		clubRepository.save(club);

		Location location = new Location();
		location.setName("Precko");

		Location location1 = new Location();
		location1.setName("Ponikve");

		locationDaoService.saveLocation(location);
		locationDaoService.saveLocation(location1);

		Schedule schedule = new Schedule();
		schedule.setBeginning(OffsetDateTime.now().minusHours(10));
		schedule.setEnd(OffsetDateTime.now().minusHours(8));
		schedule.setValue(BigDecimal.TEN);
		schedule.setLocation(location);
		schedule.setPlayed(Boolean.FALSE);

		Schedule schedule2 = new Schedule();
		schedule2.setBeginning(OffsetDateTime.now().minusHours(9));
		schedule2.setEnd(OffsetDateTime.now().minusHours(7));
		schedule2.setValue(BigDecimal.TEN);
		schedule2.setLocation(location);
		schedule2.setPlayed(Boolean.FALSE);

		Schedule schedule1 = new Schedule();
		schedule1.setBeginning(OffsetDateTime.now().minusHours(6));
		schedule1.setEnd(OffsetDateTime.now().minusHours(5));
		schedule1.setValue(BigDecimal.TEN);
		schedule1.setLocation(location1);
		schedule1.setPlayed(Boolean.TRUE);

		Schedule schedule3 = new Schedule();
		schedule3.setBeginning(OffsetDateTime.now().minusHours(3));
		schedule3.setEnd(OffsetDateTime.now().minusHours(2));
		schedule3.setValue(BigDecimal.TEN);
		schedule3.setLocation(location1);
		schedule3.setPlayed(Boolean.TRUE);

		scheduleRepository.save(schedule);
		scheduleRepository.save(schedule1);
		scheduleRepository.save(schedule2);
		scheduleRepository.save(schedule3);

		SubscriptionPackage aPackage = new SubscriptionPackage();
		aPackage.setAmount(BigDecimal.valueOf(1000L));
		aPackage.setValidUntil(LocalDateTime.now().plusYears(1));
		aPackage.setPurchaseDate(LocalDateTime.now());

		SubscriptionPackage aPackage1 = new SubscriptionPackage();
		aPackage1.setAmount(BigDecimal.valueOf(1000L));
		aPackage1.setValidUntil(LocalDateTime.now().plusYears(1));
		aPackage1.setPurchaseDate(LocalDateTime.now());

		SubscriptionPackage savedPackage1 = packageRepository.save(aPackage);
		SubscriptionPackage savedPackage2 = packageRepository.save(aPackage1);

		User user = getUser("Goran", "Gogi", Set.copyOf(Arrays.asList(trainer)), admin, club);
		user.setScheduleList(Arrays.asList(schedule1));

		User user2 = getUser("Srdjan", "Rizvan", Set.copyOf(Arrays.asList(trainer, owner)), admin, club);
		user2.setScheduleList(Arrays.asList(schedule));

		User user3 = getUser("Tin", "Prpic", Set.copyOf(Arrays.asList(trainer)), read, club);
		user3.setScheduleList(Arrays.asList(schedule2, schedule3));

		User user4 = getUser("Zoran", "Brajkovic", Set.copyOf(Arrays.asList(player)), admin, club);
		user4.setLeftAmount(BigDecimal.valueOf(100L));
		user4.setScheduleList(Arrays.asList(schedule, schedule1, schedule2));
		user4.setPackageList(Arrays.asList(savedPackage1));
		User user5 = getUser("Josipa", "Dika", Set.copyOf(Arrays.asList(player)), admin, club);
		user5.setLeftAmount(BigDecimal.valueOf(100L));
		user5.setScheduleList(Arrays.asList(schedule, schedule1, schedule3));
		user5.setPackageList(Arrays.asList(savedPackage2));

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

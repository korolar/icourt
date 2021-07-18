package com.korolar.itennis.service.dao.user;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.korolar.itennis.entity.BusinessRole;
import com.korolar.itennis.entity.Club;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.repositories.UserRepository;
import com.korolar.itennis.service.dao.role.IRoleDaoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDaoService implements IUserDaoService {

	private final UserRepository userRepository;
	private final IRoleDaoService roleDaoService;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<User> getUsersByScheduleAndBusinessRole(Schedule schedule, EBusinessRole eBusinessRole) {
		BusinessRole byName = roleDaoService.getByName(eBusinessRole);
		return userRepository.findByScheduleListIsContainingAndBusinessRolesIsContaining(schedule, byName);
	}

	@Override
	public List<User> getUsersByClubAndRole(Club club, EBusinessRole eBusinessRole) {
		BusinessRole byName = roleDaoService.getByName(eBusinessRole);
		return userRepository.findByBusinessRolesIsContainingAndClub(byName, club);
	}

	@Override
	public User createUser(User user) {
		//temporary
		user.setUsername(user.getFirstName());
		user.setPassword(passwordEncoder.encode("testing"));
		return userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	/**
	 * Get by user Id from repository
	 * 
	 * @param id
	 * @return if found return User, throw exception otherwise
	 */
	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User with id " + id + " not found!"));
	}

	/**
	 * Get user with business role
	 *
	 * @param id
	 * @return if found return Trainer, throw exception otherwise
	 */
	@Override
	public User getUserWithBusinessRole(Long id, EBusinessRole eBusinessRole) {
		Optional<User> user = userRepository.findByIdAndBusinessRolesName(id, eBusinessRole);
		return user.orElseThrow(() -> new NoSuchElementException("User with id " + id + " and role " + eBusinessRole.name() + " not found!"));
	}
}

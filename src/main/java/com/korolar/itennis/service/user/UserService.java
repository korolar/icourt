package com.korolar.itennis.service.user;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.korolar.itennis.entity.Club;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korolar.itennis.entity.BusinessRole;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.repositories.BusinessRoleRepository;
import com.korolar.itennis.repositories.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BusinessRoleRepository businessRoleRepository;

	@Override
	public List<User> getPlayersForSchedule(Schedule schedule) {
		BusinessRole byName = businessRoleRepository.findByName(EBusinessRole.ROLE_PLAYER);
		return userRepository.findByScheduleListIsContainingAndBusinessRolesIsContaining(schedule, byName);
	}

	@Override
	public User getTrainerForSchedule(Schedule schedule) {
		BusinessRole byName = businessRoleRepository.findByName(EBusinessRole.ROLE_TRAINER);
		List<User> listUsers = userRepository.findByScheduleListIsContainingAndBusinessRolesIsContaining(schedule, byName);

		if (CollectionUtils.isEmpty(listUsers)) {
			//throw some exception bcs no trainers are assigned to schedule
			return null;
		}

		if (listUsers.size() > 1) {
			//throw some exception bcs to much trainers are assigned to schedule
			return null;
		}

		return listUsers.get(0);
	}

	@Override
	public List<User> getTrainersForClub(Club club) {
		BusinessRole byName = businessRoleRepository.findByName(EBusinessRole.ROLE_TRAINER);
		return userRepository.findByBusinessRolesIsContainingAndClub(byName, club);
	}

	/**
	 * Get by user Id from repository
	 * 
	 * @param id
	 * @return if found return User, throw exception otherwise
	 */
	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User with id " + id + " not found"));
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
		return user.orElseThrow(() -> new NoSuchElementException("No user with id " + id + " and role " + eBusinessRole.name()));
	}
}

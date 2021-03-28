package com.korolar.itennis.service.user;

import java.util.List;
import java.util.NoSuchElementException;

import com.korolar.itennis.service.utils.BusinessRuleUtils;
import com.korolar.itennis.service.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.korolar.itennis.dto.user.OwnerDto;
import com.korolar.itennis.entity.BusinessRole;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.repositories.BusinessRoleRepository;
import com.korolar.itennis.repositories.UserRepository;

@Service(value = "owner_service")
public class OwnerService implements IUserService<OwnerDto, User> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TrainerService trainerService;

	@Autowired
	private BusinessRoleRepository businessRoleRepository;

	@Override
	public OwnerDto getEntityAsDto(Long id) {
		return fromEntity(getOwnerFromRepository(id, EBusinessRole.ROLE_OWNER));
	}

	@Override
	public OwnerDto fromEntity(User user) {
		OwnerDto ownerDto = new OwnerDto();

		UserUtils.mapFromUserToDto(user, ownerDto);

		BusinessRole byName = businessRoleRepository.findByName(EBusinessRole.ROLE_TRAINER);
		List<User> byBusinessRolesIsContainingAndClub = userRepository.findByBusinessRolesIsContainingAndClub(byName, user.getClub());

		ownerDto.setTrainerList(trainerService.fromEntities(byBusinessRolesIsContainingAndClub));

		return ownerDto;
	}

	/**
	 * Get Owner by user Id from repository
	 * 
	 * @param id
	 * @return if found return User, throw exception otherwise
	 * @throws NoSuchElementException is the user with id doesnt have OWNER Business Role 2
	 */
	private User getOwnerFromRepository(Long id, EBusinessRole businessRole) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceAccessException("Owner with id " + id + " not found"));

		if (BusinessRuleUtils.checkIfUserHasBusinessRole(user, businessRole)) {
			return user;
		} else {
			throw new NoSuchElementException("User with id " + user.getId() + " is not " + businessRole);
		}
	}

}

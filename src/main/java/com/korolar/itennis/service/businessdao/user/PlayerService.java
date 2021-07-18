package com.korolar.itennis.service.businessdao.user;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.korolar.itennis.entity.Club;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.SubscriptionPackage;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.enums.ESecurityRole;
import com.korolar.itennis.service.dao.role.IRoleDaoService;
import com.korolar.itennis.service.dao.user.IUserDaoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService implements IPlayerService {

	private final IUserDaoService userService;
	private final IOwnerService ownerService;
	private final IRoleDaoService roleDaoService;

	@Override
	public List<User> getPlayersForSchedule(Schedule schedule) {
		return userService.getUsersByScheduleAndBusinessRole(schedule, EBusinessRole.ROLE_PLAYER);
	}

	@Override
	public User getPlayerById(Long id) {
		return userService.getUserWithBusinessRole(id, EBusinessRole.ROLE_PLAYER);
	}

	@Override
	public List<User> getPlayersForClub(Club club) {
		return userService.getUsersByClubAndRole(club, EBusinessRole.ROLE_PLAYER);
	}

	@Override
	public User createPlayerForOwner(User user, Long ownerId) {
		//data that came from UI are already filled in -> firstName and lastName

		//set the amount to 0
		user.setLeftAmount(BigDecimal.ZERO);

		//get Club from owner
		User ownerUser = ownerService.getOwnerById(ownerId);
		user.setClub(ownerUser.getClub());

		//add rolesSet.copyOf(Arrays.asList(trainer)
		user.setBusinessRoles(Set.copyOf(Arrays.asList(roleDaoService.getByName(EBusinessRole.ROLE_PLAYER))));
		user.setSecurityRoles(Set.copyOf(Arrays.asList(roleDaoService.getByName(ESecurityRole.ROLE_READ))));

		return userService.createUser(user);
	}

	@Override
	public void addPackageToPlayer(Long id, SubscriptionPackage aPackage) {
		User user = userService.getUserWithBusinessRole(id, EBusinessRole.ROLE_PLAYER);
		///add package
		List<SubscriptionPackage> packageList = user.getPackageList();
		packageList.add(aPackage);
		//increase left amount
		BigDecimal leftAmount = user.getLeftAmount();
		user.setLeftAmount(leftAmount.add(aPackage.getAmount()));
		userService.updateUser(user);
	}

	@Override
	public void addScheduleToPlayers(List<Long> playerIds, Schedule schedule) {
		int numberOfPlayers = playerIds.size();
		BigDecimal valuePerPlayer = schedule.getValue().divide(BigDecimal.valueOf(numberOfPlayers));
		for (Long id : playerIds) {
			User user = userService.getUserWithBusinessRole(id, EBusinessRole.ROLE_PLAYER);
			///todo: catch NoSuchElementFoundException and throw BusinnessExce unable to add schedule to given users
			BigDecimal leftAmount = user.getLeftAmount();
			//todo: do we need to check if the value is less then 0..do we throw exception?
			user.setLeftAmount(leftAmount.subtract(valuePerPlayer));
			user.getScheduleList().add(schedule);
			userService.updateUser(user);
		}
	}
}

package com.korolar.itennis.service.businessdao.user;

import java.util.List;

import com.korolar.itennis.entity.Club;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.SubscriptionPackage;
import com.korolar.itennis.entity.User;

public interface IPlayerService {

	User getPlayerById(Long id);

	List<User> getPlayersForClub(Club club);

	User createPlayerForOwner(User user, Long ownerId);

	void addPackageToPlayer(Long id, SubscriptionPackage aPackage);

	void addScheduleToPlayers(List<Long> playerId, Schedule schedule);

	List<User> getPlayersForSchedule(Schedule schedule);

}

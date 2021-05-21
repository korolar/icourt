package com.korolar.itennis.service.dao.user;

import java.util.List;

import com.korolar.itennis.entity.BusinessRole;
import com.korolar.itennis.entity.Club;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.SubscriptionPackage;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;

public interface IUserDaoService {

	User getUser(Long id);

	User createUser(User user);

	void updateUser(User user);

	User getUserWithBusinessRole(Long id, EBusinessRole eBusinessRole);

	List<User> getUsersByScheduleAndBusinessRole(Schedule schedule, EBusinessRole byName);

	List<User> getUsersByClubAndRole(Club club, EBusinessRole byName);

}

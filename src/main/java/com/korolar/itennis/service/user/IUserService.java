package com.korolar.itennis.service.user;

import java.util.List;

import com.korolar.itennis.entity.Club;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;

public interface IUserService {

	User getUser(Long id);

	User getUserWithBusinessRole(Long id, EBusinessRole eBusinessRole);

	List<User> getPlayersForSchedule(Schedule schedule);

	User getTrainerForSchedule(Schedule schedule);

	List<User> getTrainersForClub(Club club);

}

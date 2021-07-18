package com.korolar.itennis.service.businessdao.user;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.korolar.itennis.entity.Club;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.service.dao.user.IUserDaoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerService implements ITrainerService {

	private final IUserDaoService userService;

	@Override
	public User getTrainerForSchedule(Schedule schedule) {
		List<User> listUsers = userService.getUsersByScheduleAndBusinessRole(schedule, EBusinessRole.ROLE_TRAINER);

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
		return userService.getUsersByClubAndRole(club, EBusinessRole.ROLE_TRAINER);
	}

	@Override
	public User getTrainerById(Long id) {
		return userService.getUserWithBusinessRole(id, EBusinessRole.ROLE_TRAINER);
	}

	@Override
	public void addScheduleToTrainer(Long userId, Schedule schedule) {
		User user = userService.getUser(userId);
		//todo: add check if user is not in conflict with other Schedule regarding start/end
		user.getScheduleList().add(schedule);
		userService.updateUser(user);
	}
}

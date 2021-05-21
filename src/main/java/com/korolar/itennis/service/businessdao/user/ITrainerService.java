package com.korolar.itennis.service.businessdao.user;

import java.util.List;

import com.korolar.itennis.entity.Club;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;

public interface ITrainerService {

	void addScheduleToTrainer(Long trainerId, Schedule schedule);

	User getTrainerForSchedule(Schedule schedule);

	List<User> getTrainersForClub(Club club);

	User getTrainerById(Long id);
}

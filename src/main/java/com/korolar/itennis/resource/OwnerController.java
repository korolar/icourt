package com.korolar.itennis.resource;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.korolar.itennis.dto.schedule.ScheduleDto;
import com.korolar.itennis.dto.subpackage.SubPackageDto;
import com.korolar.itennis.dto.user.PlayerDto;
import com.korolar.itennis.dto.user.TrainerDto;
import com.korolar.itennis.dto.user.UserDto;
import com.korolar.itennis.service.dto.player.IPlayerDtoService;
import com.korolar.itennis.service.dto.schedule.ISchedulerDtoService;
import com.korolar.itennis.service.dto.subpackage.ISubPackageDtoService;
import com.korolar.itennis.service.dto.trainer.ITrainerDtoService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OwnerController {

	private final ITrainerDtoService iTrainerService;
	private final IPlayerDtoService iPlayerService;
	private final ISchedulerDtoService scheduleService;
	private final ISubPackageDtoService packageDtoService;

	@GetMapping(value = "/owner/{id}/trainers/schedule")
	public List<TrainerDto> getTrainersScheduleForOwner(@PathVariable("id") Long id) {
		return iTrainerService.getTrainersWithScheduleForOwner(id);
	}

	@GetMapping(value = "/owner/{id}/players")
	public List<UserDto> getPlayersForOwner(@PathVariable("id") Long id) {
		return iPlayerService.getAllPlayersForOwner(id);
	}

	@PostMapping(value = "/owner/{id}/players")
	public List<UserDto> createPlayers(@PathVariable("id") Long id, @RequestBody List<UserDto> userDtos) {
		return iPlayerService.createPlayersForOwner(id, userDtos);
	}

	@GetMapping(value = "/owner/{id}/players/status")
	public List<PlayerDto> getPlayersStatusForOwner(@PathVariable("id") Long id) {
		return iPlayerService.getPlayersStatusForOwner(id);
	}

	@GetMapping(value = "/owner/{id}/trainers")
	public List<UserDto> getTrainersForOwner(@PathVariable("id") Long id) {
		return iTrainerService.getAllTrainersForOwner(id);
	}

	//todo:check if needs to add {id}
	@PostMapping(value = "/owner/schedule")
	public List<ScheduleDto> createSchedule(@RequestBody List<ScheduleDto> scheduleDto) {
		return scheduleService.createSchedule(scheduleDto);
	}

	//todo:check if needs to add {id}
	@PostMapping(value = "/owner/package")
	public void addPackage(@RequestBody List<SubPackageDto> scheduleDto) {
		packageDtoService.addSubPackageToPlayers(scheduleDto);
	}

}

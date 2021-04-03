package com.korolar.itennis.repositories;

import com.korolar.itennis.entity.BusinessRole;
import com.korolar.itennis.entity.Club;
import com.korolar.itennis.entity.Schedule;
import com.korolar.itennis.entity.User;
import com.korolar.itennis.enums.EBusinessRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByIdAndBusinessRolesName(Long id, EBusinessRole businessRole);

	List<User> findByBusinessRolesIsContainingAndClub(BusinessRole businessRole, Club club);

	List<User> findByScheduleListIsContainingAndBusinessRolesIsContaining(Schedule schedule, BusinessRole businessRole);

}

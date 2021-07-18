package com.korolar.itennis.service.user;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.korolar.itennis.repositories.BusinessRoleRepository;
import com.korolar.itennis.repositories.UserRepository;
import com.korolar.itennis.service.dao.user.IUserDaoService;
import com.korolar.itennis.service.dao.user.UserDaoService;

//@RunWith(SpringRunner.class)
public class UserServiceTest {

//	@MockBean
//	private UserRepository userRepository;
//
//	@MockBean
//	private BusinessRoleRepository businessRoleRepository;
//
//	@Autowired
//	private IUserDaoService userService;
//
//	@TestConfiguration
//	static class UserServiceImplTestContextConfiguration {
//		@Bean
//		public IUserDaoService userService() {
//			return new UserDaoService();
//		}
//	}

//	@Test
//	public void shouldReturnPlayersAssignedToSchedule() {
//		BusinessRole businessRole = new BusinessRole();
//		User user = new User();
//		Schedule schedule = new Schedule();
//
//		Mockito.when(businessRoleRepository.findByName(EBusinessRole.ROLE_PLAYER)).thenReturn(businessRole);
//		Mockito.when(userRepository.findByScheduleListIsContainingAndBusinessRolesIsContaining(schedule, businessRole)).thenReturn(Arrays.asList(user));
//
//		List<User> playersForSchedule = userService.getPlayersForSchedule(schedule);
//
//		assertThat(playersForSchedule.size()).isEqualTo(1);
//	}

}

package com.korolar.itennis.service.dao.role;

import java.util.List;

import com.korolar.itennis.entity.BusinessRole;
import com.korolar.itennis.entity.Location;
import com.korolar.itennis.entity.SecurityRole;
import com.korolar.itennis.enums.EBusinessRole;
import com.korolar.itennis.enums.ESecurityRole;

public interface IRoleDaoService {

	BusinessRole getByName(EBusinessRole eBusinessRole);

	SecurityRole getByName(ESecurityRole eSecurityRole);
}

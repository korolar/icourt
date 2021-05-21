package com.korolar.itennis.service.dao.subpackage;

import com.korolar.itennis.entity.SubscriptionPackage;
import com.korolar.itennis.repositories.SubscriptionPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubPackageDaoService implements ISubPackageDaoService {

	@Autowired
	private SubscriptionPackageRepository packageRepository;

	@Override
	public SubscriptionPackage saveSubPackage(SubscriptionPackage subscriptionPackage) {
		return packageRepository.save(subscriptionPackage);
	}
}

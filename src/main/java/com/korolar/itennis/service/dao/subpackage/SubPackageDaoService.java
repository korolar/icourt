package com.korolar.itennis.service.dao.subpackage;

import org.springframework.stereotype.Service;

import com.korolar.itennis.entity.SubscriptionPackage;
import com.korolar.itennis.repositories.SubscriptionPackageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubPackageDaoService implements ISubPackageDaoService {

	private final SubscriptionPackageRepository packageRepository;

	@Override
	public SubscriptionPackage saveSubPackage(SubscriptionPackage subscriptionPackage) {
		return packageRepository.save(subscriptionPackage);
	}
}

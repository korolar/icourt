package com.korolar.itennis.service.dto.subpackage;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korolar.itennis.dto.subpackage.SubPackageDto;
import com.korolar.itennis.entity.SubscriptionPackage;
import com.korolar.itennis.service.businessdao.user.IPlayerService;
import com.korolar.itennis.service.dao.subpackage.ISubPackageDaoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubPackageDtoService implements ISubPackageDtoService {

	private final IPlayerService playerService;
	private final ISubPackageDaoService subPackageDaoService;

	@Override
	public void addSubPackageToPlayers(List<SubPackageDto> packageDto) {
		packageDto.stream().forEach(p -> addSubPackage(p));
	}

	@Override
	public List<SubPackageDto> getAllPackagesForUser(Long id) {
		List<SubscriptionPackage> packageList = playerService.getPlayerById(id).getPackageList();
		return packageList.stream().map(x -> fromEntity(x)).collect(Collectors.toList());
	}

	private SubPackageDto fromEntity(SubscriptionPackage subscriptionPackage) {
		SubPackageDto aPackage = new SubPackageDto();
		aPackage.setId(subscriptionPackage.getId());
		aPackage.setValidUntil(subscriptionPackage.getValidUntil());
		aPackage.setPurchaseDate(subscriptionPackage.getPurchaseDate());
		aPackage.setAmount(subscriptionPackage.getAmount());

		return aPackage;
	}

	private final void addSubPackage(SubPackageDto packageDto) {
		SubscriptionPackage aPackage = new SubscriptionPackage();
		aPackage.setValidUntil(packageDto.getValidUntil());
		aPackage.setPurchaseDate(packageDto.getPurchaseDate());
		aPackage.setAmount(packageDto.getAmount());

		SubscriptionPackage packageSaved = subPackageDaoService.saveSubPackage(aPackage);

		playerService.addPackageToPlayer(packageDto.getPlayers().getId(), packageSaved);
	}
}
